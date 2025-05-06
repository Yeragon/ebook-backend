package org.example.review;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.UUID;

@Mapper
public interface ReviewMapper {

    void insertReview(Review review);

    List<Review> findByEbookId(UUID ebookId);

}
