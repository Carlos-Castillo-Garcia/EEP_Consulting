package com.eep.EEP_Consulting.Service;

import com.eep.EEP_Consulting.Model.Camionero;
import com.eep.EEP_Consulting.Model.Registro;

import javax.print.DocFlavor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface DatosService {
    public abstract List<Camionero> ListarCamioneros() throws IOException;
    public abstract String GuardarCamionero(ArrayList<Camionero> lista_datos);
    public abstract String BajaCamioneros(String nombre, String apellidos);
    public abstract String ModificacionCamioneros(Camionero nombre, Camionero modificado);
    public abstract String GuardarCamionero_BM(ArrayList<Camionero> lista_datos);
    public abstract String BajaCamionerosId(int id);
    public abstract List<Camionero> BusquedaCamionerosrepes(String nombre, String apellidos);
    public abstract List<Registro> RegistroOperaciones();
}
