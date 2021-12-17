package kg.academy.maken.repository;

import kg.academy.maken.entity.CardMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardMemberRepository extends JpaRepository<CardMember, Long> {
    Optional<List<CardMember>> findByCard(Long id);
    Optional<CardMember> findByCardAndDashboardMember(Long cardId, Long userID);
}
