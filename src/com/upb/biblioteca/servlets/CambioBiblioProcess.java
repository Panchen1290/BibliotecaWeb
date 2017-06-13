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
 * Servlet implementation class CambioBiblioProcess
 */
@WebServlet("/CambioBiblioProcess")
public class CambioBiblioProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idUsuario = request.getParameter("idCambioB");
		if (DataManager.idUsuario == null) {
			DataManager.setIdUsuario(idUsuario);
		}

		if (request.getParameter("aceptarCambioB") != null) {
			boolean correcto = DatabaseManager.biblioValido(idUsuario);
			if (correcto) {
				response.sendRedirect("HTML/DatosBiblio.html");
			} else {
				request.getSession().setAttribute("ResultadoCambioBiblio", "ERROR: El bibliotecario no fue cambiado");
				response.sendRedirect("JSP/ConfirmarCambioBiblio.jsp");
			}
		} else if (request.getParameter("cancelarCambioB") != null) {
			DataManager.setIdUsuario(null);
			response.sendRedirect("HTML/Biblios.html");
		} else if (request.getParameter("aceptarDatosCambioB") != null) {
			String nombreBiblio = request.getParameter("nombreB");
			String passwordBiblio = request.getParameter("passwordB");
			DatabaseManager.cambiarBiblio(DataManager.idUsuario, nombreBiblio, passwordBiblio);
			request.getSession().setAttribute("ResultadoCambioBiblio", "El bibliotecario fue cambiado");
			response.sendRedirect("JSP/ConfirmarCambioBiblio.jsp");
		}  else if (request.getParameter("volverMenu") != null) {
			DataManager.setIdUsuario(null);
			response.sendRedirect("HTML/Menu.html");
		}
	}

}
