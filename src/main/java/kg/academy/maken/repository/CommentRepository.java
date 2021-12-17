package kg.academy.maken.repository;

import kg.academy.maken.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query(value ="select * FROM comments where card_id = :id", nativeQuery = true)
    Optional<List<Comment>> findByCard(@Param("id") Long id);
}
