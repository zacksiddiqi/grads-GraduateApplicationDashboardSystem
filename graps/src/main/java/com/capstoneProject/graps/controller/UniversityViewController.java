package com.capstoneProject.graps.controller;

import com.capstoneProject.graps.entity.University;
import com.capstoneProject.graps.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/universities")
public class UniversityViewController {
    @Autowired
    private UniversityRepository universityRepository;

    @GetMapping("/view")
    public String showUnis(Model model){
        model.addAttribute("universities", universityRepository.findAll());
        return "universities";
    }

    @PostMapping("/add")
    public String addUni(@RequestParam String name,
                         @RequestParam(required = false) String country,
                         @RequestParam(required = false) String city,
                         @RequestParam(required = false) String state,
                         @RequestParam(required = false) String programName,
                         @RequestParam(required = false) String programUrl,
                         @RequestParam(required = false) Double tuition){
        University uni = new University();
        uni.setName(name);
        uni.setCountry(country);
        uni.setCity(city);
        uni.setState(state);
        uni.setProgramName(programName);
        uni.setProgramUrl(programUrl);
        if (tuition != null){
            uni.setTuition(java.math.BigDecimal.valueOf(tuition));
        }
        universityRepository.save(uni);
        return "redirect:/universities/view";
    }

    @GetMapping("/{id}/delete")
    public String deleteUni(@PathVariable Integer id){
        universityRepository.deleteById(id);
        return "redirect:/universities/view";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Integer id, Model model){
        University university = universityRepository.findById(id).orElse(null);
        if (university == null){
            return "redirect:/universities/view";
        }
        model.addAttribute("university", university);
        return "edit-university";
    }

    @PostMapping("/{id}/update")
    public String updateUniversity(@PathVariable Integer id,
                                   @RequestParam String name,
                                   @RequestParam(required = false) String country,
                                   @RequestParam(required = false) String city,
                                   @RequestParam(required = false) String state,
                                   @RequestParam(required = false) String programName,
                                   @RequestParam(required = false) String programUrl,
                                   @RequestParam(required = false) Double tuition) {

        University uni = universityRepository.findById(id).orElse(null);
        if (uni == null){
            return "redirect:/universities/view";
        }
        uni.setName(name);
        uni.setCountry(country);
        uni.setCity(city);
        uni.setState(state);
        uni.setProgramName(programName);
        uni.setProgramUrl(programUrl);
        if (tuition != null){
            uni.setTuition(java.math.BigDecimal.valueOf(tuition));
        }
        universityRepository.save(uni);
        return "redirect:/universities/view";

    }

}
