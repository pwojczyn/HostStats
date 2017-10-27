package com.pwojczyn.HostStats.controllers;

import com.pwojczyn.HostStats.models.forms.repositories.HostInfoRepository;
import com.pwojczyn.HostStats.models.forms.repositories.HostsRepository;
import com.pwojczyn.HostStats.models.forms.repositories.UserRepository;
import com.pwojczyn.HostStats.services.HostInfoModel;
import com.pwojczyn.HostStats.services.UserModel;
import com.pwojczyn.HostStats.services.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;

/*
#TODO db:hostStat
id
hostId
apiKey
cpu
memory
disk
loadAverage
network
process
alerts
other
-----------------------------------
#TODO db:hosts
id
userId
hostId
hostKey

-----------------------------------
#TODO db:hostInfo
id
userId
hostId
hostTitle
hostMemory
hostDisk
hostCpu
hostUpdate
hostIp
hostUptime

#TODO db:procList

 */

@Controller
public class DashboardController {
    UserSession userSession;
    @Autowired
    HostInfoRepository hostInfoRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    HostsRepository hostsRepository;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        String status = userSession.session().getAttribute("isLogged").toString();
        if(status.equals("false") | status.equals("null")) {
            return "redirect:home";
        }
        //model.addAttribute("info","Session: "+userSession.session().getAttribute("isLogged")+" API: "+userSession.session().getAttribute("userApiKey"));
        // show List of your hosts
        model.addAttribute("hostsList",hostInfoRepository.findByUserId(Integer.parseInt(userSession.session().getAttribute("userId").toString())));



        return "dashboard";
    }

    @GetMapping("/dashboard/account")
    public String dashboardAccount(Model model) {
        String status = userSession.session().getAttribute("isLogged").toString();
        if(status.equals("false") | status.equals("null")) {
            return "redirect:home";
        }
        UserModel userData = userRepository.findById(Integer.parseInt(userSession.session().getAttribute("userId").toString()));

        model.addAttribute("userEmail",userData.getEmail());
        model.addAttribute("userApiKey",userData.getApikey());



        return "dashboard_account";
    }
    @GetMapping("/dashboard/hostinfo/{hostkey}/")
    public String dashboardHostInfo(Model model,@PathVariable("hostkey") String hostKey) {
        HostInfoModel hostInfoModel = hostInfoRepository.findByHostId(hostKey);

        model.addAttribute("hostTitle",hostInfoModel.getHostTitle());
        model.addAttribute("hostInfo",hostInfoModel);

        return "dashboard_hostinfo";
    }


    @GetMapping("/logout")
    public String logout(){
        userSession.session().setAttribute("isLogged",false);
        userSession.session().setAttribute("userId",null);
        userSession.session().setAttribute("userApiKey",null);

        return "home";
    }
}
