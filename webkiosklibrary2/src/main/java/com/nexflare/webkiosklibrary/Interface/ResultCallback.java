package com.nexflare.webkiosklibrary.Interface;

/**
 * Created by Aakarshak on 25-09-2017.
 */

public interface ResultCallback<T> {

    void onResult(T resukt);

    void onError(Exception e);

}
