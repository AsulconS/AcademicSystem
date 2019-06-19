package com.aandb.AcademicSystem.controller;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aandb.AcademicSystem.Application;

@Controller
public class MainController
{
    @RequestMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model)
    {
        model.addAttribute("name", name);
        return "greeting";
    }
    
    @RequestMapping("/students")
    public String students(Model model) throws SQLException
    {
        model.addAttribute("students", Application.studentDAO.listStudents());
        return "tables";
    }
}
