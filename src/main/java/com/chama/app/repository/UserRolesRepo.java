package com.chama.app.repository;

import com.chama.app.models.UserRoles;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRolesRepo extends CrudRepository<UserRoles, Integer> {

    @Query(nativeQuery = true,
            value = "select * from ur_user_roles where us_id_fk = :us_id_fk")
    UserRoles findByUserForeignKey(@Param("us_id_fk") int userForeignKey);
}
