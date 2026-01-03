package com.studio.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.studio.bean.Booking;
import com.studio.util.DBUtil;

public class BookingDAO {

	Connection con = DBUtil.getDBConnection();

	public int generateBookingID() {
		String sql = "select booking_seq.nextval from dual";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			if (rs.next()) {
				return (int)rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	public boolean recordBooking(Booking booking) {
		String sql = "insert into booking_tbl values(?,?,?,?,?,?,?)";

		try {
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, booking.getBookingId());
			ps.setString(2, booking.getCustomerId());
			ps.setDate(3, new Date(booking.getShootDate().getTime()));
			ps.setString(4, booking.getStartTime());
			ps.setString(5, booking.getEndTime());
			ps.setString(6, booking.getPackageType());
			ps.setString(7, booking.getStatus());

			return ps.executeUpdate() > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateBookingStatus(int bookingID, String status) {
		String sql = "update booking_tbl set status=? where booking_id=?";

		try {
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, status);
			ps.setInt(2, bookingID);

			return ps.executeUpdate() > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateBookingSlot(int bookingID, Date newShootDate, String newStartTime, String newEndTime) {

		String sql = "update booking_tbl set shoot_date=?, start_time=?, end_time=?, status='Rescheduled' where booking_id=?";

		try {
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setDate(1, new Date(newShootDate.getTime()));
			ps.setString(2, newStartTime);
			ps.setString(3, newEndTime);
			ps.setInt(4, bookingID);

			return ps.executeUpdate() > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<Booking> findBookingsByCustomer(String customerID) {
		List<Booking> list = new ArrayList<>();
		String sql = "select * from booking_tbl where customer_id=?";

		try {
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, customerID);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				list.add(mapBooking(rs));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Booking> findBookingsForDate(Date shootDate) {
		List<Booking> list = new ArrayList<>();
		String sql = "select * from booking_tbl where shoot_date=?";

		try {
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setDate(1, new Date(shootDate.getTime()));
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				list.add(mapBooking(rs));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	private Booking mapBooking(ResultSet rs) throws Exception {
		Booking b = new Booking();
		b.setBookingId(rs.getInt("booking_id"));
		b.setCustomerId(rs.getString("customer_id"));
		b.setShootDate(rs.getDate("shoot_date"));
		b.setStartTime(rs.getString("start_time"));
		b.setEndTime(rs.getString("end_time"));
		b.setPackageType(rs.getString("package_type"));
		b.setStatus(rs.getString("status"));
		return b;
	}
}
