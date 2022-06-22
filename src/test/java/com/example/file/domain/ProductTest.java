package com.example.file.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

public class ProductTest {

    @Test
    public void testEquals() {
        Product product = new Product();
        product.setProduct_id(1);
        product.setQtd(2);
        product.setValues(BigDecimal.valueOf(200));

        Assertions.assertEquals(product.hashCode(), product.hashCode());
        Assertions.assertNotNull(product.toString());
    }
}