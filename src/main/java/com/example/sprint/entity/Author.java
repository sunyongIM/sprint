package com.example.sprint.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Author {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(nullable = false)
    private LocalDate birth;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<Register> registerList;

    @Builder
    public Author(Long id, String name, LocalDate birth){
        this.id = id;
        this.name = name;
        this.birth = birth;
    }
}
