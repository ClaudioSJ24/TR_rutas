package com.sanchez.app.rutas.services;

import com.sanchez.app.rutas.models.Camion;
import com.sanchez.app.rutas.repositories.CamionesRepository;
import com.sanchez.app.rutas.repositories.IRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CamionesService implements IService<Camion> {

    private IRepository<Camion> camionesRepository;

    public CamionesService(Connection conn) {
        camionesRepository = new CamionesRepository(conn);
    }

    @Override
    public List<Camion> listar() {
        try {
            return camionesRepository.listar();
        }catch (SQLException e){

            throw  new RuntimeException(e.getMessage(), e.getCause());

        }
    }

    @Override
    public Optional<Camion> getBy(Long id) {
        try {
            return Optional.ofNullable(camionesRepository.getBy(id));
        }
        catch (SQLException e){
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void guardar(Camion camion) {

        try {
            camionesRepository.guardar(camion);
        }
        catch (SQLException e){
            throw new RuntimeException(e.getMessage(), e.getCause());
        }

    }

    @Override
    public void eliminar(Long id) {

        try {
            camionesRepository.eliminar(id);
        }
        catch (SQLException e){
            throw new RuntimeException(e.getMessage(), e.getCause());
        }

    }
}
