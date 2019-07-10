package com.aandb.AcademicSystem.controller;

import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aandb.AcademicSystem.Application;
import com.aandb.AcademicSystem.model.Student;
import com.aandb.AcademicSystem.model.Silabo;
import com.aandb.AcademicSystem.model.Cronograma;
import com.aandb.AcademicSystem.model.Competencia;
import com.aandb.AcademicSystem.model.Resultado;
import com.aandb.AcademicSystem.model.Subject;
import com.aandb.AcademicSystem.model.Teacher;
import com.aandb.AcademicSystem.model.DepartamentoAcademico;
import com.aandb.AcademicSystem.model.SilaboDocente;
import com.aandb.AcademicSystem.model.Horario;
import com.aandb.AcademicSystem.model.CompetenciaResultado;
import com.aandb.AcademicSystem.model.EstrategiaEnsenianza;
import com.aandb.AcademicSystem.model.SilaboEstrategia;
import com.aandb.AcademicSystem.model.Bibliography;
import com.aandb.AcademicSystem.model.SilaboBibliografia;
import com.aandb.AcademicSystem.model.Evaluacion;
import com.aandb.AcademicSystem.model.Pregunta;
import com.aandb.AcademicSystem.model.ResultadoPregunta;

@Controller
public class MainController
{
	private int foreignkey;
	
	public void setforeignkey(int _key) {
		foreignkey=_key;
	}
	
