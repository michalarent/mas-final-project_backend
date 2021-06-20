package com.example.mas_final_project.service;

import com.example.mas_final_project.model.Account;
import com.example.mas_final_project.model.Album;
import com.example.mas_final_project.model.Label;
import com.example.mas_final_project.model.LabelUser;
import com.example.mas_final_project.model.exception.AlbumIsAlreadyAssigned;
import com.example.mas_final_project.model.exception.LabelNotFoundException;
import com.example.mas_final_project.repository.LabelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
public class LabelService {

    private final LabelRepository labelRepository;
    private final AlbumService albumService;

    @Autowired
    public LabelService(LabelRepository labelRepository, AlbumService albumService) {
        this.labelRepository = labelRepository;
        this.albumService = albumService;
    }

    public Label addLabel(Label label) {
        label.setAccount(Account.createAccount(0, null, label));
        label.setLabelUser(LabelUser.createLabelUser("John", "Malkovich", "jmalkovich", label));
        return labelRepository.save(label);
    }

    public List<Label> getLabels() {
        return StreamSupport
                .stream(labelRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Label getLabel(Long id) {
        return labelRepository.findById(id).orElseThrow(() -> new LabelNotFoundException(id));
    }

    public Label deleteLabel(Long id) {
        Label label = getLabel(id);
        labelRepository.delete(label);
        return label;
    }

    @Transactional
    public Label editLabel(Long id, Label label) {
        Label toEdit = getLabel(id);
        toEdit.setCountry(label.getCountry());
        toEdit.setDateOfClosure(label.getDateOfClosure());
        toEdit.setDateOfFounding(label.getDateOfFounding());
        toEdit.setName(label.getName());
        return toEdit;
    }

    @Transactional
    public Label addAlbumToLabel(Long labelId, Long albumId) {
        Label label = getLabel(labelId);
        Album album = albumService.getAlbum(albumId);

        if (Objects.nonNull(album.getLabel())) {
            throw new AlbumIsAlreadyAssigned(albumId, album.getLabel().getId());
        }
        label.addAlbum(album);
        album.setLabel(label);
        return label;
    }

    @Transactional
    public Label removeAlbumFromLabel(Long labelId, Long albumId) {
        Label label = getLabel(labelId);
        Album album = albumService.getAlbum(albumId);
        if (!Objects.nonNull(album.getLabel())) {
            throw new AlbumIsAlreadyAssigned(albumId, album.getLabel().getId());
        }
        label.removeAlbum(album);
        album.setLabel(null);
        return label;
    }
}
