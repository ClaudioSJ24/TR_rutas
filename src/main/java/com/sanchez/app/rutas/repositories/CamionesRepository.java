package com.sanchez.app.rutas.repositories;

import com.sanchez.app.rutas.models.Camion;
import com.sanchez.app.rutas.models.Chofer;
import com.sanchez.app.rutas.models.enums.Marcas;
import com.sanchez.app.rutas.models.enums.Tipos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CamionesRepository implements IRepository<Camion>{

    private Connection conn;

    public CamionesRepository(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Camion> listar() throws SQLException {
        List<Camion> camiones = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM CAMIONES")){
             while (rs.next()){

                 Camion c = this.getCamion(rs);
                 camiones.add(c);

             }

        }
        catch(SQLException e){

            throw  new RuntimeException(e);

        }

        return camiones;

    }

    //maper/transformar un renglon/fila/registro/row en un objeto de tipo chofer
    private Camion getCamion(ResultSet rs) throws SQLException{

        /*
        ID_CAMION NUMBER NOT NULL,
    MATRICULA VARCHAR2(45),
    TIPO_CAMION VARCHAR2(45),
    MODELO NUMBER,
    MARCA VARCHAR2 (45),
    CAPACIDAD NUMBER,
    KILOMETRAJE FLOAT,
    DISPONIBILIDAD NUMBER,
         */

        Camion c = new Camion();
        c.setId(rs.getLong("ID_CAMION"));
        c.setMatricula(rs.getString("MATRICULA"));
        c.setTipoCamion(Tipos.getFromString(rs.getString(("TIPO_CAMION"))));
        c.setModelo(rs.getInt("MODELO"));
        c.setMarca(Marcas.getFromString(rs.getString("MARCA")));
        c.setCapacidad(rs.getInt("CAPACIDAD"));
        c.setKilometraje(rs.getDouble("KILOMETRAJE"));
        c.setDisponibilidad(rs.getBoolean("DISPONIBILIDAD"));

        return c;


    }

    @Override
    public Camion getBy(Long id) throws SQLException {
        Camion camion = null;
        try (PreparedStatement stmt =
                     conn.prepareStatement(("SELECT * FROM camiones Where ID_CAMION = ?"))){
            stmt.setLong(1, id);
            try (ResultSet rs =stmt.executeQuery()){

                if(rs.next()){
                    camion = this.getCamion(rs);
                }

            }
        }
        return camion;
    }

    @Override
    public void guardar(Camion camion) throws SQLException {

        String sql = "";
        if (camion.getId() != null && camion.getId() > 0) {

            sql = "update camiones set matricula=?, tipo_camion=?, modelo=?, marca=?, capacidad=?, kilometraje=?, disponibilidad=? " +

                    "where id_camion=?";

        }else{
            sql = "insert into camiones (id_camion, matricula, " +
                    "tipo_camion, modelo, marca, " +
                    "capacidad, kilometraje, disponibilidad) " +
                    "values(SEQUENCE2.NEXTVAL,?,?,?,?,?,?,?)";
        }

        try (PreparedStatement stmt = conn.prepareStatement(sql)){

            if (camion.getId() != null && camion.getId() > 0) {

                stmt.setString(1, camion.getMatricula());
                stmt.setString(2, camion.getTipoCamion().getDescripcion());
                stmt.setInt(3,camion.getModelo());
                stmt.setString(4, camion.getMarca().getDescripcion());
                stmt.setLong(5, camion.getCapacidad());
                stmt.setDouble(6, camion.getKilometraje());
                stmt.setInt(7,camion.getDisponibilidad() ? 1: 0);
                stmt.setLong(8, camion.getId());


            }else{

                stmt.setString(1, camion.getMatricula());
                stmt.setString(2, camion.getTipoCamion().getDescripcion());
                stmt.setInt(3,camion.getModelo());
                stmt.setString(4, camion.getMarca().getDescripcion());
                stmt.setLong(5, camion.getCapacidad());
                stmt.setDouble(6, camion.getKilometraje());
                stmt.setInt(7,camion.getDisponibilidad()? 1: 0);
            }

            stmt.executeUpdate();

        }

    }

    @Override
    public void eliminar(Long id) throws SQLException {

        String sql = "delete from camiones where id_camion=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setLong(1, id);
            stmt.executeUpdate();

        }

    }
}
