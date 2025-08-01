package ru.stashhub.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.stashhub.dto.StashItemDto;
import ru.stashhub.entity.StashItem;
import ru.stashhub.mapper.StashItemMapper;
import ru.stashhub.repository.StashItemRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StashItemService {

    private final StashItemRepository repository;
    private final StashItemMapper mapper;

    public List<StashItemDto> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public StashItemDto findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElse(null);
    }

    public StashItemDto save(StashItemDto dto) {
        StashItem entity = mapper.toEntity(dto);
        StashItem saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public List<StashItemDto> searchByText(String text) {
        return repository.searchByText(text)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}

