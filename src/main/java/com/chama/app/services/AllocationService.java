package com.chama.app.services;

import com.chama.app.repository.AllocationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AllocationService {

    @Autowired
    AllocationRepo allocationRepo;

}
