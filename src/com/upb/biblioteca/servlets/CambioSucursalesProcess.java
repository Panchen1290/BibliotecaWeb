package com.upb.biblioteca.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.upb.biblioteca.database.DataManager;
import com.upb.biblioteca.database.DatabaseManager;

/**
 * Servlet implementation class CambioSucursalesProcess
 */
@WebServlet("/CambioSucursalesProcess")
public class CambioSucursalesProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idSucursal = request.getParameter("idCambioS");
		if (DataManager.idUsuario == null) {
			DataManager.setIdUsuario(idSucursal);
		}

		if (request.getParameter("aceptarCambioS") != null) {
			boolean correcto = DatabaseManager.sucursalValida(idSucursal);
			if (correcto) {
				response.sendRedirect("HTML/DatosSucursales.html");
			} else {
				request.getSession().setAttribute("ResultadoCambioSucursal", "ERROR: La sucursal no fue cambiada");
				response.sendRedirect("JSP/ConfirmarCambioSucursal.jsp");
			}
		} else if (request.getParameter("cancelarCambioS") != null) {
			DataManager.setIdUsuario(null);
			response.sendRedirect("HTML/Sucursales.html");
		} else if (request.getParameter("aceptarDatosCambioS") != null) {
			String direccionSucursal = request.getParameter("direccionS");
			DatabaseManager.cambiarSucursal(DataManager.idUsuario, direccionSucursal);
			request.getSession().setAttribute("ResultadoCambioSucursal", "La sucursal fue cambiada");
			response.sendRedirect("JSP/ConfirmarCambioSucursal.jsp");
		}  else if (request.getParameter("volverMenu") != null) {
			DataManager.setIdUsuario(null);
			response.sendRedirect("HTML/Menu.html");
		}
	}

}
