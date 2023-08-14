package com.sanchez.app.rutas.controllers;

import com.sanchez.app.rutas.models.Camion;
import com.sanchez.app.rutas.models.enums.Marcas;
import com.sanchez.app.rutas.models.enums.Tipos;
import com.sanchez.app.rutas.services.CamionesService;
import com.sanchez.app.rutas.services.IService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Double.parseDouble;

@WebServlet("/camiones/alta")
public class AltaCamionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/altaCamion.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection conn = (Connection) req.getAttribute("conn");
        IService<Camion> serviceC = new CamionesService(conn);


        String matricula = req.getParameter("matricula");
        String tipoCamion = req.getParameter("tipoCamion");
        Integer modelo = Integer.parseInt(req.getParameter("modelo"));
        String marca = req.getParameter("marca");
        Integer capacidad = Integer.parseInt(req.getParameter("capacidad"));
        Double kilometraje = Double.parseDouble(req.getParameter("kilometraje"));
        String checkbox[];
        checkbox = req.getParameterValues("disponibilidad");
        Boolean habilitar;
        if(checkbox != null){
            habilitar= true;
        }else{
            habilitar = false;
        }
        Map<String, String> errores = new HashMap<>();
        if(matricula == null || matricula.isBlank()){
            errores.put("matricula", "la matricula es requerida!");
        }
        if(tipoCamion == null || tipoCamion.isBlank()){
            errores.put("tipoCamion","el tipoCamion es requerido!");
        }
        if(modelo == null){
            errores.put("modelo","el modelo es requerido!");
        }
        if(marca == null || marca.isBlank()){
            errores.put("marcas","la marca es requerida!");
        }
        if(capacidad == null){
            errores.put("capacidad","la capacidad es requerida!");
        }
        if(kilometraje == null){
            errores.put("kilometraje","el kilometraje es requerido!");
        }
        if(errores.isEmpty()){
            Camion camion = new Camion();
            camion.setId(0L);
            camion.setMatricula(matricula);
            camion.setTipoCamion(Tipos.getFromString(tipoCamion));
            camion.setModelo(modelo);
            camion.setMarca(Marcas.getFromString(marca));
            camion.setCapacidad(capacidad);
            camion.setKilometraje(kilometraje);
            camion.setDisponibilidad(habilitar);
            serviceC.guardar(camion);
            resp.sendRedirect(req.getContextPath()+"/camiones/listar");


        }else{
            req.setAttribute("errores", errores);
            getServletContext().getRequestDispatcher("/altaCamion.jsp").forward(req, resp);
        }
    }
}
