package de.bg.lagersystem.component;

import de.bg.lagersystem.model.Storage;
import de.bg.lagersystem.repository.StorageRepository;
import de.bg.lagersystem.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class CheckStock
{
    private final StorageRepository storageRepository;
    private final MailService mailService;

    @Scheduled(fixedRate = 10, timeUnit = TimeUnit.MINUTES)
    public void checkStock()
    {
        List<Storage> storage = storageRepository.findAll();

        for (Storage item : storage) {
            int itemStock = item.getStock();
            int itemMinStock = item.getMinStock();

            if (itemStock < itemMinStock) {
                mailService.sendMail(item.getProductName(), itemStock);
            }
        }
    }
}
