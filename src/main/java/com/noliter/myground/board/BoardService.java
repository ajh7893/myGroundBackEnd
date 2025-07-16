package com.noliter.myground.board;

import com.noliter.myground.user.User;
import com.noliter.myground.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;

    public BoardDto createBoard(BoardDto boardDto, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        Board board = new Board(boardDto.getTitle(), boardDto.getContent(), user);
        board = boardRepository.save(board);
        return toDto(board);
    }

    public List<BoardDto> getAllBoards() {
        return boardRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    public BoardDto getBoardById(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new RuntimeException("Board not found"));
        return toDto(board);
    }

    public BoardDto updateBoard(Long id, BoardDto boardDto, String username) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new RuntimeException("Board not found"));
        if (!board.getUser().getUsername().equals(username)) {
            throw new RuntimeException("You are not authorized to update this board");
        }
        board.setTitle(boardDto.getTitle());
        board.setContent(boardDto.getContent());
        board = boardRepository.save(board);
        return toDto(board);
    }

    public void deleteBoard(Long id, String username) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new RuntimeException("Board not found"));
        if (!board.getUser().getUsername().equals(username)) {
            throw new RuntimeException("You are not authorized to delete this board");
        }
        boardRepository.delete(board);
    }

    private BoardDto toDto(Board board) {
        return new BoardDto(
                board.getId(),
                board.getTitle(),
                board.getContent(),
                board.getUser().getUsername(),
                board.getCreatedAt(),
                board.getUpdatedAt()
        );
    }
}
