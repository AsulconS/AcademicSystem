package com.aandb.AcademicSystem.model;

public class SilaboDocente
{
	int id;
	int id_docente;
	int id_silabo;
	
	public SilaboDocente() {}
	
	public SilaboDocente(int id_docente, int id_silabo)
	{
		this.id_docente = id_docente;
		this.id_silabo = id_silabo;
	}
	
	public SilaboDocente(int id, int id_docente, int id_silabo)
	{
		this.id = id;
		this.id_docente = id_docente;
		this.id_silabo = id_silabo;
	}
	
	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public int getId_docente()
	{
		return id_docente;
	}
	
	public void setId_docente(int id_docente)
	{
		this.id_docente = id_docente;
	}
	
	public int getId_silabo()
	{
		return id_silabo;
	}
	
	public void setId_silabo(int id_silabo)
	{
		this.id_silabo = id_silabo;
	}
}
