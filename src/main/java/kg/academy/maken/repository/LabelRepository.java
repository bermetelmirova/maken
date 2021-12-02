package kg.academy.maken.repository;

import kg.academy.maken.entity.Label;
import kg.academy.maken.model.LabelModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabelRepository extends JpaRepository<Label, Long> {
}
