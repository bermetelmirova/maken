package kg.academy.maken.repository;

import kg.academy.maken.entity.DashboardMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<DashboardMember, Long> {
    Optional<List<DashboardMember>> findByDashboard(Long id);
}
