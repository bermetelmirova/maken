package kg.academy.maken.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor

public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    @PostPersist
    public void prePersist() {
        this.createDate = LocalDateTime.now();
    }

    @PostUpdate
    public void preUpdate() {
        this.updateDate = LocalDateTime.now();
    }
}