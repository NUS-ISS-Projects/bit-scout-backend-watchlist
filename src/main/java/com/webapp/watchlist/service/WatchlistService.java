package com.webapp.watchlist.service;

import com.webapp.watchlist.dto.WatchlistDto;
import com.webapp.watchlist.entity.Watchlist;
import com.webapp.watchlist.repository.WatchlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class WatchlistService {

    @Autowired
    private WatchlistRepository watchlistRepository;

    public WatchlistDto getWatchlistByUserId(Long userId) {
        Optional<Watchlist> watchlistOpt = watchlistRepository.findByUserId(userId);
        if (watchlistOpt.isPresent()) {
            Watchlist watchlist = watchlistOpt.get();
            WatchlistDto watchlistDto = new WatchlistDto();
            watchlistDto.setUserId(watchlist.getUserId());
            watchlistDto.setCryptoIds(watchlist.getCryptoIds());
            return watchlistDto;
        }
        return null; // or throw a custom exception
    }

    public WatchlistDto createOrUpdateWatchlist(WatchlistDto watchlistDto) {
        Watchlist watchlist = watchlistRepository.findByUserId(watchlistDto.getUserId())
                .orElse(new Watchlist());
        watchlist.setUserId(watchlistDto.getUserId());
        watchlist.setCryptoIds(watchlistDto.getCryptoIds());
        watchlist = watchlistRepository.save(watchlist);
        return watchlistDto;
    }

    public WatchlistDto addCryptoToWatchlist(Long userId, String cryptoId) {
        Watchlist watchlist = watchlistRepository.findByUserId(userId)
                .orElse(new Watchlist());
        watchlist.setUserId(userId);
        watchlist.getCryptoIds().add(cryptoId);
        watchlistRepository.save(watchlist);

        WatchlistDto watchlistDto = new WatchlistDto();
        watchlistDto.setUserId(userId);
        watchlistDto.setCryptoIds(watchlist.getCryptoIds());
        return watchlistDto;
    }

    public WatchlistDto removeCryptoFromWatchlist(Long userId, String cryptoId) {
        Watchlist watchlist = watchlistRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Watchlist not found for user id: " + userId));

        watchlist.getCryptoIds().remove(cryptoId);
        watchlistRepository.save(watchlist);

        WatchlistDto watchlistDto = new WatchlistDto();
        watchlistDto.setUserId(userId);
        watchlistDto.setCryptoIds(watchlist.getCryptoIds());
        return watchlistDto;
    }
}
