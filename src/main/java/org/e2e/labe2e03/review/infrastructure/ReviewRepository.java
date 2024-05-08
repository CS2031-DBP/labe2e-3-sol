package org.e2e.labe2e03.review.infrastructure;

import org.e2e.labe2e03.review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Set<Review> findByRating(Integer rating);
    Set<Review> findByAuthor_Id(Long authorId);
    Long countByAuthor_Id(Long authorId);
}