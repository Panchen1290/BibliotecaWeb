package com.upb.biblioteca.tests;

import org.junit.Test;

import com.upb.biblioteca.database.DatabaseManager;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class Tests {

	@Test
	public void testUsuarioValido() {
		boolean valido = DatabaseManager.usuarioValido("1");
		Assert.assertEquals(valido, true);
	}
	
	@Test(expected = AssertionError.class)
	public void testUsuarioNoValido() {
		boolean valido = DatabaseManager.usuarioValido("100");
		Assert.assertEquals(valido, true);
	}
	
	@Test
	public void testAgregarUsuario() {
		DatabaseManager.agregarUsuario("Nuevo Usuario");
	}
	
	@Test
	public void testEliminarUsuario() {
		DatabaseManager.eliminarUsuario("10");
	}
	
	@Test
	public void testSucursalValida() {
		boolean valido = DatabaseManager.sucursalValida("1");
		Assert.assertEquals(valido, true);
	}
	
	@Test(expected = AssertionError.class)
	public void testSucursalNoValida() {
		boolean valido = DatabaseManager.sucursalValida("100");
		Assert.assertEquals(valido, true);
	}
	
	@Test
	public void testAgregarSucursal() {
		DatabaseManager.agregarSucursal("El Alto");
	}
	
	@Test
	public void testEliminarSucursal() {
		DatabaseManager.eliminarSucursal("5");
	}
	
	

}
