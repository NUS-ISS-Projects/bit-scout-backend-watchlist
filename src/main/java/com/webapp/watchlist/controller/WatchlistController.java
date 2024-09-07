package com.webapp.watchlist.controller;

import com.webapp.watchlist.dto.WatchlistDto;
import com.webapp.watchlist.service.FirebaseService;
import com.webapp.watchlist.service.WatchlistService;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/watchlist")
public class WatchlistController {

    @Autowired
    private WatchlistService watchlistService;

    @Autowired
    private FirebaseService firebaseService;

    @GetMapping("/{userId}")
    public ResponseEntity<WatchlistDto> getWatchlist(@PathVariable Long userId)
            throws ExecutionException, InterruptedException {
        // WatchlistDto watchlist = watchlistService.getWatchlistByUserId(userId);
        WatchlistDto watchlist = firebaseService.getWatchlistByUserId(userId);
        if (watchlist != null) {
            return ResponseEntity.ok(watchlist);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        String podName = System.getenv("HOSTNAME");
        return ResponseEntity.ok("Watchlist service is up and running on pod: " + podName);
    }

    @PostMapping("/")
    public ResponseEntity<WatchlistDto> createOrUpdateWatchlist(@RequestBody WatchlistDto watchlistDto)
            throws ExecutionException, InterruptedException {
        // WatchlistDto updatedWatchlist =
        // watchlistService.createOrUpdateWatchlist(watchlistDto);
        WatchlistDto updatedWatchlist = firebaseService.createOrUpdateWatchlist(watchlistDto);
        return ResponseEntity.ok(updatedWatchlist);
    }

    @PostMapping("/{userId}/add")
    public ResponseEntity<WatchlistDto> addCryptoToWatchlist(@PathVariable Long userId, @RequestParam String cryptoId)
            throws ExecutionException, InterruptedException {
        // WatchlistDto updatedWatchlist = watchlistService.addCryptoToWatchlist(userId,
        // cryptoId);
        WatchlistDto updatedWatchlist = firebaseService.addCryptoToWatchlist(userId, cryptoId);
        return ResponseEntity.ok(updatedWatchlist);
    }

    @DeleteMapping("/{userId}/remove")
    public ResponseEntity<WatchlistDto> removeCryptoFromWatchlist(@PathVariable Long userId,
            @RequestParam String cryptoId) throws ExecutionException, InterruptedException {
        // WatchlistDto updatedWatchlist =
        // watchlistService.removeCryptoFromWatchlist(userId, cryptoId);
        WatchlistDto updatedWatchlist = firebaseService.removeCryptoFromWatchlist(userId, cryptoId);
        return ResponseEntity.ok(updatedWatchlist);
    }
}
