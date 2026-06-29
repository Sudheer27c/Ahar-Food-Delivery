package com.tap.utility.LaunchOrderItem;

import java.util.Scanner;

import com.tap.DAOImpl.OrderItemDAOImpl;
import com.tap.model.OrderItem;

public class GetOrderItem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter OrderItem ID:");
        int orderItemId = sc.nextInt();

        OrderItemDAOImpl dao = new OrderItemDAOImpl();

        OrderItem item = dao.getOrderItem(orderItemId);

        if(item != null) {
            System.out.println(item);
        }
        else {
            System.out.println("Order Item Not Found");
        }

        sc.close();
    }
}