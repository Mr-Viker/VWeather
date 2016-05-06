package com.viker.vweather.util;

/**
 * Created by Viker on 2016/5/6.
 */
public interface HttpCallbackListener {
    void onFinish(String response);

    void onError(Exception e);
}
