package com.example.schooljournal.services;

import com.example.schooljournal.data.entity.user.View;
import com.example.schooljournal.data.repository.ViewRepository;
import org.springframework.stereotype.Service;

@Service
public class ViewService extends GenericService<View, ViewRepository> {
    ViewService(ViewRepository repository) {
        super(repository);
    }
}
