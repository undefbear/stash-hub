package ru.stashhub.mapper;

import org.springframework.stereotype.Component;
import ru.stashhub.dto.StashItemDto;
import ru.stashhub.entity.StashItem;

import java.time.LocalDateTime;

@Component
public class StashItemMapper {

    public StashItemDto toDto(StashItem entity) {
        return StashItemDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .body(entity.getBody())
                .tag(entity.getTag())
                .category(entity.getCategory())
                .created(entity.getCreated())
                .build();
    }

    public StashItem toEntity(StashItemDto dto) {
        String category = dto.getCategory();
        /* Категория по умолчанию */ //TODO Сделать отдельную обработку входящих категорий и тегов
        if (category == null || category.isEmpty()) {
            category = "Inbox";
        }

        return StashItem.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .body(dto.getBody())
                .tag(dto.getTag())
                .category(category)
                .created(LocalDateTime.now())
                .build();
    }
}
