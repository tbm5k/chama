package com.chama.app.repository;

import com.chama.app.models.UserIntegrations;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserIntegrationsRepo extends CrudRepository<UserIntegrations, Integer> {

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM Ui_User_Integrations ui WHERE ui.ch_id_fk = :id"
    )
    Iterable<UserIntegrations> findByChamaId(@Param("id") int chamaId);

    @Query(nativeQuery = true,
            value = "SELECT * FROM Ui_User_Integrations WHERE us_id_fk = :userId")
    boolean existsByUser(@Param("userId") int userId);
}
