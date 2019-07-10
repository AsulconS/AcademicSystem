package com.aandb.AcademicSystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.aandb.AcademicSystem.model.DBConnection;
import com.aandb.AcademicSystem.model.CompetenciaResultado;

public class CompetenciaResultadoDAO
{
	private DBConnection dbConnection;
    private Connection connection;

    public CompetenciaResultadoDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) throws SQLException
    {
        dbConnection = new DBConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }
    
    public boolean insert(CompetenciaResultado competencia_resultados) throws SQLException
    {
    	int nextId = 0;
    	String sql_id = "SELECT * FROM `competencia-resultado`";
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
        
        String sql = "INSERT INTO `competencia-resultado` VALUES (?, ?, ?, ?)";
        dbConnection.connect();
        connection = dbConnection.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, nextId + 1);
        statement.setInt(2, competencia_resultados.getCompetencias_id());
        statement.setInt(3, competencia_resultados.getResultados_id());
        statement.setInt(4, competencia_resultados.getNivel());

        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        dbConnection.disconnect();
        return rowInserted;
    }
}
