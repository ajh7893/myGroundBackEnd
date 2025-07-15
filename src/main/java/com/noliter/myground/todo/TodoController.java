package com.noliter.myground.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @PostMapping
    public ResponseEntity<TodoDto> createTodo(@AuthenticationPrincipal UserDetails userDetails, @RequestBody TodoDto todoDto) {
        return ResponseEntity.ok(todoService.createTodo(userDetails.getUsername(), todoDto));
    }

    @GetMapping
    public ResponseEntity<List<TodoDto>> getTodos(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(todoService.getTodos(userDetails.getUsername()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoDto> updateTodo(@PathVariable Long id, @RequestBody TodoDto todoDto) {
        return ResponseEntity.ok(todoService.updateTodo(id, todoDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.ok().build();
    }
}
