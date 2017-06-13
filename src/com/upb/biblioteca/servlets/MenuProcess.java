package com.upb.biblioteca.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MenuProcess
 */
@WebServlet("/MenuProcess")
public class MenuProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("libros") != null) {
			response.sendRedirect("HTML/Libros.html");
		} else if (request.getParameter("usuarios") != null) {
			response.sendRedirect("HTML/Usuarios.html");			
		} else if (request.getParameter("sucursales") != null) {
			response.sendRedirect("HTML/Sucursales.html");
		} else if (request.getParameter("biblios") != null) {
			response.sendRedirect("HTML/Biblios.html");
		}
	}

}
