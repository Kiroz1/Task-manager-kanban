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

    model.addAttribute("pendientes", service.buscarPorEstado(Estado.PENDIENTE));
    model.addAttribute("progreso", service.buscarPorEstado(Estado.EN_PROGRESO));
    model.addAttribute("terminadas", service.buscarPorEstado(Estado.TERMINADO));

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
    @GetMapping("/mover/{id}/{estado}")
    public String mover(
        @PathVariable("id") Long id,
        @PathVariable("estado") Estado estado){

    Task task = service.buscarPorId(id);

    task.setEstado(estado);

    service.guardar(task);

    return "redirect:/tasks";
}
}