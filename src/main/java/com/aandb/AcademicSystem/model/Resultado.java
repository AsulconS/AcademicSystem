package com.aandb.AcademicSystem.model;

public class Resultado
{
	private int id;
	private String descripcion;
	
	public Resultado()
	{
		descripcion="descripcion";
	}
	
	public Resultado(String descripcion)
	{
		this.descripcion = descripcion;
	}
	
	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public String getDescripcion()
	{
		return descripcion;
	}
	
	public void setDescripcion(String descripcion)
	{
		this.descripcion = descripcion;
	}
}
