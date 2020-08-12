package com.chama.app.services;

import com.chama.app.models.Member;
import com.chama.app.repository.MemberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService {

    @Autowired
    MemberRepo memberRepo;

    public List<Member> findAllMembers(){
        List<Member> list = new ArrayList<>();
        memberRepo.findByChamaForeignKey(0).forEach(list::add);
        return list;
    }

}
