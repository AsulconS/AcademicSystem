package com.aandb.AcademicSystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.aandb.AcademicSystem.model.Resultado;
import com.aandb.AcademicSystem.model.DBConnection;

public class ResultadoDAO
{	
	private DBConnection dbConnection;
    private Connection connection;
    
    public ResultadoDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) throws SQLException
    {
        dbConnection = new DBConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }
    
    public boolean insert(Resultado resultado) throws SQLException
    {
    	int nextId = 0;
    	String sql = "SELECT * FROM resultado";
        dbConnection.connect();
        connection = dbConnection.getJdbcConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        
        while(resultSet.next())
        {
            nextId = resultSet.getInt("id");
        }
        
        String sqlinsert = "INSERT INTO resultado VALUES (?, ?)";
        dbConnection.connect();
        connection = dbConnection.getJdbcConnection();
        PreparedStatement statementinsert = connection.prepareStatement(sqlinsert);
        statementinsert.setInt(1, nextId + 1);
        statementinsert.setString(2, resultado.getDescripcion());
        
        boolean rowInserted = statementinsert.executeUpdate() > 0;
        statement.close();
        dbConnection.disconnect();
        return rowInserted;
    }
    
    public List<Resultado> listResults() throws SQLException
    {
        List<Resultado> listResults = new ArrayList<Resultado>();
        String sql = "SELECT * FROM resultado";
        dbConnection.connect();
        connection = dbConnection.getJdbcConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        
        while(resultSet.next())
        {
            int id = resultSet.getInt("id");
            String descripcion = resultSet.getString("descripcion");
            
            Resultado result = new Resultado();
            result.setId(id);
            result.setDescripcion(descripcion);
            
            listResults.add(result);
        }
        
        dbConnection.disconnect();
        return listResults;
    }
    
    public Resultado getResultById(int id) throws SQLException
    {
    	Resultado result = new Resultado();
        
        String sql = "SELECT * FROM resultado WHERE id=?";
        dbConnection.connect();
        connection = dbConnection.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        
        ResultSet res = statement.executeQuery();
        if(res.next())
        {
        	result.setId(res.getInt("id"));
        	result.setDescripcion(res.getString("descripcion"));
        }
        res.close();
        dbConnection.disconnect();
        
        return result;
    }
}
