package ru.stashhub.mapper;

import org.springframework.stereotype.Component;
import ru.stashhub.dto.StashItemDto;
import ru.stashhub.entity.StashItem;

@Component
public class StashItemMapper {

    public StashItemDto toDto(StashItem entity) {
        return StashItemDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .body(entity.getBody())
                .tag(entity.getTag())
                .created(entity.getCreated())
                .build();
    }

    public StashItem toEntity(StashItemDto dto) {
        return StashItem.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .body(dto.getBody())
                .tag(dto.getTag())
                .created(dto.getCreated())
                .build();
    }
}
