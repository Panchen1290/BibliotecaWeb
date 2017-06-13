package com.upb.biblioteca.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LibrosProcess
 */
@WebServlet("/LibrosProcess")
public class LibrosProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("altaLibros") != null) {
			response.sendRedirect("HTML/AltaLibros.html");
		} else if (request.getParameter("bajaLibros") != null) {
			response.sendRedirect("HTML/BajaLibros.html");
		} else if (request.getParameter("cambioLibros") != null) {
			response.sendRedirect("HTML/CambioLibros.html");
		}
	}

}
