package com.eep.EEP_Consulting.Controllers;

import com.eep.EEP_Consulting.Component.TrazasComponent;
import com.eep.EEP_Consulting.Model.Camionero;
import com.eep.EEP_Consulting.Model.Transportes;
import com.eep.EEP_Consulting.impl.ImplServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;

@Controller
public class GeneralController {

    final String ALTA = "alta";
    final String BAJA = "baja";
    final String MODIF = "modificacion";
    final String REGISTRO = "registro";
    final String MENSAJE = "mensaje_usuarios";
    final String LISTADO = "listado";

    @Autowired
    @Qualifier("ServiciosDatos")
    private ImplServicios servicio;

    @Autowired
    @Qualifier("TrazasComponent")
    private TrazasComponent componente;

    @GetMapping("/alta")
    public String altaget(Model model){
        model.addAttribute("camionero", new Camionero());
        model.addAttribute("valoresTransporte", Transportes.values());
        return ALTA;
    }

    @PostMapping("/mensajealta")
    public String altapost(Model model, @Validated Camionero camionero, BindingResult result){
        if(result.hasErrors()){
            model.addAttribute("valoresTransporte", Transportes.values());
            return ALTA;
        }else {
            ArrayList<Camionero> camionero_guardar = new ArrayList();
            camionero_guardar.add(camionero);
            model.addAttribute("mensaje", servicio.GuardarCamionero(camionero_guardar));
            return MENSAJE;
        }
    }

    @GetMapping("/baja")
    public String nombrebaja(Model model){
        model.addAttribute("camionero", new Camionero());
        return BAJA;
    }

    @PostMapping("/mensajebaja")
    public String darbaja(@ModelAttribute Camionero nombre, Model model){
        model.addAttribute("mensaje", servicio.BajaCamionerosrepes(nombre));
        return MENSAJE;
    }

    @GetMapping("/listado")
    public String listado(Model model) throws FileNotFoundException {
        model.addAttribute("Camionero", servicio.ListarCamioneros());
        return LISTADO;
    }

    @GetMapping("/modificacion")
    public String modificacion(Model model){
        model.addAttribute("valoresTransporte", Transportes.values());
        model.addAttribute("mods", new Camionero());
        return MODIF;
    }

    @PostMapping("/mesajemods")
    public String modificaciones(@RequestParam(value = "busqueda") String nombre, @ModelAttribute Camionero mods, Model model){
        if (nombre == null || nombre.isEmpty()) {
            model.addAttribute("mensaje",componente.errores("El nombre a buscar no puede estar vacio"));
        }else {
            model.addAttribute("mensaje", servicio.ModificacionCamioneros(nombre, mods));
        }
        return MENSAJE;
    }
}
