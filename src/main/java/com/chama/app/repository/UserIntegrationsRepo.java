package com.chama.app.repository;

import com.chama.app.models.User;
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
            value = "SELECT * from Ui_User_Integrations ui where ui.us_id_fk = :id")
    Iterable<UserIntegrations> findByUser(@Param("id") int userId);

    @Query(nativeQuery = true,
    value = "select * from Ui_User_Integrations where us_id_fk = :userId and ch_id_fk = :chamaId")
    UserIntegrations findByUserAndChama(@Param("userId") int userId,@Param("chamaId") int chamaId);
}
