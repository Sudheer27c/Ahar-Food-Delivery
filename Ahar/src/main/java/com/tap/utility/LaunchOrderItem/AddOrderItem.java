package com.tap.utility.LaunchOrderItem;

import java.util.Scanner;

import com.tap.DAOImpl.OrderItemDAOImpl;
import com.tap.model.OrderItem;

public class AddOrderItem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Order ID:");
        int orderId = sc.nextInt();

        System.out.println("Enter Menu ID:");
        int menuId = sc.nextInt();

        System.out.println("Enter Quantity:");
        int quantity = sc.nextInt();

        System.out.println("Enter Item Total:");
        double itemTotal = sc.nextDouble();

        OrderItem item = new OrderItem(
                0,
                orderId,
                menuId,
                quantity,
                itemTotal);

        OrderItemDAOImpl dao = new OrderItemDAOImpl();

        dao.addOrderItem(item);

        System.out.println("Order Item Added Successfully");

        sc.close();
    }
}