package com.aandb.AcademicSystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.aandb.AcademicSystem.model.DBConnection;
import com.aandb.AcademicSystem.model.SilaboEstrategia;

public class SilaboEstrategiaDAO
{
	private DBConnection dbConnection;
    private Connection connection;

    public SilaboEstrategiaDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) throws SQLException
    {
        dbConnection = new DBConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }
    
    public boolean insert(SilaboEstrategia silabo_estrategia) throws SQLException
    {
    	int nextId = 0;
    	String sql_id = "SELECT * FROM `silabo-estrategia`";
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
    	
        String sql = "INSERT INTO `silabo-estrategia` VALUES (?, ?, ?, ?)";
        dbConnection.connect();
        connection = dbConnection.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, nextId + 1);
        statement.setInt(2, silabo_estrategia.getIdsilabo());
        statement.setInt(3, silabo_estrategia.getIdestrategia());
        statement.setString(4, silabo_estrategia.getDescripcion());
        
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        dbConnection.disconnect();
        return rowInserted;
    }
}