    @RequestMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "Guy") String name, Model model)
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
    
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/teacherInformation")
    public String teacherInformation(Model model) throws SQLException
    {
    	model.addAttribute("dep_academico", Application.departamento_academicoDAO.listDepartamentos());
        return "registerTeacher";
    }
    
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/registerTeacher")
    public String registerTeacher(@RequestParam(name = "name") String name,
			  @RequestParam(name = "ap_m") String ap_m,
			  @RequestParam(name = "ap_p") String ap_p,
			  @RequestParam(name = "grado_academico") String grado_academico,
			  @RequestParam(name = "dni") String dni,
			  @RequestParam(name = "selected") String fk) throws SQLException
	{
		Teacher teacher = new Teacher(Integer.parseInt(dni), name,ap_p,ap_m,grado_academico,Integer.parseInt(fk));
		Application.teacherDAO.insert(teacher);
		return "redirect:index.html";
	}
    
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/subjectInformation")
    public String subjectInformation(Model model) throws SQLException
    {
    	model.addAttribute("subjects", Application.subjectDAO.listSubjects());
        return "selectSubject";
    }
    
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value="/selectSubject")
    public String selectSubject(Model model,@RequestParam(name = "selected") String selected,
    							@RequestParam(name = "periodo_academico") String periodo_academico) throws SQLException
    {
    	Subject curso = new Subject();
    	curso=Application.subjectDAO.getSubjectbyName(selected);
    	foreignkey=curso.getCasi();
    	//crear un silabo del curso insertarle el periodo academico y guardar el id del silabo
    	Silabo silabo = new Silabo();
    	silabo.setId_asignatura(foreignkey);
    	silabo.setPeriodo_academico(periodo_academico);
    	Application.silaboDAO.insert(silabo);
    	silabo=Application.silaboDAO.getSilabusByAsignatura(foreignkey);
    	foreignkey=silabo.getId();
    	model.addAttribute("teachers",Application.teacherDAO.listTeachers());
    	return "registerSyllabus";
    }
    
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/registerSyllabus")
    public String registerSyllabus(Model model,@RequestParam(name="docente_name") String docente_name,@RequestParam(name="cursos_lunes") String cursos_lunes,
    							@RequestParam(name="cursos_martes") String cursos_martes,@RequestParam(name="cursos_miercoles") String cursos_miercoles,
    							@RequestParam(name="cursos_jueves") String cursos_jueves,@RequestParam(name="cursos_viernes") String cursos_viernes,
    							@RequestParam(name="tipo_clase_lunes") String tipo_clase_lunes,@RequestParam(name="tipo_clase_martes") String tipo_clase_martes,
    							@RequestParam(name="tipo_clase_miercoles") String tipo_clase_miercoles,@RequestParam(name="tipo_clase_jueves") String tipo_clase_jueves,
    							@RequestParam(name="tipo_clase_viernes") String tipo_clase_viernes,@RequestParam(name="grupo_lunes") String grupo_lunes,
    							@RequestParam(name="grupo_martes") String grupo_martes,@RequestParam(name="grupo_miercoles") String grupo_miercoles,
    							@RequestParam(name="grupo_jueves") String grupo_jueves,@RequestParam(name="grupo_viernes") String grupo_viernes) throws SQLException
    {
    	Teacher teacher=Application.teacherDAO.getTeacherByName(docente_name);
    	System.out.println(docente_name);
    	SilaboDocente silabo_docente=new SilaboDocente(teacher.getId(),foreignkey);
    	
    	Horario lunes= new Horario(foreignkey,"lunes",tipo_clase_lunes,grupo_lunes,cursos_lunes);
    	Horario martes= new Horario(foreignkey,"martes",tipo_clase_martes,grupo_martes,cursos_martes);
    	Horario miercoles= new Horario(foreignkey,"miercoles",tipo_clase_miercoles,grupo_miercoles,cursos_miercoles);
    	Horario jueves= new Horario(foreignkey,"jueves",tipo_clase_jueves,grupo_jueves,cursos_jueves);
    	Horario viernes= new Horario(foreignkey,"viernes",tipo_clase_viernes,grupo_viernes,cursos_viernes);
    	
    	if(lunes.complete())
    		Application.horarioDAO.insert(lunes);
    	if(martes.complete())
    		Application.horarioDAO.insert(martes);
    	if(miercoles.complete())
    		Application.horarioDAO.insert(miercoles);
    	if(jueves.complete())
    		Application.horarioDAO.insert(jueves);
    	if(viernes.complete())
    		Application.horarioDAO.insert(viernes);
    	
    	Application.silabo_docenteDAO.insert(silabo_docente);
    	
        return "syllabusData1";
    }
    
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/syllabusData1")
    public String syllabusData1(Model model,@RequestParam(name="fundamentacion") String fundamentacion,
    							@RequestParam(name="sumilla") String sumilla) throws SQLException
	{
    	Silabo silabus= new Silabo();
    	silabus = Application.silaboDAO.getSilabusById(foreignkey);
    	Subject curso = new Subject();
    	curso = Application.subjectDAO.getSubjectByCasi(silabus.getId_asignatura());
    	curso.setFoundation(fundamentacion);
    	curso.setSummary(sumilla);
    	
    	Application.subjectDAO.update(curso);
    	
    	model.addAttribute("competencias",Application.competenciasDAO.listCompetences());
    	model.addAttribute("resultados",Application.resultadosDAO.listResults());
    	return "registerCompetencias";
	}
    
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/registerCompetencias")
    public String registerCompetencia(Model model,@RequestParam(name="nombre") String nombre) throws SQLException
	{
    	Competencia competencia = new Competencia();
    	competencia.setIdsilabo(foreignkey);
    	competencia.setNombre(nombre);
    	Application.competenciasDAO.insert(competencia);
    	
    	model.addAttribute("competencias",Application.competenciasDAO.listCompetences());
    	model.addAttribute("resultados",Application.resultadosDAO.listResults());
    	return "registerCompetencias";
	}
    
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/addResult")
    public String addResult(Model model,@RequestParam(name="competencia") String competencia,
    						@RequestParam(name="resultado") String resultado,
    						@RequestParam(name="nivel") String nivel) throws SQLException
	{
    	CompetenciaResultado competencia_resultado = new CompetenciaResultado();
    	competencia_resultado.setCompetencias_id(Integer.parseInt(competencia));
    	competencia_resultado.setResultados_id(Integer.parseInt(resultado));
    	
    	
    	Application.competencia_resultadosDAO.insert(competencia_resultado);
    	
    	model.addAttribute("competencias",Application.competenciasDAO.listCompetences());
    	model.addAttribute("resultados",Application.resultadosDAO.listResults());
    	return "registerCompetencias";
	}
    
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/registerCompetenciasNext")
    public String registerCompetenciaNext() throws SQLException
	{	
    	return "syllabusData2";
	}
    
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/syllabusData2")
    public String syllabusData2(Model model,@RequestParam(name="unidad1") String unidad1,
    							@RequestParam(name="unidad2") String unidad2,@RequestParam(name="unidad3") String unidad3) throws SQLException
	{	
    	Silabo silabus= new Silabo();
    	silabus = Application.silaboDAO.getSilabusById(foreignkey);
    	silabus.setContenido("PRIMERA UNIDAD: "+unidad1+"SEGUNDA UNIDAD: "+unidad2+"TERCERA UNIDAD: "+unidad3);
    	
    	Application.silaboDAO.update(silabus);
    	
    	model.addAttribute("estrategias",Application.estrategia_enseniansaDAO.listEstrategias());	
    	return "selectEstrategias";
	}
    
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/registerEstrategy")
    public String selectEstrategia(Model model,@RequestParam(name="selected") String selected,
    								@RequestParam(name="descripcion") String descripcion) throws SQLException
	{
    	SilaboEstrategia silabo_estrategia = new SilaboEstrategia(foreignkey,Integer.parseInt(selected),descripcion);
    	
    	Application.silabo_estrategiaDAO.insert(silabo_estrategia);
    	
    	model.addAttribute("estrategias",Application.estrategia_enseniansaDAO.listEstrategias());	
    	return "selectEstrategias";
	}
    
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/registerEstrategyNext")
    public String registerEstrategyNext() throws SQLException
	{
    	return "registerSchedule";
	}
    

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/registerCronograma")
    public String registerCronograma(Model model,@RequestParam(name = "te1") String te1,@RequestParam(name = "te2") String te2,
    		@RequestParam(name = "te3") String te3,@RequestParam(name = "te4") String te4,@RequestParam(name = "te5") String te5,
    		@RequestParam(name = "te6") String te6,@RequestParam(name = "te7") String te7,@RequestParam(name = "te8") String te8,
    		@RequestParam(name = "te9") String te9,@RequestParam(name = "te10") String te10,@RequestParam(name = "te11") String te11,
    		@RequestParam(name = "te12") String te12,@RequestParam(name = "te13") String te13,@RequestParam(name = "te14") String te14,
    		@RequestParam(name = "te15") String te15,@RequestParam(name = "te16") String te16,@RequestParam(name = "te17") String te17,
    		@RequestParam(name = "te18") String te18,@RequestParam(name = "te19") String te19,@RequestParam(name = "te20") String te20,
    		@RequestParam(name = "av1") String av1,@RequestParam(name = "av2") String av2,@RequestParam(name = "av3") String av3,
    		@RequestParam(name = "av4") String av4,@RequestParam(name = "av5") String av5,@RequestParam(name = "av6") String av6,
    		@RequestParam(name = "av7") String av7,@RequestParam(name = "av8") String av8,@RequestParam(name = "av9") String av9,
    		@RequestParam(name = "av10") String av10,@RequestParam(name = "av11") String av11,@RequestParam(name = "av12") String av12,
    		@RequestParam(name = "av13") String av13,@RequestParam(name = "av14") String av14,@RequestParam(name = "av15") String av15,
    		@RequestParam(name = "av16") String av16,@RequestParam(name = "av17") String av17,@RequestParam(name = "av18") String av18,
    		@RequestParam(name = "av19") String av19,@RequestParam(name = "av20") String av20) throws SQLException
    {
    	Subject curso =new Subject();
    	int id_asignatura =  Application.silaboDAO.getSilabusById(foreignkey).getId_asignatura();
    	curso = Application.subjectDAO.getSubjectByCasi(id_asignatura);
    	int semanas = curso.getDuration();
    	List<String> te = new ArrayList<String>();
    	te.add(te1);te.add(te2);te.add(te3);te.add(te4);te.add(te5);
    	te.add(te6);te.add(te7);te.add(te8);te.add(te9);te.add(te10);
    	te.add(te11);te.add(te12);te.add(te13);te.add(te14);te.add(te15);
    	te.add(te16);te.add(te17);te.add(te18);te.add(te19);te.add(te20);
    	
    	List<String> av = new ArrayList<String>();
    	
    	av.add(av1);av.add(av2);av.add(av3);av.add(av4);av.add(av5);
    	av.add(av6);av.add(av7);av.add(av8);av.add(av9);av.add(av10);
    	av.add(av11);av.add(av12);av.add(av13);av.add(av14);av.add(av15);
    	av.add(av16);av.add(av17);av.add(av18);av.add(av19);av.add(av20);
    	
    	for(int i=0;i<semanas;i++) {
    		Cronograma cronograma= new Cronograma(foreignkey,Integer.toString(i+1),te.get(i),Integer.parseInt(av.get(i)));
    		Application.cronogramaDAO.insert(cronograma);
    	}
    	
        return "registerEstrategiaEvaluacion";
    }
    
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/registerEstrategiaEvaluacion")
    public String registerEstrategiaEvaluacion(@RequestParam(name = "evcontinua") String evcontinua,@RequestParam(name = "evperiodica") String evperiodica,
    											@RequestParam(name = "fecha1") String fecha1,@RequestParam(name = "fecha2") String fecha2,
    											@RequestParam(name = "fecha3") String fecha3,@RequestParam(name = "et1") String et1,
    											@RequestParam(name = "et2") String et2,@RequestParam(name = "et3") String et3,
    											@RequestParam(name = "ec1") String ec1,@RequestParam(name = "ec2") String ec2,
    											@RequestParam(name = "ec3") String ec3,@RequestParam(name = "tipo_evaluacion") String tipo_evaluacion,
    											@RequestParam(name = "instrumento_evaluacion") String instrumento_evaluacion) throws SQLException
    {
    	Silabo silabo = new Silabo();
    	silabo= Application.silaboDAO.getSilabusById(foreignkey);
    	silabo.setEvaluacion_continua(evcontinua);
    	silabo.setEvaluacion_periodica(evperiodica);
    	silabo.setFECHA_EP1(fecha1);
    	silabo.setFECHA_EP2(fecha2);
    	silabo.setFECHA_EP3(fecha3);
    	silabo.setPORCENTAJE_EP1(Integer.parseInt(et1));
    	silabo.setPORCENTAJE_EP2(Integer.parseInt(et2));
    	silabo.setPORCENTAJE_EP3(Integer.parseInt(et3));
    	silabo.setPORCENTAJE_EC1(Integer.parseInt(ec1));
    	silabo.setPORCENTAJE_EC2(Integer.parseInt(ec2));
    	silabo.setPORCENTAJE_EC3(Integer.parseInt(ec3));
    	silabo.setTipos_evaluacion(tipo_evaluacion);
    	silabo.setInstrumentos(instrumento_evaluacion);
    	
    	Application.silaboDAO.update(silabo);

        return "registerRequirements";
    }
    
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/registerRequirements")
    public String registerRequirements(@RequestParam(name = "requisitos") String requisitos) throws SQLException
    {
    	Silabo silabo = new Silabo();
    	silabo= Application.silaboDAO.getSilabusById(foreignkey);
    	silabo.setRequisitos_evaluacion(requisitos);
    	
    	Application.silaboDAO.update(silabo);
    	
        return "registerBibliography";
    }
    
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/registerBibliography")
    public String registerBibliography(@RequestParam(name = "nombre") String nombre,@RequestParam(name = "fecha") String fecha,
    		@RequestParam(name = "autor") String autor,@RequestParam(name = "editorial") String editorial,
    		@RequestParam(name = "edicion") String edicion,@RequestParam(name = "tipo") String tipo) throws SQLException
    {
    	Bibliography bibliografia =  new Bibliography();
    	bibliografia.setNombre(nombre);
    	bibliografia.setAutor(autor);
    	bibliografia.setEditorial(editorial);
    	bibliografia.setEdicion(edicion);
    	bibliografia.setFecha(fecha);
    	
    	Application.bibliographyDAO.insert(bibliografia);
    	int id_bibliografia=Application.bibliographyDAO.getLastId();
    	
    	SilaboBibliografia silabo_bibliografia = new SilaboBibliografia();
    	silabo_bibliografia.setTipo(tipo);
    	silabo_bibliografia.setId_bibliografia(id_bibliografia);
    	silabo_bibliografia.setId_silabo(foreignkey);
    	
    	Application.silabo_bibliografiaDAO.insert(silabo_bibliografia);
    	
        return "registerBibliography";
    }

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/registerBibliographyNext")
    public String registerBibliographyNext() throws SQLException
    {
    	
    	return "redirect:index.html";
    }
    
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/evaluacionInformation")
    public String evaluacionInformation(Model model) throws SQLException
	{
    	
    	model.addAttribute("silabos",Application.silaboDAO.listSilabos());
    	return "registerEvaluacion";
	}
    
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/registerEvaluacion")
    public String registerEvaluacion(Model model,@RequestParam(name = "tipo") String tipo,@RequestParam(name = "evaluacion") String evaluacion,
    								@RequestParam(name = "peso") String peso,@RequestParam(name = "fecha") String fecha,
    								@RequestParam(name = "selected") String selected) throws SQLException
	{
    	Evaluacion evaluation= new Evaluacion();
    	evaluation.setSilabo_id(Integer.parseInt(selected));
    	evaluation.setTipo(tipo);
    	evaluation.setEvaluacion(evaluacion);
    	evaluation.setPeso(Integer.parseInt(peso));
    	evaluation.setFecha(fecha);
    	Application.evaluacionDAO.insert(evaluation);
    	
    	foreignkey=Application.evaluacionDAO.getLastId();
    	
    	
    	model.addAttribute("preguntas",Application.preguntaDAO.listPreguntas());
    	model.addAttribute("resultados",Application.resultadosDAO.listResults());
    	return "registerResPre";
	}
    
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/registerPregunta")
    public String registerPregunta(Model model,@RequestParam(name = "pregunta") String pregunta,
										@RequestParam(name = "respuesta") String respuesta) throws SQLException
	{
    	Pregunta quest=new Pregunta();
    	quest.setDescripcion(pregunta);
    	quest.setRespuesta(respuesta);
    	quest.setId_evaluacion(foreignkey);
    	Application.preguntaDAO.insert(quest);
    	
    	model.addAttribute("preguntas",Application.preguntaDAO.listPreguntas());
    	model.addAttribute("resultados",Application.resultadosDAO.listResults());
    	return "registerResPre";
	}
    
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/registerResPre")
    public String registerResPre(Model model,@RequestParam(name = "nivel") String nivel,
    		@RequestParam(name = "resultado") String resultado,@RequestParam(name = "pregunta") String pregunta) throws SQLException
	{
    	ResultadoPregunta res_pre = new ResultadoPregunta();
    	res_pre.setNivel(Integer.parseInt(nivel));
    	res_pre.setId_preguntas(Integer.parseInt(pregunta));
    	res_pre.setId_resultados(Integer.parseInt(resultado));
    	Application.res_preguntasDAO.insert(res_pre);
    	
    	
    	model.addAttribute("preguntas",Application.preguntaDAO.listPreguntas());
    	model.addAttribute("resultados",Application.resultadosDAO.listResults());
    	return "registerResPre";
	}
    
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/evaluacionEnd")
    public String evaluacionEnd() throws SQLException
	{
    	foreignkey=0;
    	return "redirect:index.html";
	}
    
}
