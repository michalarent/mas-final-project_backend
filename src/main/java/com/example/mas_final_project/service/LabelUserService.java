package com.example.mas_final_project.service;

import com.example.mas_final_project.model.Account;
import com.example.mas_final_project.model.Label;
import com.example.mas_final_project.model.LabelUser;
import com.example.mas_final_project.model.exception.AccountNotFoundException;
import com.example.mas_final_project.repository.AccountRepository;
import com.example.mas_final_project.repository.LabelUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class LabelUserService {

    private final LabelUserRepository labelUserRepository;

    @Autowired
    public LabelUserService(LabelUserRepository labelUserRepository) {
        this.labelUserRepository = labelUserRepository;
    }

    public List<LabelUser> getLabelUsers() {
        return StreamSupport
                .stream(labelUserRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public LabelUser getLabelUser(Long id) {
        return labelUserRepository.findById(id).orElseThrow(() -> new AccountNotFoundException(id));
    }


    @Transactional
    public LabelUser editLabelUser(Long id, LabelUser labelUser) {
        LabelUser toEdit = getLabelUser(id);
        toEdit.setFirstName(labelUser.getFirstName());
        toEdit.setLastName(labelUser.getLastName());
        toEdit.setUsername(labelUser.getUsername());
        System.out.println("Editing label user: username = " + labelUser.getUsername());
        return toEdit;
    }
}
