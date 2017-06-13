package com.upb.biblioteca.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.upb.biblioteca.database.DatabaseManager;

/**
 * Servlet implementation class LoginProcess
 */
@WebServlet("/LoginProcess")
public class LoginProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usuarioB = request.getParameter("usuarioB");
		String passwordB = request.getParameter("passwordB");
		
		if (DatabaseManager.bibliotecarioValido(usuarioB, passwordB)) {
			response.sendRedirect("HTML/Menu.html");
		} else {
			response.sendRedirect("HTML/Login.html");
		}
	}

}
