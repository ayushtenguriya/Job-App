package com.example.Job.App.review;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews(Long companyId);
}
