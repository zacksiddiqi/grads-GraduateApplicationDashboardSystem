package com.capstoneProject.graps.controller;


import com.capstoneProject.graps.repository.ApplicationRepository;
import com.capstoneProject.graps.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeViewController {

    @Autowired
    private UniversityRepository universityRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    @GetMapping("/")
    public String homepage(Model model){
        model.addAttribute("universityCount", universityRepository.count());
        model.addAttribute("applicationCount", applicationRepository.count());
        model.addAttribute("upcomingDeadline", 0);
        return "homepage";
    }
}
