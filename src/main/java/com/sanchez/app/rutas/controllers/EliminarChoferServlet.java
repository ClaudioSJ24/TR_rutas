package com.sanchez.app.rutas.controllers;

import com.sanchez.app.rutas.models.Chofer;
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

@WebServlet("/choferes/eliminar")
public class EliminarChoferServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        IService<Chofer> service = new ChoferesService(conn);
        long id;

        try {
            id = Long.parseLong(req.getParameter("id"));

        } catch (NumberFormatException e) {
            id = 0L;
        }

        Chofer chofer = new Chofer();

        if (id > 0) {
            Optional<Chofer> opChofer = service.getBy(id);
            if (opChofer.isPresent()) {
                service.eliminar(id);
                resp.sendRedirect(req.getContextPath() + "/choferes/listar");
            } else {

                resp.sendError(HttpServletResponse.SC_NOT_FOUND,
                        "nO EXISTE EL CHOFER EN LA BASE DE DATOS");
            }
        }else

        {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND,
                    "EL ID ES NULO EN LA URL");

        }
    }
}

