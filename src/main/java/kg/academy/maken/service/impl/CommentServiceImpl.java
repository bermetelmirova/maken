package kg.academy.maken.service.impl;

import kg.academy.maken.entity.Comment;
import kg.academy.maken.repository.CommentRepository;
import kg.academy.maken.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment findById(Long id) {
        return commentRepository.findById(id).orElse(null);
    }

    @Override
    public Comment deleteById(Long id) {
        Comment comment = findById(id);
        if (comment != null)
            commentRepository.deleteById(id);
        return comment;
    }
}
