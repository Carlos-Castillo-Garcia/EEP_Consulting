package com.eep.EEP_Consulting.Controllers;

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
    final String MODIF = "modificacion";
    final String REGISTRO = "registro";
    final String MENSAJE = "mensaje_usuarios";
    final String LISTADO = "listado";
    final String LIST_BUSQUEDA = "listado_busqueda";
    final String BUSQUEDA = "busqueda";
    String nombre_busqueda = "";
    String apellido_busqueda = "";
    String eleccion_busqueda = "";
    Camionero camionero_busqueda = new Camionero();

    @Autowired
    @Qualifier("ServiciosDatos")
    private ImplServicios servicio;

    @GetMapping("/listado")
    public String listado(Model model) throws FileNotFoundException {
        model.addAttribute("Camionero", servicio.ListarCamioneros());
        return LISTADO;
    }

    @PostMapping("/postlistado")
    public String postlistado(@RequestParam(value = "seleccion") int seleccion, Model model){
        ArrayList<Camionero> listado = (ArrayList<Camionero>) servicio.BusquedaCamionerosrepes(nombre_busqueda, apellido_busqueda);
        camionero_busqueda = listado.get(seleccion);
        if(eleccion_busqueda.equals("true")){
            model.addAttribute("mensaje", servicio.BajaCamionerosId(camionero_busqueda.getid()));
            return MENSAJE;
        }else if(eleccion_busqueda.equals("false")){
            model.addAttribute("camionero", camionero_busqueda);
            model.addAttribute("valoresTransporte", Transportes.values());
            return MODIF;
        }
        return null;
    }

    @GetMapping("/busqueda")
    public String busqueda_camioneros(Model model){
        model.addAttribute("camionero", new Camionero());
        return BUSQUEDA;
    }

    @PostMapping("/busqueda")
    public String posbusqueda(@RequestParam(value = "eleccion") String eleccion, @Validated Camionero busqueda, BindingResult result, Model model){
        String mensaje = "";
        eleccion_busqueda = eleccion;
        nombre_busqueda = busqueda.getNombre();
        apellido_busqueda = busqueda.getApellidos();
        if(result.hasFieldErrors("nombre") && result.hasFieldErrors("apellidos")){
            return BUSQUEDA;
        }else {
            if(servicio.BusquedaCamionerosrepes(busqueda.getNombre(), busqueda.getApellidos()).size() > 1){
                model.addAttribute("Camionero", servicio.BusquedaCamionerosrepes(busqueda.getNombre(), busqueda.getApellidos()));
                return LIST_BUSQUEDA;
            }else if(servicio.BusquedaCamionerosrepes(busqueda.getNombre(), busqueda.getApellidos()).size() > 0){
                if(eleccion.equals("true")){
                    model.addAttribute("mensaje", servicio.BajaCamioneros(busqueda.getNombre(), busqueda.getApellidos()));
                    return MENSAJE;
                }else if(eleccion.equals("false")){
                    nombre_busqueda = servicio.BusquedaCamionerosrepes(busqueda.getNombre(), busqueda.getApellidos()).get(0).getNombre();
                    apellido_busqueda = servicio.BusquedaCamionerosrepes(busqueda.getNombre(), busqueda.getApellidos()).get(0).getApellidos();
                    camionero_busqueda = servicio.BusquedaCamionerosrepes(busqueda.getNombre(), busqueda.getApellidos()).get(0);
                    model.addAttribute("camionero", camionero_busqueda);
                    model.addAttribute("valoresTransporte", Transportes.values());
                    return MODIF;
                }
            }
        }
        model.addAttribute("mensaje", "No se ha encontrado ningun Camionero con ese nombre o apellidos");
        return MENSAJE;
    }

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

    @PostMapping("/mesajemods")
    public String postmodificacion(@Validated Camionero camionero, BindingResult result, Model model){
        model.addAttribute("mensaje", servicio.ModificacionCamioneros(camionero_busqueda, camionero));
        return MENSAJE;
    }

    @GetMapping("/registro")
    public String registro(Model model){
        model.addAttribute("registro", servicio.RegistroOperaciones());
        return REGISTRO;
    }
}
