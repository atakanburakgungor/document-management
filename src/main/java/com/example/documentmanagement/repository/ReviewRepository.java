package com.example.documentmanagement.repository;

import com.example.documentmanagement.entity.Review;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Long>, QuerydslPredicateExecutor<Review> {
}
