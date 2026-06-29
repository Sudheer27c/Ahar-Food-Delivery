package com.tap.utility.LaunchOrderTable;

import java.util.List;

import com.tap.DAOImpl.OrderTableDAOImpl;
import com.tap.model.OrderTable;

public class GetAllOrderTables {

    public static void main(String[] args) {

        OrderTableDAOImpl dao = new OrderTableDAOImpl();

        List<OrderTable> orders = dao.getAllOrders();

        if(orders.isEmpty()) {
            System.out.println("No Orders Found");
        }
        else {
            for(OrderTable order : orders) {
                System.out.println(order);
            }
        }
    }
}