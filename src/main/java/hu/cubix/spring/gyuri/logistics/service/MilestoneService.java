package hu.cubix.spring.gyuri.logistics.service;

import hu.cubix.spring.gyuri.logistics.model.Milestone;
import hu.cubix.spring.gyuri.logistics.repository.MilestoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MilestoneService {
    @Autowired
    private MilestoneRepository milestoneRepository;

    public Optional<Milestone> findById(Long id) {
        return milestoneRepository.findById(id);
    }

    @Transactional
    public void save(Milestone milestone) {
        milestoneRepository.save(milestone);
    }
}
