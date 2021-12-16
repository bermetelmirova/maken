package kg.academy.maken.converter;

import kg.academy.maken.entity.Comment;
import kg.academy.maken.model.CommentModel;
import org.springframework.stereotype.Component;

@Component
public class CommentConverter implements BaseConverter<CommentModel, Comment> {
    @Override
    public CommentModel convertToModel(Comment comment) {
        if (comment == null) return null;
        return CommentModel.builder()
                .ID(comment.getId())
                .text(comment.getText())
                .build();
    }

    @Override
    public Comment convertToEntity(CommentModel commentModel) {
        if (commentModel == null) return null;
        return Comment.builder()
                .text(commentModel.getText())
                .build();
    }
}
