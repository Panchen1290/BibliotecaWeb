package com.upb.biblioteca.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.upb.biblioteca.database.DatabaseManager;

/**
 * Servlet implementation class AltaUsuariosProcess
 */
@WebServlet("/AltaUsuariosProcess")
public class AltaUsuariosProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombreUsuario = request.getParameter("nombreU");

		if (request.getParameter("aceptarAltaU") != null) {
			boolean correcto = DatabaseManager.agregarUsuario(nombreUsuario);
			if (correcto) {
				request.getSession().setAttribute("ResultadoAltaUsuarios", "El usuario fue agregado");
			} else {
				request.getSession().setAttribute("ResultadoAltaUsuarios", "ERROR: El usuario no fue agregado");
			}
			response.sendRedirect("JSP/ResultadoAltaUsuario.jsp");
		} else if (request.getParameter("cancelarAltaU") != null) {
			response.sendRedirect("HTML/Usuarios.html");
		} else if (request.getParameter("volverMenu") != null) {
			response.sendRedirect("HTML/Menu.html");
		}
	}

}
