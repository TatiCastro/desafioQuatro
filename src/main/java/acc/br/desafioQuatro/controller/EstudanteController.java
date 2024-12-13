package acc.br.desafioQuatro.controller;

import acc.br.desafioQuatro.model.Estudante;
import acc.br.desafioQuatro.service.EstudanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/studentRegistration")
public class EstudanteController {

    @Autowired
    private EstudanteService estudanteService;

    @GetMapping
    public String registrationPage(Model model) {
        model.addAttribute("estudante", new Estudante());
        return "studentRegistration";
    }

    @PostMapping
    public String registerStudent(@ModelAttribute Estudante estudante, Model model) {
        Estudante savedEstudante = estudanteService.register(estudante);
        model.addAttribute("estudante", savedEstudante);
        return "reviewRegistration";
    }
    @PostMapping("/confirm")
    public String confirmRegistration(@ModelAttribute Estudante estudante) {
        return "registrationConfirmed";
    }

    @PostMapping("/edit")
    public String editRegistration(@ModelAttribute Estudante estudante, Model model) {
        model.addAttribute("estudante", estudante);
        return "studentRegistration";
    }

    @PostMapping("/cancel")
    public String cancelRegistration() {
        return "registrationCancelled";
    }
}


