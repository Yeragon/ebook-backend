package org.example.review;

import org.example.user.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/review")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/add")
    public ApiResponse<String> addReview(@RequestBody ReviewRequest request) {
        reviewService.addReview(request);
        return ApiResponse.success("Review added successfully");
    }

    @GetMapping("/list")
    public ApiResponse<List<Review>> getReviews(@RequestParam UUID ebookId) {
        return ApiResponse.success(reviewService.getReviewsByEbookId(ebookId));
    }
}
