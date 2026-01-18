package com.example.Job.App.review;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/companies/{companyId}")


public class ReviewController {
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }
    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long  companyId){
        return  new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
    }

    @PostMapping("/reviews")
    public ResponseEntity<String> addReview(@PathVariable Long companyId, @RequestBody Review review){
        boolean isReviewSaved = reviewService.addReview(companyId, review);
        if(isReviewSaved)
        return new ResponseEntity<>("Review added successfully", HttpStatus.OK);
        else
            return new ResponseEntity<>("Review Not Saved", HttpStatus.NOT_FOUND);

        // Here you would typically call a service method to save the review
        // e.g., reviewService.addReview(review);
        
    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long companyId, @PathVariable Long reviewId){
       return  new ResponseEntity<>(reviewService.getReview(companyId, reviewId), HttpStatus.OK);
    }

     // Here you would typically call a service method to get the review
    @PutMapping("/reviews/{reviewId}")
    public  ResponseEntity<String> updateReview(@PathVariable Long companyId, @PathVariable Long reviewId, @RequestBody Review review){
        boolean isReviewUpdated = reviewService.updateReview(companyId, reviewId, review);
        if(isReviewUpdated)
            return new ResponseEntity<>("Review updated successfully", HttpStatus.OK);
        else
            return new ResponseEntity<>("Review Not Updated", HttpStatus.NOT_FOUND);
        // Here you would typically call a service method to update the review
        // e.g., reviewService.updateReview(reviewId, review);

    }

    @DeleteMapping("/reviews/{reviewId}")
    public  ResponseEntity<String> deleteReview(@PathVariable Long companyId, @PathVariable Long reviewId){

        boolean isReviewDeleted = reviewService.deleteReview(companyId, reviewId);
        if(isReviewDeleted)
            return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
        else
            return new ResponseEntity<>("Review Not Deleted", HttpStatus.NOT_FOUND);
        // Here you would typically call a service method to delete the review
        // e.g., reviewService.deleteReview(reviewId);

    }

    
}
