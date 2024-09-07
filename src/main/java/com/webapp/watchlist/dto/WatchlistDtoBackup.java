package com.webapp.watchlist.dto;

import java.util.Set;

public class WatchlistDtoBackup {
    private Long userId;
    private Set<String> cryptoIds;

    // Getters and Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Set<String> getCryptoIds() {
        return cryptoIds;
    }

    public void setCryptoIds(Set<String> cryptoIds) {
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
