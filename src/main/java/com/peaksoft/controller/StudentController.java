package com.peaksoft.controller;
import com.peaksoft.entity.Group;
import com.peaksoft.entity.Student;
import com.peaksoft.service.GroupService;
import com.peaksoft.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/students")

public class StudentController {
    private final GroupService groupService;
     private final StudentService studentService;
     @Autowired
    public StudentController(GroupService groupService, StudentService studentService) {
         this.groupService = groupService;
         this.studentService = studentService;
    }
    @ModelAttribute("groupList")
    public List<Group> findAllGroup(){
         return groupService.getAllGroups();
    }
    @GetMapping()
    public String getAllStudent(Model model){
        model.addAttribute("students",studentService.getAllStudents());
        return "student/student";
    }
    @GetMapping("/addStudent")
    public String addStudent(Model model){
        model.addAttribute("student",new Student());
        return "student/addStudent";
    }
    @PostMapping("/saveStudent")
    public String saveStudent(@ModelAttribute("student")Student student){
        studentService.saveStudent(student);
        return "redirect:/students";
    }
    @GetMapping("/{id}/update")
    public String updateStudent(@RequestParam("studentId")Long id, Model model){
        Student student=studentService.getStudentById(id);
        model.addAttribute("student",student);
        return "student/updateStudent";
    }
    @PatchMapping("/saveUpdateStudent")
    public String saveUpdateStudent(@ModelAttribute("student")Student student){
        studentService.updateStudent(student);
        return "redirect:/students";
    }
    @DeleteMapping("/deleteStudent")
    public String deleteStudent(@RequestParam("studentId")Long id){
        studentService.deleteStudent(studentService.getStudentById(id));
        return "redirect:/students";
    }


}