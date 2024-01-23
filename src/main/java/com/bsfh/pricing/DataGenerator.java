package com.bsfh.pricing;


import com.bsfh.pricing.model.PriceHistory;
import com.bsfh.pricing.model.Product;
import com.bsfh.pricing.service.DBService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Component
public class DataGenerator {
    @Bean
    public CommandLineRunner loadData(DBService<Product> productService,
                                      DBService<PriceHistory> historyService) {
        return args -> {
            if (productService.exists(Product.class)) {
                return;
            }

            //Create Products
            Product p1 = new Product("Rake", "Sturdy, wooden rake with iron head. length = 1.5 m, width = 75 cm.", BigDecimal.valueOf(50));
            Product p2 = new Product("Toaster", "Offers 3 combinable functionalities, indicated by indicator lights: Defrost (Defrost), Reheat (Reheat) and buns (Bagel)", BigDecimal.valueOf(60));
            Product p3 = new Product("Pillow", "White 100 % polyester - hollow fibre (100% recycled)", BigDecimal.valueOf(20.90));
            Product p4 = new Product("Plant Pot", "With their elegant yet simple shape, they’re perfect for any room – and all your plants.", BigDecimal.valueOf(20));
            Product p5 = new Product("Kettle", "Made from 18/10 stainless steel, diameter 15 cm, capacity 4 L, welded stainless steel handle with bakelite insert", BigDecimal.valueOf(55));
            Product p6 = new Product("Trowel", "Stainless-steel blade and tang, welded at 3 points.", BigDecimal.valueOf(16.50));
            Product p7 = new Product("Hosepipe", "Without couplings. Internal diameter: 13 mm", BigDecimal.valueOf(60));
            Product p8 = new Product("Wooden Chair", "Comfy and sturdy chair with a wooden frame, a soft seat and a nice backrest with a curved shape", BigDecimal.valueOf(48));
            Product p9 = new Product("Laundry Basket", "Stable construction will outlast loads of laundry, and the liner bag is both removable and washable", BigDecimal.valueOf(30));
            productService.createAll(Set.of(p1, p2, p3, p4, p5, p6, p7, p8, p9));

            //Create price history
            PriceHistory ph1a = new PriceHistory(p1, LocalDateTime.now().minusYears(2), BigDecimal.valueOf(60));
            PriceHistory ph1b = new PriceHistory(p1, LocalDateTime.now().minusYears(1), BigDecimal.valueOf(56));
            PriceHistory ph1c = new PriceHistory(p1, LocalDateTime.now().minusDays(100), BigDecimal.valueOf(52.50));

            PriceHistory ph2a = new PriceHistory(p2, LocalDateTime.now().minusYears(1), BigDecimal.valueOf(75));
            PriceHistory ph2b = new PriceHistory(p2, LocalDateTime.now().minusDays(200), BigDecimal.valueOf(70));
            PriceHistory ph2c = new PriceHistory(p2, LocalDateTime.now().minusDays(100), BigDecimal.valueOf(68.25));

            PriceHistory ph3a = new PriceHistory(p3, LocalDateTime.now().minusYears(3), BigDecimal.valueOf(25));
            PriceHistory ph3b = new PriceHistory(p3, LocalDateTime.now().minusYears(1), BigDecimal.valueOf(23.50));
            PriceHistory ph3c = new PriceHistory(p3, LocalDateTime.now().minusDays(70), BigDecimal.valueOf(22));

            PriceHistory ph4a = new PriceHistory(p4, LocalDateTime.now().minusYears(2), BigDecimal.valueOf(20));
            PriceHistory ph4b = new PriceHistory(p4, LocalDateTime.now().minusYears(1), BigDecimal.valueOf(24.50));
            PriceHistory ph4c = new PriceHistory(p4, LocalDateTime.now().minusDays(100), BigDecimal.valueOf(22));

            PriceHistory ph5a = new PriceHistory(p5, LocalDateTime.now().minusYears(1), BigDecimal.valueOf(60));
            PriceHistory ph5b = new PriceHistory(p5, LocalDateTime.now().minusDays(250), BigDecimal.valueOf(55));
            PriceHistory ph5c = new PriceHistory(p5, LocalDateTime.now().minusDays(100), BigDecimal.valueOf(57));

            PriceHistory ph6a = new PriceHistory(p6, LocalDateTime.now().minusYears(1), BigDecimal.valueOf(18));
            PriceHistory ph6b = new PriceHistory(p6, LocalDateTime.now().minusDays(200), BigDecimal.valueOf(17.65));
            PriceHistory ph6c = new PriceHistory(p6, LocalDateTime.now().minusDays(70), BigDecimal.valueOf(17));

            PriceHistory ph7a = new PriceHistory(p7, LocalDateTime.now().minusYears(3), BigDecimal.valueOf(69));
            PriceHistory ph7b = new PriceHistory(p7, LocalDateTime.now().minusYears(1).minusDays(100), BigDecimal.valueOf(66.95));
            PriceHistory ph7c = new PriceHistory(p7, LocalDateTime.now().minusDays(88), BigDecimal.valueOf(63));

            PriceHistory ph8a = new PriceHistory(p8, LocalDateTime.now().minusYears(3), BigDecimal.valueOf(55));
            PriceHistory ph8b = new PriceHistory(p8, LocalDateTime.now().minusYears(2), BigDecimal.valueOf(50.90));
            PriceHistory ph8c = new PriceHistory(p8, LocalDateTime.now().minusDays(255), BigDecimal.valueOf(49));

            PriceHistory ph9a = new PriceHistory(p9, LocalDateTime.now().minusYears(3), BigDecimal.valueOf(35));
            PriceHistory ph9b = new PriceHistory(p9, LocalDateTime.now().minusYears(2), BigDecimal.valueOf(33.90));
            PriceHistory ph9c = new PriceHistory(p9, LocalDateTime.now().minusDays(255), BigDecimal.valueOf(32));

            historyService.createAll(Set.of(
                    ph1a, ph1b, ph1c,
                    ph2a, ph2b, ph2c,
                    ph3a, ph3b, ph3c,
                    ph4a, ph4b, ph4c,
                    ph5a, ph5b, ph5c,
                    ph6a, ph6b, ph6c,
                    ph7a, ph7b, ph7c,
                    ph8a, ph8b, ph8c,
                    ph9a, ph9b, ph9c));
        };
    }
}
