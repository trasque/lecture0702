package com.raisetech.lecture0702;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CityService {
  
  //==========
  // GET
  //==========
  public List<String> cityInfo(String name) {
    // データベースから引っ張ってきたかのような仮実装
    // cityData 要素の使い道
    // 0] cityName
    // 1] cityDescription
    // 2] cityPopulation
    // 3] cityIndustrie
    List<String> cityData = new ArrayList<>();

    switch (name) {
      case "urayasu" -> {
        cityData.add("urayasu");
        cityData.add("千葉県最後の防衛ライン。しかし既にその実態は、魔法のネズミによって＜＜東京＞＞に捕らえられているも同然だった。");
        cityData.add("164000");
        cityData.add("鉄鋼流通・東京ディズニーリゾート商業施設郡・湾岸高級住宅地");
      }
      case "atsugi" -> {
        cityData.add("atsugi");
        cityData.add("衛星都市の位置にありながら神奈川県内で唯一昼間人口の方が多い産業都市としての側面を持つ。交通要所とされるが山間・農業地帯も多い。");
        cityData.add("223000");
        cityData.add("研究開発・流通及びサービス業・とん漬");
      }
      default -> {
        cityData.add("指定なし");
        cityData.add("指定なし");
        cityData.add("指定なし");
        cityData.add("指定なし");
      }
    }

    return cityData;
  }

  //==========
  // POST 用
  //==========
  public String newCityName(String name) {
    // データベースへ要素を登録する仮実装
    return name;
  }

  public String newCityDescription(String description) {
    // データベースへ要素を登録する仮実装
    return description;
  }

  public String newCityPopulation(String population) {
    // データベースへ要素を登録する仮実装
    return population;
  }

  public String newCityIndustrie(String industrie) {
    // データベースへ要素を登録する仮実装
    return industrie;
  }
}
