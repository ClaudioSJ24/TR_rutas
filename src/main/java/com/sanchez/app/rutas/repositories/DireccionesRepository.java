package com.sanchez.app.rutas.repositories;

import com.sanchez.app.rutas.models.Direccion;

import java.sql.*;
import java.util.List;

public class DireccionesRepository implements IDireccionesRepository{


    private Connection conn;

    public DireccionesRepository(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Long guardarReturnId(Direccion direccion) throws SQLException {
        String sql = "";

        long resultado = -1L;
        sql = "insert into direcciones (ID_DIRECION, calle, numero, colonia, " +
            "cuidad, estado, cp)" +
            "values (SEQUENCE3.NEXTVAL,?,?,?,?,?,?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql, new String[]{"ID_DIRECCION"})) {

            if (direccion.getId() != null && direccion.getId() > 0) {

                stmt.setString(1, direccion.getCalle());
                stmt.setString(2, direccion.getNumero());
                stmt.setString(3, direccion.getColonia());
                stmt.setString(4, direccion.getCiudad());
                stmt.setString(5, direccion.getEstado());
                stmt.setString(6, direccion.getCp());


                ResultSet rs = stmt.getGeneratedKeys();

                if (rs.next()) {

                    resultado = rs.getLong(1);
                }


            }

        }    return resultado;
    }

    @Override
    public List<Direccion> listar() throws SQLException {
        return null;
    }

    @Override
    public Direccion getBy(Long id) throws SQLException {
        return null;
    }

    @Override
    public void guardar(Direccion direccion) throws SQLException {

    }

    @Override
    public void eliminar(Long id) throws SQLException {

    }
}
