package com.webapp.watchlist.dto;

import java.util.List;

public class WatchlistDto {
    private String userId;
    private List<String> cryptoIds;

    // Getters and Setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<String> getCryptoIds() {
        return cryptoIds;
    }

    public void setCryptoIds(List<String> cryptoIds) {
        this.cryptoIds = cryptoIds;
    }

    // toString
    @Override
    public String toString() {
        return "WatchlistDto{" +
                "userId=" + userId +
                ", cryptoIds=" + cryptoIds +
                '}';
    }
}
