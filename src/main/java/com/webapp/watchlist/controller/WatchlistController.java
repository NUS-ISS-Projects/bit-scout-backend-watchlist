package com.webapp.watchlist.controller;

import com.webapp.watchlist.dto.WatchlistDto;
import com.webapp.watchlist.service.FirebaseService;
import com.webapp.watchlist.service.UserService;
import com.webapp.watchlist.service.WatchlistService;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/watchlist")
public class WatchlistController {

    @Autowired
    private FirebaseService firebaseService;

    @Autowired
    private UserService userService;

    @GetMapping("/getWatchList")
    public ResponseEntity<WatchlistDto> getWatchlist(@RequestHeader("Authorization") String token)
            throws ExecutionException, InterruptedException {
        String userId = userService.validateTokenAndGetUserId(token);
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
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

    @PostMapping("/create")
    public ResponseEntity<WatchlistDto> createOrUpdateWatchlist(@RequestHeader("Authorization") String token,
                                                                @RequestBody WatchlistDto watchlistDto)
            throws ExecutionException, InterruptedException {
        String userId = userService.validateTokenAndGetUserId(token);
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        watchlistDto.setUserId(userId);
        WatchlistDto updatedWatchlist = firebaseService.createWatchlist(watchlistDto);
        return ResponseEntity.ok(updatedWatchlist);
    }

    @PostMapping("/add")
    public ResponseEntity<WatchlistDto> addCryptoToWatchlist(@RequestHeader("Authorization") String token, @RequestParam String cryptoId)
            throws ExecutionException, InterruptedException {
        String userId = userService.validateTokenAndGetUserId(token);
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        WatchlistDto updatedWatchlist = firebaseService.addCryptoToWatchlist(userId, cryptoId);
        return ResponseEntity.ok(updatedWatchlist);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<WatchlistDto> removeCryptoFromWatchlist(@RequestHeader("Authorization") String token,
            @RequestParam String cryptoId) throws ExecutionException, InterruptedException {
        String userId = userService.validateTokenAndGetUserId(token);
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        WatchlistDto updatedWatchlist = firebaseService.removeCryptoFromWatchlist(userId, cryptoId);
        return ResponseEntity.ok(updatedWatchlist);
    }
}
