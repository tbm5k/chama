package com.chama.app.repository;

import com.chama.app.models.UserIntegrations;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserIntegrationsRepo extends CrudRepository<UserIntegrations, Integer> {

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM Ui_User_Integrations WHERE ch_id_fk = 2;"
    )
    Iterable<UserIntegrations> findByChama();
}
