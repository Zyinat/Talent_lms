package com.peaksoft.controller;

import com.peaksoft.entity.Teacher;
import com.peaksoft.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/teachers")
public class TeacherController {
    private final TeacherService teacherService;

@Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }
    @GetMapping()
    public String getAllTeacher(Model model){
        List<Teacher> teachers=teacherService.getAllTeachers();
        model.addAttribute("teachers",teachers);
        return "teacher/teachers";
    }
    @GetMapping("/addTeacher")
    public String addTeacher(Model model){
        model.addAttribute("teacher",new Teacher());
        return "teacher/addTeacher";
    }
    @PostMapping("/saveTeacher")
    public String saveTeachers(@ModelAttribute("teacher")Teacher teacher){
        teacherService.saveTeacher(teacher);
        return "redirect:/teachers";
    }
    @GetMapping("/{id}/update")
    public String updateCompany(Model model, @PathVariable("id") long id){
        model.addAttribute("teacherUpdate",teacherService.getTeacherById(id));
        return "teacher/updateTeacher";
    }


    @PatchMapping("/saveUpdateTeacher")
    public String saveUpdateTeacher(@ModelAttribute("teacher")Teacher teacher){
        teacherService.updateTeacher(teacher);
        return "redirect:/teachers";
    }
    @DeleteMapping("/deleteTeacher")
    public String deleteTeacher(@RequestParam("teacherId")Long id){
       teacherService .deleteTeacher(teacherService.getTeacherById(id));
        return "redirect:/teachers";
    }

}
