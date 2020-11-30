package ru.ndg.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import ru.ndg.model.Product;

import java.util.Map;

@RequiredArgsConstructor
class FilterProduct {

    private Specification<Product> spec = Specification.where(null);
    private final Map<String, String> params;

    Specification<Product> getProductSpecification() {

        if (params == null) return spec;

        String titlePart = params.get("title_part");
        if (titlePart != null && !titlePart.isEmpty()) {
            spec = spec.and((Specification<Product>) (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), String.format("%%%s%%", titlePart)));
        }
        String minPriceParam = params.get("min_price");
        if (minPriceParam != null) {
            Integer minPrice = null;
            try {
                minPrice = Integer.parseInt(minPriceParam);
            } catch (NumberFormatException ignore) {}
            if (minPrice != null) {
                final Integer minPriceForSpec = minPrice;
                spec = spec.and((Specification<Product>) (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPriceForSpec));
            }
        }
        String maxPriceParam = params.get("max_price");
        if (maxPriceParam != null) {
            Integer maxPrice = null;
            try {
                maxPrice = Integer.parseInt(maxPriceParam);
            } catch (NumberFormatException ignore) {}
            if (maxPrice != null) {
                final Integer maxPriceForSpec = maxPrice;
                spec = spec.and((Specification<Product>) (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPriceForSpec));
            }
        }

        return spec;
    }
}
