package com.sanchez.app.rutas.controllers;

import com.sanchez.app.rutas.models.Camion;
import com.sanchez.app.rutas.models.Chofer;
import com.sanchez.app.rutas.services.CamionesService;
import com.sanchez.app.rutas.services.ChoferesService;
import com.sanchez.app.rutas.services.IService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/camiones/detalle")
public class DetalleCamionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        IService<Camion> service = new CamionesService(conn);
        long id;

        try {
            id = Long.parseLong(req.getParameter("id"));

        } catch (NumberFormatException e) {
            id = 0L;
        }

        Camion camion = new Camion();

        if (id > 0) {
            Optional<Camion> opCamion = service.getBy(id);
            if (opCamion.isPresent()) {

                camion = opCamion.get();
                req.setAttribute("camion", camion);
                getServletContext().getRequestDispatcher("/detalleCamion.jsp").forward(req,resp);

            } else {

                resp.sendError(HttpServletResponse.SC_NOT_FOUND,
                        "NO EXISTE EL CAMION EN LA BASE DE DATOS");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND,
                    "EL ID ES NULO EN LA URL");

        }


    }
}
