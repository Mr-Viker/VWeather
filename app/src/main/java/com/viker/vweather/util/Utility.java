package com.viker.vweather.util;

import android.text.TextUtils;

import com.viker.vweather.model.City;
import com.viker.vweather.model.County;
import com.viker.vweather.model.Province;
import com.viker.vweather.model.VWeatherDB;

/**
 * Created by Viker on 2016/5/6.
 * 提供一个可以解析和处理服务器返回数据的工具类。创建一个Utility类。
 */
public class Utility {

    //解析和处理服务器返回的省级数据
    public synchronized static boolean handleProvincesResponse(VWeatherDB vWeatherDB,
                                                               String response) {
        if (!TextUtils.isEmpty(response)) {
            String[] allProvinces = response.split(",");
            if (allProvinces.length > 0 && allProvinces != null) {
                for (String p : allProvinces) {
                    String[] array = p.split("\\|");
                    Province province = new Province();
                    province.setProvinceCode(array[0]);
                    province.setProvinceName(array[1]);
                    //将解析出来的数据存储到Province表
                    vWeatherDB.saveProvince(province);
                }
                return true;
            }
        }
        return false;
    }

    //解析和处理服务器返回的市级数据
    public static boolean handleCitiesResponse(VWeatherDB vWeatherDB, String response,
                                               int provinceId) {
        if (!TextUtils.isEmpty(response)) {
            String[] allCities = response.split(",");
            if (allCities != null && allCities.length > 0) {
                for (String c : allCities) {
                    String[] array = c.split("\\|");
                    City city = new City();
                    city.setCityCode(array[0]);
                    city.setCityName(array[1]);
                    city.setProvinceId(provinceId);
                    //将解析出来的数据存储到City表
                    vWeatherDB.saveCity(city);
                }
                return true;
            }
        }
        return false;
    }

    //解析和处理服务器返回的县级数据
    public static boolean handleCoutiesResponse(VWeatherDB vWeatherDB, String response,
                                                int cityId) {
        if (!TextUtils.isEmpty(response)) {
            String[] allCounties = response.split(",");
            if (allCounties != null && allCounties.length > 0) {
                for (String c : allCounties) {
                    String[] array = c.split("\\|");
                    County county = new County();
                    county.setCountyCode(array[0]);
                    county.setCountyName(array[1]);
                    county.setCityId(cityId);
                    //将解析出来的数据存储到County表
                    vWeatherDB.saveCounty(county);
                }
                return true;
            }
        }
        return false;
    }

}
