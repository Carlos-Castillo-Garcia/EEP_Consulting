package com.eep.EEP_Consulting.impl;

import com.eep.EEP_Consulting.Component.TrazasComponent;
import com.eep.EEP_Consulting.Model.Camionero;
import com.eep.EEP_Consulting.Model.Registro;
import com.eep.EEP_Consulting.Service.DatosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service("ServiciosDatos")
public class ImplServicios implements DatosService {
    File archivoCamioneros = new File("src\\main\\java\\com\\eep\\EEP_Consulting\\Repository\\Camioneros.txt");
    File archivoLog = new File("src\\main\\java\\com\\eep\\EEP_Consulting\\Repository\\Log_errores.txt");
    ArrayList <Camionero> datos = new ArrayList();

    @Autowired
    @Qualifier("TrazasComponent")
    private TrazasComponent trazasComponent;

    @Override
    public List<Camionero> ListarCamioneros() {
        List<Camionero> camionerolist = new ArrayList();
        FileReader fr = null;
        BufferedReader br = null;
        String contenido;
        try {
            fr = new FileReader(archivoCamioneros);
            br = new BufferedReader(fr);
            while ((contenido = br.readLine()) != null) {
                String[] camioneros = contenido.split("#");
                Camionero test = new Camionero(Integer.parseInt(camioneros[0]), camioneros[1], camioneros[2], camioneros[3],
                        camioneros[4], camioneros[5], camioneros[6], camioneros[7], camioneros[8], camioneros[9]);
                camionerolist.add(test);
            }
        } catch (FileNotFoundException e) {
            trazasComponent.errores("Listado Camioneros", "Archivo no encontrado");
        } catch (IOException e) {
            trazasComponent.errores("Listado Camioneros","Lectura del Archivo incorrecta");
        } finally {
            try {
                fr.close();
                br.close();
            } catch (IOException e) {
                trazasComponent.errores("Listado Camioneros","Error en el cierre del BufferWriter y el FileReader de la lectura de los Camioneros");
            }
        }
        trazasComponent.info("Listado Camioneros", "Listado correcto");
        return camionerolist;
    }

    @Override
    public String GuardarCamionero(ArrayList<Camionero> lista_datos) {
        BufferedWriter bw = null;
        int ultimo_id;
        int nuevo_id;
        if(ListarCamioneros().size()>0){
            ultimo_id = ListarCamioneros().get(ListarCamioneros().size() - 1).getid();
            nuevo_id = ultimo_id+1;
        }else{
            nuevo_id = 1;
        }

        lista_datos.get(0).setId(nuevo_id);
        try {
            if(lista_datos.size()>0) {
                bw = new BufferedWriter(new FileWriter(archivoCamioneros, true));
                for (int i = 0; i < lista_datos.size(); i++) {
                    lista_datos.get(i).setNumero_telefono("+34"+lista_datos.get(i).getNumero_telefono());
                    String datos = lista_datos.get(i).toString();
                    bw.write(datos + "\n");
                }
            }else{
                trazasComponent.errores("Guardado de Camioneros", "No hay ningun camionero a guardar");
            }
            try {
                bw.close();
            } catch (IOException e) {
                trazasComponent.errores("Guardado de Camioneros", "Error en el cierre del BufferWriter del guardado de los Camioneros");
            }
        } catch (IOException e) {
            trazasComponent.errores("Guardado de Camioneros","Error en el creado del BufferWriter del guardado de los Camioneros");
        }
        trazasComponent.info("Guardado de Camioneros", "Guardado Correcto");
        return "Camionero guardado con exito";
    }

    @Override
    public String GuardarCamionero_BM(ArrayList<Camionero> lista_datos) {
        BufferedWriter bw = null;
        try {
            if(lista_datos.size()>0) {
                bw = new BufferedWriter(new FileWriter(archivoCamioneros));
                for (int i = 0; i < lista_datos.size(); i++) {
                    if(!lista_datos.get(i).getNumero_telefono().substring(0,3).equals("+34")){
                        lista_datos.get(i).setNumero_telefono("+34"+lista_datos.get(i).getNumero_telefono());
                    }
                    String datos = lista_datos.get(i).toString();
                    bw.write(datos + "\n");
                }
            }else{
                trazasComponent.errores("Guardado de Camioneros en Borrado o Modificacion", "No hay ningun camionero a guardar");
            }
            bw.close();
        } catch (IOException e) {
            trazasComponent.errores("Guardado de Camioneros en Borrado o Modificacion","Error en el creado del BufferWriter del guardado de los Camioneros");
        }
        trazasComponent.info("Guardado de Camioneros en Borrado o Modificacion", "Guardado correcto");
        return "Camionero guardado con exito";
    }

