package com.aandb.AcademicSystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.aandb.AcademicSystem.model.Student;
import com.aandb.AcademicSystem.model.DBConnection;

public class StudentDAO
{
    private DBConnection dbConnection;
    private Connection connection;

    public StudentDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) throws SQLException
    {
        dbConnection = new DBConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }
    
    public boolean insert(Student student) throws SQLException
    {
        String sql = "INSERT INTO alumno VALUES (?, ?, ?, ?, ?)";
        dbConnection.connect();
        connection = dbConnection.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, student.getCui());
        statement.setString(2, student.getName());
        statement.setString(3, student.getLastName());
        statement.setInt(4, student.getAge());
        statement.setString(5, student.getSex());
        
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        dbConnection.disconnect();
        return rowInserted;
    }
    
    public List<Student> listStudents() throws SQLException
    {
        List<Student> listStudents = new ArrayList<Student>();
        String sql = "SELECT * FROM alumno";
        dbConnection.connect();
        connection = dbConnection.getJdbcConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        
        while(resultSet.next())
        {
            int cui = resultSet.getInt("cui");
            String name = resultSet.getString("nombres");
            String lastName = resultSet.getString("apellidos");
            int age = resultSet.getInt("edad");
            String sex = resultSet.getString("sexo");
            
            Student student = new Student();
            student.setCui(cui);
            student.setName(name);
            student.setLastName(lastName);
            student.setAge(age);
            student.setSex(sex);
            
            listStudents.add(student);
        }
        
        dbConnection.disconnect();
        return listStudents;
    }
    
    public Student getStudentByCui(int cui) throws SQLException
    {
        Student student = new Student();
        
        String sql = "SELECT * FROM alumno WHERE cui=?";
        dbConnection.connect();
        connection = dbConnection.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, cui);
        
        ResultSet res = statement.executeQuery();
        if(res.next())
        {
            student.setCui(res.getInt("cui"));
            student.setName(res.getString("nombres"));
            student.setLastName(res.getString("apellidos"));
            student.setAge(res.getInt("edad"));
            student.setSex(res.getString("sexo"));
        }
        res.close();
        dbConnection.disconnect();
        
        return student;
    }
    
    public boolean update(Student student) throws SQLException
    {
        String sql = "UPDATE alumno SET nombres=?, apellidos=?, edad=?, sexo=? WHERE cui=?";
        dbConnection.connect();
        connection = dbConnection.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, student.getName());
        statement.setString(2, student.getLastName());
        statement.setInt(3, student.getAge());       
        statement.setString(4, student.getSex());
        statement.setInt(5, student.getCui());
        
        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        dbConnection.disconnect();
        return rowUpdated;
    }
    
    public boolean delete(Student student) throws SQLException
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
    }
}
