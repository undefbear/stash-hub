package ru.stashhub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.stashhub.entity.StashItem;

import java.util.List;

public interface StashItemRepository extends JpaRepository<StashItem, Long> {
    List<StashItem> findByTag(String tag);

    List<StashItem> findByCategory(String category);

    @Query("""
            SELECT s FROM StashItem s
            WHERE LOWER(s.title) LIKE LOWER(CONCAT('%', :text, '%'))
            OR LOWER(s.body) LIKE LOWER(CONCAT('%', :text, '%'))
            """)
    List<StashItem> searchByText(@Param("text") String text);

}
