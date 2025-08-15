package org.example.entities;

import jakarta.persistence.*;
import lombok.*;
/*
    Category entity - e.g. "Work"
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "userId")
    private String userId; //one user might have many categories

}
