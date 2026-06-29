package com.tap.utility.LaunchOrderTable;

import java.util.Scanner;

import com.tap.DAOImpl.OrderTableDAOImpl;

public class DeleteOrderTable {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Order ID:");
        int orderId = sc.nextInt();

        OrderTableDAOImpl dao = new OrderTableDAOImpl();

        dao.deleteOrder(orderId);

        System.out.println("Order Deleted Successfully");

        sc.close();
    }
}