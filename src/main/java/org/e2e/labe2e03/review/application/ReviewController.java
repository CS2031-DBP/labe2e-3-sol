package org.e2e.labe2e03.review.application;

import org.e2e.labe2e03.review.domain.Review;
import org.e2e.labe2e03.review.domain.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping()
    public ResponseEntity<Void> createNewReview (@RequestBody Review newReview, @RequestParam Long rideId){
        reviewService.createNewReview(newReview, rideId);
        return ResponseEntity.created(null).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReview (@PathVariable Long id){
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }
}