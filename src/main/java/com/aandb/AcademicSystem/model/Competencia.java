package com.aandb.AcademicSystem.model;

public class Competencia
{
	private int id;
	private int idsilabo;
	private String nombre;
	
	public Competencia()
	{
		nombre="nombre";
	}
	
	public Competencia(int idsilabo, String nombre)
	{
		this.idsilabo = idsilabo;
		this.nombre = nombre;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getIdsilabo()
	{
		return idsilabo;
	}

	public void setIdsilabo(int idsilabo)
	{
		this.idsilabo = idsilabo;
	}

	public String getNombre()
	{
		return nombre;
	}

	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}
}
