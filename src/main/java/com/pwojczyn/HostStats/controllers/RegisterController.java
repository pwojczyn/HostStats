package com.pwojczyn.HostStats.controllers;

import com.pwojczyn.HostStats.models.forms.RegisterForm;
import com.pwojczyn.HostStats.models.forms.repositories.UserRegisterRepository;
import com.pwojczyn.HostStats.services.UserRegisterModel;
import com.pwojczyn.HostStats.services.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

/*#TODO rejestarcja konta


*/
@Controller
public class RegisterController {
    @Autowired
    UserRegisterRepository userRegisterRepository;

    UserSession userSession;

    @GetMapping("/register")
    public String registerGet(Model model) {
        model.addAttribute("registerForm", new RegisterForm());

        return "register";
    }
    @PostMapping(value = "/register")
    public String registerPost(@ModelAttribute("registerForm") @Valid RegisterForm registerForm, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("info","Register errors.");
        }
        if(!registerForm.getEmail().isEmpty() & !registerForm.getPassword().isEmpty() & !result.hasErrors() & !userRegisterRepository.existsByemail(registerForm.getEmail())){
            model.addAttribute("info","Your account was registed.");
            userRegisterRepository.save(new UserRegisterModel(registerForm.getEmail(),registerForm.getPassword()));
            return "register";
        }else{
            model.addAttribute("info","All fields must be filled.");
        }


        return "register";
    }
}
