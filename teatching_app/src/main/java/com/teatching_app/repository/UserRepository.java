package com.teatching_app.repository;

import com.teatching_app.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

    @Query("from UserEntity u where u.username = :username and u.isDeleted = false ")
    Optional<UserEntity> findUserByUsername(@Param("username") String username);

    @Query("from UserEntity u where u.id = :id and u.isDeleted = false ")
    Optional<UserEntity> findUserByUserId(@Param("id") Long id);

    @Query("from UserEntity u where u.email = :email and u.isDeleted = false ")
    Optional<UserEntity>  findUserByEmail(@Param("email")String email);
}
