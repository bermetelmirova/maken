package kg.academy.maken.repository;

import kg.academy.maken.entity.CardMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CardMemberRepository extends JpaRepository<CardMember, Long> {
    Optional<List<CardMember>> findByCard(Long id);
}
