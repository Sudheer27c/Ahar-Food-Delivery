package com.tap.utility.LaunchOrderTable;

import java.util.Scanner;

import com.tap.DAOImpl.OrderTableDAOImpl;
import com.tap.model.OrderTable;

public class GetOrderTable {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Order ID:");
        int orderId = sc.nextInt();

        OrderTableDAOImpl dao = new OrderTableDAOImpl();

        OrderTable order = dao.getOrder(orderId);

        if(order != null) {
            System.out.println(order);
        }
        else {
            System.out.println("Order Not Found");
        }

        sc.close();
    }
}