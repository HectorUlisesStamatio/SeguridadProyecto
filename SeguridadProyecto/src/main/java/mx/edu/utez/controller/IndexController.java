package mx.edu.utez.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/")
    public String index(){
        return "login";
    }
    @GetMapping("/registro")
    public String registro(){
        return "register";
    }
    @GetMapping("/404")
    public String error404(){
        return "error404";
    }
    @GetMapping("/500")
    public String error500(){
        return "error500";
    }
    @GetMapping("/403")
    public String error403(){
        return "error403";
    }


    @GetMapping("/panel")
    public String gestionPersonas(){
        return "Persona/gestionPersonas";
    }

    @GetMapping("/api/admin/panel")
    public String panel(){
        return "panel";
    }
}
