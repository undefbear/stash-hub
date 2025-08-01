package ru.stashhub.entity;

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
    private String body;

    /** Тэг */
    private String tag; //TODO вынести в отдельную сущность

    /** Дата сохранения записи */
    private LocalDateTime created;
}
