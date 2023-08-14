package com.sanchez.app.rutas.controllers;

import com.sanchez.app.rutas.models.Camion;
import com.sanchez.app.rutas.services.CamionesService;
import com.sanchez.app.rutas.services.IService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/camiones/listar")
public class ListaCamionesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection conn  = (Connection) req.getAttribute("conn");
        IService<Camion> serviceI = new CamionesService(conn);
        List<Camion> camiones = serviceI.listar();



       req.setAttribute("camiones", camiones);
       getServletContext().getRequestDispatcher("/listarCamiones.jsp").forward(req, resp);



    }


}
