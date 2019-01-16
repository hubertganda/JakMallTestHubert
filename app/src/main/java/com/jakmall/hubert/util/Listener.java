package com.jakmall.hubert.util;

public class Listener {

    public interface HttpListener {
        void onRequestSuccess(String result);
        void onRequestFailed(String errCode, String errMsg);
    }

    public interface RequestListener {
        void onRequestSuccess();
        void onRequestFailed(String errCode, String errMsg);
    }
}
