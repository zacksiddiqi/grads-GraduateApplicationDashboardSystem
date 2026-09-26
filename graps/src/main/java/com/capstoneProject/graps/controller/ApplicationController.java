package com.capstoneProject.graps.controller;

import com.capstoneProject.graps.entity.Application;
import com.capstoneProject.graps.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//map string literal endpoint to controller
@RequestMapping("/applications")

public class ApplicationController {

    @Autowired
    private ApplicationRepository applicationRepository;

    @GetMapping
    public List<Application> getAllApps(){
        return applicationRepository.findAll();
    }

    @GetMapping("/{id}")
    public Application getAppsById(@PathVariable Integer id){
        return applicationRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Application createApp (@RequestBody Application application){
        return applicationRepository.save(application);
    }
}
