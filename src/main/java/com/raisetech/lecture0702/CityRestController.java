package com.raisetech.lecture0702;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CityRestController {
  private final CityService cityService;
  private final List<String> errorData = new ArrayList<>();

  @Autowired
  public CityRestController(CityService cityService) {
    this.cityService = cityService;
    this.errorData.add("Invalid value.");
  }

  @GetMapping("/city/data")
  public List<String> getCity(@RequestParam(defaultValue = "none") String name) {
    return cityService.cityInfo(name);
  }
}
