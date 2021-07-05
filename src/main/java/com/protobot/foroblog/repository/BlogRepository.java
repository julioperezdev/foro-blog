package com.protobot.foroblog.repository;

import com.protobot.foroblog.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {

    @Query(value = "EXEC saveBlog @Name = :name, @Dates = :dates, @Description = :description, @IdCategory = :idCategory ", nativeQuery = true)
    Blog saveBlog (@Param("name") String name,
                   @Param("dates") Instant dates,
                   @Param("description") String description,
                   @Param("idCategory") Long idCategory);

    @Query(value = "EXEC deleteBlogById @Id = :id ", nativeQuery = true)
    String deleteBlogById (@Param("id") Long id);
}
