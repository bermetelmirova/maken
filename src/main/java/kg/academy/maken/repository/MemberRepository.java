package kg.academy.maken.repository;

import kg.academy.maken.entity.DashboardMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<DashboardMember, Long> {
    Optional<DashboardMember> findByDashboard(Long id);
}
