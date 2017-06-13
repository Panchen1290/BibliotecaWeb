package com.upb.biblioteca.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.upb.biblioteca.database.DatabaseManager;

/**
 * Servlet implementation class BajaLibrosProcess
 */
@WebServlet("/BajaLibrosProcess")
public class BajaLibrosProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idLibro = request.getParameter("idBajaL");

		if (request.getParameter("aceptarBajaL") != null) {
			boolean correcto = DatabaseManager.libroValido(idLibro);
			if (correcto) {
				DatabaseManager.eliminarLibro(idLibro);
				request.getSession().setAttribute("ResultadoBaja", "El libro fue eliminado");
			} else {
				request.getSession().setAttribute("ResultadoBaja", "ERROR: El libro no fue eliminado");
			}
			response.sendRedirect("JSP/ResultadoBaja.jsp");
		} else if (request.getParameter("cancelarBajaL") != null) {
			response.sendRedirect("HTML/Libros.html");
		} else if (request.getParameter("volverMenu") != null) {
			response.sendRedirect("HTML/Menu.html");
		}
	}

}
