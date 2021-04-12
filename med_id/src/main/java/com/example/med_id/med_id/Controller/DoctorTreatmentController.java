package com.example.med_id.Med_Id.controller;

import com.example.med_id.Med_Id.models.User;
import com.example.med_id.Med_Id.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping(value = "/doctortreatment")
public class DoctorTreatmentController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping(value = "")
    public ModelAndView index(Model model)
    {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();

        String email = loggedInUser.getName();

        Optional<User> userDetail = this.userRepo.FindByEmail(email.toString());

        String fullName = userDetail.get().biodata.getFullname();
        Long biodataID = userDetail.get().getId();

        String[] firstName = fullName.split("\\s+");

        model.addAttribute("fullName", fullName);
        model.addAttribute("firstName", firstName[0]);
        model.addAttribute("biodataID", biodataID);

//    @GetMapping(value = "index")
//    public ModelAndView index() {
        ModelAndView view = new ModelAndView("doctortreatment/index");
        return view;
    }
}
