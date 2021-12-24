package kg.academy.maken.repository;


import kg.academy.maken.entity.DashboardMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DashboardMemberRepository extends JpaRepository<DashboardMember, Long> {
    @Query(value = "SELECT * FROM members WHERE dashboard_id = :dash_id AND user_id = :user_id", nativeQuery = true)
    Optional<DashboardMember> findByDashboardIdAndUserId(@Param("dash_id") Long id, @Param("user_id") Long userId);

    @Query(value = "SELECT * FROM members WHERE dashboard_id = :dash_id ", nativeQuery = true)
    Optional<List<DashboardMember>> findByDashboard(@Param("dash_id") Long id);

}
