package com.aandb.AcademicSystem.model;

public class EstrategiaEnsenianza
{
	int id;
	String tipo;
	
	public EstrategiaEnsenianza()
	{
		tipo = "tipo";
	}
	
	public EstrategiaEnsenianza(String tipo)
	{
		this.tipo = tipo;
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
}
