package kg.academy.maken.repository;

import kg.academy.maken.entity.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ListRepository extends JpaRepository<List, Long> {
    @Query(value = "select * from lists where dashboard_id = :id", nativeQuery = true)
    Optional<java.util.List<List>> findByDashboardId(@Param("id") Long id);

    @Query(value = "select * from lists where status_id=:s_id and dashboard_id =:d_id", nativeQuery = true)
    Optional<List> findByStatusIdAndDashboardId(@Param("s_id") Long idStatus, @Param("d_id") Long idDashboard);

    Optional<List> findByName(String name);
}
