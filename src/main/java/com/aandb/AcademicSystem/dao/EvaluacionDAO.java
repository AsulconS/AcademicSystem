package com.aandb.AcademicSystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.aandb.AcademicSystem.model.DBConnection;
import com.aandb.AcademicSystem.model.Evaluacion;

public class EvaluacionDAO
{
    private DBConnection dbConnection;
    private Connection connection;

    public EvaluacionDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) throws SQLException
    {
        dbConnection = new DBConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }
    
    public boolean insert(Evaluacion evaluacion) throws SQLException
    {
    	int nextId = 0;
    	String sql_id = "SELECT * FROM evaluacion";
        dbConnection.connect();
        connection = dbConnection.getJdbcConnection();
        Statement statement_id = connection.createStatement();
        ResultSet resultSet = statement_id.executeQuery(sql_id);
        
        while(resultSet.next())
        {
            nextId = resultSet.getInt("id");
        }
        
    	String sql = "INSERT INTO evaluacion VALUES (?, ?, ?, ?, ?, ?)";
        dbConnection.connect();
        connection = dbConnection.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, nextId + 1);
        statement.setString(2, evaluacion.getTipo());
        statement.setString(3, evaluacion.getEvaluacion());
        statement.setInt(4, evaluacion.getPeso());
        statement.setString(5, evaluacion.getFecha());
        statement.setInt(6, evaluacion.getSilabo_id());
        
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        dbConnection.disconnect();
        return rowInserted;
    }
    
    public List<Evaluacion> listEvaluacion() throws SQLException
    {
        List<Evaluacion> listEvaluacion = new ArrayList<Evaluacion>();
        String sql = "SELECT * FROM evaluacion";
        dbConnection.connect();
        connection = dbConnection.getJdbcConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        
        while(resultSet.next())
        {
            int id = resultSet.getInt("id");
            String tipo = resultSet.getString("tipo");
            String evaluacionStr = resultSet.getString("evaluacion");
            int peso = resultSet.getInt("peso");
            String fecha = resultSet.getString("fecha");
            int id_silabo = resultSet.getInt("id_silabo");
            
            Evaluacion evaluacion = new Evaluacion();
            evaluacion.setId(id);
            evaluacion.setTipo(tipo);
            evaluacion.setEvaluacion(evaluacionStr);
            evaluacion.setPeso(peso);
            evaluacion.setFecha(fecha);
            evaluacion.setSilabo_id(id_silabo);
            
            listEvaluacion.add(evaluacion);
        }
        
        dbConnection.disconnect();
        return listEvaluacion;
    }
    
    public int getLastId() throws SQLException {
    	int nextId = 0;
    	String sql_id = "SELECT * FROM evaluacion";
        dbConnection.connect();
        connection = dbConnection.getJdbcConnection();
        Statement statement_id = connection.createStatement();
        ResultSet resultSet = statement_id.executeQuery(sql_id);
        
        while(resultSet.next())
        {
            nextId = resultSet.getInt("id");
        }
        
        return nextId;
    }
    
}
