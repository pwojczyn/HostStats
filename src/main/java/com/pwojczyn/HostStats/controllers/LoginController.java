package com.pwojczyn.HostStats.controllers;

import com.pwojczyn.HostStats.models.forms.LoginForm;
import com.pwojczyn.HostStats.models.forms.RegisterForm;
import com.pwojczyn.HostStats.models.forms.repositories.UserRepository;
import com.pwojczyn.HostStats.services.UserModel;
import com.pwojczyn.HostStats.services.UserSession;
import com.pwojczyn.HostStats.utils.PasswordHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class LoginController {
    @Autowired
    UserRepository userRepository;

    UserSession userSession;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginForm", new LoginForm());

        return "login";
    }
    @PostMapping(value = "/login")
    public String loginPost(@ModelAttribute("loginForm") @Valid LoginForm loginForm, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("info","Login errors ");
        }
        UserModel userData = userRepository.findByEmail(loginForm.getEmail());
        if ( userData.getEmail().equals(loginForm.getEmail()) & userData.getPassword().equals(PasswordHash.hash(loginForm.getPassword())) ){
            model.addAttribute("info","Loged");
            userSession.session().setAttribute("isLogged",true);
            userSession.session().setAttribute("userId",userData.getId());
            userSession.session().setAttribute("userApiKey",userData.getApikey());
            return "redirect:/dashboard";

        }else{
            model.addAttribute("info","Some errors");
        }
        //userSession.session().setAttribute("isLogged",false);
        //PasswordHash.hash(password);
        //model.addAttribute("info","Post form session: "+userSession.session().getAttribute("isLogged")+" ID: "+userSession.session().getId()+"User: "+loginForm.getEmail()+" p: "+loginForm.getPassword());

        return "login";
    }

    @GetMapping("/login_test")
    public String loginTest(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        UserModel userData = userRepository.findByEmail("piotr.wojczynski@outlook.com");

        model.addAttribute("info","User: "+userData.getEmail());

        return "login";
    }

}
