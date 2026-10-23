package com.capstoneProject.graps.controller;


import com.capstoneProject.graps.entity.ApplicationStatus;
import com.capstoneProject.graps.repository.ApplicationRepository;
import com.capstoneProject.graps.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

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
        model.addAttribute("inProgressCount",applicationRepository.countByStatus(ApplicationStatus.in_progress));
        model.addAttribute("upcomingDeadline", 0);

        List<String> statusLabels = new ArrayList<>();
        List<Long> statusData = new ArrayList<>();
        for(ApplicationStatus s : ApplicationStatus.values()){
            statusLabels.add(s.getLabel());
            statusData.add(applicationRepository.countByStatus(s));
        }
        model.addAttribute("statusLabels",statusLabels);
        model.addAttribute("statusData",statusData);


        return "homepage";
    }
}
