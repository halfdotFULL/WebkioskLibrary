package com.nexflare.webkiosklibrary.Interface;

/**
 * Created by shubhamagarwal on 25/09/17.
 */

public interface WebkioskContract<T> {

    public void addResultCallback(ResultCallback<T> resultCallback);


    public void removeCallback();
}
