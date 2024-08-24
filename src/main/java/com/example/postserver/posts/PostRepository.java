package com.example.postserver.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Integer> {

    List<PostEntity> findByUploaderId(int uploaderId);
    long countByUploaderId(int uploaderId);
    @Modifying @Query("UPDATE post p SET p.status = :status WHERE p.uploaderId = :uploaderId")
    int updatePostStatusByUploaderId(@Param("uploaderId") int userId, @Param("status") String status);

    @Query("SELECT DISTINCT p.uploaderId FROM post p WHERE p.status = :status")
    List<Integer> findDistinctUploaderIdsByStatus(@Param("status") String status);
}
