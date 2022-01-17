package com.teatching_app.repository;

import com.teatching_app.model.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Short> {

    @Query("select  r.roleName from RoleEntity r where r.roleName <> 'ADMIN'")
    List<String> findRoleNameWithoutAdmin();

    @Query("from RoleEntity r where r.roleName = :roleName")
    RoleEntity findRoleByName(@Param("roleName") String roleName);
}
