package com.webapp.watchlist.controller;

import com.webapp.watchlist.dto.WatchlistDto;
import com.webapp.watchlist.service.FirebaseService;
import com.webapp.watchlist.service.UserService;
import com.webapp.watchlist.service.WatchlistService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.concurrent.ExecutionException;

class WatchlistControllerTests {

    @Mock
    private UserService userService;

    @Mock
    private FirebaseService firebaseService;

    @InjectMocks
    private WatchlistController watchlistController;

    private WatchlistDto mockWatchlist;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockWatchlist = new WatchlistDto();
        mockWatchlist.setUserId("123");
    }

    @Test
    void testGetWatchlist_Found() throws ExecutionException, InterruptedException {
        when(userService.validateTokenAndGetUserId(anyString())).thenReturn("123");
        when(firebaseService.getWatchlistByUserId("123")).thenReturn(mockWatchlist);
        ResponseEntity<WatchlistDto> response = watchlistController.getWatchlist("12345");
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockWatchlist, response.getBody());
    }

    @Test
    void testGetWatchlist_NotFound() throws ExecutionException, InterruptedException {
        when(userService.validateTokenAndGetUserId(anyString())).thenReturn("123");
        when(firebaseService.getWatchlistByUserId("123")).thenReturn(null);
        ResponseEntity<WatchlistDto> response = watchlistController.getWatchlist("2L");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    // @Test
    // void testHealthCheck() {
    // ResponseEntity<String> response = watchlistController.healthCheck();
    // assertEquals(200, response.getStatusCodeValue());
    // assertEquals("Watchlist service is up and running", response.getBody());
    // }

    @Test
    void testCreateOrUpdateWatchlist() throws ExecutionException, InterruptedException {
        when(userService.validateTokenAndGetUserId(anyString())).thenReturn("123");
        when(firebaseService.createWatchlist(mockWatchlist)).thenReturn(mockWatchlist);
        ResponseEntity<WatchlistDto> response = watchlistController.createOrUpdateWatchlist("12345", mockWatchlist);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockWatchlist, response.getBody());
    }

    @Test
    void testAddCryptoToWatchlist() throws ExecutionException, InterruptedException {
        when(userService.validateTokenAndGetUserId(anyString())).thenReturn("123");
        when(firebaseService.addCryptoToWatchlist("123","BTC")).thenReturn(mockWatchlist);
        ResponseEntity<WatchlistDto> response = watchlistController.addCryptoToWatchlist("12345", "BTC");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockWatchlist, response.getBody());
    }

    @Test
    void testRemoveCryptoFromWatchlist() throws ExecutionException, InterruptedException {
        when(userService.validateTokenAndGetUserId(anyString())).thenReturn("123");
        when(firebaseService.removeCryptoFromWatchlist("123","BTC")).thenReturn(mockWatchlist);
        ResponseEntity<WatchlistDto> response = watchlistController.removeCryptoFromWatchlist("12345", "BTC");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockWatchlist, response.getBody());
    }
}
