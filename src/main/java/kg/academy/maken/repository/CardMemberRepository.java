package kg.academy.maken.repository;

import kg.academy.maken.entity.CardMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardMemberRepository extends JpaRepository<CardMember, Long> {
    @Query(value = "select * from card_members where card_id =:id", nativeQuery = true)
    Optional<List<CardMember>> findByCardId(Long id);

    @Query(value = "select * from card_members where card_id = :c_id and member_id =:m_id", nativeQuery = true)
    Optional<CardMember> findByCardAndDashboardMember(@Param("c_id") Long cardId, @Param("m_id") Long userID);
}
