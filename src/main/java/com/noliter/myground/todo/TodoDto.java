package com.noliter.myground.todo;

public class TodoDto {
    private Long id;
    private String text;
    private boolean completed;

    public TodoDto(Long id, String text, boolean completed) {
        this.id = id;
        this.text = text;
        this.completed = completed;
    }

    //getter, setter
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public boolean isCompleted() {
        return completed;
    }
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public TodoDto() {}
    public TodoDto(String text, boolean completed) {
        this.text = text;
        this.completed = completed;
    }
    @Override
    public String toString() {
        return "TodoDto{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", completed=" + completed +
                '}';
    }
}
