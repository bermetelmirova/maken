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

    @Query(value = "select * from cards where status_id = 3", nativeQuery = true)
    List<Card> getDoneCards();

    @Query(value = "select u.email from card_members cm  join members m on cm.member_id = m.id" +
            " join users u on u.id = m.user_id where cm.card_id = :id", nativeQuery = true)
    Optional<List<String>> getEmails(@Param("id") Long id);

    @Query(value = "select u.email from  members m join users u on m.user_id = u.id where m.id = :id", nativeQuery = true)
    Optional<String> getEmailOfAddUser(@Param("id") Long id);

    @Query(value = "select * from cards where create_date >= current_date at time zone 'UTC' - interval '7 days' " +
            "and status_id = 3 ORDER by admin_rating desc ", nativeQuery = true)
    Optional<List<Card>> getRatingCards();

}
