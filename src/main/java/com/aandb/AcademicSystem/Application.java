package com.aandb.AcademicSystem;

import java.sql.SQLException;

import javax.annotation.PreDestroy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


import com.aandb.AcademicSystem.dao.StudentDAO;
import com.aandb.AcademicSystem.dao.SubjectDAO;
import com.aandb.AcademicSystem.dao.SilaboDAO;
import com.aandb.AcademicSystem.dao.CronogramaDAO;
import com.aandb.AcademicSystem.dao.CompetenciaDAO;
import com.aandb.AcademicSystem.dao.ResultadoDAO;
import com.aandb.AcademicSystem.dao.TeacherDAO;
import com.aandb.AcademicSystem.dao.DepartamentoAcademicoDAO;
import com.aandb.AcademicSystem.dao.SilaboDocenteDAO;
import com.aandb.AcademicSystem.dao.HorarioDAO;
import com.aandb.AcademicSystem.dao.CompetenciaResultadoDAO;
import com.aandb.AcademicSystem.dao.EstrategiaEnsenianzaDAO;
import com.aandb.AcademicSystem.dao.SilaboEstrategiaDAO;
import com.aandb.AcademicSystem.dao.BibliographyDAO;
import com.aandb.AcademicSystem.dao.SilaboBibliografiaDAO;
import com.aandb.AcademicSystem.dao.EvaluacionDAO;
import com.aandb.AcademicSystem.dao.PreguntaDAO;
import com.aandb.AcademicSystem.dao.ResultadoPreguntaDAO;

@SpringBootApplication
public class Application
{
    private static ConfigurableApplicationContext context;
    public static SilaboDAO silaboDAO;
    public static StudentDAO studentDAO;
    public static SubjectDAO subjectDAO;
    public static CronogramaDAO cronogramaDAO;
    public static CompetenciaDAO competenciasDAO;
    public static ResultadoDAO resultadosDAO;
    public static TeacherDAO teacherDAO;
    public static DepartamentoAcademicoDAO departamento_academicoDAO;
    public static SilaboDocenteDAO silabo_docenteDAO;
    public static HorarioDAO horarioDAO;
    public static CompetenciaResultadoDAO competencia_resultadosDAO;
    public static EstrategiaEnsenianzaDAO estrategia_enseniansaDAO;
    public static SilaboEstrategiaDAO silabo_estrategiaDAO;
    public static BibliographyDAO bibliographyDAO;
    public static SilaboBibliografiaDAO silabo_bibliografiaDAO;
    public static EvaluacionDAO evaluacionDAO;
    public static PreguntaDAO preguntaDAO;
    public static ResultadoPreguntaDAO res_preguntasDAO;
    
    public static void main(String[] args)
    {
        String jdbcURL = "jdbc:mysql://localhost:3306/academicSystemDB?useSSL=false";
        String jdbcUsername = "root";
        String jdbcPassword = "root";
        
        try
        {
        	silaboDAO = new SilaboDAO(jdbcURL, jdbcUsername, jdbcPassword);
            studentDAO = new StudentDAO(jdbcURL, jdbcUsername, jdbcPassword);
            subjectDAO = new SubjectDAO(jdbcURL, jdbcUsername, jdbcPassword);
            cronogramaDAO = new CronogramaDAO(jdbcURL, jdbcUsername, jdbcPassword);
            competenciasDAO = new CompetenciaDAO(jdbcURL, jdbcUsername, jdbcPassword);
            resultadosDAO = new ResultadoDAO(jdbcURL, jdbcUsername, jdbcPassword);
            teacherDAO  = new TeacherDAO(jdbcURL, jdbcUsername, jdbcPassword);
            departamento_academicoDAO = new DepartamentoAcademicoDAO(jdbcURL, jdbcUsername, jdbcPassword);
            silabo_docenteDAO = new SilaboDocenteDAO(jdbcURL, jdbcUsername, jdbcPassword);
            horarioDAO = new HorarioDAO(jdbcURL, jdbcUsername, jdbcPassword);
            competencia_resultadosDAO = new CompetenciaResultadoDAO(jdbcURL, jdbcUsername, jdbcPassword);
            estrategia_enseniansaDAO = new EstrategiaEnsenianzaDAO(jdbcURL, jdbcUsername, jdbcPassword);
            silabo_estrategiaDAO = new SilaboEstrategiaDAO(jdbcURL, jdbcUsername, jdbcPassword);
            bibliographyDAO =  new BibliographyDAO(jdbcURL, jdbcUsername, jdbcPassword);
            silabo_bibliografiaDAO = new SilaboBibliografiaDAO(jdbcURL, jdbcUsername, jdbcPassword);
            evaluacionDAO = new EvaluacionDAO(jdbcURL, jdbcUsername, jdbcPassword);
            preguntaDAO = new PreguntaDAO(jdbcURL, jdbcUsername, jdbcPassword);
            res_preguntasDAO = new ResultadoPreguntaDAO(jdbcURL, jdbcUsername, jdbcPassword);
        } 
        catch(SQLException e)
        {
            System.out.println("Failed to initialize the DAOs!");
        }
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("CONNECTION SUCCESS! Done At: " + jdbcURL);
        System.out.println("--------------------------------------------------------------------------");
        context = SpringApplication.run(Application.class, args);
    }
    
    @PreDestroy
    public void onShutDown()
    {
        context.close();
        System.out.println("Closing safety . . .");
    }
}
