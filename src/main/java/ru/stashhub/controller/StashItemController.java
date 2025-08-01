package ru.stashhub.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.stashhub.dto.StashItemDto;
import ru.stashhub.service.StashItemService;

import java.util.List;

@RestController
@RequestMapping("/stash")
@RequiredArgsConstructor
public class StashItemController {

    private final StashItemService service;

    /**
     * GET /stash Показать все сохраненки.
     */

    @GetMapping
    public List<StashItemDto> getAll() {
        return service.findAll();
    }

    /**
     * GET /stash/{id} Найти сохраненку по айдишнику.
     */

    @GetMapping("/{id}")
    public ResponseEntity<StashItemDto> getById(@PathVariable Long id) {
        StashItemDto dto = service.findById(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }

    /**
     * POST /stash Создать сохраненку.
     */

    @PostMapping
    public StashItemDto create(@RequestBody StashItemDto dto) {
        return service.save(dto);
    }

    /**
     * PUT /stash/{id} Внести изменения в сохраненку.
     */

    @PutMapping("/{id}")
    public ResponseEntity<StashItemDto> update(@PathVariable Long id, @RequestBody StashItemDto dto) {
        if (service.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        dto.setId(id);
        return ResponseEntity.ok(service.save(dto));
    }

    /**
     * DELETE /stash/{id} Удалить сохраненку.
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * GET /stash/search Поиск сохраненки по тексту.
     */

    @GetMapping("/search")
    public List<StashItemDto> search(@RequestParam String q) {
        return service.searchByText(q);
    }
}