    @Override
    public String BajaCamioneros(String nombre, String apellidos) {
        BufferedWriter bw;
        String mensaje = null;
        datos = (ArrayList<Camionero>) this.ListarCamioneros();
        for (int i = 0; i < datos.size(); i++){
            if(datos.get(i).getNombre().equals(nombre)){
                datos.remove(i);
                mensaje = "Camionero dado de baja";
                break;
            }else{
                mensaje = "No se ha encontrado ningun camionero con ese nombre.";
            }
        }
        this.GuardarCamionero_BM(datos);
        trazasComponent.info("Baja de Camioneros", "Camionero dado de baja correctamente");
        return mensaje;
    }

    @Override
    public String BajaCamionerosId(int id){
        BufferedWriter bw;
        String mensaje = null;
        datos = (ArrayList<Camionero>) this.ListarCamioneros();
        for (int i = 0; i < datos.size(); i++){
            if(datos.get(i).getid() == id){
                datos.remove(i);
                mensaje = "Camionero dado de baja";
                break;
            }else {
                mensaje = "No se ha encontrado ningun camionero con ese nombre.";
            }
        }
        this.GuardarCamionero_BM(datos);
        trazasComponent.info("Baja de Camioneros", "Camionero dado de baja correctamente");
        return mensaje;
    }

    @Override
    public String ModificacionCamioneros(Camionero nombre, Camionero modificado) {
        this.datos = (ArrayList<Camionero>) this.ListarCamioneros();
        Camionero antiguo = new Camionero();
        String mensaje = null;
        for (int i = 0; i < datos.size(); i++){
            if(datos.get(i).getid() == nombre.getid()){
                antiguo = datos.get(i);
                datos.get(i).setNombre(modificado.getNombre());
                datos.get(i).setApellidos(modificado.getApellidos());
                datos.get(i).setCorreo_electronico(modificado.getCorreo_electronico());
                datos.get(i).setNumero_telefono(modificado.getNumero_telefono());
                datos.get(i).setFecha_nacimiento(modificado.getFecha_nacimiento());
                datos.get(i).setGenero(modificado.getGenero());
                datos.get(i).setTransporte(modificado.getTransporte());
                datos.get(i).setComentarios(modificado.getComentarios());
                datos.get(i).setContratado(modificado.getContratado());
                mensaje = "Camionero modificado correctamente";
                break;
            }else{
                mensaje = "No se ha encontrado ningun camionero";
                trazasComponent.errores("Modificacion de Camioneros", "No se encontro ningun camionero a modificar");
            }
        }
        this.GuardarCamionero_BM(datos);
        trazasComponent.info("Modificacion de Camioneros", "Camionero modificado correctamente " + antiguo.toStringMod());
        return mensaje;
    }

    @Override
    public List<Camionero> BusquedaCamionerosrepes(String nombre, String apellidos) {
        List<Camionero> repes = new ArrayList();
        BufferedWriter bw;
        datos = (ArrayList<Camionero>) this.ListarCamioneros();
        for (int i = 0; i < datos.size(); i++){
            if(datos.get(i).getNombre().equals(nombre) || datos.get(i).getApellidos().equals(apellidos)){
                repes.add(datos.get(i));
            }
        }
        trazasComponent.info("Busqueda de Camioneros", "Camioneros encontrado");
        return repes;
    }

    @Override
    public List<Registro> RegistroOperaciones() {
        List<Registro> ListaLog = new ArrayList();
        FileReader fr = null;
        BufferedReader br = null;
        String contenido;
        try {
            fr = new FileReader(archivoLog);
            br = new BufferedReader(fr);
            while ((contenido = br.readLine()) != null) {
                String[] logs = contenido.split("#");
                Registro log = new Registro(logs[0], logs[1], logs[2]);
                ListaLog.add(log);
            }
        } catch (FileNotFoundException e) {
            trazasComponent.errores("Registro de Operaciones","Archivo no encontrado");
        } catch (IOException e) {
            trazasComponent.errores("Registro de Operaciones","Lectura del Archivo incorrecta");
        } finally {
            try {
                fr.close();
                br.close();
            } catch (IOException e) {
                trazasComponent.errores("Registro de Operaciones","Error en el cierre del BufferWriter y el FileReader de la lectura del registro");
            }
        }
        trazasComponent.info("Registro de Operaciones", "Registro de Operacion obtenido correctamente");
        return ListaLog;
    }
}
