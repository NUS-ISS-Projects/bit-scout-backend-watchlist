package com.webapp.watchlist.controller;

import com.webapp.watchlist.dto.WatchlistDto;
import com.webapp.watchlist.service.WatchlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/watchlist")
public class WatchlistController {

    @Autowired
    private WatchlistService watchlistService;

    @GetMapping("/{userId}")
    public ResponseEntity<WatchlistDto> getWatchlist(@PathVariable Long userId) {
        WatchlistDto watchlist = watchlistService.getWatchlistByUserId(userId);
        if (watchlist != null) {
            return ResponseEntity.ok(watchlist);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/")
    public ResponseEntity<WatchlistDto> createOrUpdateWatchlist(@RequestBody WatchlistDto watchlistDto) {
        WatchlistDto updatedWatchlist = watchlistService.createOrUpdateWatchlist(watchlistDto);
        return ResponseEntity.ok(updatedWatchlist);
    }

    @PostMapping("/{userId}/add")
    public ResponseEntity<WatchlistDto> addCryptoToWatchlist(@PathVariable Long userId, @RequestParam String cryptoId) {
        WatchlistDto updatedWatchlist = watchlistService.addCryptoToWatchlist(userId, cryptoId);
        return ResponseEntity.ok(updatedWatchlist);
    }

    @DeleteMapping("/{userId}/remove")
    public ResponseEntity<WatchlistDto> removeCryptoFromWatchlist(@PathVariable Long userId, @RequestParam String cryptoId) {
        WatchlistDto updatedWatchlist = watchlistService.removeCryptoFromWatchlist(userId, cryptoId);
        return ResponseEntity.ok(updatedWatchlist);
    }
}
