package ru.stashhub.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.stashhub.dto.StashItemDto;
import ru.stashhub.service.StashItemService;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/ui/stash")
@RequiredArgsConstructor
public class StashItemHtmlController {

    private final StashItemService service;

    /**
     * GET /stash Показать все сохраненки.
     */

    @GetMapping
    public String findAll(Model model) {
        List<StashItemDto> items = service.findAll();

        // Группировка по категориям //TODO "Work" и "work" должны быть одинаковыми?
        Map<String, List<StashItemDto>> categoryItems = items.stream()
                .collect(Collectors.groupingBy(StashItemDto::getCategory));

        // Сортировка. Inbox будет показывать вверху, остальные категории — в алфавитном порядке
        LinkedHashMap<String, List<StashItemDto>> sorted = categoryItems.entrySet().stream()
                .sorted((c1, c2) -> {
                    if ("Inbox".equals(c1.getKey())) return -1; // c1 < c2 -> c1 ставим выше c2
                    if ("Inbox".equals(c2.getKey())) return 1; // c1 > c2
                    return c1.getKey().compareToIgnoreCase(c2.getKey());
                })
                .collect(LinkedHashMap::new,
                        (map, category) -> map.put(category.getKey(), category.getValue()),
                        LinkedHashMap::putAll);

        // Список категорий
        Set<String> categories = items.stream()
                .map(StashItemDto::getCategory)
                .collect(Collectors.toSet());

        // Список тегов
        Set<String> tags = items.stream()
                .map(StashItemDto::getTag)
                .filter(Objects::nonNull)
                .filter(tag -> !tag.trim().isEmpty())
                .collect(Collectors.toSet());

        model.addAttribute("categoryItems", sorted);
        model.addAttribute("categories", categories);
        model.addAttribute("tags", tags);
        model.addAttribute("stashItem", new StashItemDto());
        model.addAttribute("items", items);
        return "index";
    }

    /**
     * POST /stash Создать сохраненку.
     */

    @PostMapping
    public String create(@ModelAttribute StashItemDto dto) {
        service.save(dto);
        return "redirect:/ui/stash";
    }

    /**
     * POST /stash/{id} Удалить сохраненку.
     */

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteById(id);
        return "redirect:/ui/stash";
    }
    /**
     * POST /stash/{id} Внести изменения в сохраненку.
     */

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, @ModelAttribute("stashItem") StashItemDto stashItemDto) {
        stashItemDto.setId(id);
        service.save(stashItemDto);
        return "redirect:/ui/stash";
    }
}