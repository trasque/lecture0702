package com.raisetech.lecture0702;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class CityRestController {
  private final CityService cityService;

  @Autowired
  public CityRestController(CityService cityService) {
    this.cityService = cityService;
  }

  @GetMapping("/city")
  public List<String> getCity(@RequestParam(defaultValue = "none") String name) {
    return cityService.cityInfo(name);
  }

  @PostMapping("/city")
  public ResponseEntity<String> newCity(@RequestBody @Validated CityCreateForm form, BindingResult result) {
    // バリデーション違反時は 400 Bad Request を返す
    if (result.hasErrors()) {
      return new ResponseEntity<>("Invalid value.", HttpStatus.BAD_REQUEST);
    }

    // ハリボテ登録処理
    System.out.println(cityService.newCityName(form.getCityName()));
    System.out.println(cityService.newCityDescription(form.getCityDescription()));
    System.out.println(cityService.newCityPopulation(form.getCityPopulation()));
    System.out.println(cityService.newCityIndustrie(form.getCityIndustrie()));

    // UriComponentsBuilder クラスは UriComponents インスタンスを作る
    // UriComponents は、URIよりも更に便利な形でURLを作る機能が備わっている
    // .fromUriString により、指定した文字列を基本にしてURLを生成できる
    // .path により、指定した文字列をパス部分に追加できる
    // 上記などで含めた情報を使って .build により UriComponents ができる
    // UriComponents のメソッド .toUri によって結果的にURLが生成される
    // ここでは POST で送信された "cityName" を使ってURLのID部分に差し込んでいる
    URI url = UriComponentsBuilder.fromUriString("http://localhost:8080")
              .path("/city/" + form.getCityName())
              .build()
              .toUri();
    
    return ResponseEntity.created(url).body("city registered.");
  }
}
