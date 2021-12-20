package com.eep.EEP_Consulting.Controllers;

import com.eep.EEP_Consulting.Model.Camionero;
import com.eep.EEP_Consulting.Model.Transportes;
import com.eep.EEP_Consulting.impl.ImplServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;

@Controller
public class GeneralController {

    final String ALTA = "alta";
    final String BAJA = "baja";
    final String MODIF = "modificaciones";
    final String REGISTRO = "registro";
    final String MENSAJE = "mensaje_usuarios";
    final String LISTADO = "listado";

    @Autowired
    @Qualifier("ServiciosDatos")
    private ImplServicios servicio;

    @GetMapping("/alta")
    public String altaget(Model model){
        model.addAttribute("camionero", new Camionero());
        model.addAttribute("valoresTransporte", Transportes.values());
        return ALTA;
    }

    @PostMapping("/mensajealta")
    public String altapost(@ModelAttribute Camionero camionero, Model model){
        model.addAttribute("mensaje", servicio.GuardarCamionero(camionero));
        return MENSAJE;
    }

    @GetMapping("/baja")
    public String nombrebaja(Model model){
        model.addAttribute("camionero", new Camionero());
        return BAJA;
    }

    @PostMapping("/mensajebaja")
    public String darbaja(@ModelAttribute Camionero nombre, Model model){
        servicio.BajaCamioneros(nombre);
        model.addAttribute("mensaje", "Camionero Borrado");
        return MENSAJE;
    }

    @GetMapping("/listado")
    public String listado(Model model) {
        model.addAttribute("Camionero", servicio.ListarCamioneros());

        return LISTADO;
    }

}
