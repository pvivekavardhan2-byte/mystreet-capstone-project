package com.mystreet.config;

import com.mystreet.entity.Product;
import com.mystreet.entity.User;
import com.mystreet.repository.ProductRepository;
import com.mystreet.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.math.BigDecimal;

@Configuration
public class DataLoader {
    @Bean
    CommandLineRunner seed(UserRepository users, ProductRepository products, PasswordEncoder encoder) {
        return args -> {
            if (!users.existsByEmail("admin@mystreet.com")) {
                User admin = new User(); admin.setEmail("admin@mystreet.com"); admin.setPasswordHash(encoder.encode("admin123")); admin.setAdmin(true); users.save(admin);
            }
            if (products.count() == 0) {
                add(products, "Air Max 90", "Nike", "Classic retro vibe", "119.99", "https://picsum.photos/seed/airmax/400", "7,8,9,10", 50);
                add(products, "Ultraboost", "Adidas", "Responsive cushioning", "139.99", "https://picsum.photos/seed/ub/400", "7,8,9,10,11", 35);
                add(products, "Classic Leather", "Reebok", "Clean white everyday sneaker", "89.99", "https://picsum.photos/seed/reebok/400", "6,7,8,9,10", 40);
                add(products, "Chuck Taylor", "Converse", "Canvas high-top sneaker", "69.99", "https://picsum.photos/seed/chuck/400", "7,8,9,10", 60);
                add(products, "RS-X", "Puma", "Bold chunky sneaker", "99.99", "https://picsum.photos/seed/rsx/400", "8,9,10,11", 30);
                add(products, "574 Core", "New Balance", "Comfort lifestyle shoe", "84.99", "https://picsum.photos/seed/nb574/400", "7,8,9,10,11", 45);
                add(products, "Old Skool", "Vans", "Skate classic sneaker", "74.99", "https://picsum.photos/seed/vans/400", "6,7,8,9,10", 55);
                add(products, "Gel Lyte", "Asics", "Lightweight running style", "109.99", "https://picsum.photos/seed/asics/400", "7,8,9,10", 28);
            }
        };
    }
    private void add(ProductRepository repo, String name, String brand, String desc, String price, String img, String sizes, int stock) {
        Product p = new Product(); p.setName(name); p.setBrand(brand); p.setDescription(desc); p.setPrice(new BigDecimal(price)); p.setImageUrl(img); p.setSizesCsv(sizes); p.setStockQty(stock); repo.save(p);
    }
}
