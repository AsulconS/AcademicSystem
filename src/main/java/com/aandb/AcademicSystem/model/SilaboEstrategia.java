package com.aandb.AcademicSystem.model;

public class SilaboEstrategia
{
	private int id;
	private int idsilabo;
	private int idestrategia;
	private String descripcion;
	
	public SilaboEstrategia()
	{
		descripcion = "descripcion";
	}
	
	public SilaboEstrategia(int idsilabo, int idestrategia, String descripcion)
	{
		this.idsilabo = idsilabo;
		this.idestrategia = idestrategia;
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

	public int getIdsilabo()
	{
		return idsilabo;
	}

	public void setIdsilabo(int idsilabo)
	{
		this.idsilabo = idsilabo;
	}

	public int getIdestrategia()
	{
		return idestrategia;
	}

	public void setIdestrategia(int idestrategia)
	{
		this.idestrategia = idestrategia;
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
