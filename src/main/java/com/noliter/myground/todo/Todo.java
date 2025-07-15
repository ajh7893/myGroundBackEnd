package com.noliter.myground.todo;

import com.noliter.myground.user.User;
import jakarta.persistence.*;

@Entity

public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private boolean completed;

    // 한 명의 유저는 여러 개의 할 일을 가질 수 있다 (ManyToOne)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username") // 외래키
    private User user;

    // 기본 생성자
    public Todo() {}
    // getter, setter
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
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
        public Todo(String text, boolean completed, User user) {
        this.text = text;
        this.completed = completed;
        this.user = user;
    }
    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", completed=" + completed + '}';
    }
}