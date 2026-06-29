package com.tap.utility.LaunchOrderItem;

import java.util.List;

import com.tap.DAOImpl.OrderItemDAOImpl;
import com.tap.model.OrderItem;

public class GetAllOrderItems {

    public static void main(String[] args) {

        OrderItemDAOImpl dao = new OrderItemDAOImpl();

        List<OrderItem> items = dao.getAllOrderItems();

        if(items.isEmpty()) {
            System.out.println("No Order Items Found");
        }
        else {
            for(OrderItem item : items) {
                System.out.println(item);
            }
        }
    }
}