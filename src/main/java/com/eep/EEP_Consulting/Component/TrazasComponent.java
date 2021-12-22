package com.eep.EEP_Consulting.Component;

import com.eep.EEP_Consulting.Model.Camionero;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Component("TrazasComponent")
public class TrazasComponent {
    private static final Log LOG = LogFactory.getLog(TrazasComponent.class);

    public void test(List<Camionero> test){
        for (int i = 0; i < test.size(); i++){
            System.out.println(test.get(i).getNombre() + test.get(i).getApellidos()+ test.get(i).getCorreo_electronio()+
                    test.get(i).getNumero_telefono()+test.get(i).getFecha_nacimiento()+ test.get(i).getGenero()+
                    test.get(i).getTransporte()+ test.get(i).getComentarios()+ test.get(i).getContratado());
        }
    }

    public void errores(String texto){
        File log = new File("src\\main\\java\\com\\eep\\EEP_Consulting\\Repository\\Log_errores.txt");
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(log, true));
            bw.write("\n"+texto);

            try{
                bw.close();
            } catch (IOException e) {
                LOG.error("Error en el cierre del BufferWrites de los log");
            }
        } catch (IOException e) {
            LOG.error("Error en la creacion del BufferWriter de los log");
        }
        LOG.error(texto);
    }

    public void info(String texto){
        File log = new File("src\\main\\java\\com\\eep\\EEP_Consulting\\Repository\\Log_errores.txt");
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(log, true));
            bw.write("\n"+texto);

            try{
                bw.close();
            } catch (IOException e) {
                LOG.info("Error en el cierre del BufferWrites de los log");
            }
        } catch (IOException e) {
            LOG.info("Error en la creacion del BufferWriter de los log");
        }
        LOG.info(texto);
    }
}
