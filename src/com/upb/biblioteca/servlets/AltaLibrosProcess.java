package com.upb.biblioteca.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.upb.biblioteca.database.DatabaseManager;

/**
 * Servlet implementation class AltaLibrosProcess
 */
@WebServlet("/AltaLibrosProcess")
public class AltaLibrosProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nombreLibro = request.getParameter("nombreL");
		String autorLibro = request.getParameter("autorL");
		String sucursalLibro = request.getParameter("sucursalL");

		if (request.getParameter("aceptarAltaL") != null) {
			boolean correcto = DatabaseManager.agregarLibro(nombreLibro, autorLibro, sucursalLibro);
			if (correcto) {
				request.getSession().setAttribute("ResultadoAlta", "El libro fue agregado");
			} else {
				request.getSession().setAttribute("ResultadoAlta", "ERROR: El libro no fue agregado");
			}
			response.sendRedirect("JSP/ResultadoAlta.jsp");
		} else if (request.getParameter("cancelarAltaL") != null) {
			response.sendRedirect("HTML/Libros.html");
		} else if (request.getParameter("volverMenu") != null) {
			response.sendRedirect("HTML/Menu.html");
		}
	}

}
