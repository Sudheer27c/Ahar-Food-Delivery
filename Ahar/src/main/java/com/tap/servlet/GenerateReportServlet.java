package com.tap.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import com.tap.DAOImpl.OrderTableDAOImpl;
import com.tap.DAOImpl.RestaurantDAOImpl;
import com.tap.DAOImpl.UserDAOImpl;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/generateReport")
public class GenerateReportServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(
			HttpServletRequest req,
			HttpServletResponse resp)
					throws IOException {

		try {

			resp.setContentType("application/pdf");

			resp.setHeader(
					"Content-Disposition",
					"attachment; filename=AharReport.pdf");

			Document document = new Document();

			PdfWriter.getInstance(
					document,
					resp.getOutputStream());

			document.open();

			// DAO Objects

			UserDAOImpl userDAO =
					new UserDAOImpl();

			RestaurantDAOImpl restaurantDAO =
					new RestaurantDAOImpl();

			OrderTableDAOImpl orderDAO =
					new OrderTableDAOImpl();

			// Fonts

			Font titleFont =
					new Font(
							Font.FontFamily.HELVETICA,
							22,
							Font.BOLD);

			Font headingFont =
					new Font(
							Font.FontFamily.HELVETICA,
							14,
							Font.BOLD);

			Font normalFont =
					new Font(
							Font.FontFamily.HELVETICA,
							12,
							Font.NORMAL);

			// Title

			Paragraph title =
					new Paragraph(
							"AHAR SALES REPORT",
							titleFont);

			title.setAlignment(
					Element.ALIGN_CENTER);

			document.add(title);

			document.add(
					new Paragraph(" "));

			// Generated Date

			String date =
					new SimpleDateFormat(
							"dd-MM-yyyy HH:mm:ss")
					.format(new Date());

			document.add(
					new Paragraph(
							"Generated On : " + date,
							normalFont));

			document.add(
					new Paragraph(
							"------------------------------------------------------------"));

			document.add(
					new Paragraph(" "));

			// Dashboard Values

			int totalUsers =
					userDAO.getUserCount();

			int totalRestaurants =
					restaurantDAO.getRestaurantCount();

			int totalOrders =
					orderDAO.getTotalOrders();

			int deliveredOrders =
					orderDAO.getDeliveredOrders();

			int pendingOrders =
					orderDAO.getPendingOrders();

			int cancelledOrders =
					orderDAO.getCancelledOrders();

			double revenue =
					orderDAO.getTotalRevenue();

			double successRate = 0;

			if(totalOrders > 0) {

				successRate =
						(deliveredOrders * 100.0)
						/ totalOrders;
			}

			// Table

			PdfPTable table =
					new PdfPTable(2);

			table.setWidthPercentage(100);

			PdfPCell metricHeader =
					new PdfPCell(
							new Paragraph(
									"Metric",
									headingFont));

			metricHeader.setBackgroundColor(
					BaseColor.LIGHT_GRAY);

			PdfPCell valueHeader =
					new PdfPCell(
							new Paragraph(
									"Value",
									headingFont));

			valueHeader.setBackgroundColor(
					BaseColor.LIGHT_GRAY);

			table.addCell(metricHeader);
			table.addCell(valueHeader);

			// Data Rows

			table.addCell("Total Users");
			table.addCell(String.valueOf(totalUsers));

			table.addCell("Total Restaurants");
			table.addCell(String.valueOf(totalRestaurants));

			table.addCell("Total Orders");
			table.addCell(String.valueOf(totalOrders));

			table.addCell("Delivered Orders");
			table.addCell(String.valueOf(deliveredOrders));

			table.addCell("Pending Orders");
			table.addCell(String.valueOf(pendingOrders));

			table.addCell("Cancelled Orders");
			table.addCell(String.valueOf(cancelledOrders));

			table.addCell("Total Revenue");
			table.addCell("₹ " + revenue);

			table.addCell("Success Rate");
			table.addCell(
					String.format(
							"%.2f %%", successRate));

			document.add(table);

			document.add(
					new Paragraph(" "));

			document.add(
					new Paragraph(
							"------------------------------------------------------------"));

			document.add(
					new Paragraph(" "));

			Paragraph footer =
					new Paragraph(
							"Thank you for using AHAR Food Delivery System.",
							normalFont);

			footer.setAlignment(
					Element.ALIGN_CENTER);

			document.add(footer);

			document.close();
		}
		catch(Exception e) {

			e.printStackTrace();
		}
	}
}