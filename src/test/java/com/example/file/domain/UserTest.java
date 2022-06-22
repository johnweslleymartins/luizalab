package com.example.file.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class UserTest {

    @Test
    public void testEquals() {
        User user = new User();
        user.setUser_id(1);
        user.setName("teste name");
        user.setOrders(new ArrayList<>());

        Assertions.assertEquals(user.hashCode(), user.hashCode());
        Assertions.assertNotNull(user.toString());
    }
}