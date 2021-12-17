package kg.academy.maken.repository;

import kg.academy.maken.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    @Query(value = "select * from cards where list_id = :id", nativeQuery = true)
    Optional<List<Card>> findByListId(@Param("id") Long id);
    @Query(value = "select l.dashboard_id from lists l join cards c on l.id = c.list_id where c.id= :cardId", nativeQuery = true)
    Long findDashboardByCardId(@Param("cardId") Long id);
}
