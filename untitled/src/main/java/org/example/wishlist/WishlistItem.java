package org.example.wishlist;

import java.time.LocalDateTime;
import java.util.UUID;

public class WishlistItem {
    private UUID id;
    private UUID userId;
    private UUID ebookId;
    private boolean favorite;
    private LocalDateTime createdAt;

    public WishlistItem() {}

    public WishlistItem(UUID id, UUID userId, UUID ebookId, boolean favorite, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.ebookId = ebookId;
        this.favorite = favorite;
        this.createdAt = createdAt;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getUserId() { return userId; }
    public void setUserId(UUID userId) { this.userId = userId; }

    public UUID getEbookId() { return ebookId; }
    public void setEbookId(UUID ebookId) { this.ebookId = ebookId; }

    public boolean isFavorite() { return favorite; }
    public void setFavorite(boolean favorite) { this.favorite = favorite; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}