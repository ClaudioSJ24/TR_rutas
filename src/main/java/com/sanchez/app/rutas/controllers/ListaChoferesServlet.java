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
import java.util.List;

@WebServlet("/choferes/listar")
public class ListaChoferesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Recuperar la connexion que provee el filtro
        Connection  conn= (Connection)req.getAttribute("conn");

        //Objeto de tipo servicio
        IService<Chofer> serviceI = new ChoferesService(conn);
        List<Chofer> choferes = serviceI.listar();


        /*
        for (int i = 0; i < choferes.size(); i++) {

            resp.getWriter().println("<h1>"+choferes.get(i).getId()+" -> "
                    +choferes.get(i).getNombre()+" -> "+choferes.get(i).getApPaterno()+"<h1>");

        }*/

        req.setAttribute("choferes", choferes);
        getServletContext().getRequestDispatcher("/listaChoferes.jsp").forward(req,resp);

    }
}
