package com.webapp.watchlist.controller;

import com.webapp.watchlist.dto.WatchlistDto;
import com.webapp.watchlist.service.WatchlistService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class WatchlistControllerTests {

    @Mock
    private WatchlistService watchlistService;

    @InjectMocks
    private WatchlistController watchlistController;

    private WatchlistDto mockWatchlist;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockWatchlist = new WatchlistDto();
        mockWatchlist.setUserId(1L);
    }

    @Test
    void testGetWatchlist_Found() {
        when(watchlistService.getWatchlistByUserId(1L)).thenReturn(mockWatchlist);
        ResponseEntity<WatchlistDto> response = watchlistController.getWatchlist(1L);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockWatchlist, response.getBody());
    }

    @Test
    void testGetWatchlist_NotFound() {
        when(watchlistService.getWatchlistByUserId(2L)).thenReturn(null);
        ResponseEntity<WatchlistDto> response = watchlistController.getWatchlist(2L);
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void testHealthCheck() {
        ResponseEntity<String> response = watchlistController.healthCheck();
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Watchlist service is up and running", response.getBody());
    }

    @Test
    void testCreateOrUpdateWatchlist() {
        when(watchlistService.createOrUpdateWatchlist(mockWatchlist)).thenReturn(mockWatchlist);
        ResponseEntity<WatchlistDto> response = watchlistController.createOrUpdateWatchlist(mockWatchlist);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockWatchlist, response.getBody());
    }

    @Test
    void testAddCryptoToWatchlist() {
        when(watchlistService.addCryptoToWatchlist(1L, "BTC")).thenReturn(mockWatchlist);
        ResponseEntity<WatchlistDto> response = watchlistController.addCryptoToWatchlist(1L, "BTC");
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockWatchlist, response.getBody());
    }

    @Test
    void testRemoveCryptoFromWatchlist() {
        when(watchlistService.removeCryptoFromWatchlist(1L, "BTC")).thenReturn(mockWatchlist);
        ResponseEntity<WatchlistDto> response = watchlistController.removeCryptoFromWatchlist(1L, "BTC");
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockWatchlist, response.getBody());
    }
}
