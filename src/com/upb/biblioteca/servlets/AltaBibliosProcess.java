package com.upb.biblioteca.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.upb.biblioteca.database.DatabaseManager;

/**
 * Servlet implementation class AltaBibliosProcess
 */
@WebServlet("/AltaBibliosProcess")
public class AltaBibliosProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombreBiblio = request.getParameter("nombreB");
		String passwordBiblio = request.getParameter("passwordB");
		String usuarioBiblio = request.getParameter("usuarioB");

		if (request.getParameter("aceptarAltaB") != null) {
			boolean correcto = DatabaseManager.agregarBiblio(nombreBiblio, passwordBiblio, usuarioBiblio);
			if (correcto) {
				request.getSession().setAttribute("ResultadoAltaBiblio", "El bibliotecario fue agregado");
			} else {
				request.getSession().setAttribute("ResultadoAltaBiblio", "ERROR: El bibliotecario no fue agregado");
			}
			response.sendRedirect("JSP/ResultadoAltaBiblio.jsp");
		} else if (request.getParameter("cancelarAltaB") != null) {
			response.sendRedirect("HTML/Biblios.html");
		} else if (request.getParameter("volverMenu") != null) {
			response.sendRedirect("HTML/Menu.html");
		}
	}

}
