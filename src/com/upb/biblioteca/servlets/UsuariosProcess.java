package com.upb.biblioteca.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UsuariosProcess
 */
@WebServlet("/UsuariosProcess")
public class UsuariosProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("altaUsuarios") != null) {
			response.sendRedirect("HTML/AltaUsuarios.html");
		} else if (request.getParameter("bajaUsuarios") != null) {
			response.sendRedirect("HTML/BajaUsuarios.html");
		} else if (request.getParameter("cambioUsuarios") != null) {
			response.sendRedirect("HTML/CambioUsuarios.html");
		}
	}

}
