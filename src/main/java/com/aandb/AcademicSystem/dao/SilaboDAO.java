package com.aandb.AcademicSystem.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.aandb.AcademicSystem.model.Silabo;
import com.aandb.AcademicSystem.model.DBConnection;

public class SilaboDAO
{
	private DBConnection dbConnection;
    private Connection connection;

    public SilaboDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) throws SQLException
    {
        dbConnection = new DBConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }
    
    public boolean insert(Silabo silabo) throws SQLException
    {
    	int nextId = 0;
    	String sql = "SELECT * FROM silabo";
        dbConnection.connect();
        connection = dbConnection.getJdbcConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        
        while(resultSet.next())
        {
            nextId = resultSet.getInt("id");
        }
        
        dbConnection.disconnect();
        
        String sqlinsert = "INSERT INTO silabo VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        dbConnection.connect();
        connection = dbConnection.getJdbcConnection();
        PreparedStatement statementinsert = connection.prepareStatement(sqlinsert);
        statementinsert.setInt(1, nextId + 1);
        statementinsert.setInt(2, silabo.getId_asignatura());
        statementinsert.setString(3, silabo.getSemestre());
        statementinsert.setString(4, silabo.getContenido());
        statementinsert.setString(5, silabo.getTipos_evaluacion());
        statementinsert.setString(6, silabo.getEvaluacion_continua());
        statementinsert.setString(7, silabo.getEvaluacion_periodica());
        statementinsert.setString(8, silabo.getInstrumentos());
        statementinsert.setString(9, silabo.getRequisitos_evaluacion());
        statementinsert.setString(10, silabo.getPeriodo_academico());
        statementinsert.setInt(11, silabo.getPORCENTAJE_EP1());
        statementinsert.setInt(12, silabo.getPORCENTAJE_EP2());
        statementinsert.setInt(13, silabo.getPORCENTAJE_EP3());
        statementinsert.setInt(14, silabo.getPORCENTAJE_EC1());
        statementinsert.setInt(15, silabo.getPORCENTAJE_EC2());
        statementinsert.setInt(16, silabo.getPORCENTAJE_EC3());
        statementinsert.setString(17, silabo.getFECHA_EP1());
        statementinsert.setString(18, silabo.getFECHA_EP2());
        statementinsert.setString(19, silabo.getFECHA_EP3());


        boolean rowInserted = statementinsert.executeUpdate() > 0;
        statement.close();
        dbConnection.disconnect();
        return rowInserted;
    }
    
    public List<Silabo> listSilabos() throws SQLException
    {
        List<Silabo> listSilabos = new ArrayList<Silabo>();
        String sql = "SELECT * FROM silabo";
        dbConnection.connect();
        connection = dbConnection.getJdbcConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        
        while(resultSet.next())
        {
        	int id = resultSet.getInt("id");
            int id_asignatura = resultSet.getInt("id_asignatura");
            String semestre = resultSet.getString("semestre");
            String contenido = resultSet.getString("contenido");
            String tipo_evaluacion = resultSet.getString("tipos_evaluacion");
            String evaluacioncontinua = resultSet.getString("evaluacion_continua");
            String evaluacionperiodica = resultSet.getString("evaluacion_periodica");
            String instrumentos = resultSet.getString("instrumentos");
            String requisitos_evaluacion = resultSet.getString("requisitos_evaluacion");
            String periodo_academico = resultSet.getString("periodo_academico");
            int PORCENTAJE_EP1 = resultSet.getInt("porcentaje_ep1");
            int PORCENTAJE_EP2 = resultSet.getInt("porcentaje_ep2");
            int PORCENTAJE_EP3 = resultSet.getInt("porcentaje_ep3");
            int PORCENTAJE_EC1 = resultSet.getInt("porcentaje_ec1");
            int PORCENTAJE_EC2 = resultSet.getInt("porcentaje_ec2");
            int PORCENTAJE_EC3 = resultSet.getInt("porcentaje_ec3");
            String FECHA_EP1 = resultSet.getString("fecha_ep1");
            String FECHA_EP2 = resultSet.getString("fecha_ep2");
            String FECHA_EP3 = resultSet.getString("fecha_ep3");
            
            Silabo silabo = new Silabo();
            silabo.setId(id);
            silabo.setId_asignatura(id_asignatura);
            silabo.setSemestre(semestre);
            silabo.setContenido(contenido);
            silabo.setTipos_evaluacion(tipo_evaluacion);
            silabo.setEvaluacion_continua(evaluacioncontinua);
            silabo.setEvaluacion_periodica(evaluacionperiodica);
            silabo.setInstrumentos(instrumentos);
            silabo.setRequisitos_evaluacion(requisitos_evaluacion);
            silabo.setPeriodo_academico(periodo_academico);
            silabo.setPORCENTAJE_EP1(PORCENTAJE_EP1);
            silabo.setPORCENTAJE_EP2(PORCENTAJE_EP2);
            silabo.setPORCENTAJE_EP3(PORCENTAJE_EP3);
            silabo.setPORCENTAJE_EC1(PORCENTAJE_EC1);
            silabo.setPORCENTAJE_EC2(PORCENTAJE_EC2);
            silabo.setPORCENTAJE_EC3(PORCENTAJE_EC3);
            silabo.setFECHA_EP1(FECHA_EP1);
            silabo.setFECHA_EP2(FECHA_EP2);
            silabo.setFECHA_EP3(FECHA_EP3);
            
            listSilabos.add(silabo);
        }
        
        dbConnection.disconnect();
        return listSilabos;
    }
    
    public Silabo getSilabusByAsignatura(int id_asignatura) throws SQLException
    {
        Silabo silabo = new Silabo();
        
        String sql = "SELECT * FROM silabo WHERE id_asignatura=?";
        dbConnection.connect();
        connection = dbConnection.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id_asignatura);
        
        ResultSet res = statement.executeQuery();
        if(res.next())
        {
        	silabo.setId(res.getInt("id"));
            silabo.setId_asignatura(res.getInt("id_asignatura"));
            silabo.setSemestre(res.getString("semestre"));
            silabo.setContenido(res.getString("contenido"));
            silabo.setTipos_evaluacion(res.getString("tipos_evaluacion"));
            silabo.setEvaluacion_continua(res.getString("evaluacion_continua"));
            silabo.setEvaluacion_periodica(res.getString("evaluacion_periodica"));
            silabo.setInstrumentos(res.getString("instrumentos"));
            silabo.setRequisitos_evaluacion(res.getString("requisitos_evaluacion"));
            silabo.setPeriodo_academico(res.getString("periodo_academico"));
            silabo.setPORCENTAJE_EP1(res.getInt("porcentaje_ep1"));
            silabo.setPORCENTAJE_EP2(res.getInt("porcentaje_ep2"));
            silabo.setPORCENTAJE_EP3(res.getInt("porcentaje_ep3"));
            silabo.setPORCENTAJE_EC1(res.getInt("porcentaje_ec1"));
            silabo.setPORCENTAJE_EC2(res.getInt("porcentaje_ec2"));
            silabo.setPORCENTAJE_EC3(res.getInt("porcentaje_ec3"));
            silabo.setFECHA_EP1(res.getString("fecha_ep1"));
            silabo.setFECHA_EP2(res.getString("fecha_ep2"));
            silabo.setFECHA_EP3(res.getString("fecha_ep3"));
        }
        res.close();
        dbConnection.disconnect();
        
        return silabo;
    }
    
    public Silabo getSilabusById(int id) throws SQLException
    {
        Silabo silabo = new Silabo();
        
        String sql = "SELECT * FROM silabo WHERE id='"+Integer.toString(id)+"';";
        dbConnection.connect();
        connection = dbConnection.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        
        ResultSet res = statement.executeQuery();
        if(res.next())
        {
        	silabo.setId(res.getInt("id"));
            silabo.setId_asignatura(res.getInt("id_asignatura"));
            silabo.setSemestre(res.getString("semestre"));
            silabo.setContenido(res.getString("contenido"));
            silabo.setTipos_evaluacion(res.getString("tipos_evaluacion"));
            silabo.setEvaluacion_continua(res.getString("evaluacion_continua"));
            silabo.setEvaluacion_periodica(res.getString("evaluacion_periodica"));
            silabo.setInstrumentos(res.getString("instrumentos"));
            silabo.setRequisitos_evaluacion(res.getString("requisitos_evaluacion"));
            silabo.setPeriodo_academico(res.getString("periodo_academico"));
            silabo.setPORCENTAJE_EP1(res.getInt("porcentaje_ep1"));
            silabo.setPORCENTAJE_EP2(res.getInt("porcentaje_ep2"));
            silabo.setPORCENTAJE_EP3(res.getInt("porcentaje_ep3"));
            silabo.setPORCENTAJE_EC1(res.getInt("porcentaje_ec1"));
            silabo.setPORCENTAJE_EC2(res.getInt("porcentaje_ec2"));
            silabo.setPORCENTAJE_EC3(res.getInt("porcentaje_ec3"));
            silabo.setFECHA_EP1(res.getString("fecha_ep1"));
            silabo.setFECHA_EP2(res.getString("fecha_ep2"));
            silabo.setFECHA_EP3(res.getString("fecha_ep3"));
        }
        res.close();
        dbConnection.disconnect();
        
        return silabo;
    }
    
    public boolean update(Silabo silabo) throws SQLException
    {
        String sql = "UPDATE silabo SET id_asignatura=?, semestre=?, contenido=?, tipos_evaluacion=?, "+
        			"evaluacion_continua=?, evaluacion_periodica=?, instrumentos=?, requisitos_evaluacion=?, periodo_academico=?,"+
        			"porcentaje_ep1=?, porcentaje_ep2=?, porcentaje_ep3=?, porcentaje_ec1=?, porcentaje_ec2=?, porcentaje_ec3=?,"+
        			"fecha_ep1=?, fecha_ep2=?, fecha_ep3=? WHERE id=?";
        dbConnection.connect();
        connection = dbConnection.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, silabo.getId_asignatura());
        statement.setString(2, silabo.getSemestre());
        statement.setString(3, silabo.getContenido());       
        statement.setString(4, silabo.getTipos_evaluacion());
        statement.setString(5, silabo.getEvaluacion_continua());
        statement.setString(6, silabo.getEvaluacion_periodica());
        statement.setString(7, silabo.getInstrumentos());
        statement.setString(8, silabo.getRequisitos_evaluacion());
        statement.setString(9, silabo.getPeriodo_academico());
        statement.setInt(10, silabo.getPORCENTAJE_EP1());
        statement.setInt(11, silabo.getPORCENTAJE_EP2());
        statement.setInt(12, silabo.getPORCENTAJE_EP3());
        statement.setInt(13, silabo.getPORCENTAJE_EC1());
        statement.setInt(14, silabo.getPORCENTAJE_EC2());
        statement.setInt(15, silabo.getPORCENTAJE_EC3());
        statement.setString(16, silabo.getFECHA_EP1());
        statement.setString(17, silabo.getFECHA_EP2());
        statement.setString(18, silabo.getFECHA_EP3());
        statement.setInt(19, silabo.getId());
        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        dbConnection.disconnect();
        return rowUpdated;
    }
    /*
    public boolean delete(Silabo silabo) throws SQLException
    {
        String sql = "DELETE FROM alumno WHERE cui=?";
        dbConnection.connect();
        connection = dbConnection.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, student.getCui());
        
        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        dbConnection.disconnect();
        return rowDeleted;
    }*/
}
