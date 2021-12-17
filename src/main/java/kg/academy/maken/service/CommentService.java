package kg.academy.maken.service;

import kg.academy.maken.entity.Comment;
import kg.academy.maken.model.commment_model.CommentGetModel;

import java.util.List;

public interface CommentService extends BaseService<Comment> {
    List<CommentGetModel> getByCardId(Long id);
}
