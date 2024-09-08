package com.webapp.watchlist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.webapp.watchlist.entity.Watchlist;

import java.util.Optional;

public interface WatchlistRepository extends JpaRepository<Watchlist, String> {
    Optional<Watchlist> findByUserId(String userId);
}
