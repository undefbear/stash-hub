package ru.stashhub.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StashItemDto {
    /** Идентификатор */
    private Long id;

    /** Заголовок */
    private String title;

    /** Информация для сохранения */
    private String body;

    /** Тэг */
    private String tag;

    /** Группа */
    private String category;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created;
}
