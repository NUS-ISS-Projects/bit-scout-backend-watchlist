package com.webapp.watchlist.service;

import com.webapp.watchlist.KafkaProducer;
import com.webapp.watchlist.dto.WatchlistDto;
import com.webapp.watchlist.entity.Watchlist;
import com.webapp.watchlist.repository.WatchlistRepository;
import com.webapp.watchlist.service.WatchlistService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.Set;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WatchlistServiceTests {

    @Mock
    private WatchlistRepository watchlistRepository;

    @Mock
    private KafkaProducer kafkaProducer;

    @InjectMocks
    private WatchlistService watchlistService;

    private Watchlist watchlist;
    private WatchlistDto watchlistDto;

    @SuppressWarnings("unchecked")
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        Set<String> cryptoIds = new HashSet<>();
        cryptoIds.add("BTC");
        cryptoIds.add("ETH");

        watchlist = new Watchlist();
        watchlist.setUserId(1L);
        watchlist.setCryptoIds(cryptoIds);

        watchlistDto = new WatchlistDto();
        watchlistDto.setUserId(1L);
        watchlistDto.setCryptoIds((List<String>) cryptoIds);
    }

    @Test
    void testGetWatchlistByUserIdSuccess() {
        when(watchlistRepository.findByUserId(1L)).thenReturn(Optional.of(watchlist));

        WatchlistDto result = watchlistService.getWatchlistByUserId(1L);
        assertNotNull(result);
        assertEquals(1L, result.getUserId());
        assertEquals(2, result.getCryptoIds().size());
    }

    @Test
    void testGetWatchlistByUserIdNotFound() {
        when(watchlistRepository.findByUserId(1L)).thenReturn(Optional.empty());

        WatchlistDto result = watchlistService.getWatchlistByUserId(1L);
        assertNull(result);
    }

    @Test
    void testCreateOrUpdateWatchlist() {
        when(watchlistRepository.findByUserId(1L)).thenReturn(Optional.of(watchlist));
        when(watchlistRepository.save(watchlist)).thenReturn(watchlist);

        WatchlistDto result = watchlistService.createOrUpdateWatchlist(watchlistDto);
        assertNotNull(result);
        assertEquals(1L, result.getUserId());
        verify(kafkaProducer, times(1)).sendMessage(anyString(), anyString());
    }

    @Test
    void testAddCryptoToWatchlist() {
        when(watchlistRepository.findByUserId(1L)).thenReturn(Optional.of(watchlist));
        when(watchlistRepository.save(watchlist)).thenReturn(watchlist);

        WatchlistDto result = watchlistService.addCryptoToWatchlist(1L, "XRP");
        assertEquals(3, result.getCryptoIds().size());
        verify(kafkaProducer, times(1)).sendMessage(anyString(), anyString());
    }

    @Test
    void testRemoveCryptoFromWatchlist() {
        when(watchlistRepository.findByUserId(1L)).thenReturn(Optional.of(watchlist));
        when(watchlistRepository.save(watchlist)).thenReturn(watchlist);

        WatchlistDto result = watchlistService.removeCryptoFromWatchlist(1L, "BTC");
        assertEquals(1, result.getCryptoIds().size());
        verify(kafkaProducer, times(1)).sendMessage(anyString(), anyString());
    }
}
