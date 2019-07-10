package com.aandb.AcademicSystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.aandb.AcademicSystem.model.DBConnection;
import com.aandb.AcademicSystem.model.ResultadoPregunta;

public class ResultadoPreguntaDAO
{
	private DBConnection dbConnection;
    private Connection connection;

    public ResultadoPreguntaDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) throws SQLException
    {
        dbConnection = new DBConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }
    
    public boolean insert(ResultadoPregunta res_pre) throws SQLException
    {
    	int nextId = 0;
    	String sql_id = "SELECT * FROM `resultado-pregunta`";
        dbConnection.connect();
        connection = dbConnection.getJdbcConnection();
        Statement statement_id = connection.createStatement();
        ResultSet resultSet = statement_id.executeQuery(sql_id);
        
        while(resultSet.next())
        {
            nextId = resultSet.getInt("id");
        }
        
    	String sql = "INSERT INTO `resultado-pregunta` VALUES (?, ?, ?, ?)";
        dbConnection.connect();
        connection = dbConnection.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, nextId + 1);
        statement.setInt(2, res_pre.getNivel());
        statement.setInt(3, res_pre.getId_resultados());
        statement.setInt(4, res_pre.getId_preguntas());
        
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        dbConnection.disconnect();
        return rowInserted;
    }
    
    public List<ResultadoPregunta> listRes_Preguntas() throws SQLException
    {
        List<ResultadoPregunta> listRes_Preguntas = new ArrayList<ResultadoPregunta>();
        String sql = "SELECT * FROM `resultado-pregunta`";
        dbConnection.connect();
        connection = dbConnection.getJdbcConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        
        while(resultSet.next())
        {
            int id = resultSet.getInt("id");
            int nivel = resultSet.getInt("nivel");
            int id_resultados = resultSet.getInt("id_resultado");
            int id_preguntas = resultSet.getInt("id_pregunta");
            
            ResultadoPregunta res_pre = new ResultadoPregunta();
            res_pre.setId(id);
            res_pre.setNivel(nivel);
            res_pre.setId_preguntas(id_preguntas);
            res_pre.setId_resultados(id_resultados);
            
            listRes_Preguntas.add(res_pre);
        }
        
        dbConnection.disconnect();
        return listRes_Preguntas;
    }
}
