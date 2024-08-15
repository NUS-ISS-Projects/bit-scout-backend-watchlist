package com.webapp.watchlist.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "watchlist")
public class Watchlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "watchlist_items", joinColumns = @JoinColumn(name = "watchlist_id"))
    @Column(name = "crypto_id")
    private Set<String> cryptoIds = new HashSet<>();

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Set<String> getCryptoIds() {
        return cryptoIds;
    }

    public void setCryptoIds(Set<String> cryptoIds) {
        this.cryptoIds = cryptoIds;
    }

    // toString
    @Override
    public String toString() {
        return "Watchlist{" +
                "id=" + id +
                ", userId=" + userId +
                ", cryptoIds=" + cryptoIds +
                '}';
    }
}
