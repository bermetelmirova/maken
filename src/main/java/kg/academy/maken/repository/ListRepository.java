package kg.academy.maken.repository;

import kg.academy.maken.entity.List;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ListRepository extends JpaRepository<List, Long> {
    Optional<java.util.List<List>> findByDashboard(Long id);
}
