package com.capstoneProject.graps.controller;


import com.capstoneProject.graps.entity.Application;
import com.capstoneProject.graps.entity.ApplicationStatus;
import com.capstoneProject.graps.entity.RouteType;
import com.capstoneProject.graps.entity.University;
import com.capstoneProject.graps.repository.ApplicationRepository;
import com.capstoneProject.graps.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Controller
@RequestMapping("/applications")
public class ApplicationViewController {
    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private UniversityRepository universityRepository;

    @GetMapping("/view")
    public String showApplications(Model model){
        model.addAttribute("applications", applicationRepository.findAll());
        model.addAttribute("universities", universityRepository.findAll());
        model.addAttribute("statuses", ApplicationStatus.values());
        model.addAttribute("routes", RouteType.values());
        return "applications";
    }

    @PostMapping("/add")
    public String addNewApplication(@RequestParam Integer universityId,
                                    @RequestParam ApplicationStatus status,
                                    @RequestParam(required = false)LocalDate deadline,
                                    @RequestParam(required = false) RouteType route,
                                    @RequestParam(required = false) Double fee){
        University university = universityRepository.findById(universityId).orElse(null);
        if (university == null){
            return "redirect:/applications/view";
        }

        Application application = new Application();
        application.setUniversity(university);
        application.setStatus(status);
        application.setDeadline(deadline);
        application.setRoute(route);
        if (fee != null){
            application.setFee(BigDecimal.valueOf(fee));
        }
        applicationRepository.save(application);
        return "redirect:/applications/view";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Integer id, Model model){
        Application app = applicationRepository.findById(id).orElse(null);
        if (app == null){
            return "redirect:/applications/view";
        }
        model.addAttribute("app", app);
        model.addAttribute("statuses", ApplicationStatus.values());
        return "edit-application";
    }

    @PostMapping("/{id}/update")
    public String updateApplication(@PathVariable Integer id,
                                    @RequestParam(required = false)ApplicationStatus status,
                                    @RequestParam(required = false)LocalDate deadline,
                                    @RequestParam(required = false)RouteType route,
                                    @RequestParam(required = false)Double fee){
        Application app = applicationRepository.findById(id).orElse(null);
        if (app == null){
            return "redirect:/applications/view";
        }
        //mapping the changed values to the setter method
        app.setStatus(status);
        app.setDeadline(deadline);
        app.setRoute(route);
        if (fee != null){
            app.setFee(java.math.BigDecimal.valueOf(fee));
        }
        applicationRepository.save(app);
        return "redirect:/applications/view";
    }



    @GetMapping("/{id}/delete")
    public String deleteApplication(@PathVariable Integer id){
        applicationRepository.deleteById(id);
        return "redirect:/applications/view";
    }

}
