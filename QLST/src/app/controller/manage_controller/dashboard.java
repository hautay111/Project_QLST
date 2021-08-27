package app.controller.manage_controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import app.dao.connectDB;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

public class dashboard implements Initializable {

	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;

	@FXML
	private Label amount_product;

	@FXML
	private Label amount_orders;

	@FXML
	private BarChart<?, ?> barChart;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		try {
			barChart();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			conn = connectDB.ConnectDb();
			String sql3 = "select count(pro_id) from product";
			pst = conn.prepareStatement(sql3);
			rs = pst.executeQuery();
			if (rs.next()) {
				product = rs.getInt(1);
				amount_product.setText(Integer.toString(product));
				System.out.println("Tong product : --->" + product);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			conn = connectDB.ConnectDb();
			String sql1 = "select count(order_id) from orders";
			pst = conn.prepareStatement(sql1);
			rs = pst.executeQuery();
			if (rs.next()) {
				orders = rs.getInt(1);
				amount_orders.setText(Integer.toString(orders));
				System.out.println("Tong orders : --->" + orders);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			String sql="SELECT ware_house.*,product.pro_name,amount_stock+amount_input AS \"total_amount\" FROM ware_house,product WHERE amount_stock+amount_input<100 AND ware_house.pro_id=product.pro_id;";
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			if(rs.next()) {
				System.out.println("Hang can nhap: "+rs.getString("pro_name")+" - So luong hang con trong kho: "+rs.getInt("total_amount"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public static int amount;
	public static int product, orders;

	private void barChart() throws SQLException {

		conn = connectDB.ConnectDb();
		String sql1 = "select count(order_id) from orders";
		pst = conn.prepareStatement(sql1);
		rs = pst.executeQuery();
		if (rs.next()) {
			orders = rs.getInt(1);
			System.out.println("=================" + orders);
		}

		XYChart.Series series = new XYChart.Series();
		series.getData().add(new XYChart.Data("orders", orders));

		XYChart.Series series1 = new XYChart.Series();
		series1.getData().add(new XYChart.Data("orders", 3));

		barChart.getData().addAll(series, series1);
//		barChart.getData().addAll(series);
	}

}
