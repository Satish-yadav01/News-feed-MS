package com.satish.finoutservice.repo.graph;

import com.satish.finoutservice.model.entity.graph.Friends;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : Satish Yadav
 * @purpose :
 */
@Repository
public interface FriendsRepository extends JpaRepository<Friends,Long> {
}
