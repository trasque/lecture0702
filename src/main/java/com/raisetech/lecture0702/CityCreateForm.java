package com.raisetech.lecture0702;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class CityCreateForm {

  // [cityName] はNULL不許可
  // A-Za-z ASCIIアルファベットのみ
  // 行頭から行末まで繰り返し文字数制限なし
  @NotNull
  @Pattern (regexp = "^[A-Za-z]+$")
  private String cityName;

  private String cityDescription;

  @Pattern (regexp = "^[0-9]{1,10}$")
  private String cityPopulation;

  private String cityIndustrie;

  public String getCityName() {
    return this.cityName;
  }

  public String getCityDescription() {
    return this.cityDescription;
  }

  public String getCityPopulation() {
    return this.cityPopulation;
  }

  public String getCityIndustrie() {
    return this.cityIndustrie;
  }

  public void setCityName(String cityName) {
    this.cityName = cityName;
  }

  public void setCityDescription(String cityDescription) {
    this.cityDescription = cityDescription;
  }

  public void setCityPopulation(String cityPopulation) {
    this.cityPopulation = cityPopulation;
  }

  public void setCityIndustrie(String cityIndustrie) {
    this.cityIndustrie = cityIndustrie;
  }

}
