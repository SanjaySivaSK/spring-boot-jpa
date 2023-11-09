package com.codewithsanjay.springbootjpa.Service;

import com.codewithsanjay.springbootjpa.Respository.AppUserRepository;
import com.codewithsanjay.springbootjpa.Respository.TodoRespository;
import com.codewithsanjay.springbootjpa.model.AppUser;
import com.codewithsanjay.springbootjpa.model.Todo;
import com.codewithsanjay.springbootjpa.request.TodoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoService {
    @Autowired
    private TodoRespository todoRespository;

    @Autowired
    private AppUserRepository appUserRepository;

    public List<Todo> findAll(int userId) {
        return todoRespository.findAll()
                .stream()
                .filter(todo -> todo.getAppUser().getId() == userId)
                .collect(Collectors.toList());
    }

    public List<Todo> addTodo(TodoRequest todo) {
        Todo originalTodo = new Todo();
        originalTodo.setTodo(todo.getTodo());
        AppUser user = appUserRepository.findById(todo.getUserId()).get();
        originalTodo.setAppUser(user);
        todoRespository.save(originalTodo);
        return findAll(todo.getUserId());
    }

    public List<Todo> update(TodoRequest todo) {
        Todo originalTodo = new Todo();
        originalTodo.setId(todo.getId());
        originalTodo.setTodo(todo.getTodo());
        AppUser user = appUserRepository.findById(todo.getUserId()).get();
        originalTodo.setAppUser(user);
        todoRespository.save(originalTodo);
        return findAll(todo.getUserId());
    }

    public List<Todo> delete(Integer id) {
        int userId = todoRespository.findById(id).get().getAppUser().getId();
        todoRespository.deleteById(id);
        return findAll(userId);
    }
}
