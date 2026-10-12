package com.PI.cinemix.repository;

import com.PI.cinemix.entity.UsersEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, Long>{
    Optional<UsersEntity> findByEmail(String email);
}
