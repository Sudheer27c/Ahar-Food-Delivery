package com.tap.servlet;

import java.io.IOException;
import java.sql.*;

import com.tap.utility.DBConnection;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/verifyOTP")
public class VerifyOTPServlet
extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(
			HttpServletRequest req,
			HttpServletResponse resp)
					throws IOException {

		String otp =
				req.getParameter("otp");

		String email =
				(String) req.getSession()
				.getAttribute("email");

		try {

			Connection con =
					DBConnection.getConnection();

			PreparedStatement pstmt =
					con.prepareStatement(
							"SELECT * FROM otpverification WHERE email=? AND otp=?");

			pstmt.setString(1, email);
			pstmt.setString(2, otp);

			ResultSet rs =
					pstmt.executeQuery();

			if(rs.next()) {

				resp.sendRedirect(
						"resetPassword.jsp");
			}
			else {

				resp.sendRedirect(
						"verifyOTP.jsp");
			}

		}
		catch(Exception e){

			e.printStackTrace();
		}
	}
}