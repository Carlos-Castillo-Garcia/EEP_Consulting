package com.eep.EEP_Consulting.Controllers;

import com.eep.EEP_Consulting.Model.Camionero;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class GeneralController {

    public final String ALTA = "alta";
    public final String BAJA = "baja";
    public final String MODIF = "modificaciones";
    public final String REGISTRO = "registro";
    public final String MENSAJE = "mensaje_usuarios";
    public final String LISTADO = "listado";

    @RequestMapping(value = "/alta", method = RequestMethod.GET)
    public String daralta(Model model){
        model.addAttribute("camionero", new Camionero());
        return ALTA;
    }

    @RequestMapping(value = "/baja", method = RequestMethod.GET)
    public String nombrebaja(Model model){
        model.addAttribute("prueba", new Camionero());
        return BAJA;
    }

    @RequestMapping(value = "/bajapost", method = RequestMethod.POST)
    public String darbaja(@ModelAttribute Camionero camionero, Model model){
        model.addAttribute("camionero", camionero);
        return BAJA;
    }
}
