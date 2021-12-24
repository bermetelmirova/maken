package kg.academy.maken.repository;

import kg.academy.maken.entity.Dashboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DashboardRepository extends JpaRepository<Dashboard, Long> {
    @Query(value = "SELECT d.* FROM dashboards d join members m on m.dashboard_id = d.id WHERE m.user_id = :id ", nativeQuery = true)
    Optional<List<Dashboard>> findByUser(@Param("id") Long id);
}
