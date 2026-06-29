package com.tap.utility.LaunchOrderTable;

import java.sql.Timestamp;
import java.util.Scanner;

import com.tap.DAOImpl.OrderTableDAOImpl;
import com.tap.model.OrderTable;

public class UpdateOrderTable {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Order ID:");
        int orderId = sc.nextInt();

        System.out.println("Enter User ID:");
        int userId = sc.nextInt();

        System.out.println("Enter Total Amount:");
        double totalAmount = sc.nextDouble();
        sc.nextLine();

        System.out.println("Enter Status:");
        String status = sc.nextLine();

        System.out.println("Enter Payment Method:");
        String paymentMethod = sc.nextLine();

        System.out.println("Enter Restaurant ID:");
        int restaurantId = sc.nextInt();

        OrderTable order = new OrderTable(
                orderId,
                userId,
                new Timestamp(System.currentTimeMillis()),
                totalAmount,
                status,
                paymentMethod,
                restaurantId);

        OrderTableDAOImpl dao = new OrderTableDAOImpl();

        dao.updateOrder(order);

        System.out.println("Order Updated Successfully");

        sc.close();
    }
}