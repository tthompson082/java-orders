package com.lambdaschool.javaorders.services;

import com.lambdaschool.javaorders.model.Orders;

import java.util.List;

public interface OrdersService
{
    List<Orders> findAll();

    Orders findOrderById(long id);

    void delete(long id);

    Orders save(Orders orders);

    Orders update(Orders orders, long id);
}
