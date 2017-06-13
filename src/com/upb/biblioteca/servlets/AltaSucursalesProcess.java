package com.upb.biblioteca.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.upb.biblioteca.database.DatabaseManager;

/**
 * Servlet implementation class AltaSucursalesProcess
 */
@WebServlet("/AltaSucursalesProcess")
public class AltaSucursalesProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String direccionSucursal = request.getParameter("direccionS");

		if (request.getParameter("aceptarAltaS") != null) {
			boolean correcto = DatabaseManager.agregarSucursal(direccionSucursal);
			if (correcto) {
				request.getSession().setAttribute("ResultadoAltaSucursal", "La sucursal fue agregada");
			} else {
				request.getSession().setAttribute("ResultadoAlta", "ERROR: La sucursal no fue agregada");
			}
			response.sendRedirect("JSP/ResultadoAltaSucursal.jsp");
		} else if (request.getParameter("cancelarAltaS") != null) {
			response.sendRedirect("HTML/Sucursales.html");
		} else if (request.getParameter("volverMenu") != null) {
			response.sendRedirect("HTML/Menu.html");
		}	}

}
