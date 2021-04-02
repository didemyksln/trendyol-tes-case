package com.trendyol.model;

public class ServerResponse {

    private final int statusCode;
    private final long responseTime;
    private final String url;

    public String getUrl() {
        return url;
    }

    public ServerResponse(int statusCode, long responseTime, String url) {
        this.statusCode = statusCode;
        this.responseTime = responseTime;
        this.url = url;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public long getResponseTime() {
        return responseTime;
    }
}
