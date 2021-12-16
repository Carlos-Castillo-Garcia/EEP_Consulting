package com.eep.EEP_Consulting.Service;

import com.eep.EEP_Consulting.Model.Camionero;

import java.io.FileNotFoundException;
import java.util.List;

public interface DatosService {
    public abstract List<Camionero> ListarCamioneros() throws FileNotFoundException;
    public abstract Camionero GuardarCamionero();
}
