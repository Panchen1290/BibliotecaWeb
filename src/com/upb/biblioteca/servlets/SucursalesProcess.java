package com.upb.biblioteca.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SucursalesProcess
 */
@WebServlet("/SucursalesProcess")
public class SucursalesProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("altaSucursales") != null) {
			response.sendRedirect("HTML/AltaSucursales.html");
		} else if (request.getParameter("bajaSucursales") != null) {
			response.sendRedirect("HTML/BajaSucursales.html");
		} else if (request.getParameter("cambioSucursales") != null) {
			response.sendRedirect("HTML/CambioSucursales.html");
		}
	}

}
