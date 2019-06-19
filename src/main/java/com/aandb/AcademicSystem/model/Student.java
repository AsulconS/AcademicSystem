package com.aandb.AcademicSystem.model;

public class Student
{
    private int cui;
    private String name;
    private String lastName;
    private int age;
    private String sex;
    
    public int getCui()
    {
        return cui;
    }
    public void setCui(int cui)
    {
        this.cui = cui;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public String getLastName()
    {
        return lastName;
    }
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }
    public int getAge()
    {
        return age;
    }
    public void setAge(int age)
    {
        this.age = age;
    }
    public String getSex()
    {
        return sex;
    }
    public void setSex(String sex)
    {
        this.sex = sex;
    }
}
