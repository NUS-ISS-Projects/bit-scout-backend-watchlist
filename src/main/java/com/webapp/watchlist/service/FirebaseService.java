package com.webapp.watchlist.service;

import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.WriteResult;
import com.webapp.watchlist.dto.WatchlistDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class FirebaseService {

    @Autowired
    private Firestore firestore;

    private static final String COLLECTION_NAME = "watchlists";

    public WatchlistDto getWatchlistByUserId(Long userId) throws ExecutionException, InterruptedException {
        DocumentReference docRef = firestore.collection(COLLECTION_NAME).document(userId.toString());
        DocumentSnapshot document = docRef.get().get();

        if (document.exists()) {
            WatchlistDto watchlistDto = document.toObject(WatchlistDto.class);
            return watchlistDto;
        }
        return null; // or throw a custom exception
    }

    public WatchlistDto createOrUpdateWatchlist(WatchlistDto watchlistDto)
            throws ExecutionException, InterruptedException {
        DocumentReference docRef = firestore.collection(COLLECTION_NAME).document(watchlistDto.getUserId().toString());
        WriteResult writeResult = docRef.set(watchlistDto).get();
        return watchlistDto;
    }

    public WatchlistDto addCryptoToWatchlist(Long userId, String cryptoId)
            throws ExecutionException, InterruptedException {
        DocumentReference docRef = firestore.collection(COLLECTION_NAME).document(userId.toString());
        DocumentSnapshot document = docRef.get().get();

        WatchlistDto watchlistDto;
        if (document.exists()) {
            watchlistDto = document.toObject(WatchlistDto.class);
            if (watchlistDto != null) {
                HashSet<String> cryptoIds = new HashSet<>(watchlistDto.getCryptoIds());
                cryptoIds.add(cryptoId);
                watchlistDto.setCryptoIds(new ArrayList<>(cryptoIds));
            }
        } else {
            watchlistDto = new WatchlistDto();
            watchlistDto.setUserId(userId);
            HashSet<String> cryptoIds = new HashSet<>();
            cryptoIds.add(cryptoId);
            watchlistDto.setCryptoIds(new ArrayList<>(cryptoIds));
        }

        docRef.set(watchlistDto).get();
        return watchlistDto;
    }

    public WatchlistDto removeCryptoFromWatchlist(Long userId, String cryptoId)
            throws ExecutionException, InterruptedException {
        DocumentReference docRef = firestore.collection(COLLECTION_NAME).document(userId.toString());
        DocumentSnapshot document = docRef.get().get();

        if (!document.exists()) {
            throw new RuntimeException("Watchlist not found for user id: " + userId);
        }

        WatchlistDto watchlistDto = document.toObject(WatchlistDto.class);
        if (watchlistDto != null) {
            HashSet<String> cryptoIds = new HashSet<>(watchlistDto.getCryptoIds());
            cryptoIds.remove(cryptoId);
            watchlistDto.setCryptoIds(new ArrayList<>(cryptoIds));
        }

        docRef.set(watchlistDto).get();
        return watchlistDto;
    }
}
