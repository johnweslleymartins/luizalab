package com.example.file.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

public class OrderTest {

    @Test
    public void testEquals() {
        Order order = new Order();
        order.setOrder_id(1);
        order.setOrder_date(LocalDate.now().toString());
        order.setProducts(new ArrayList<>());

        Assertions.assertEquals(order.hashCode(), order.hashCode());
        Assertions.assertNotNull(order.toString());
    }
}