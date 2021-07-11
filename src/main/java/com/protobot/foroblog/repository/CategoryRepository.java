package com.protobot.foroblog.repository;

import com.protobot.foroblog.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value = "EXEC saveCategory @Name = :name ", nativeQuery = true)
    Category saveCategory (@Param("name") String name);

    @Query(value = "EXEC deleteCategoryById @Id = :id ", nativeQuery = true)
    String deleteCategoryById (@Param("id") Long id);

}
