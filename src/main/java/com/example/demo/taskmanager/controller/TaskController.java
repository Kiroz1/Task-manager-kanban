package com.example.demo.taskmanager.controller;

import com.example.demo.taskmanager.model.Task;
import com.example.demo.taskmanager.model.Estado;
import com.example.demo.taskmanager.service.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService service;

    @GetMapping
    public String listar(Model model){

        List<Task> tasks = service.listarTodas();
        long pendientes = service.contarPendientes();

        model.addAttribute("tasks", tasks);
        model.addAttribute("pendientes", pendientes);

        return "tasks";
    }

    @PostMapping("/crear")
    public String crear(Task task){
        service.guardar(task);
        return "redirect:/tasks";
    }

   
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Long id){
    service.eliminar(id);
    return "redirect:/tasks";
}

    @GetMapping("/estado/{estado}")
    public String filtrar(@PathVariable("estado") Estado estado, Model model){

        List<Task> tasks = service.buscarPorEstado(estado);

        model.addAttribute("tasks", tasks);
        model.addAttribute("pendientes", service.contarPendientes());

        return "tasks";
    }
}