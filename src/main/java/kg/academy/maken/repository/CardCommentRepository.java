package kg.academy.maken.repository;

import kg.academy.maken.entity.CardComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CardCommentRepository extends JpaRepository<CardComment, Long> {
    Optional<List<CardComment>> findByCard(Long id);
}
