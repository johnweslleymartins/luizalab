package com.example.file.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private Integer product_id;
    private BigDecimal values;
    private Integer qtd = 1;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Objects.equals(product_id, product.product_id) && Objects.equals(values, product.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product_id, values);
    }
}