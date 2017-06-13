package com.upb.biblioteca.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.upb.biblioteca.database.DatabaseManager;

/**
 * Servlet implementation class BajaUsuariosProcess
 */
@WebServlet("/BajaUsuariosProcess")
public class BajaUsuariosProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idUsuario = request.getParameter("idBajaU");

		if (request.getParameter("aceptarBajaU") != null) {
			boolean correcto = DatabaseManager.usuarioValido(idUsuario);
			if (correcto) {
				DatabaseManager.eliminarUsuario(idUsuario);
				request.getSession().setAttribute("ResultadoBajaUsuario", "El usuario fue eliminado");
			} else {
				request.getSession().setAttribute("ResultadoBajaUsuario", "ERROR: El usuario no fue eliminado");
			}
			response.sendRedirect("JSP/ResultadoBajaUsuario.jsp");
		} else if (request.getParameter("cancelarBajaU") != null) {
			response.sendRedirect("HTML/Usuarios.html");
		} else if (request.getParameter("volverMenu") != null) {
			response.sendRedirect("HTML/Menu.html");
		}
	}

}
