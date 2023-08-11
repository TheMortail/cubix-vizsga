package hu.cubix.spring.gyuri.logistics.service;

import hu.cubix.spring.gyuri.logistics.config.TransportPlansProperties;
import hu.cubix.spring.gyuri.logistics.dto.DelayDto;
import hu.cubix.spring.gyuri.logistics.model.Milestone;
import hu.cubix.spring.gyuri.logistics.model.Section;
import hu.cubix.spring.gyuri.logistics.model.TransportPlan;
import hu.cubix.spring.gyuri.logistics.repository.TransportPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class TransportPlansService {

    @Autowired
    private TransportPlanRepository transportPlanRepository;

    @Autowired
    private MilestoneService milestoneService;

    @Autowired
    private TransportPlansProperties transportConfig;

    public void delay(Long id, DelayDto dto){
        Optional<TransportPlan> transportPlanOptional = transportPlanRepository.findById(id);
        Optional<Milestone> milestoneOptional = milestoneService.findById(dto.getMilestoneId());
        if (transportPlanOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (milestoneOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        TransportPlan transport = transportPlanOptional.get();
        Milestone milestone = milestoneOptional.get();
        Section foundSection = findSectionWithMilestone(milestone, transport);
        if (foundSection == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        milestone.setPlannedTime(
                milestone.getPlannedTime().plusMinutes(dto.getMinutes()));
        milestoneService.save(milestone);

        if (foundSection.getFromMilestone() == milestone){
            foundSection.getToMilestone().setPlannedTime(
                    foundSection.getToMilestone().getPlannedTime().plusMinutes(dto.getMinutes()));
            milestoneService.save(foundSection.getToMilestone());

        }else{
            Section nextSection = findSectionWithNumber(foundSection.getNumber()+1L, transport);
            if (nextSection != null){
                nextSection.getFromMilestone().setPlannedTime(
                        nextSection.getFromMilestone().getPlannedTime().plusMinutes(dto.getMinutes()));
                milestoneService.save(nextSection.getFromMilestone());
            }
        }

        if (dto.getMinutes() >= 120){
            transport.setIncome(transport.getIncome() - transportConfig.getDecrease().getValue4());
        }else if (dto.getMinutes() >= 60){
            transport.setIncome(transport.getIncome() - transportConfig.getDecrease().getValue3());
        }else if (dto.getMinutes() >= 30){
            transport.setIncome(transport.getIncome() - transportConfig.getDecrease().getValue2());
        }else{
            transport.setIncome(transport.getIncome() - transportConfig.getDecrease().getValue1());
        }
        transportPlanRepository.save(transport);
    }

    private Section findSectionWithNumber(Long id, TransportPlan transportPlan){
        for (Section section : transportPlan.getSections()){
            if (section.getNumber() == id){
                return section;
            }
        }
        return null;
    }

    private Section findSectionWithMilestone(Milestone milestone, TransportPlan transportPlan){
        for (Section section : transportPlan.getSections()){
            if (section.getToMilestone() == milestone){
                return section;
            }
            if (section.getFromMilestone() == milestone){
                return section;
            }
        }
        return null;
    }

}
