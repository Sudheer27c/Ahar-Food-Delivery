package com.tap.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.tap.DAOImpl.OrderItemDAOImpl;
import com.tap.DAOImpl.OrderTableDAOImpl;
import com.tap.DAOImpl.RestaurantDAOImpl;
import com.tap.model.OrderItem;
import com.tap.model.OrderTable;
import com.tap.model.Restaurant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/downloadInvoice")
public class DownloadInvoiceServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(
			HttpServletRequest req,
			HttpServletResponse resp)
					throws ServletException, IOException {

		try {

			int orderId =
					Integer.parseInt(
							req.getParameter("orderId"));

			OrderTableDAOImpl orderDAO =
					new OrderTableDAOImpl();

			OrderItemDAOImpl itemDAO =
					new OrderItemDAOImpl();

			RestaurantDAOImpl restaurantDAO =
					new RestaurantDAOImpl();

			// Get Order

			OrderTable order =
					orderDAO.getOrder(orderId);

			if (order == null) {

				resp.sendError(
						HttpServletResponse.SC_NOT_FOUND,
						"Order not found");

				return;
			}

			// Get Items

			List<OrderItem> items =
					itemDAO.getOrderItems(orderId);

			// Get Restaurant

			Restaurant restaurant =
					restaurantDAO.getRestaurant(
							order.getRestaurantId());

			resp.setContentType("application/pdf");

			resp.setHeader(
					"Content-Disposition",
					"attachment; filename=Invoice_" +
							orderId + ".pdf");

			Document document =
					new Document(
							PageSize.A4,
							40,
							40,
							40,
							40);

			PdfWriter.getInstance(
					document,
					resp.getOutputStream());

			document.open();

			Font title =
					new Font(
							Font.FontFamily.HELVETICA,
							20,
							Font.BOLD,
							BaseColor.ORANGE);

			Paragraph heading =
					new Paragraph(
							"AHAR FOOD DELIVERY",
							title);


			Font subFont =
					new Font(
							Font.FontFamily.HELVETICA,
							12,
							Font.NORMAL,
							BaseColor.DARK_GRAY);

			Paragraph sub =
					new Paragraph(
							"Fast • Fresh • Delicious",
							subFont);

			heading.setAlignment(Element.ALIGN_CENTER);
			document.add(heading);

			sub.setAlignment(Element.ALIGN_CENTER);
			document.add(sub);

			document.add(new Paragraph(" "));

			Font invoiceFont =
					new Font(
							Font.FontFamily.HELVETICA,
							16,
							Font.BOLD);

			Paragraph invoiceTitle =
					new Paragraph(
							"TAX INVOICE",
							invoiceFont);

			invoiceTitle.setAlignment(
					Element.ALIGN_CENTER);

			invoiceTitle.setSpacingAfter(15);

			document.add(invoiceTitle);

			String invoiceNo =
					"INV-"
							+ new SimpleDateFormat("yyyy")
							.format(order.getOrderDate())
							+ "-"
							+ String.format("%05d", orderId);

			document.add(
					new Paragraph(
							"Invoice No : " + invoiceNo));
			document.add(
					new Paragraph(
							"Restaurant : "
									+ restaurant.getName()));

			document.add(
					new Paragraph(
							"Payment : "
									+ order.getPaymentMethod()));

			document.add(
					new Paragraph(
							"Status : "
									+ order.getStatus()));

			document.add(
					new Paragraph(
							"Date : "
									+ new SimpleDateFormat(
											"dd-MM-yyyy HH:mm")
									.format(order.getOrderDate())));

			document.add(
					new Paragraph(
							"Generated On : "
									+ new SimpleDateFormat(
											"dd-MM-yyyy HH:mm:ss")
									.format(new java.util.Date())));

			document.add(new Paragraph(" "));

			document.add(
					new Paragraph(
							"Customer ID : "
									+ order.getUserId()));

			document.add(
					new Paragraph(
							"Order ID : "
									+ order.getOrderId()));

			document.add(
					new Paragraph(
							"Restaurant Address : "
									+ restaurant.getAddress()));

			document.add(
					new Paragraph(
							"Delivery Time : "
									+ restaurant.getDeliveryTime()
									+ " mins"));

			document.add(new Paragraph(" "));

			document.add(
					new Paragraph(
							"------------------------------------------------------------"));

			document.add(
					new Paragraph(" "));

			PdfPTable table =
					new PdfPTable(4);

			table.setWidthPercentage(100);

			table.setSpacingBefore(10);

			table.setSpacingAfter(10);

			// Header

			Font headerFont =
					new Font(
							Font.FontFamily.HELVETICA,
							12,
							Font.BOLD,
							BaseColor.WHITE);

			PdfPCell c1 =
					new PdfPCell(
							new Paragraph("Item", headerFont));
			c1.setBackgroundColor(BaseColor.ORANGE);

			PdfPCell c2 =
					new PdfPCell(
							new Paragraph("Price", headerFont));
			c2.setBackgroundColor(BaseColor.ORANGE);

			PdfPCell c3 =
					new PdfPCell(
							new Paragraph("Qty", headerFont));
			c3.setBackgroundColor(BaseColor.ORANGE);

			PdfPCell c4 =
					new PdfPCell(
							new Paragraph("Total", headerFont));
			c4.setBackgroundColor(BaseColor.ORANGE);

			table.addCell(c1);
			table.addCell(c2);
			table.addCell(c3);
			table.addCell(c4);

			double subTotal = 0;

			for (OrderItem item : items) {

				table.addCell(item.getItemName());

				table.addCell(
						"₹ " + item.getPrice());

				table.addCell(
						String.valueOf(
								item.getQuantity()));

				table.addCell(
						"₹ " + item.getItemTotal());

				subTotal += item.getItemTotal();
			}

			document.add(table);

			document.add(new Paragraph(" "));

			document.add(
					new Paragraph(
							"--------------------------------------------------------"));

			document.add(
					new Paragraph(
							"Sub Total : ₹ "
									+ String.format("%.2f", subTotal)));

			double delivery = 40;

			document.add(
					new Paragraph(
							"Delivery Fee : ₹ "
									+ delivery));

			double gst =
					(order.getTotalAmount() - delivery) * 5 / 105;

			document.add(
					new Paragraph(
							"GST (5%) : ₹ "
									+ String.format("%.2f", gst)));


			document.add(
					new Paragraph(" "));

			Font totalFont =
					new Font(
							Font.FontFamily.HELVETICA,
							16,
							Font.BOLD,
							BaseColor.ORANGE);

			Paragraph total =
					new Paragraph(
							"Grand Total : ₹ "
									+ String.format("%.2f",
											order.getTotalAmount()),
									totalFont);

			total.setSpacingBefore(15);

			total.setSpacingAfter(15);

			total.setAlignment(
					Element.ALIGN_RIGHT);

			document.add(total);

			document.add(
					new Paragraph(" "));

			document.add(new Paragraph(" "));



			document.add(new Paragraph(" "));

			document.add(
					new Paragraph(
							"--------------------------------------------------------"));

			Font footerFont =
					new Font(
							Font.FontFamily.HELVETICA,
							13,
							Font.BOLD,
							BaseColor.ORANGE);

			Paragraph thanks =
					new Paragraph(
							"Thank You For Ordering With AHAR!",
							footerFont);

			thanks.setAlignment(Element.ALIGN_CENTER);

			document.add(thanks);

			Paragraph visit =
					new Paragraph(
							"Visit Again 😊");

			visit.setAlignment(Element.ALIGN_CENTER);

			document.add(visit);

			document.add(new Paragraph(" "));

			Paragraph support =
					new Paragraph(
							"Support : support@ahar.com");

			support.setAlignment(Element.ALIGN_CENTER);

			document.add(support);

			Paragraph phone =
					new Paragraph(
							"Phone : +91-9876543210");

			phone.setAlignment(Element.ALIGN_CENTER);

			document.add(phone);

			Paragraph copyright =
					new Paragraph(
							"© AHAR Food Delivery");

			copyright.setAlignment(Element.ALIGN_CENTER);

			document.add(copyright);

			document.close();

		}
		catch (Exception e) {

			e.printStackTrace();
		}
	}
}