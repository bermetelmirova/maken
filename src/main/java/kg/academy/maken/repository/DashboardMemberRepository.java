package kg.academy.maken.repository;

import kg.academy.maken.entity.DashboardMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DashboardMemberRepository extends JpaRepository<DashboardMember, Long> {
    Optional<List<DashboardMember>> findByDashboard(Long id);
}
