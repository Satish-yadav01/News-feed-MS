package com.satish.postservice.repo;

import com.satish.postservice.model.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : Satish Yadav
 * @purpose :
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
