package com.aandb.AcademicSystem.model;

public class ResultadoPregunta
{
	private int id;
	private int id_resultados;
	private int id_preguntas;
	private int nivel;
	
	public ResultadoPregunta()
	{
		nivel = 0;
	}
	
	public ResultadoPregunta(int id_resultados, int id_preguntas, int nivel)
	{
		this.id_resultados = id_resultados;
		this.id_preguntas = id_preguntas;
		this.nivel = nivel;
	}
	
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	
	public int getId_resultados()
	{
		return id_resultados;
	}
	
	public void setId_resultados(int id_resultados)
	{
		this.id_resultados = id_resultados;
	}
	
	public int getId_preguntas()
	{
		return id_preguntas;
	}
	
	public void setId_preguntas(int id_preguntas)
	{
		this.id_preguntas = id_preguntas;
	}
	public int getNivel()
	{
		return nivel;
	}
	
	public void setNivel(int nivel)
	{
		this.nivel = nivel;
	}
}
