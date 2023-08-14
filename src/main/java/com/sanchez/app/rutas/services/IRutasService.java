package com.sanchez.app.rutas.services;

import com.sanchez.app.rutas.models.Camion;
import com.sanchez.app.rutas.models.Chofer;
import com.sanchez.app.rutas.models.Direccion;
import com.sanchez.app.rutas.models.Ruta;

import java.util.List;

public interface IRutasService extends IService<Ruta>{

    List<Camion> listarCamiones();

    List<Chofer> listarChoferes();

    Long guardarReturnId(Ruta ruta);


}
