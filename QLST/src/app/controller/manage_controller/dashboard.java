package app.controller.manage_controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import app.dao.connectDB;
import app.model.Category1;
import app.model.Dashboard;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class dashboard implements Initializable {

	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;
	int index=-1;
    ObservableList<Dashboard> listC;
	ObservableList<Category1> dataListC;

	@FXML
	private Label amount_product;

	@FXML
	private Label amount_orders;

	@FXML
	private BarChart<?, ?> barChart;
	
	@FXML
    private BarChart<?, ?> barChart1;
	
	@FXML
    private LineChart<?, ?> lineChart;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tableRunOut();
		// TODO Auto-generated method stub
		try {
			iniLineChart();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			barChart();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			barChart1();
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
		
//		try {
//			String sql="SELECT ware_house.*,product.pro_name,amount_stock+amount_input AS \"total_amount\" FROM ware_house,product WHERE amount_stock+amount_input<100 AND ware_house.pro_id=product.pro_id;";
//			pst = conn.prepareStatement(sql);
//			rs = pst.executeQuery();
//			while(rs.next()) {
//				System.out.println("Hang can nhap: "+rs.getString("pro_name")+" - So luong hang con trong kho: "+rs.getInt("total_amount"));
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		try {
//			String sql1="SELECT product.pro_name,Sum(orders_detail.quantity) AS 'amount' FROM orders_detail,product WHERE orders_detail.pro_id=product.pro_id GROUP BY orders_detail.pro_id ORDER BY Sum(orders_detail.quantity) DESC";
//			pst = conn.prepareStatement(sql1);
//			rs = pst.executeQuery();
//			while(rs.next()) {
//				System.out.println("Hang ban chay: "+rs.getString("pro_name")+" - So luong hang con trong kho: "+rs.getInt("amount"));
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	public static int amount;
	public static int product, orders;

	private void barChart() throws SQLException {

		conn = connectDB.ConnectDb();
//		String sql1 = "select count(order_id) from orders";
//		pst = conn.prepareStatement(sql1);
//		rs = pst.executeQuery();
//		if (rs.next()) {
//			orders = rs.getInt(1);
//			System.out.println("=================" + orders);
//		}
		
		String sql1="SELECT product.pro_name,Sum(orders_detail.quantity) AS amount FROM orders_detail,product WHERE orders_detail.pro_id=product.pro_id GROUP BY product.pro_name ORDER BY Sum(orders_detail.quantity) DESC";
		pst = conn.prepareStatement(sql1);
		rs = pst.executeQuery();
		while(rs.next()) {
			System.out.println("Hang ban chay: "+rs.getString("pro_name")+" - So luong da ban: "+rs.getInt("amount"));
			
			XYChart.Series series = new XYChart.Series();
			series.getData().add(new XYChart.Data(rs.getString("pro_name"), rs.getInt("amount")));
		
			barChart.getData().addAll(series);
			barChart.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent;");
		}
		
		
	}

	
	
	
	private int order,order1,order2,order3,order4,order5,order6,order7,order8,order9,order10,order11,order12;
//	private int lead,lead1,lead2,lead3,lead4,lead5,lead6,lead7,lead8,lead9,lead10,lead11,lead12;
//	private int account,account1,account2,account3,account4,account5,account6,account7,account8,account9,account10,account11,account12;
	private void iniLineChart() throws SQLException {
		conn=connectDB.ConnectDb();		
//		--------------------------------query order--------------------------------
		String c1="select count(time) from orders where time BETWEEN '2021-01-01 ' AND '2021-01-31 '";
		pst=conn.prepareStatement(c1);
		rs=pst.executeQuery();
		if(rs.next()) {
			order1=rs.getInt(1);System.out.println("================="+order1);
		}
		String c2="select count(time) from orders where time BETWEEN '2021-02-01' AND '2021-02-29' ";
		pst=conn.prepareStatement(c2);
		rs=pst.executeQuery();
		if(rs.next()) {
			order2=rs.getInt(1);System.out.println("================="+order2);
		}
		String c3="select count(time) from orders where time BETWEEN '2021-03-01' AND '2021-03-31'";
		pst=conn.prepareStatement(c3);
		rs=pst.executeQuery();
		if(rs.next()) {
			order3=rs.getInt(1);System.out.println("================="+order3);
		}
		String c4="select count(time) from orders where time BETWEEN '2021-04-01' AND '2021-04-30'";
		pst=conn.prepareStatement(c4);
		rs=pst.executeQuery();
		if(rs.next()) {
			order4=rs.getInt(1);System.out.println("================="+order4);
		}
		String c5="select count(time) from orders where time BETWEEN '2021-05-01' AND '2021-05-31'";
		pst=conn.prepareStatement(c5);
		rs=pst.executeQuery();
		if(rs.next()) {
			order5=rs.getInt(1);System.out.println("================="+order5);
		}
		String c6="select count(time) from orders where time BETWEEN '2021-06-01' AND '2021-06-30'";
		pst=conn.prepareStatement(c6);
		rs=pst.executeQuery();
		if(rs.next()) {
			order6=rs.getInt(1);System.out.println("================="+order6);
		}
		String c7="select count(time) from orders where time BETWEEN '2021-07-01' AND '2021-07-31'";
		pst=conn.prepareStatement(c7);
		rs=pst.executeQuery();
		if(rs.next()) {
			order7=rs.getInt(1);System.out.println("================="+order7);
		}
		String c8="select count(time) from orders where time BETWEEN '2021-08-01' AND '2021-08-31'";
		pst=conn.prepareStatement(c8);
		rs=pst.executeQuery();
		if(rs.next()) {
			order8=rs.getInt(1);System.out.println("================="+order8);
		}
		String c9="select count(time) from orders where time BETWEEN '2021-09-01' AND '2021-09-30'";
		pst=conn.prepareStatement(c9);
		rs=pst.executeQuery();
		if(rs.next()) {
			order9=rs.getInt(1);System.out.println("================="+order9);
		}
		String c10="select count(time) from orders where time BETWEEN '2021-10-01' AND '2021-10-31'";
		pst=conn.prepareStatement(c10);
		rs=pst.executeQuery();
		if(rs.next()) {
			order10=rs.getInt(1);System.out.println("================="+order10);
		}
		String c11="select count(time) from orders where time BETWEEN '2021-11-01' AND '2021-11-30'";
		pst=conn.prepareStatement(c11);
		rs=pst.executeQuery();
		if(rs.next()) {
			order11=rs.getInt(1);System.out.println("================="+order11);
		}
		String c12="select count(time) from orders where time BETWEEN '2021-12-01' AND '2021-12-31'";
		pst=conn.prepareStatement(c12);
		rs=pst.executeQuery();
		if(rs.next()) {
			order12=rs.getInt(1);System.out.println("================="+order12);
		}
		
//		
//		----------------------------chart-----------------------------------------------------
		XYChart.Series line1=new XYChart.Series();
		line1.setName("Orders");
		line1.getData().add(new XYChart.Data("Jan",order1));
		line1.getData().add(new XYChart.Data("Feb",order2));
		line1.getData().add(new XYChart.Data("Mar",order3));
		line1.getData().add(new XYChart.Data("Apr",order4));
		line1.getData().add(new XYChart.Data("May",order5));
		line1.getData().add(new XYChart.Data("June",order6));
		line1.getData().add(new XYChart.Data("July",order7));
		line1.getData().add(new XYChart.Data("Aug",order8));
		line1.getData().add(new XYChart.Data("Sep",order9));
		line1.getData().add(new XYChart.Data("Oct",order10));
		line1.getData().add(new XYChart.Data("Nov",order11));
		line1.getData().add(new XYChart.Data("Dec",order12));
		
		lineChart.getData().addAll(line1);
		lineChart.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent;");
	}
	
	
	@FXML
    private TableView<Dashboard> table_run_out;

    @FXML
    private TableColumn<Dashboard, Integer> col_no;

    @FXML
    private TableColumn<Dashboard, Integer> col_id;

    @FXML
    private TableColumn<Dashboard, String> col_pro_name;

    @FXML
    private TableColumn<Dashboard, Integer> col_amount;
	
	
    @FXML
	void tableRunOut() {
		col_no.setCellValueFactory(new PropertyValueFactory<Dashboard, Integer>("no"));
		col_id.setCellValueFactory(new PropertyValueFactory<Dashboard, Integer>("pro_id"));
		col_pro_name.setCellValueFactory(new PropertyValueFactory<Dashboard, String>("pro_name"));
		col_amount.setCellValueFactory(new PropertyValueFactory<Dashboard, Integer>("amount"));
		
		listC = connectDB.getDataRunOut();
        table_run_out.setItems(listC);

	}
	
	
	
	
    private void barChart1() throws SQLException {

		conn = connectDB.ConnectDb();
		
		String sql2="SELECT customer.* FROM customer ORDER BY customer.cus_point DESC LIMIT 5";
		pst = conn.prepareStatement(sql2);
		rs = pst.executeQuery();
		while(rs.next()) {
			System.out.println("ten: "+rs.getString("cus_name")+" - Point: "+rs.getInt("cus_point"));
			
			XYChart.Series series = new XYChart.Series();
			series.getData().add(new XYChart.Data(rs.getString("cus_name"), rs.getInt("cus_point")));
		
			
			barChart1.getData().addAll(series);
			barChart1.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent;");
		}
    }
	
	
	
	
	
	
	
	
	
	
	
	
}
