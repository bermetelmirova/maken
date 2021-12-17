package kg.academy.maken.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "images")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Image extends BaseEntity {
    @Column(name = "image_url")
    private String imageUrl;
}
