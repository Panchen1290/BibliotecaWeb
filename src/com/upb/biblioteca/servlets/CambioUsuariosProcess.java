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
 * Servlet implementation class CambioUsuariosProcess
 */
@WebServlet("/CambioUsuariosProcess")
public class CambioUsuariosProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idUsuario = request.getParameter("idCambioU");
		if (DataManager.idUsuario == null) {
			DataManager.setIdUsuario(idUsuario);
		}

		if (request.getParameter("aceptarCambioU") != null) {
			boolean correcto = DatabaseManager.usuarioValido(idUsuario);
			if (correcto) {
				response.sendRedirect("HTML/DatosUsuarios.html");
			} else {
				request.getSession().setAttribute("ResultadoCambioUsuario", "ERROR: El usuario no fue cambiado");
				response.sendRedirect("JSP/ConfirmarCambioUsuario.jsp");
			}
		} else if (request.getParameter("cancelarCambioU") != null) {
			DataManager.setIdUsuario(null);
			response.sendRedirect("HTML/Usuarios.html");
		} else if (request.getParameter("aceptarDatosCambioU") != null) {
			String nombreUsuario = request.getParameter("nombreU");
			DatabaseManager.cambiarUsuario(DataManager.idUsuario, nombreUsuario);
			request.getSession().setAttribute("ResultadoCambioUsuario", "El usuario fue cambiado");
			response.sendRedirect("JSP/ConfirmarCambioUsuario.jsp");
		}  else if (request.getParameter("volverMenu") != null) {
			DataManager.setIdUsuario(null);
			response.sendRedirect("HTML/Menu.html");
		}
	}

}
