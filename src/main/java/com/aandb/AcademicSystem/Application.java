package com.aandb.AcademicSystem;

import java.sql.SQLException;

import javax.annotation.PreDestroy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.aandb.AcademicSystem.dao.StudentDAO;

@SpringBootApplication
public class Application
{
    private static ConfigurableApplicationContext context;
    public static StudentDAO studentDAO;
    
    public static void main(String[] args)
    {
        String jdbcURL = "jdbc:mysql://localhost:3306/academicSystemDB";
        String jdbcUsername = "root";
        String jdbcPassword = "root";
        
        try
        {
            studentDAO = new StudentDAO(jdbcURL, jdbcUsername, jdbcPassword);
        }
        catch(SQLException e)
        {
            System.out.println("Failed to create StudentDAO!");
        }
        
        context = SpringApplication.run(Application.class, args);
    }
    
    @PreDestroy
    public void onShutDown()
    {
        context.close();
        System.out.println("Closing safety");
    }
}
