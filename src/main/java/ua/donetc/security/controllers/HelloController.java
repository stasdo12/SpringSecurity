package ua.donetc.security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.donetc.security.security.PersonDetails;
import ua.donetc.security.services.AdminService;
import ua.donetc.security.services.PeopleService;


@Controller
public class HelloController {

    private final AdminService adminService;

    private final PeopleService peopleService;

    @Autowired
    public HelloController(AdminService adminService, PeopleService peopleService) {
        this.adminService = adminService;
        this.peopleService = peopleService;
    }


    @GetMapping("/hello")
    public String sayHello(Model model) {
        model.addAttribute("people", peopleService.show(13));
        return "hello";
    }

    @GetMapping("/showUserInfo")
    @ResponseBody
    public String showUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();

        return personDetails.getUsername();

    }


    @GetMapping("/admin")
    public String adminPage() {
        adminService.doAdminStuff();
        return "admin";
    }
}