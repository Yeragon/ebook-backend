package org.example.review;

import java.time.LocalDateTime;
import java.util.UUID;

public class Review {
    private UUID id;
    private UUID ebookId;
    private UUID userId;
    private String username;
    private int rating;
    private String content;
    private LocalDateTime createdAt;

    public Review() {}

    public Review(UUID id, UUID ebookId, UUID userId, String username, int rating, String content, LocalDateTime createdAt) {
        this.id = id;
        this.ebookId = ebookId;
        this.userId = userId;
        this.username = username;
        this.rating = rating;
        this.content = content;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getEbookId() {
        return ebookId;
    }

    public void setEbookId(UUID ebookId) {
        this.ebookId = ebookId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
