package com.chama.app.repository;

import com.chama.app.models.UserIntegrations;
import org.springframework.data.repository.CrudRepository;

public interface UserIntegrationsRepo extends CrudRepository<UserIntegrations, Integer> {

    Iterable<UserIntegrations> findByChamaId(int chamaId);
}
