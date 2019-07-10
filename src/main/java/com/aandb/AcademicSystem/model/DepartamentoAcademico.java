package com.aandb.AcademicSystem.model;

public class DepartamentoAcademico
{
	int id;
	String name;
	String description;
	
	public DepartamentoAcademico()
	{
		name = "nombre";
	}
	
	public DepartamentoAcademico(String name)
	{
		this.name = name;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
}
