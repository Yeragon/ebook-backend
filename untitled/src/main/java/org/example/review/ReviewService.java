package org.example.review;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ReviewService {

    private final ReviewMapper reviewMapper;

    public ReviewService(ReviewMapper reviewMapper) {
        this.reviewMapper = reviewMapper;
    }

    public void addReview(ReviewRequest req) {
        Review review = new Review();
        review.setId(UUID.randomUUID());
        review.setEbookId(req.getEbookId());
        review.setUserId(req.getUserId());
        review.setContent(req.getContent());
        review.setRating(req.getRating());
        review.setCreatedAt(LocalDateTime.now());

        reviewMapper.insertReview(review);
    }

    public List<Review> getReviewsByEbookId(UUID ebookId) {
        return reviewMapper.findByEbookId(ebookId);
    }
}
