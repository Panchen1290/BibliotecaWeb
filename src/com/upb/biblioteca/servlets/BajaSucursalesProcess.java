package com.upb.biblioteca.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.upb.biblioteca.database.DatabaseManager;

/**
 * Servlet implementation class BajaSucursalesProcess
 */
@WebServlet("/BajaSucursalesProcess")
public class BajaSucursalesProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idSucursal = request.getParameter("idBajaS");

		if (request.getParameter("aceptarBajaS") != null) {
			boolean correcto = DatabaseManager.sucursalValida(idSucursal);
			if (correcto) {
				DatabaseManager.eliminarSucursal(idSucursal);
				request.getSession().setAttribute("ResultadoBajaSucursal", "La sucursal fue eliminada");
			} else {
				request.getSession().setAttribute("ResultadoBajaSucursal", "ERROR: La sucursal no fue eliminada");
			}
			response.sendRedirect("JSP/ResultadoBajaSucursal.jsp");
		} else if (request.getParameter("cancelarBajaS") != null) {
			response.sendRedirect("HTML/Sucursales.html");
		} else if (request.getParameter("volverMenu") != null) {
			response.sendRedirect("HTML/Menu.html");
		}
	}

}
