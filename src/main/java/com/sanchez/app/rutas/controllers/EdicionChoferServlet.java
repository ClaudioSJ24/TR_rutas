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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet("/choferes/editar")
public class EdicionChoferServlet extends HttpServlet {

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

                chofer = opChofer.get();
                req.setAttribute("chofer", chofer);
                getServletContext().getRequestDispatcher("/edicionChofer.jsp").forward(req,resp);

            } else {

                resp.sendError(HttpServletResponse.SC_NOT_FOUND,
                        "nO EXISTE EL CHOFER EN LA BASE DE DATOS");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND,
                    "EL ID ES NULO EN LA URL");

        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        IService<Chofer> service = new ChoferesService(conn);

        String nombre = req.getParameter("nombre");
        String apPaterno = req.getParameter("apPaterno");
        String apMaterno = req.getParameter("apMaterno");
        String licencia = req.getParameter("licencia");
        String telefono = req.getParameter("telefono");
        String fechaNacimiento = req.getParameter("fechaNacimiento"); // 08/09/6534
        LocalDate fecha;

        try {
            fecha = LocalDate.parse(fechaNacimiento, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }catch (DateTimeParseException e){

            fecha = null;

        }

        String checkbox [] ; //{}

        checkbox = req.getParameterValues("disponibilidad");

        Boolean habilitar;

        if (checkbox != null) {

            habilitar = true;

        }else{
            habilitar = false;
        }

        Map<String, String> errores = new HashMap<>();

        if (nombre == null || nombre.isBlank()) {

            errores.put("nombre", "el nombre es requerido");

        }

        if (apPaterno == null || apPaterno.isBlank()) {

            errores.put("apPaterno", "el apPaterno es requerido");

        }

        if (apMaterno == null || apMaterno.isBlank()) {

            errores.put("apMaterno", "el apMaterno es requerido");

        }

        if (licencia == null || licencia.isBlank()) {

            errores.put("licencia", "el licencia es requerido");

        }

        if (telefono == null || telefono.isBlank()) {

            errores.put("telefono", "el telefono es requerido");

        }

        if (fechaNacimiento == null || fechaNacimiento.isBlank()) {

            errores.put("fechaNacimiento", "el fechaNacimiento es requerido");

        }


        long id;
        id = Long.parseLong(req.getParameter("id"));
        Chofer chofer = new Chofer();
        chofer.setId(id);
        chofer.setNombre(nombre);
        chofer.setApPaterno(apPaterno);
        chofer.setApMaterno(apMaterno);
        chofer.setLicencia(licencia);
        chofer.setTelefono(telefono);
        chofer.setFechaNacimiento(fecha);
        chofer.setDisponibilidad(habilitar);


        if (errores.isEmpty()) {

            service.guardar(chofer);
            resp.sendRedirect(req.getContextPath()+"/choferes/listar");

        }else {

            req.setAttribute("errores",errores);
            getServletContext().getRequestDispatcher("/edicionChofer.jsp").forward(req, resp);
        }

    }
}
