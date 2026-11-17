package com.klef.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.klef.exam.entity.Tourist;

public interface TouristRepository
        extends JpaRepository<Tourist, Long> {
}