package de.bg.lagersystem.service;

import de.bg.lagersystem.model.Storage;
import de.bg.lagersystem.repository.StorageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class StorageService
{
    private final StorageRepository storageRepository;
    private final MailService mailService;

    public Storage findById(Long id)
    {
        return storageRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produkt nicht gefunden"));
    }

    public Storage create(String productName, Integer shelfNumber, Integer stock,
                          BigDecimal price, LocalDateTime deliveryTime, String seller)
    {
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
                          BigDecimal price, LocalDateTime deliveryTime, String seller)
    {
        Storage product = findById(id);
        product.setProductName(productName);
        product.setShelfNumber(shelfNumber);
        product.setStock(stock);
        product.setPrice(price);
        product.setDeliveryTime(deliveryTime);
        product.setSeller(seller);
        return storageRepository.save(product);
    }

    public Storage updateStock(Long id, int stock, LocalDateTime deliveryTime)
    {
        return updateStock(id, stock, deliveryTime, "", "", "");
    }

    public Storage updateStock(Long id, int stock, LocalDateTime deliveryTime,
                               String firstName, String lastName, String useFor)
    {
        Storage product = findById(id);
        product.setStock(stock);
        product.setDeliveryTime(deliveryTime);
        product.setUseFor(useFor);
        Storage savedProduct = storageRepository.save(product);
        return savedProduct;
    }

    public Storage changeStock(Long id, int difference)
    {
        Storage product = findById(id);
        int newStock = product.getStock() + difference;
        product.setStock(Math.max(newStock, 0));
        Storage savedProduct = storageRepository.save(product);
        mailService.sendHtmlMail(savedProduct.getProductName(), savedProduct.getPrice(),
                Math.abs(difference), savedProduct.getStock(), savedProduct.getSeller());
        return savedProduct;
    }

    public Storage changeStock(Long id, int difference, String firstName, String lastName)
    {
        Storage product = findById(id);
        int newStock = product.getStock() + difference;
        product.setStock(Math.max(newStock, 0));
        Storage savedProduct = storageRepository.save(product);
        mailService.sendHtmlMail(savedProduct.getProductName(), savedProduct.getPrice(),
                Math.abs(difference), savedProduct.getStock(), savedProduct.getSeller(),
                firstName, lastName, difference >= 0 ? "plus" : "minus");
        return savedProduct;
    }

    public void delete(Long id)
    {
        storageRepository.delete(findById(id));
    }
}
