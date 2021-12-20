package com.eep.EEP_Consulting.Service;

import com.eep.EEP_Consulting.Model.Camionero;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface DatosService {
    public abstract List<Camionero> ListarCamioneros() throws IOException;
    public abstract String GuardarCamionero(Camionero camionero);
    public abstract String BajaCamioneros(Camionero nombre);
    public abstract String ModificacionCamioneros(Camionero nombre);
}
