package com.capstoneProject.graps.repository;

import com.capstoneProject.graps.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UniversityRepository extends JpaRepository<University, Integer> {
}
