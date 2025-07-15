package com.noliter.myground.todo;

import com.noliter.myground.user.User;
import com.noliter.myground.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserRepository userRepository;

    public TodoDto createTodo(String username, TodoDto todoDto) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Todo todo = new Todo(todoDto.getText(), todoDto.isCompleted(), user);
        todo = todoRepository.save(todo);
        return new TodoDto(todo.getId(), todo.getText(), todo.isCompleted());
    }

    public List<TodoDto> getTodos(String username) {
        return todoRepository.findByUserUsername(username).stream()
                .map(todo -> new TodoDto(todo.getId(), todo.getText(), todo.isCompleted()))
                .collect(Collectors.toList());
    }

    public TodoDto updateTodo(Long id, TodoDto todoDto) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found"));
        todo.setText(todoDto.getText());
        todo.setCompleted(todoDto.isCompleted());
        todo = todoRepository.save(todo);
        return new TodoDto(todo.getId(), todo.getText(), todo.isCompleted());
    }

    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }
}
