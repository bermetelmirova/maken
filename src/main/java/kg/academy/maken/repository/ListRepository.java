package kg.academy.maken.repository;

import kg.academy.maken.entity.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ListRepository extends JpaRepository<List, Long> {
    Optional<java.util.List<List>> findByDashboard(Long id);

    Optional<List> findByStatusAndDashboard(Long idStatus, long idDashboard);

    Optional<List> findByName(String name);
}
