package com.example.demo.taskmanager.service;
import com.example.demo.taskmanager.model.Task;
import com.example.demo.taskmanager.model.Estado;
import com.example.demo.taskmanager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository repository;

    public List<Task> listarTodas(){
        return repository.findAll();
    }

    public Task guardar(Task task){
        return repository.save(task);
    }

    public void eliminar( Long id){
        repository.deleteById(id);
    }

    public List<Task> buscarPorEstado(Estado estado){
        return repository.findByEstado(estado);
    }

    public long contarPendientes(){
        return repository.countByEstado(Estado.PENDIENTE);
    }
    public Task buscarPorId(Long id){

    return repository.findById(id).orElse(null);

}

}
