package com.chama.app.services;

import com.chama.app.models.UserIntegrations;
import com.chama.app.repository.UserIntegrationsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserIntegrationsService {

    @Autowired
    UserIntegrationsRepo integrationsRepo;

    public List<UserIntegrations> findByChama(int chamaId) {
        List<UserIntegrations> members = new ArrayList<>();
        integrationsRepo.findByChamaId(chamaId).forEach(members::add);
        return members;
    }
}
