package org.example.wishlist;

import java.util.UUID;

public class WishlistBook {
    private UUID id;
    private UUID ebookId;
    private UUID userId;
    private boolean favorite;
    private boolean available;
    private String title;
    private String author;

    // Getters and setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getEbookId() { return ebookId; }
    public void setEbookId(UUID ebookId) { this.ebookId = ebookId; }

    public UUID getAccountId() { return userId; }
    public void setAccountId(UUID userId) { this.userId = userId; }

    public boolean isFavorite() { return favorite; }
    public void setFavorite(boolean favorite) { this.favorite = favorite; }

    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
}
