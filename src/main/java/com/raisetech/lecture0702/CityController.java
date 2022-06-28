package com.raisetech.lecture0702;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CityController {
  private final CityService cityService;
  private final String cityName;
  private final String cityDescription;
  private final String cityPopulation;
  private final String cityIndustrie;

  @Autowired
  public CityController(CityService cityService) {
    this.cityService = cityService;
    this.cityName = "cityName";
    this.cityDescription = "cityDescription";
    this.cityPopulation = "cityPopulation";
    this.cityIndustrie = "cityIndustrie";
  }

  // 今回の GET ではバリデーションしない
  // 登録された市だけ動作し、それ以外の文字列はすべて「登録なし」のデータを返す
  @GetMapping("/citysite")
  public String getCity(@RequestParam(defaultValue = "none") String name, Model model) {
    List<String> information = cityService.cityInfo(name);

    model.addAttribute(cityName, information.get(0));
    model.addAttribute(cityDescription, information.get(1));
    model.addAttribute(cityPopulation, information.get(2));
    model.addAttribute(cityIndustrie, information.get(3));
    return "/city";
  }
}
