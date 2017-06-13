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
 * Servlet implementation class CambioLibrosProcess
 */
@WebServlet("/CambioLibrosProcess")
public class CambioLibrosProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idLibro = request.getParameter("idCambioL");
		if (DataManager.idLibro == null) {
			DataManager.setIdLibro(idLibro);
		}

		if (request.getParameter("aceptarCambioL") != null) {
			boolean correcto = DatabaseManager.libroValido(DataManager.idLibro);
			if (correcto) {
				String[] datos = DatabaseManager.datosLibro(DataManager.idLibro);
				request.getSession().setAttribute("dato0", "Nombre: " + datos[0]);
				request.getSession().setAttribute("dato1", "Auntor: " + datos[1]);
				if (datos[2].equals("1")) {
					request.getSession().setAttribute("dato2", "Disponible");
				} else {
					request.getSession().setAttribute("dato2", "Prestado");
				}
				if (datos[3] == null) {
					request.getSession().setAttribute("dato3", "Sin prestatario");
				} else {
					request.getSession().setAttribute("dato3", "Prestatario: " + datos[3]);
				}
				request.getSession().setAttribute("dato4", "ID: " + datos[4]);
				request.getSession().setAttribute("dato5", "Sucursal: " + datos[5]);

			} else {
				request.getSession().setAttribute("ResultadoAlta", "ERROR: El libro no puede ser cambiado");
			}
			response.sendRedirect("JSP/ConfirmarCambioLibro.jsp");
		} else if (request.getParameter("cancelarCambioL") != null) {
			DataManager.setIdLibro(null);
			DataManager.setIdUsuario(null);
			response.sendRedirect("HTML/Libros.html");
		} else if (request.getParameter("volverMenu") != null) {
			DataManager.setIdLibro(null);
			DataManager.setIdUsuario(null);
			response.sendRedirect("HTML/Menu.html");
		} else if (request.getParameter("prestarL") != null) {
			response.sendRedirect("HTML/PrestarLibro.html");
		} else if (request.getParameter("devolverL") != null) {
			DatabaseManager.devolverLibro(DataManager.idLibro);
			request.getSession().setAttribute("ResultadoCambio", "El libro fue devuelto");
			response.sendRedirect("JSP/ResultadoCambio.jsp");
		} else if (request.getParameter("aceptarPrestamoL") != null) {
			String idPrestatario = request.getParameter("prestatarioL");
			if (DataManager.idUsuario == null) {
				DataManager.setIdUsuario(idPrestatario);
			}
			boolean valido = DatabaseManager.usuarioValido(idPrestatario);
			if (valido) {
				DatabaseManager.prestarLibro(DataManager.idLibro, DataManager.idUsuario);
				System.out.println(DataManager.idLibro);
				request.getSession().setAttribute("ResultadoCambio", "El libro fue prestado");
			} else {
				request.getSession().setAttribute("ResultadoCambio", "ERROR: El libro no fue prestado");
			}
			response.sendRedirect("JSP/ResultadoCambio.jsp");
		}
	}

}
