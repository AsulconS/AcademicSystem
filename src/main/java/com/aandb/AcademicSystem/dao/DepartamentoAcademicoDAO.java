package com.aandb.AcademicSystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.aandb.AcademicSystem.model.DBConnection;
import com.aandb.AcademicSystem.model.DepartamentoAcademico;

public class DepartamentoAcademicoDAO
{
	private DBConnection dbConnection;
    private Connection connection;

    public DepartamentoAcademicoDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) throws SQLException
    {
        dbConnection = new DBConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }
    
    public boolean insert(DepartamentoAcademico departamento_academico) throws SQLException
    {
    	int nextId = 0;
    	String sql_id = "SELECT * FROM `departamento_academico`";
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
        
        String sql = "INSERT INTO `departamento_academico` VALUES (?, ?, ?)";
        dbConnection.connect();
        connection = dbConnection.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, nextId + 1);
        statement.setString(2, departamento_academico.getName());
        statement.setString(3, departamento_academico.getDescription());

        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        dbConnection.disconnect();
        return rowInserted;
    }
    
    public List<DepartamentoAcademico> listDepartamentos() throws SQLException
    {
        List<DepartamentoAcademico> listDepartamentos = new ArrayList<DepartamentoAcademico>();
        String sql = "SELECT * FROM `departamento_academico`";
        dbConnection.connect();
        connection = dbConnection.getJdbcConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        
        while(resultSet.next())
        {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("nombre");
            String description = resultSet.getString("descripcion");
            
            DepartamentoAcademico dep = new DepartamentoAcademico();
            dep.setId(id);
            dep.setName(name);
            dep.setDescription(description);
            
            listDepartamentos.add(dep);
        }
        
        dbConnection.disconnect();
        return listDepartamentos;
    }
}
