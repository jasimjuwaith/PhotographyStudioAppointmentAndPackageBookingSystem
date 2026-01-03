package com.studio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.studio.bean.Customer;
import com.studio.util.DBUtil;

public class CustomerDAO {

	public Customer findCustomer(String customerID) {

		Customer customer = null;
		try {
			Connection con = DBUtil.getDBConnection();
			PreparedStatement ps = con.prepareStatement("select * from customer_tbl where customer_id= ? ");

			ps.setString(1, customerID);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				customer = new Customer();
				customer.setCustomerID(rs.getString("customer_id"));
				customer.setFullName(rs.getString("full_name"));
				customer.setEmail(rs.getString("email"));
				customer.setMobile(rs.getString("mobile"));
				customer.setPrefferedPackage(rs.getString("preferred_package"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return customer;
	}

	public List<Customer> viewAllCustomers() {

		List<Customer> customer = new ArrayList<>();
		try {
			Connection con = DBUtil.getDBConnection();
			PreparedStatement ps = con.prepareStatement("select * from customer_tbl");
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				Customer c = new Customer();
				c.setCustomerID(rs.getString("customer_id"));
				c.setFullName(rs.getString("full_name"));
				c.setEmail(rs.getString("email"));
				c.setMobile(rs.getString("mobile"));
				c.setPrefferedPackage(rs.getString("preferred_package"));
				
				customer.add(c);
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return customer;
	}

	public boolean insertCustomer(Customer customer) {
		try {
			Connection con = DBUtil.getDBConnection();
			PreparedStatement ps = con.prepareStatement("insert into customer_tbl values(?,?,?,?,?)");
			
			ps.setString(1, customer.getCustomerID());
            ps.setString(2, customer.getFullName());
            ps.setString(3, customer.getEmail());
            ps.setString(4, customer.getMobile());
            ps.setString(5, customer.getPrefferredPackage());
            
            int rows = ps.executeUpdate();
            con.commit();
            return rows > 0;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteCustomer(String customerID) {
		try {
			Connection con = DBUtil.getDBConnection();
			PreparedStatement ps = con.prepareStatement("delete from customer_tbl where customer_id= ?");
			
			ps.setString(1, customerID);
			int rows = ps.executeUpdate();
            con.commit();
            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
		return false;
	}
}
