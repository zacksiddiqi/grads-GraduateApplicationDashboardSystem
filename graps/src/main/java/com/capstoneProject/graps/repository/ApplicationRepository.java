package com.capstoneProject.graps.repository;

import com.capstoneProject.graps.entity.Application;
import com.capstoneProject.graps.entity.ApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {

    long countByStatus(ApplicationStatus status);

    long countByRoute(Application route);
}
