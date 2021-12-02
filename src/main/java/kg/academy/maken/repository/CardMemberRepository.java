package kg.academy.maken.repository;

import kg.academy.maken.entity.CardMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardMemberRepository extends JpaRepository<CardMember,Long> {
}
