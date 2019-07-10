package com.aandb.AcademicSystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.aandb.AcademicSystem.model.Competencia;
import com.aandb.AcademicSystem.model.DBConnection;

public class CompetenciaDAO
{
	private DBConnection dbConnection;
    private Connection connection;
    
    public CompetenciaDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) throws SQLException
    {
        dbConnection = new DBConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }
    
    public boolean insert(Competencia competencia) throws SQLException
    {
    	int nextId = 0;
    	String sql = "SELECT * FROM competencia";
        dbConnection.connect();
        connection = dbConnection.getJdbcConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        
        while(resultSet.next())
        {
            nextId = resultSet.getInt("id");
        }
        
        String sqlinsert = "INSERT INTO competencia VALUES (?, ?, ?)";
        dbConnection.connect();
        connection = dbConnection.getJdbcConnection();
        PreparedStatement statementinsert = connection.prepareStatement(sqlinsert);
        statementinsert.setInt(1, nextId + 1);
        statementinsert.setString(2, competencia.getNombre());
        statementinsert.setInt(3, competencia.getIdsilabo());
        
        boolean rowInserted = statementinsert.executeUpdate() > 0;
        statement.close();
        dbConnection.disconnect();
        return rowInserted;
    }
    
    public List<Competencia> listCompetences() throws SQLException
    {
        List<Competencia> listCompetences = new ArrayList<Competencia>();
        String sql = "SELECT * FROM competencia";
        dbConnection.connect();
        connection = dbConnection.getJdbcConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        
        while(resultSet.next())
        {
            int id = resultSet.getInt("id");
            String nombre = resultSet.getString("nombre");
            int id_silabo = resultSet.getInt("id_silabo");
            
            Competencia competence = new Competencia();
            competence.setId(id);
            competence.setNombre(nombre);
            competence.setIdsilabo(id_silabo);
            
            listCompetences.add(competence);
        }
        
        dbConnection.disconnect();
        return listCompetences;
    }
    
    public Competencia getCompetenceById(int id) throws SQLException
    {
    	Competencia competence = new Competencia();
        
        String sql = "SELECT * FROM competencia WHERE id=?";
        dbConnection.connect();
        connection = dbConnection.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        
        ResultSet res = statement.executeQuery();
        if(res.next())
        {
        	competence.setId(res.getInt("id"));
        	competence.setNombre(res.getString("nombre"));
        	competence.setIdsilabo(res.getInt("id_silabo"));
        }
        res.close();
        dbConnection.disconnect();
        
        return competence;
    }
}
