package com.aandb.AcademicSystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.aandb.AcademicSystem.model.Bibliography;
import com.aandb.AcademicSystem.model.DBConnection;

public class BibliographyDAO
{
	private DBConnection dbConnection;
    private Connection connection;
    
    public BibliographyDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) throws SQLException
    {
        dbConnection = new DBConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }
    
    public boolean insert(Bibliography bibliografia) throws SQLException
    {
    	int nextId = 0;
    	String sql = "SELECT * FROM bibliografia";
        dbConnection.connect();
        connection = dbConnection.getJdbcConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        
        while(resultSet.next())
        {
            nextId = resultSet.getInt("id");
        }
        
        String sqlinsert = "INSERT INTO bibliografia VALUES (?, ?, ?, ?, ?, ?)";
        dbConnection.connect();
        connection = dbConnection.getJdbcConnection();
        PreparedStatement statementinsert = connection.prepareStatement(sqlinsert);
        statementinsert.setInt(1, nextId + 1);
        statementinsert.setString(2, bibliografia.getNombre());
        statementinsert.setString(3, bibliografia.getAutor());
        statementinsert.setString(4, bibliografia.getFecha());
        statementinsert.setString(5, bibliografia.getEditorial());
        statementinsert.setString(6, bibliografia.getEdicion());
        
        boolean rowInserted = statementinsert.executeUpdate() > 0;
        statement.close();
        dbConnection.disconnect();
        return rowInserted;
    }
    
    public List<Bibliography> listBibliographys() throws SQLException
    {
        List<Bibliography> listBibliographys = new ArrayList<Bibliography>();
        String sql = "SELECT * FROM bibliografia";
        dbConnection.connect();
        connection = dbConnection.getJdbcConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        
        while(resultSet.next())
        {
            int id = resultSet.getInt("id");
            String nombre = resultSet.getString("nombre");
            String autor = resultSet.getString("autor");
            String fecha = resultSet.getString("fecha");
            String editorial = resultSet.getString("editorial");
            String edicion = resultSet.getString("edicion");
            
            Bibliography bibliography = new Bibliography();
            bibliography.setId(id);
            bibliography.setNombre(nombre);
            bibliography.setAutor(autor);
            bibliography.setFecha(fecha);
            bibliography.setEditorial(editorial);
            bibliography.setEdicion(edicion);
            
            listBibliographys.add(bibliography);
        }
        
        dbConnection.disconnect();
        return listBibliographys;
    }
    
    public int getLastId() throws SQLException
    {
    	int nextId = 0;
    	String sql = "SELECT * FROM bibliografia";
        dbConnection.connect();
        connection = dbConnection.getJdbcConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        
        while(resultSet.next())
        {
            nextId = resultSet.getInt("id");
        }
        return nextId;
    }
}
