package com.tap.utility.LaunchOrderItem;

import java.util.Scanner;

import com.tap.DAOImpl.OrderItemDAOImpl;
import com.tap.model.OrderItem;

public class UpdateOrderItem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter OrderItem ID:");
        int orderItemId = sc.nextInt();

        System.out.println("Enter Order ID:");
        int orderId = sc.nextInt();

        System.out.println("Enter Menu ID:");
        int menuId = sc.nextInt();

        System.out.println("Enter Quantity:");
        int quantity = sc.nextInt();

        System.out.println("Enter Item Total:");
        double itemTotal = sc.nextDouble();

        OrderItem item = new OrderItem(
                orderItemId,
                orderId,
                menuId,
                quantity,
                itemTotal);

        OrderItemDAOImpl dao = new OrderItemDAOImpl();

        dao.updateOrderItem(item);

        System.out.println("Order Item Updated Successfully");

        sc.close();
    }
}