package com.eep.EEP_Consulting.Component;

import com.eep.EEP_Consulting.Model.Registro;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component("TrazasComponent")
public class TrazasComponent {
    private static final Log LOG = LogFactory.getLog(TrazasComponent.class);
    private static final File archivoLog = new File("src\\main\\java\\com\\eep\\EEP_Consulting\\Repository\\Log_errores.txt");
    Registro texto = null;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    public String errores(String operacion, String log){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(archivoLog, true));
            texto = new Registro(dtf.format(LocalDateTime.now()), operacion, log);
            bw.write(texto + "\n");
            try{
                bw.close();
            } catch (IOException e) {
                LOG.error("Error en el cierre del BufferWrites de los log");
            }
        } catch (IOException e) {
            LOG.error("Error en la creacion del BufferWriter de los log");
        }
        LOG.error(texto.toString());
        return texto.toString();
    }

    public String info(String operacion, String log){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(archivoLog, true));
            texto = new Registro(dtf.format(LocalDateTime.now()), operacion, log);
            bw.write(texto + "\n");
            try{
                bw.close();
            } catch (IOException e) {
                LOG.info("Error en el cierre del BufferWrites de los log");
            }
        } catch (IOException e) {
            LOG.info("Error en la creacion del BufferWriter de los log");
        }
        LOG.info(texto.toString());
        return texto.toString();
    }
}
