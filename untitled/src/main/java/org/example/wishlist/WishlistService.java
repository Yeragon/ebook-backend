package org.example.wishlist;

import org.example.ebook.EbookMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class WishlistService {
    private final WishlistMapper wishlistMapper;
    private final EbookMapper ebookMapper;

    public WishlistService(WishlistMapper wishlistMapper, EbookMapper ebookMapper) {
        this.wishlistMapper = wishlistMapper;
        this.ebookMapper = ebookMapper;
    }

    public WishlistAddResult addToWishlist(UUID userId, UUID ebookId) {
        int stock = ebookMapper.getStockByEbookId(ebookId);
        if (stock <= 0) {
            return WishlistAddResult.OUT_OF_STOCK;
        }

        Optional<WishlistItem> existing = wishlistMapper.findByUserIdAndEbookId(userId, ebookId);
        if (existing.isPresent()) {
            return WishlistAddResult.ALREADY_EXISTS;
        }

        WishlistItem item = new WishlistItem(UUID.randomUUID(), userId, ebookId, true, LocalDateTime.now());
        wishlistMapper.insert(item);
        ebookMapper.decreaseStockById(ebookId);
        return WishlistAddResult.SUCCESS;
    }

    public void removeFromWishlist(UUID userId, UUID ebookId) {
        wishlistMapper.deleteByUserIdAndEbookId(userId, ebookId);
    }

    public List<WishlistBook> getWishlistByUser(UUID userId) {
        return wishlistMapper.getWishlistByUser(userId);
    }

}