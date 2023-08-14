package com.sanchez.app.rutas.services;

import com.sanchez.app.rutas.models.Direccion;
import com.sanchez.app.rutas.repositories.DireccionesRepository;
import com.sanchez.app.rutas.repositories.IDireccionesRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class DireccionesService implements IDireccionesService{


    private IDireccionesRepository direccionesRepository;

    public DireccionesService(Connection conn) {
        this.direccionesRepository = new DireccionesRepository(conn);
    }

    @Override
    public Long guardarReturnId(Direccion direccion) {
        try {
            return direccionesRepository.guardarReturnId(direccion);
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<Direccion> listar() {
        return null;
    }

    @Override
    public Optional<Direccion> getBy(Long id) {
        return Optional.empty();
    }

    @Override
    public void guardar(Direccion direccion) {

    }

    @Override
    public void eliminar(Long id) {

    }
}
