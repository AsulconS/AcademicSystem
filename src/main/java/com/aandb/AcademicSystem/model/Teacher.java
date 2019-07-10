package com.aandb.AcademicSystem.model;

public class Teacher
{
	private int id;
	private int dni;
	private String nombre;
	private String apellido_materno;
	private String apellido_paterno;
	private String grado_academico;
	private int id_departamento_academico;
	
	public Teacher()
	{
		this.dni = 0;
		this.nombre = "nombre";
		this.grado_academico = "grado_academico";
		this.id_departamento_academico = 0;
		this.apellido_materno = "Apellido Materno";
		this.apellido_paterno = "Apellido paterno";
	}
	
	public Teacher(int dni, String nombre, String ap_paterno, String ap_materno, String grado_academico,
			int id_departemento_academico)
	{
		this.dni = dni;
		this.nombre = nombre;
		this.apellido_paterno = ap_paterno;
		this.apellido_materno = ap_materno;
		this.grado_academico = grado_academico;
		this.id_departamento_academico = id_departemento_academico;
	}
	
	public Teacher(int id, int dni, String nombre, String grado_academico,
			int id_departemento_academico)
	{
		this.id = id;
		this.dni = dni;
		this.nombre = nombre;
		this.grado_academico = grado_academico;
		this.id_departamento_academico = id_departemento_academico;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getDni()
	{
		return dni;
	}

	public void setDni(int dni)
	{
		this.dni = dni;
	}

	public String getNombre()
	{
		return nombre;
	}

	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}

	public String getGrado_academico()
	{
		return grado_academico;
	}

	public void setGrado_academico(String grado_academico)
	{
		this.grado_academico = grado_academico;
	}

	public int getId_departamento_academico()
	{
		return id_departamento_academico;
	}

	public void setId_departamento_academico(int id_departemento_academico)
	{
		this.id_departamento_academico = id_departemento_academico;
	}
	
	public String getApellido_materno()
	{
		return apellido_materno;
	}
	
	public void setApellido_materno(String apellido_materno)
	{
		this.apellido_materno = apellido_materno;
	}
	
	public String getApellido_paterno()
	{
		return apellido_paterno;
	}
	
	public void setApellido_paterno(String apellido_paterno)
	{
		this.apellido_paterno = apellido_paterno;
	}
}
