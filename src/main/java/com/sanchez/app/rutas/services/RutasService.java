package com.sanchez.app.rutas.services;

import com.sanchez.app.rutas.models.Camion;
import com.sanchez.app.rutas.models.Chofer;
import com.sanchez.app.rutas.models.Ruta;
import com.sanchez.app.rutas.repositories.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class RutasService implements IRutasService{


    private IRepository<Chofer> choferesRepository;
    private  IRepository<Camion> camionesRepository;
    private IRutasRepository rutasRepository;

    public RutasService(Connection conn){

        this.choferesRepository = new ChoferesRepository(conn);
        this.camionesRepository = new CamionesRepository(conn);
        this.rutasRepository = new RutasRepository(conn);
    }






    @Override
    public List<Camion> listarCamiones() {
        try {
            return camionesRepository.listar();
        } catch (SQLException e){
            throw  new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<Chofer> listarChoferes() {
        try {
            return choferesRepository.listar();
        } catch (SQLException e){
            throw  new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Long guardarReturnId(Ruta ruta) {
        try {
            return rutasRepository.guardarReturnId(ruta);
        } catch (SQLException e){
            throw  new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<Ruta> listar() {
        return null;
    }

    @Override
    public Optional<Ruta> getBy(Long id) {
        return Optional.empty();
    }

    @Override
    public void guardar(Ruta ruta) {

    }

    @Override
    public void eliminar(Long id) {

    }
}
