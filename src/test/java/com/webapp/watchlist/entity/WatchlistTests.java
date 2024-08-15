package com.webapp.watchlist.entity;

import com.webapp.watchlist.entity.Watchlist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class WatchlistTests {

    private Watchlist watchlist;
    private Set<String> cryptoIds;

    @BeforeEach
    void setUp() {
        watchlist = new Watchlist();
        cryptoIds = new HashSet<>();
        cryptoIds.add("BTC");
        cryptoIds.add("ETH");
    }

    @Test
    void testGetId() {
        watchlist.setId(1L);
        assertEquals(1L, watchlist.getId());
    }

    @Test
    void testSetId() {
        watchlist.setId(2L);
        assertEquals(2L, watchlist.getId());
    }

    @Test
    void testGetUserId() {
        watchlist.setUserId(1L);
        assertEquals(1L, watchlist.getUserId());
    }

    @Test
    void testSetUserId() {
        watchlist.setUserId(2L);
        assertEquals(2L, watchlist.getUserId());
    }

    @Test
    void testGetCryptoIds() {
        watchlist.setCryptoIds(cryptoIds);
        assertEquals(cryptoIds, watchlist.getCryptoIds());
    }

    @Test
    void testSetCryptoIds() {
        Set<String> newCryptoIds = new HashSet<>();
        newCryptoIds.add("LTC");
        watchlist.setCryptoIds(newCryptoIds);
        assertEquals(newCryptoIds, watchlist.getCryptoIds());
    }

    @Test
    void testToString() {
        watchlist.setId(1L);
        watchlist.setUserId(1L);
        watchlist.setCryptoIds(cryptoIds);
        String expected = "Watchlist{id=1, userId=1, cryptoIds=[BTC, ETH]}";
        assertEquals(expected, watchlist.toString());
    }

    @Test
    void testEmptyCryptoIds() {
        watchlist.setCryptoIds(new HashSet<>());
        assertTrue(watchlist.getCryptoIds().isEmpty());
    }
}
