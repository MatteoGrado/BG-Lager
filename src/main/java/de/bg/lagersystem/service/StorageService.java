package de.bg.lagersystem.service;

import de.bg.lagersystem.model.Storage;
import de.bg.lagersystem.repository.StorageRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class StorageService {
    private final StorageRepository storageRepository;

    public StorageService(StorageRepository storageRepository) {
        this.storageRepository = storageRepository;
    }

    public Storage findById(Long id) {
        return storageRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produkt nicht gefunden"));
    }

    public Storage create(String productName, Integer shelfNumber, Integer stock,
                          BigDecimal price, LocalDateTime deliveryTime, String seller) {
        Storage storage = new Storage();
        storage.setProductName(productName);
        storage.setShelfNumber(shelfNumber);
        storage.setStock(stock);
        storage.setPrice(price);
        storage.setDeliveryTime(deliveryTime);
        storage.setSeller(seller);
        return storageRepository.save(storage);
    }

    public Storage update(Long id, String productName, Integer shelfNumber, Integer stock,
                          BigDecimal price, LocalDateTime deliveryTime, String seller) {
        Storage product = findById(id);
        product.setProductName(productName);
        product.setShelfNumber(shelfNumber);
        product.setStock(stock);
        product.setPrice(price);
        product.setDeliveryTime(deliveryTime);
        product.setSeller(seller);
        return storageRepository.save(product);
    }

    public Storage updateStock(Long id, int stock, LocalDateTime deliveryTime) {
        Storage product = findById(id);
        product.setStock(stock);
        product.setDeliveryTime(deliveryTime);
        return storageRepository.save(product);
    }

    public Storage changeStock(Long id, int difference) {
        Storage product = findById(id);
        int newStock = product.getStock() + difference;
        product.setStock(Math.max(newStock, 0));
        return storageRepository.save(product);
    }

    public void delete(Long id) {
        storageRepository.delete(findById(id));
    }
}
