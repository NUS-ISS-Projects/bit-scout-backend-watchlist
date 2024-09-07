package com.webapp.watchlist.dto;

import java.util.List;

public class WatchlistDto {
    private Long userId;
    private List<String> cryptoIds;

    // Getters and Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
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
