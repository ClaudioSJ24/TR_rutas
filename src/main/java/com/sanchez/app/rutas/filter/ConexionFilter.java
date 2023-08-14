package com.sanchez.app.rutas.filter;

import com.sanchez.app.rutas.utils.ConexionBD;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;
import java.sql.Connection;

//Decorador que permite cualquier URL en la aplicación
@WebFilter("/*")
public class ConexionFilter implements Filter {

    private Connection getConnection(){
        ConexionBD c = new ConexionBD();
        return c.getInstance();
    }

    //En este metodo llega la peticion echa por el usuario al hacer enter (url)
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //La peticion que envia el usuario servletRequest
        //La respuesta del servidor al usuario servletResponse
        Connection conn = this.getConnection();
        servletRequest.setAttribute("conn", conn);
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        }catch (IOException e){
            throw new RuntimeException(e);
        }catch (ServletException e){
            throw new RuntimeException(e);
        }
    }
}

