package com.chama.app.services;

import com.chama.app.models.MemberContribution;
import com.chama.app.repository.ContributionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContributionService {

    @Autowired
    ContributionRepo contributionRepo;

    public void makeContribution(MemberContribution memberContribution) {
        contributionRepo.save(memberContribution);
    }

    public List<MemberContribution> getMembersContributions(int chamaId) {
        List<MemberContribution> contributionList = new ArrayList<>();

        List<MemberContribution> contributions = (List<MemberContribution>) contributionRepo.findAll();
        for (MemberContribution contribution : contributions){
            if(contribution.getMember().getChama().getChamaId() == chamaId){
                contributionList.add(contribution);
            }
        }

        return contributionList;
    }

    public void denyContribution(int contributionId) {
        contributionRepo.deleteById(contributionId);
    }

    public void updateContribution(MemberContribution contribution) {
        contributionRepo.save(contribution);
    }

    public MemberContribution getMembersContribution(int memberContributionId) {
        return contributionRepo.findById(memberContributionId).get();
    }

    public MemberContribution getMemberContribution(int userIntegrationsId) {
        List<MemberContribution> contributions = contributionRepo.findAllByMember(userIntegrationsId);
        //returning the last member contribution which is the latest contribution
        return contributions.get(contributions.size()-1);
    }
}
