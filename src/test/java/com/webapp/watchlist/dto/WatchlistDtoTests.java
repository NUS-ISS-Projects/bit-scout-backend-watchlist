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
    private List<String> cryptoIds;

    @BeforeEach
    void setUp() {
        watchlistDto = new WatchlistDto();
        cryptoIds = new ArrayList<>();
        cryptoIds.add("BTC");
        cryptoIds.add("ETH");
    }

    @Test
    void testGetUserId() {
        watchlistDto.setUserId("123");
        assertEquals("123", watchlistDto.getUserId());
    }

    @Test
    void testSetUserId() {
        watchlistDto.setUserId("1234");
        assertEquals("1234", watchlistDto.getUserId());
    }

    @Test
    void testGetCryptoIds() {
        watchlistDto.setCryptoIds(new ArrayList<>(cryptoIds));
        assertEquals(cryptoIds, watchlistDto.getCryptoIds());
    }

    @Test
    void testSetCryptoIds() {
        List<String> newCryptoIds = new ArrayList<>();
        newCryptoIds.add("LTC");
        watchlistDto.setCryptoIds(newCryptoIds);
        assertEquals(newCryptoIds, watchlistDto.getCryptoIds());
    }

    @Test
    void testToString() {
        watchlistDto.setUserId("123");
        watchlistDto.setCryptoIds(new ArrayList<>(cryptoIds));
        String expected = "WatchlistDto{userId=123, cryptoIds=[BTC, ETH]}";
        assertEquals(expected, watchlistDto.toString());
    }

    @Test
    void testEmptyCryptoIds() {
        // Convert HashSet to List and set it to watchlistDto
        watchlistDto.setCryptoIds(new ArrayList<>(new HashSet<>()));
        assertTrue(watchlistDto.getCryptoIds().isEmpty());
    }

}
