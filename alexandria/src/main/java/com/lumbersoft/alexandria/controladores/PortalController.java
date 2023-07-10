package com.lumbersoft.alexandria.controladores;


import com.lumbersoft.alexandria.entidades.SystemEntities.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PortalController {




    @GetMapping("/")
    public String index() {
        return "landing.html";
    }


    @GetMapping("/log")
    public String login() {

        return "nLogin";
    }

    @PostMapping("/logProcess")
    public String logInProcessing(@RequestParam(required = false) String error, ModelMap modelo){
        System.out.println(error);
        if (error != null) {
            modelo.put("msjError", "User o contraseña invalidos");
        }
        return "redirect:/admin/home";
    }

    @GetMapping("/admin/home")
    public String adminHomePage(HttpSession session,ModelMap model){
        User logged = (User) session.getAttribute("userSession");

        model.addAttribute("user",logged);

        return "homePage";
    }


}















