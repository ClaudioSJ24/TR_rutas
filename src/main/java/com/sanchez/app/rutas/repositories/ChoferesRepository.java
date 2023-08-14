package com.sanchez.app.rutas.repositories;

import com.sanchez.app.rutas.models.Chofer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChoferesRepository implements IRepository<Chofer> {

    private Connection conn;

    public ChoferesRepository(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Chofer> listar() throws SQLException {
        List<Chofer> choferes = new ArrayList<>();
        try (Statement stmt = conn.createStatement();

             //ResultSet ---> Almacena el resultado de una consulta (Query)
             ResultSet rs = stmt.executeQuery("SELECT * FROM CHOFERES")){
            while (rs.next()){//recorre cada fila de la tabla almacenada en  ResultSet
                Chofer a = this.getChofer(rs); //agraga la fila almacenada en rs al objeto  "a" chofer
                choferes.add(a);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return choferes;
    }
    @Override
    public Chofer getBy(Long id) throws SQLException {

        Chofer chofer = null;
        try (PreparedStatement stmt =
                     conn.prepareStatement(("SELECT * FROM choferes Where ID_CHOFER = ?"))){
            stmt.setLong(1, id);
            try (ResultSet rs =stmt.executeQuery()){

                if(rs.next()){
                    chofer = this.getChofer(rs);
                }

            }
        }
        return chofer;
    }

    @Override
    public void guardar(Chofer chofer) throws SQLException {

        String sql = "";

        if (chofer.getId() != null && chofer.getId() > 0) {

            sql = "update choferes set nombre=?, ap_paterno=?, " +
                    "ap_materno=?, licencia=?, telefono=?, " +
                    "fecha_nacimiento=?, disponibilidad=?" +
                    "where id_chofer=?";


        }else{

            sql = "insert into choferes (id_chofer, nombre, " +
                    "ap_paterno, ap_materno, licencia, telefono, "+
                    "fecha_nacimiento, disponibilidad) " +
                    "values(SEQUENCE1.NEXTVAL,?,?,?,?,?,?,?)";
        }

        try (PreparedStatement stmt = conn.prepareStatement(sql)){

            if (chofer.getId() != null && chofer.getId() > 0){

                stmt.setString(1, chofer.getNombre());
                stmt.setString(2, chofer.getApMaterno());
                stmt.setString(3, chofer.getApMaterno());
                stmt.setString(4, chofer.getLicencia());
                stmt.setString(5, chofer.getTelefono());
                stmt.setDate(6, Date.valueOf(
                        chofer.getFechaNacimiento()));

                stmt.setInt(7, chofer.getDisponibilidad()? 1: 0);
                stmt.setLong(8, chofer.getId());
            }else{

                stmt.setString(1, chofer.getNombre());
                stmt.setString(2, chofer.getApMaterno());
                stmt.setString(3, chofer.getApMaterno());
                stmt.setString(4, chofer.getLicencia());
                stmt.setString(5, chofer.getTelefono());
                stmt.setDate(6, Date.valueOf(
                        chofer.getFechaNacimiento()));

                stmt.setInt(7, chofer.getDisponibilidad()? 1: 0);


            }

            stmt.executeUpdate();


        }


    }

    @Override
    public void eliminar(Long id) throws SQLException {

        String sql = "delete from choferes where id_chofer=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setLong(1, id);
            stmt.executeUpdate();

        }

    }

    //maper/transformar un renglon/fila/registro/row en un objeto de tipo chofer
    private Chofer getChofer(ResultSet rs) throws SQLException{
        Chofer a = new Chofer();
        a.setId(rs.getLong("ID_CHOFER"));
        a.setNombre(rs.getString("NOMBRE"));
        a.setApPaterno(rs.getString("AP_PATERNO"));
        a.setApMaterno(rs.getString("AP_MATERNO"));
        a.setLicencia(rs.getString("LICENCIA"));
        a.setTelefono(rs.getString("TELEFONO"));
        a.setFechaNacimiento(rs.getDate("FECHA_NACIMIENTO").toLocalDate());
        a.setDisponibilidad(rs.getBoolean("DISPONIBILIDAD"));
        return a;
    }
}
