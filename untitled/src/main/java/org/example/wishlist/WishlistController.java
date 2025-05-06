package org.example.wishlist;

import org.example.user.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {

    private final WishlistService wishlistService;

    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @PostMapping("/add")
    public ApiResponse<String> addToWishlist(@RequestBody Map<String, String> payload) {
        UUID userId = UUID.fromString(payload.get("userId"));
        UUID ebookId = UUID.fromString(payload.get("ebookId"));
        WishlistAddResult result = wishlistService.addToWishlist(userId, ebookId);

        return switch (result) {
            case SUCCESS -> ApiResponse.success("Added to wishlist");
            case ALREADY_EXISTS -> ApiResponse.fail("Book already in wishlist");
            case OUT_OF_STOCK -> ApiResponse.fail("No stock available");
        };

    }

    @DeleteMapping("/remove")
    public ApiResponse<String> removeFromWishlist(@RequestParam("userId") UUID userId,
                                                  @RequestParam("ebookId") UUID ebookId) {
        wishlistService.removeFromWishlist(userId, ebookId);
        return ApiResponse.success("Removed from wishlist");
    }

    @GetMapping("/{userId}")
    public ApiResponse<List<WishlistBook>> getWishlist(@PathVariable UUID userId) {
        return ApiResponse.success(wishlistService.getWishlistByUser(userId));
    }
}