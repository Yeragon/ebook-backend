package org.example.wishlist;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Mapper
public interface WishlistMapper {
    void insert(WishlistItem item);

    void deleteByUserIdAndEbookId(@Param("userId") UUID userId, @Param("ebookId") UUID ebookId);

    Optional<WishlistItem> findByUserIdAndEbookId(@Param("userId") UUID userId, @Param("ebookId") UUID ebookId);

    List<WishlistBook> getWishlistByUser(@Param("userId") UUID userId);

}
