package com.example.demo.controllers;

import com.example.demo.models.Teacher;
import com.example.demo.repositories.TeacherRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherRepository teacherRepository;

    public TeacherController(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @GetMapping
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    @GetMapping("/{id}")
    public Teacher getTeacherById(@PathVariable int id) {
        return teacherRepository.findById(id).orElse(null);
    }

    @PostMapping
    public  Teacher addTeacher(@RequestBody Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @PutMapping("/{id}")
    public Teacher updateTeacher(@PathVariable int id, @RequestBody Teacher teacherDetails) {
        Teacher teacher = teacherRepository.findById(id).orElse(null);
        if (teacher != null) {
            // Update teacher details
            teacher.setName(teacherDetails.getName());
            teacher.setEmail(teacherDetails.getEmail());
            return teacherRepository.save(teacher);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteTeacher(@PathVariable int id) {
        teacherRepository.deleteById(id);
    }

}
