package com.upb.biblioteca.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.upb.biblioteca.database.DatabaseManager;

/**
 * Servlet implementation class BajaBibliosProcess
 */
@WebServlet("/BajaBibliosProcess")
public class BajaBibliosProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usuarioBiblio = request.getParameter("usuarioBajaB");

		if (request.getParameter("aceptarBajaB") != null) {
			boolean correcto = DatabaseManager.biblioValido(usuarioBiblio);
			if (correcto) {
				DatabaseManager.eliminarBiblio(usuarioBiblio);
				request.getSession().setAttribute("ResultadoBajaBiblio", "El bibliotecario fue eliminado");
			} else {
				request.getSession().setAttribute("ResultadoBajaBiblio", "ERROR: El bibliotecario no fue eliminado");
			}
			response.sendRedirect("JSP/ResultadoBajaBiblio.jsp");
		} else if (request.getParameter("cancelarBajaB") != null) {
			response.sendRedirect("HTML/Biblios.html");
		} else if (request.getParameter("volverMenu") != null) {
			response.sendRedirect("HTML/Menu.html");
		}
	}

}
