package com.aandb.AcademicSystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.aandb.AcademicSystem.model.DBConnection;
import com.aandb.AcademicSystem.model.EstrategiaEnsenianza;

public class EstrategiaEnsenianzaDAO
{
	private DBConnection dbConnection;
    private Connection connection;

    public EstrategiaEnsenianzaDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) throws SQLException
    {
        dbConnection = new DBConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }
    
    public boolean insert(EstrategiaEnsenianza estrategia_ensenianza) throws SQLException
    {
    	int nextId = 0;
    	String sql_id = "SELECT * FROM `estrategia-ensenianza`";
        dbConnection.connect();
        connection = dbConnection.getJdbcConnection();
        Statement statement_id = connection.createStatement();
        ResultSet resultSet = statement_id.executeQuery(sql_id);
        
        while(resultSet.next())
        {
            nextId = resultSet.getInt("id");
        }
    	
        statement_id.close();
        dbConnection.disconnect();
    	
        String sql = "INSERT INTO `estrategia-ensenianza` VALUES (?, ?)";
        dbConnection.connect();
        connection = dbConnection.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, nextId + 1);
        statement.setString(2, estrategia_ensenianza.getTipo());
        
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        dbConnection.disconnect();
        return rowInserted;
    }
    
    public List<EstrategiaEnsenianza> listEstrategias() throws SQLException
    {
        List<EstrategiaEnsenianza> listEstrategias = new ArrayList<EstrategiaEnsenianza>();
        String sql = "SELECT * FROM `estrategia-ensenianza`";
        dbConnection.connect();
        connection = dbConnection.getJdbcConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        
        while(resultSet.next())
        {
            int id = resultSet.getInt("id");
            String tipo = resultSet.getString("tipo");
            
            EstrategiaEnsenianza estrategia = new EstrategiaEnsenianza();
            estrategia.setId(id);
            estrategia.setTipo(tipo);
            
            listEstrategias.add(estrategia);
        }
        
        dbConnection.disconnect();
        return listEstrategias;
    }
    
    public EstrategiaEnsenianza getEstrategiaByType(String type) throws SQLException
    {
    	EstrategiaEnsenianza estrategia = new EstrategiaEnsenianza();
        
        String sql = "SELECT * FROM `estrategia-ensenianza` WHERE tipo=?";
        dbConnection.connect();
        connection = dbConnection.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, type);
        
        ResultSet res = statement.executeQuery();
        if(res.next())
        {
        	estrategia.setId(res.getInt("id"));
        	estrategia.setTipo(res.getString("tipo"));
        }
        res.close();
        dbConnection.disconnect();
        
        return estrategia;
    }

}
