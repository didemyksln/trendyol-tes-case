package com.trendyol.model;

public class BoutiquePageResponse {
    private String url;
    private int responseCode;

    public BoutiquePageResponse(String url, int responseCode) {
        this.url = url;
        this.responseCode = responseCode;
    }

    public String getUrl() {
        return url;
    }

    public int getResponseCode() {
        return responseCode;
    }

}
