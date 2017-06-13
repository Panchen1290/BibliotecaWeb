package com.upb.biblioteca.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseManager {
	public static boolean bibliotecarioValido(String usuario, String password) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/biblioteca", "root",
					"2312419013mysql");
			Statement s = conexion.createStatement();
			ResultSet rs = s.executeQuery("select * from bibliotecario");

			while (rs.next()) {
				if (rs.getString("usuario").equals(usuario)) {
					boolean answer = rs.getString("password").equals(password);
					rs.close();
					return answer;
				}
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	public static boolean agregarLibro(String nombre, String autor, String sucursal) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/biblioteca", "root",
					"2312419013mysql");

			String query = "insert into libro (nombre, autor, sucursal) values (?, ?, ?)";

			PreparedStatement preparedStmt = conexion.prepareStatement(query);
			preparedStmt.setString(1, nombre);
			preparedStmt.setString(2, autor);
			preparedStmt.setString(3, sucursal);

			preparedStmt.execute();
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean libroValido(String id) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/biblioteca", "root",
					"2312419013mysql");
			Statement s = conexion.createStatement();
			ResultSet rs = s.executeQuery("select * from libro");

			while (rs.next()) {
				if (rs.getString("id").equals(id)) {
					boolean answer = true;
					rs.close();
					return answer;
				}
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	public static void eliminarLibro(String id) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/biblioteca", "root",
					"2312419013mysql");

			String query = "delete from libro where id = " + id;
			PreparedStatement preparedStmt = conexion.prepareStatement(query);
			preparedStmt.execute();			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String[] datosLibro(String id) {
		String[] datos = new String[6];
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/biblioteca", "root",
					"2312419013mysql");
			Statement s = conexion.createStatement();
			ResultSet rs = s.executeQuery("select * from libro where id like " + id);

			while (rs.next()) {
				datos[0] = rs.getString("nombre");
				datos[1] = rs.getString("autor");
				datos[2] = rs.getString("disponible");
				datos[3] = rs.getString("usuario_posesion");
				datos[4] = rs.getString("id");
				datos[5] = rs.getString("sucursal");
				
				rs.close();
				return datos;
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	public static boolean usuarioValido(String id) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/biblioteca", "root",
					"2312419013mysql");
			Statement s = conexion.createStatement();
			ResultSet rs = s.executeQuery("select * from usuario");

			while (rs.next()) {
				if (rs.getString("id").equals(id)) {
					boolean answer = true;
					rs.close();
					return answer;
				}
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	public static void prestarLibro(String id, String idPrestatario) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/biblioteca", "root",
					"2312419013mysql");

			String query = "update libro set usuario_posesion = " + idPrestatario + " where id = " + id;
			PreparedStatement preparedStmt = conexion.prepareStatement(query);
			preparedStmt.execute();
			
			String query2 = "update libro set disponible = " + 0;
			PreparedStatement preparedStmt2 = conexion.prepareStatement(query2);
			preparedStmt2.execute();			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void devolverLibro(String id) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/biblioteca", "root",
					"2312419013mysql");

			String query = "update libro set usuario_posesion = NULL";
			PreparedStatement preparedStmt = conexion.prepareStatement(query);
			preparedStmt.execute();
			
			String query2 = "update libro set disponible = " + 1;
			PreparedStatement preparedStmt2 = conexion.prepareStatement(query2);
			preparedStmt2.execute();			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static boolean agregarUsuario(String nombre) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/biblioteca", "root",
					"2312419013mysql");

			String query = "insert into usuario (nombre) values (?)";

			PreparedStatement preparedStmt = conexion.prepareStatement(query);
			preparedStmt.setString(1, nombre);
			preparedStmt.execute();
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static void eliminarUsuario(String id) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/biblioteca", "root",
					"2312419013mysql");

			String query = "delete from usuario where id = " + id;
			PreparedStatement preparedStmt = conexion.prepareStatement(query);
			preparedStmt.execute();			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static boolean agregarSucursal(String direccion) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/biblioteca", "root",
					"2312419013mysql");

			String query = "insert into sucursal (direccion) values (?)";

			PreparedStatement preparedStmt = conexion.prepareStatement(query);
			preparedStmt.setString(1, direccion);
			preparedStmt.execute();
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean sucursalValida(String id) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/biblioteca", "root",
					"2312419013mysql");
			Statement s = conexion.createStatement();
			ResultSet rs = s.executeQuery("select * from sucursal");

			while (rs.next()) {
				if (rs.getString("id").equals(id)) {
					boolean answer = true;
					rs.close();
					return answer;
				}
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	public static void eliminarSucursal(String id) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/biblioteca", "root",
					"2312419013mysql");

			String query = "delete from sucursal where id = " + id;
			PreparedStatement preparedStmt = conexion.prepareStatement(query);
			preparedStmt.execute();			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static boolean agregarBiblio(String nombre, String password, String usuario) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/biblioteca", "root",
					"2312419013mysql");

			String query = "insert into bibliotecario (nombre, password, usuario) values (?, ?, ?)";

			PreparedStatement preparedStmt = conexion.prepareStatement(query);
			preparedStmt.setString(1, nombre);
			preparedStmt.setString(2, password);
			preparedStmt.setString(3, usuario);

			preparedStmt.execute();
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean biblioValido(String usuario) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/biblioteca", "root",
					"2312419013mysql");
			Statement s = conexion.createStatement();
			ResultSet rs = s.executeQuery("select * from bibliotecario");

			while (rs.next()) {
				if (rs.getString("usuario").equals(usuario)) {
					boolean answer = true;
					rs.close();
					return answer;
				}
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	public static void eliminarBiblio(String usuario) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/biblioteca", "root",
					"2312419013mysql");

			String query = "delete from bibliotecario where usuario = '" + usuario + "'";
			PreparedStatement preparedStmt = conexion.prepareStatement(query);
			preparedStmt.execute();			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void cambiarUsuario(String id, String nuevoNombre) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/biblioteca", "root",
					"2312419013mysql");

			String query = "update usuario set nombre = '" + nuevoNombre + "' where id = " + id;
			PreparedStatement preparedStmt = conexion.prepareStatement(query);
			preparedStmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void cambiarBiblio(String id, String nuevoNombre, String nuevaPassword) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/biblioteca", "root",
					"2312419013mysql");

			String query = "update bibliotecario set nombre = '" + nuevoNombre + "', "
					+ "password = '" + nuevaPassword + "' where usuario = '" + id + "'";
			PreparedStatement preparedStmt = conexion.prepareStatement(query);
			preparedStmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void cambiarSucursal(String id, String nuevaDireccion) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/biblioteca", "root",
					"2312419013mysql");

			String query = "update sucursal set direccion = '" + nuevaDireccion + "' where id = " + id;
			PreparedStatement preparedStmt = conexion.prepareStatement(query);
			preparedStmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
