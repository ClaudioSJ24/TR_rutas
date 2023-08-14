package com.sanchez.app.rutas.services;

import com.sanchez.app.rutas.models.Direccion;

public interface IDireccionesService extends IService<Direccion>{

    Long guardarReturnId(Direccion direccion);
}
