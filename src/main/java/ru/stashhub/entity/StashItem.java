package ru.stashhub.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "stash_item")
public class StashItem {
    /** Идентификатор */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Заголовок */
    private String title;

    /** Информация для сохранения */
    @Column(length = 10000)
    private String body;

    /** Тэг */
    private String tag; //TODO вынести в отдельную сущность

    /** Группа/категория */
    private String category; //TODO вынести в отдельную сущность

    /** Дата сохранения записи */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created;
}
