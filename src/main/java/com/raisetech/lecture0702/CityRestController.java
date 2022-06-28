package com.raisetech.lecture0702;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

  @PatchMapping("/city/{cityName}")
  public ResponseEntity<Map<String, List<String>>> editCity(@PathVariable("cityName") String cityName,
                                                            @RequestBody @Validated CityUpdateForm form,
                                                            BindingResult result) {
    // バリデーション違反の処理
    if (result.hasErrors()) {
      return new ResponseEntity<>(Map.of("Message", List.of("Invalid value.")), HttpStatus.BAD_REQUEST);
    }
    List<String> editData = new ArrayList<>();

    // ハリボテ処理
    // 変更後の都市情報をすべてリストで表示するような想定
    editData.add(form.getCityName());
    editData.add(form.getCityDescription());
    editData.add(form.getCityPopulation());
    editData.add(form.getCityIndustrie());

    return ResponseEntity.ok(Map.of("Message", editData));
  }
}
