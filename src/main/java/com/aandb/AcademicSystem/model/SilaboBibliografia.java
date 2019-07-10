package com.aandb.AcademicSystem.model;

public class SilaboBibliografia
{
	private int id;
	private String tipo;
	private int id_silabo;
	private int id_bibliografia;
	
	public SilaboBibliografia()
	{
		tipo = "tipo";
	}
	
	public SilaboBibliografia(String tipo, int id_silabo, int id_bibliografia)
	{
		this.tipo = tipo;
		this.id_silabo = id_silabo;
		this.id_bibliografia = id_bibliografia;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getTipo()
	{
		return tipo;
	}

	public void setTipo(String tipo)
	{
		this.tipo = tipo;
	}

	public int getId_silabo()
	{
		return id_silabo;
	}

	public void setId_silabo(int id_silabo)
	{
		this.id_silabo = id_silabo;
	}

	public int getId_bibliografia()
	{
		return id_bibliografia;
	}

	public void setId_bibliografia(int id_bibliografia)
	{
		this.id_bibliografia = id_bibliografia;
	}
}
