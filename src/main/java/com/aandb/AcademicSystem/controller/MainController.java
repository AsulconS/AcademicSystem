package com.aandb.AcademicSystem.controller;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aandb.AcademicSystem.Application;
import com.aandb.AcademicSystem.model.Student;

@Controller
public class MainController
{
    @RequestMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model)
    {
        model.addAttribute("name", name);
        return "greeting";
    }
    
    @RequestMapping("/students")
    public String students(Model model) throws SQLException
    {
        model.addAttribute("students", Application.studentDAO.listStudents());
        return "students";
    }
    
    @RequestMapping("/subjects")
    public String subjects(Model model) throws SQLException
    {
        model.addAttribute("subjects", Application.subjectDAO.listSubjects());
        return "subjects";
    }
    
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/registerStudent")
    public String registerStudent(@RequestParam(name = "cui") String cui,
    							  @RequestParam(name = "name") String name,
    							  @RequestParam(name = "lastName") String lastName,
    							  @RequestParam(name = "age") String age,
    							  @RequestParam(name = "sex") String sex) throws SQLException
    {
    	Student student = new Student(Integer.parseInt(cui), name, lastName, Integer.parseInt(age), sex);
    	Application.studentDAO.insert(student);
        return "redirect:index.html";
    }
}
