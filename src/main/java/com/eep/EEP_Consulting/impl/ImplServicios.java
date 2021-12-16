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
public class ImplServicios  implements DatosService {
    File archivo = new File("D:\\Repositorios\\EEP_Consulting\\src\\main\\java\\com\\eep\\EEP_Consulting\\Repository");

    @Autowired
    @Qualifier("TrazasComponent")
    private TrazasComponent trazasComponent;

    @Override
    public List<Camionero> ListarCamioneros() throws FileNotFoundException {
        List<Camionero> camionerolist = new ArrayList();
        FileReader fr = new FileReader(archivo);
        BufferedReader br = new BufferedReader(fr);
        String contenido;
        try {
            while ((contenido = br.readLine()) != null) {
                String[] camioneros = contenido.split("#");

                for (int i=0; i<camioneros.length; i++) {
                    Camionero test = new Camionero(camioneros[1], camioneros[2], camioneros[3],
                            camioneros[4], camioneros[5], camioneros[6], camioneros[7], camioneros[8], true);
                }
            }
        }catch (IOException e){
            System.out.println("Lectura del archivo incorrecta");
        }
        trazasComponent.test(camionerolist);
        return camionerolist;
    }

    @Override
    public Camionero GuardarCamionero() {
        return null;
    }
}
