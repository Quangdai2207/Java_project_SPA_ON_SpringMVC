package com.example.demo.helpers.address_helper;

import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AddressHelper {
    private final static RestTemplate restTemplate = new RestTemplate();

    public static String getProvinceName(int provinceCode) {
        String url = "https://provinces.open-api.vn/api/p/" + provinceCode + "?depth=1";
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);
        return (String) response.get("name");
    }

    public static String getDistrictName(int districtCode) {
        String url = "https://provinces.open-api.vn/api/d/" + districtCode + "?depth=1";
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);
        return (String) response.get("name");
    }

    public static String getWardName(int districtCode,  int wardCode) {
        String url = "https://provinces.open-api.vn/api/d/" + districtCode + "?depth=2";
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);

        List<Map<String, Object>> wards = (List<Map<String, Object>>) response.get("wards");
        String wardName = "";
        for (Map<String, Object> ward : wards) {
            if (ward.get("code").equals(wardCode)) {
                wardName = ward.get("name").toString();
            }
        }
        return wardName;
    }
}
