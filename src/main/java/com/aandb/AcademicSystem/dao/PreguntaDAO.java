package com.aandb.AcademicSystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.aandb.AcademicSystem.model.DBConnection;
import com.aandb.AcademicSystem.model.Pregunta;

public class PreguntaDAO
{
	private DBConnection dbConnection;
    private Connection connection;

    public PreguntaDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) throws SQLException
    {
        dbConnection = new DBConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }
    
    public boolean insert(Pregunta pregunta) throws SQLException
    {
    	int nextId = 0;
    	String sql_id = "SELECT * FROM pregunta";
        dbConnection.connect();
        connection = dbConnection.getJdbcConnection();
        Statement statement_id = connection.createStatement();
        ResultSet resultSet = statement_id.executeQuery(sql_id);
        
        while(resultSet.next())
        {
            nextId = resultSet.getInt("id");
        }
        
    	String sql = "INSERT INTO pregunta VALUES (?, ?, ?, ?)";
        dbConnection.connect();
        connection = dbConnection.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, nextId + 1);
        statement.setString(2, pregunta.getDescripcion());
        statement.setString(3, pregunta.getRespuesta());
        statement.setInt(4, pregunta.getId_evaluacion());
        
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        dbConnection.disconnect();
        return rowInserted;
    }
    
    public List<Pregunta> listPreguntas() throws SQLException
    {
        List<Pregunta> listPreguntas = new ArrayList<Pregunta>();
        String sql = "SELECT * FROM pregunta";
        dbConnection.connect();
        connection = dbConnection.getJdbcConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        
        while(resultSet.next())
        {
            int id = resultSet.getInt("id");
            String descripcion = resultSet.getString("descripcion");
            String respuesta = resultSet.getString("respuesta");
            int idevaluacion = resultSet.getInt("id_evaluacion");
            
            Pregunta pregunta = new Pregunta();
            pregunta.setId(id);
            pregunta.setDescripcion(descripcion);
            pregunta.setRespuesta(respuesta);
            pregunta.setId_evaluacion(idevaluacion);
            
            listPreguntas.add(pregunta);
        }
        
        dbConnection.disconnect();
        return listPreguntas;
    }
}
