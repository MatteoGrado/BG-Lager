package de.bg.lagersystem.service;

import de.bg.lagersystem.model.Storage;
import de.bg.lagersystem.repository.StorageRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class StorageService {
    private final StorageRepository storageRepository;
    private final MailService mailService;

    public StorageService(StorageRepository storageRepository, MailService mailService) {
        this.storageRepository = storageRepository;
        this.mailService = mailService;
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
        return updateStock(id, stock, deliveryTime, "", "", "");
    }

    public Storage updateStock(Long id, int stock, LocalDateTime deliveryTime,
                               String firstName, String lastName, String useFor) {
        Storage product = findById(id);
        product.setStock(stock);
        product.setDeliveryTime(deliveryTime);
        product.setUseFor(useFor);
        Storage savedProduct = storageRepository.save(product);
        sendHtmlMail(savedProduct, "", firstName, lastName, useFor);
        return savedProduct;
    }

    public Storage changeStock(Long id, int difference) {
        Storage product = findById(id);
        int newStock = product.getStock() + difference;
        product.setStock(Math.max(newStock, 0));
        return storageRepository.save(product);
    }

    private void sendHtmlMail(Storage product, String action,
                              String firstName, String lastName, String useFor) {
        try {
            mailService.sendHtmlMail(product.getProductName(), product.getStock(), useFor,
                    firstName, lastName, action);
        } catch (RuntimeException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void delete(Long id) {
        storageRepository.delete(findById(id));
    }
}
