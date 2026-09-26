package com.capstoneProject.graps.controller;

import com.capstoneProject.graps.entity.University;
import com.capstoneProject.graps.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//map string literal endpoint to controller
@RequestMapping("/universities")

public class UniversityController {

    @Autowired
    private UniversityRepository universityRepository;

    @GetMapping
    public List<University> getAllUnis(){
        return universityRepository.findAll();
    }

    @GetMapping("/{id}")
    public University getUniById(@PathVariable Integer id){
        return universityRepository.findById(id).orElse(null);
    }

    @PostMapping
    public University creatUni (@RequestBody University university){
        return universityRepository.save(university);
    }
}
