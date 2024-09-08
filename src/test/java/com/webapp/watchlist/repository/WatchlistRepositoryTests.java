package com.webapp.watchlist.repository;

import com.webapp.watchlist.entity.Watchlist;
import com.webapp.watchlist.repository.WatchlistRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WatchlistRepositoryTests {

    @Mock
    private WatchlistRepository watchlistRepository;

    @InjectMocks
    private Watchlist watchlist;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        watchlist = new Watchlist();
        watchlist.setUserId("1L");
    }

    @Test
    void testFindByUserIdSuccess() {
        when(watchlistRepository.findByUserId("1L")).thenReturn(Optional.of(watchlist));

        Optional<Watchlist> result = watchlistRepository.findByUserId("1L");
        assertTrue(result.isPresent());
        assertEquals("1L", result.get().getUserId());
    }

    @Test
    void testFindByUserIdNotFound() {
        when(watchlistRepository.findByUserId("2L")).thenReturn(Optional.empty());

        Optional<Watchlist> result = watchlistRepository.findByUserId("2L");
        assertFalse(result.isPresent());
    }

    @Test
    void testSaveWatchlist() {
        when(watchlistRepository.save(watchlist)).thenReturn(watchlist);

        Watchlist savedWatchlist = watchlistRepository.save(watchlist);
        assertEquals(watchlist, savedWatchlist);
        verify(watchlistRepository, times(1)).save(watchlist);
    }
}
