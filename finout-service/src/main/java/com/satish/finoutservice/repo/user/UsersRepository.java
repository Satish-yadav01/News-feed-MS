package com.satish.finoutservice.repo.user;

import com.satish.finoutservice.model.entity.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : Satish Yadav
 * @purpose :
 */
@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
}
