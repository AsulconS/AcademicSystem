package com.aandb.AcademicSystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.aandb.AcademicSystem.model.DBConnection;
import com.aandb.AcademicSystem.model.SilaboDocente;

public class SilaboDocenteDAO
{
	private DBConnection dbConnection;
    private Connection connection;

    public SilaboDocenteDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) throws SQLException
    {
        dbConnection = new DBConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }
    
    public boolean insert(SilaboDocente silabo_docente) throws SQLException
    {
    	int nextId=0;
    	String sql_id = "SELECT * FROM `silabo-docente`";
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
    	
        String sql = "INSERT INTO `silabo-docente` VALUES (?, ?, ?)";
        dbConnection.connect();
        connection = dbConnection.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, nextId+1);
        statement.setInt(2, silabo_docente.getId_silabo());
        statement.setInt(3, silabo_docente.getId_docente());
        
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        dbConnection.disconnect();
        return rowInserted;
    }
    
    public List<SilaboDocente> listSilabo_Docente() throws SQLException
    {
        List<SilaboDocente> listSilabo_docente = new ArrayList<SilaboDocente>();
        String sql = "SELECT * FROM `silabo-docente`";
        dbConnection.connect();
        connection = dbConnection.getJdbcConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        
        while(resultSet.next())
        {
            int id = resultSet.getInt("id");
            int id_silabo = resultSet.getInt("id_silabo");
            int id_docente = resultSet.getInt("id_docente");
            
            SilaboDocente silabo_docente = new SilaboDocente();
            silabo_docente.setId(id);
            silabo_docente.setId_silabo(id_silabo);
            silabo_docente.setId_docente(id_docente);
            
            listSilabo_docente.add(silabo_docente);
        }
        
        dbConnection.disconnect();
        return listSilabo_docente;
    }
}
