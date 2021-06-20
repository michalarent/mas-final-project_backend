package com.example.mas_final_project.service;

import com.example.mas_final_project.model.Performer;
import com.example.mas_final_project.model.exception.AlbumNotFoundException;
import com.example.mas_final_project.repository.PerformerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PerformerService {

    private final PerformerRepository performerRepository;

    @Autowired
    public PerformerService(PerformerRepository performerRepository) {
        this.performerRepository = performerRepository;
    }

    public Performer addPerformer(Performer performer) {
        return performerRepository.save(performer);
    }

    public List<Performer> getPerformers() {
        return StreamSupport
                .stream(performerRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Performer getPerformer(Long id) {
        return performerRepository.findById(id).orElseThrow(() -> new AlbumNotFoundException(id));
    }

    public Performer deletePerformer(Long id) {
        Performer performer = getPerformer(id);
        performerRepository.delete(performer);
        return performer;
    }

    public Performer editPerformer(Long id, Performer performer) {
        Performer toEdit = getPerformer(id);
        toEdit.setFirstName(performer.getFirstName());
        toEdit.setLastName(performer.getLastName());
        return toEdit;
    }
}
