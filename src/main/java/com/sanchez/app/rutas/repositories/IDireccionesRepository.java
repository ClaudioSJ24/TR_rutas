package com.sanchez.app.rutas.repositories;

import com.sanchez.app.rutas.models.Direccion;
import com.sanchez.app.rutas.models.Ruta;

import java.sql.SQLException;

public interface IDireccionesRepository extends IRepository<Direccion>{

    Long guardarReturnId(Direccion direccion) throws SQLException;
}
