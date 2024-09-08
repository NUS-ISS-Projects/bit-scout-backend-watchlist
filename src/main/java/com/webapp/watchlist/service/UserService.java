package com.webapp.watchlist.service;

public interface UserService {
    String validateTokenAndGetUserId(String token);
}
