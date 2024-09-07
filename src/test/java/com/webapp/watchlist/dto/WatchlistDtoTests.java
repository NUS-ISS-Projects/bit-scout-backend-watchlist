package com.webapp.watchlist.dto;

import com.webapp.watchlist.dto.WatchlistDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class WatchlistDtoTests {

    private WatchlistDto watchlistDto;
    private Set<String> cryptoIds;

    @BeforeEach
    void setUp() {
        watchlistDto = new WatchlistDto();
        cryptoIds = new HashSet<>();
        cryptoIds.add("BTC");
        cryptoIds.add("ETH");
    }

    @Test
    void testGetUserId() {
        watchlistDto.setUserId(1L);
        assertEquals(1L, watchlistDto.getUserId());
    }

    @Test
    void testSetUserId() {
        watchlistDto.setUserId(2L);
        assertEquals(2L, watchlistDto.getUserId());
    }

    @Test
    void testGetCryptoIds() {
        watchlistDto.setCryptoIds((List<String>) cryptoIds);
        assertEquals(cryptoIds, watchlistDto.getCryptoIds());
    }

    @Test
    void testSetCryptoIds() {
        Set<String> newCryptoIds = new HashSet<>();
        newCryptoIds.add("LTC");
        watchlistDto.setCryptoIds((List<String>) newCryptoIds);
        assertEquals(newCryptoIds, watchlistDto.getCryptoIds());
    }

    @Test
    void testToString() {
        watchlistDto.setUserId(1L);
        watchlistDto.setCryptoIds((List<String>) cryptoIds);
        String expected = "WatchlistDto{userId=1, cryptoIds=[BTC, ETH]}";
        assertEquals(expected, watchlistDto.toString());
    }

    @Test
    void testEmptyCryptoIds() {
        // Convert HashSet to List and set it to watchlistDto
        watchlistDto.setCryptoIds(new ArrayList<>(new HashSet<>()));
        assertTrue(watchlistDto.getCryptoIds().isEmpty());
    }

}
