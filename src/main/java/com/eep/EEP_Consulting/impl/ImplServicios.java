package com.eep.EEP_Consulting.impl;

import com.eep.EEP_Consulting.Component.TrazasComponent;
import com.eep.EEP_Consulting.Model.Camionero;
import com.eep.EEP_Consulting.Service.DatosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service("ServiciosDatos")
public class ImplServicios implements DatosService {
    File archivo = new File("src\\main\\java\\com\\eep\\EEP_Consulting\\Repository\\Camioneros.txt");

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
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            while ((contenido = br.readLine()) != null) {
                String[] camioneros = contenido.split("#");
                Camionero test = new Camionero(camioneros[0], camioneros[1], camioneros[2],
                        camioneros[3], camioneros[4], camioneros[5], camioneros[6], camioneros[7], camioneros[8]);
                camionerolist.add(test);
            }
        } catch (FileNotFoundException e) {
            trazasComponent.errores("Archivo no encontrado");
        } catch (IOException e) {
            trazasComponent.errores("Lectura del Archivo incorrecta");
        } finally {
            try {
                fr.close();
                br.close();
            } catch (IOException e) {
                trazasComponent.errores("Error en el cierre del BufferWriter y el FileReader de la lectura de los Camioneros");
            }
        }
        trazasComponent.test(camionerolist);
        return camionerolist;
    }

    //volver a pensar la forma de hacer el guardado, borrado y modificacion de registros utilizando un array
//    @Override
//    public String GuardarCamionero(Camionero camionero) {
//        try {
//            BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true));
//            String texto = camionero.toString();
//            bw.write(texto + "\n");
//            try {
//                bw.close();
//            } catch (IOException e) {
//                trazasComponent.errores("Error en el cierre del BufferWriter del guardado de los Camioneros");
//            }
//        } catch (IOException e) {
//            trazasComponent.errores("Error en el creado del BufferWriter del guardado de los Camioneros");
//        }
//        return "Camionero Guardado";
//    }
//
//    @Override
//    public String BajaCamioneros(Camionero nombre) {
//        FileReader fr = null;
//        BufferedReader br = null;
//        String contenido;
//        String lineToRemove = "";
//        File outputFile = new File(archivo.getAbsolutePath()+".temp");
//
//        try {
//            fr = new FileReader(archivo);
//            br = new BufferedReader(fr);
//            while ((contenido = br.readLine()) != null) {
//                String[] camioneros = contenido.split("#");
//                Camionero test = new Camionero(camioneros[0], camioneros[1], camioneros[2],
//                        camioneros[3], camioneros[4], camioneros[5], camioneros[6], camioneros[7], camioneros[8]);
//                if(test.getNombre().equals(nombre.getNombre())){
//                    lineToRemove = test.toString();
//                    break;
//                }
//            }
//        } catch (FileNotFoundException e) {
//            trazasComponent.errores("Archivo no encontrado");
//        } catch (IOException e) {
//            trazasComponent.errores("Lectura del Archivo incorrecta");
//        } finally {
//            try {
//                fr.close();
//                br.close();
//            } catch (IOException e) {
//                trazasComponent.errores("Error en el cierre del BufferWriter y el FileReader del borrado de Camioneros");
//            }
//        }
//
//        try {
//            BufferedReader reader = new BufferedReader(new FileReader(archivo));
//            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
//            String currentLine;
//            while((currentLine = reader.readLine()) != null) {
//                if(currentLine.trim().equals(lineToRemove)){
//                    continue;
//                }
//                writer.write(currentLine + System.getProperty("#") + "\n");
//            }
//            writer.close();
//            reader.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        if (archivo.delete()) {
//            trazasComponent.info("El fichero ha sido borrado satisfactoriamente");
//        }else {
//            trazasComponent.errores("El fichero no puede ser borrado");
//        }
//
//        if(outputFile.renameTo(new File(archivo.getAbsolutePath()))) {
//            trazasComponent.info("El fichero a sido renombrado satisfactoriamente");
//        }else{
//            trazasComponent.errores("El fichero no a sido renombrado satisfactoriamente");
//        }
//        return "Camionero dado de baja";
//}
//
//    @Override
//    public String ModificacionCamioneros(Camionero busqueda, Camionero modificado) {
//        Camionero mods = new Camionero();
//        FileReader fr = null;
//        BufferedReader br = null;
//        String contenido;
//        File outputFile = new File(archivo.getAbsolutePath()+".temp");
//
//        try {
//            fr = new FileReader(archivo);
//            br = new BufferedReader(fr);
//            while ((contenido = br.readLine()) != null) {
//                String[] camioneros = contenido.split("#");
//                Camionero test = new Camionero(camioneros[0], camioneros[1], camioneros[2],
//                        camioneros[3], camioneros[4], camioneros[5], camioneros[6], camioneros[7], camioneros[8]);
//                if(test.getNombre().equals(busqueda.getNombre())){
//                    mods = test;
//                    break;
//                }
//            }
//        } catch (FileNotFoundException e) {
//            trazasComponent.errores("Archivo no encontrado");
//        } catch (IOException e) {
//            trazasComponent.errores("Lectura del Archivo incorrecta");
//        } finally {
//            try {
//                fr.close();
//                br.close();
//            } catch (IOException e) {
//                trazasComponent.errores("Error en el cierre del BufferWriter y el FileReader del borrado de Camioneros");
//            }
//        }
//
//        mods = modificado;
//
//        try {
//            BufferedReader reader = new BufferedReader(new FileReader(archivo));
//            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
//            String currentLine;
//            while((currentLine = reader.readLine()) != null) {
//                writer.write(currentLine + System.getProperty("#")+"\n");
//            }
//            writer.close();
//            reader.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        if (archivo.delete()) {
//            trazasComponent.info("El fichero ha sido borrado satisfactoriamente");
//        }else {
//            trazasComponent.errores("El fichero no puede ser borrado");
//        }
//
//        if(outputFile.renameTo(new File(archivo.getAbsolutePath()))) {
//            trazasComponent.info("El fichero a sido renombrado satisfactoriamente");
//        }else{
//            trazasComponent.errores("El fichero no a sido renombrado satisfactoriamente");
//        }
//
//        return "Camionero Modificado";
//    }


}
