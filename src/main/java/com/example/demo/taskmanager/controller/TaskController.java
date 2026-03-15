package com.example.demo.taskmanager.controller;

import com.example.demo.taskmanager.model.Task;
import com.example.demo.taskmanager.model.Estado;
import com.example.demo.taskmanager.service.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/board")
public class TaskController {

@Autowired
private TaskService service;

@GetMapping
public String listar(Model model){

model.addAttribute("pendientes", service.buscarPorEstado(Estado.PENDIENTE));
model.addAttribute("progreso", service.buscarPorEstado(Estado.EN_PROGRESO));
model.addAttribute("terminadas", service.buscarPorEstado(Estado.TERMINADO));

return "board";
}

@PostMapping("/crear")
public String crear(Task task){

service.guardar(task);

return "redirect:/board";
}

@PutMapping("/mover/{id}/{estado}")
@ResponseBody
public void mover(@PathVariable("id") Long id,
@PathVariable("estado") Estado estado){

Task task = service.buscarPorId(id);

task.setEstado(estado);

service.guardar(task);

}

@DeleteMapping("/eliminar/{id}")
@ResponseBody
public void eliminar(@PathVariable("id") Long id){

service.eliminar(id);

}

}