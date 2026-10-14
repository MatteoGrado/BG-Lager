package de.bg.lagersystem.service;

import de.bg.lagersystem.model.Storage;
import de.bg.lagersystem.repository.StorageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService {
    private final StorageRepository storageRepository;

    public DashboardService(StorageRepository storageRepository) {
        this.storageRepository = storageRepository;
    }

    public List<Storage> findProducts(String search) {
        if (search == null || search.isBlank()) {
            return storageRepository.findAll();
        }
        return storageRepository.findByProductNameContaining(search.trim());
    }
}
