package kg.academy.maken.service.impl;

import kg.academy.maken.converter.CommentConverter;
import kg.academy.maken.entity.Comment;
import kg.academy.maken.exception.ApiException;
import kg.academy.maken.model.commment_model.CommentGetModel;
import kg.academy.maken.repository.CommentRepository;
import kg.academy.maken.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private CommentConverter commentConverter;

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
        return commentRepository.findById(id).orElseThrow(() -> new ApiException("Комментария нет", HttpStatus.NO_CONTENT));
    }

    @Override
    public Comment deleteById(Long id) {
        Comment comment = findById(id);
        if (comment == null)
            throw new ApiException("Карта не найдена!", HttpStatus.BAD_REQUEST);
        commentRepository.deleteById(id);
        return comment;
    }

    @Override
    public List<CommentGetModel> getByCardId(Long id) {
        List<Comment> comments = commentRepository.findByCard(id)
                .orElseThrow(() -> new ApiException("Нет  комментариев", HttpStatus.NO_CONTENT));
        return comments
                .stream().map(commentConverter::convertToModel)
                .collect(Collectors.toList());
    }
}
