package org.e2e.labe2e03.review.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.e2e.labe2e03.ride.domain.Ride;
import org.e2e.labe2e03.user.domain.User;

@Entity
@Data
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String comment;


    @Column(nullable = false)
    private Integer rating;

    @OneToOne
    private Ride ride;

    @ManyToOne(fetch = FetchType.LAZY)
    private User author;

    @ManyToOne(fetch = FetchType.LAZY)
    private User target;
}