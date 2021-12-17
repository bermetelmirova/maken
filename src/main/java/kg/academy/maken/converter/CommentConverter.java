package kg.academy.maken.converter;

import kg.academy.maken.entity.Comment;
import kg.academy.maken.model.commment_model.CommentGetModel;
import org.springframework.stereotype.Component;

@Component
public class CommentConverter implements BaseConverter<CommentGetModel, Comment>{

    @Override
    public Comment convertToEntity(CommentGetModel commentGetModel) {
        return Comment.builder()
                .text(commentGetModel.getComment())
                .build();
    }

    @Override
    public CommentGetModel convertToModel(Comment comment) {
        return CommentGetModel.builder()
                .ID(comment.getId())
                .userId(comment.getUser().getId())
                .comment(comment.getText())
                .build();
    }
}
