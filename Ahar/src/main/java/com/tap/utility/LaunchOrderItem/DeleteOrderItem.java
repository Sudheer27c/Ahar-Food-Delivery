package com.tap.utility.LaunchOrderItem;

import java.util.Scanner;

import com.tap.DAOImpl.OrderItemDAOImpl;

public class DeleteOrderItem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter OrderItem ID:");
        int orderItemId = sc.nextInt();

        OrderItemDAOImpl dao = new OrderItemDAOImpl();

        dao.deleteOrderItem(orderItemId);

        System.out.println("Order Item Deleted Successfully");

        sc.close();
    }
}