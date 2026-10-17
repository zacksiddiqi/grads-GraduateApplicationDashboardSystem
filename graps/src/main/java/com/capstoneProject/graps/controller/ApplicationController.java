package com.capstoneProject.graps.controller;

import com.capstoneProject.graps.dto.ApplicationRequest;
import com.capstoneProject.graps.entity.Application;
import com.capstoneProject.graps.entity.University;
import com.capstoneProject.graps.repository.ApplicationRepository;
import com.capstoneProject.graps.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
//map string literal endpoint to controller
@RequestMapping("/applications")
public class ApplicationController {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private UniversityRepository universityRepository;

    @GetMapping
    public List<Application> getAllApps() {
        return applicationRepository.findAll();
    }

    @GetMapping("/{id}")
    public Application getAppsById(@PathVariable Integer id) {
        return applicationRepository.findById(id).orElse(null);
    }

    @PostMapping
    public ResponseEntity<Application> createApp(@RequestBody ApplicationRequest request) {
        University university = universityRepository.findById(request.getUniversityId()).orElse(null);

        if (university == null) {
            return ResponseEntity.badRequest().build();
        }

        Application application = new Application();
        application.setUniversity(university);
        application.setStatus(request.getStatus());
        application.setDeadline(request.getDeadline());
//        application.setSubmittedDate(request.getSubmittedDate());
//        application.setDecisionDate(request.getDecisionDate());
        application.setRoute(request.getRoute());
        application.setFee(request.getFee());
//        application.setNotes(request.getNotes());

        Application saved = applicationRepository.save(application);
        return ResponseEntity.ok(saved);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Application> updateApplication(@PathVariable Integer id, @RequestBody ApplicationRequest request) {
        Application existing = applicationRepository.findById(id).orElse(null);
        if (existing == null) {
            return ResponseEntity.badRequest().build();
        }

        if (request.getUniversityId() != null) {
            University university = universityRepository.findById(request.getUniversityId()).orElse(null);
            if (university == null) {
                return ResponseEntity.badRequest().build();
            }
            existing.setUniversity(university);
        }
        existing.setStatus(request.getStatus());
        existing.setDeadline(request.getDeadline());
//        existing.setSubmittedDate(request.getSubmittedDate());
//        existing.setDecisionDate(request.getDecisionDate());
        existing.setRoute(request.getRoute());
        existing.setFee(request.getFee());
//        existing.setNotes(request.getNotes());

        Application updated = applicationRepository.save(existing);
        return ResponseEntity.ok(updated);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApplication(@PathVariable Integer id){
        if(!applicationRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        applicationRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }



}
