package hu.cubix.spring.gyuri.logistics.service;

import hu.cubix.spring.gyuri.logistics.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SectionService {
    @Autowired
    private SectionRepository sectionRepository;


}
