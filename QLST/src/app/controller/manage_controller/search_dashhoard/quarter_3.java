package app.controller.manage_controller.search_dashhoard;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import app.dao.connectDB;
import app.model.Category1;
import app.model.Dashboard;
import app.model.search_dashboard.Quarter_3;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.ExporterInput;
import net.sf.jasperreports.export.OutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.view.JasperViewer;

public class quarter_3 implements Initializable{
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;
	int index=-1;
    ObservableList<Quarter_3> listC;

    @FXML
    private BorderPane main;

    @FXML
    private Label quarter;

    @FXML
    private LineChart<?, ?> lineChart;

    @FXML
    private Label amount_product;

    @FXML
    private Label amount_orders;

    @FXML
    private Label amount_input;
    
    @FXML
    private Label money;
    
    @FXML
    private Label import_money;

    @FXML
    private Label sales_money;

    @FXML
    private BarChart<?, ?> barChart;

    @FXML
    private BarChart<?, ?> barChart1;
    
    @FXML
    private TableView<Quarter_3> table_quarter_3;
    
    @FXML
    private TableColumn<Quarter_3, Integer> col_no;

    @FXML
    private TableColumn<Quarter_3, Integer> col_id;

    @FXML
    private TableColumn<Quarter_3, String> col_pro_name;

    @FXML
    private TableColumn<Quarter_3, Integer> col_amount;

    public static int amount;
	public static int input, sales_money1, import_money1,input_price,orders,product;
	
    @FXML
    void back(MouseEvent event) {
    	Stage stage = (Stage) main.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	tableQuarter1();
		// TODO Auto-generated method stub
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
			iniLineChart();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			conn = connectDB.ConnectDb();
			String sql1 = "SELECT COUNT(order_id) FROM orders WHERE QUARTER(orders.time)=3;";
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
			conn = connectDB.ConnectDb();
			String sql2 = "SELECT COUNT(input_id) FROM input WHERE QUARTER(input.time)=3";
			pst = conn.prepareStatement(sql2);
			rs = pst.executeQuery();
			if (rs.next()) {
				input = rs.getInt(1);
				amount_input.setText(Integer.toString(input));
				System.out.println("Tong orders : --->" + input);
			}
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
			String sql2 = "SELECT SUM(input_detail.input_price*input_detail.amount) AS 'total' FROM input_detail,input WHERE QUARTER(input.time)=3 AND input.input_id=input_detail.input_id";
			String sql1 = "SELECT SUM(orders.total_price) FROM orders WHERE QUARTER(orders.time)=3";
			String sql3 = "SELECT SUM(output_detail.quantity)*input_detail.input_price FROM output_detail,input_detail,ware_house,output WHERE output_detail.wh_id=ware_house.wh_id AND input_detail.input_detail_id=ware_house.input_detail_id AND QUARTER(output.time)=3 AND output_detail.output_id=output.output_id";
			pst = conn.prepareStatement(sql2);
			rs = pst.executeQuery();
			if (rs.next()) {
				import_money1=rs.getInt("total");
				import_money.setText(Integer.toString(import_money1));
				pst = conn.prepareStatement(sql1);
				ResultSet rs1 = pst.executeQuery();
				if(rs1.next()) {
					sales_money1=rs1.getInt("SUM(orders.total_price)");
					sales_money.setText(Integer.toString(sales_money1));
				}
				pst = conn.prepareStatement(sql3);
				ResultSet rs3 = pst.executeQuery();
				if(rs3.next()) {
					input_price=rs3.getInt("SUM(output_detail.quantity)*input_detail.input_price");
				}
				input=sales_money1-input_price;
				money.setText(Integer.toString(input));
				System.out.println("DT: --->" + input);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    private void barChart() throws SQLException {

		conn = connectDB.ConnectDb();
		
		String sql1="SELECT Sum(orders_detail.quantity) AS 'amount',product.pro_name ,product.pro_id FROM orders_detail,orders,product WHERE orders.order_id=orders_detail.order_id AND orders_detail.pro_id=product.pro_id AND quarter(orders.time)=3 GROUP BY product.pro_name ORDER BY Sum(orders_detail.quantity) DESC";
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
		String c1="SELECT COUNT(order_id) FROM orders where MONTH(time)='1'";
		pst=conn.prepareStatement(c1);
		rs=pst.executeQuery();
		if(rs.next()) {
			order1=rs.getInt(1);System.out.println("================="+order1);
		}
		String c2="SELECT COUNT(order_id) FROM orders where MONTH(time)='2' ";
		pst=conn.prepareStatement(c2);
		rs=pst.executeQuery();
		if(rs.next()) {
			order2=rs.getInt(1);System.out.println("================="+order2);
		}
		String c3="SELECT COUNT(order_id) FROM orders where MONTH(time)='3'";
		pst=conn.prepareStatement(c3);
		rs=pst.executeQuery();
		if(rs.next()) {
			order3=rs.getInt(1);System.out.println("================="+order3);
		}
		String c4="SELECT COUNT(order_id) FROM orders where MONTH(time)='4'";
		pst=conn.prepareStatement(c4);
		rs=pst.executeQuery();
		if(rs.next()) {
			order4=rs.getInt(1);System.out.println("================="+order4);
		}
		String c5="SELECT COUNT(order_id) FROM orders where MONTH(time)='5'";
		pst=conn.prepareStatement(c5);
		rs=pst.executeQuery();
		if(rs.next()) {
			order5=rs.getInt(1);System.out.println("================="+order5);
		}
		String c6="SELECT COUNT(order_id) FROM orders where MONTH(time)='6'";
		pst=conn.prepareStatement(c6);
		rs=pst.executeQuery();
		if(rs.next()) {
			order6=rs.getInt(1);System.out.println("================="+order6);
		}
		String c7="SELECT COUNT(order_id) FROM orders where MONTH(time)='7'";
		pst=conn.prepareStatement(c7);
		rs=pst.executeQuery();
		if(rs.next()) {
			order7=rs.getInt(1);System.out.println("================="+order7);
		}
		String c8="SELECT COUNT(order_id) FROM orders where MONTH(time)='8'";
		pst=conn.prepareStatement(c8);
		rs=pst.executeQuery();
		if(rs.next()) {
			order8=rs.getInt(1);System.out.println("================="+order8);
		}
		String c9="SELECT COUNT(order_id) FROM orders where MONTH(time)='9'";
		pst=conn.prepareStatement(c9);
		rs=pst.executeQuery();
		if(rs.next()) {
			order9=rs.getInt(1);System.out.println("================="+order9);
		}
		String c10="SELECT COUNT(order_id) FROM orders where MONTH(time)='10'";
		pst=conn.prepareStatement(c10);
		rs=pst.executeQuery();
		if(rs.next()) {
			order10=rs.getInt(1);System.out.println("================="+order10);
		}
		String c11="SELECT COUNT(order_id) FROM orders where MONTH(time)='11'";
		pst=conn.prepareStatement(c11);
		rs=pst.executeQuery();
		if(rs.next()) {
			order11=rs.getInt(1);System.out.println("================="+order11);
		}
		String c12="SELECT COUNT(order_id) FROM orders where MONTH(time)='12'";
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

	
	private void barChart1() throws SQLException {

		conn = connectDB.ConnectDb();
		
		String sql2="SELECT c.cus_name,SUM(o.total_price) AS 'sales_price' FROM orders o, customer c WHERE o.cus_id=c.cus_id and c.cus_code!=1 and quarter(o.time)=3 GROUP BY o.cus_id ORDER BY SUM(o.total_price) DESC LIMIT 5";
		pst = conn.prepareStatement(sql2);
		rs = pst.executeQuery();
		while(rs.next()) {
			System.out.println("ten: "+rs.getString("cus_name")+" - sales_price: "+rs.getInt("sales_price"));
			
			XYChart.Series series = new XYChart.Series();
			series.getData().add(new XYChart.Data(rs.getString("cus_name"), rs.getInt("sales_price")));
		
			
			barChart1.getData().addAll(series);
			barChart1.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent;");
		}
    }
	
	@FXML
	void tableQuarter1() {
		col_no.setCellValueFactory(new PropertyValueFactory<Quarter_3, Integer>("no"));
		col_id.setCellValueFactory(new PropertyValueFactory<Quarter_3, Integer>("pro_id"));
		col_pro_name.setCellValueFactory(new PropertyValueFactory<Quarter_3, String>("pro_name"));
		col_amount.setCellValueFactory(new PropertyValueFactory<Quarter_3, Integer>("amount"));		
		listC = connectDB.getDataQuater3();
		table_quarter_3.setItems(listC);

	}
	@FXML
    void In(ActionEvent event) throws JRException {
//		a();
		b();
    }

	public void a() throws JRException {
		String reportSrcFile = "C:/java/work-space/Project_QLST - 1/QLST/src/app/report/TestReport.jrxml";
	       
	       // Compile file nguá»“n trÆ°á»›c.
	       JasperReport jasperReport =    JasperCompileManager.compileReport(reportSrcFile);
	 
	       Connection conn = connectDB.ConnectDb();
	 
	       // Tham sá»‘ truyá»�n vÃ o bÃ¡o cÃ¡o.
	       Map<String, Object> parameters = new HashMap<String, Object>();
	 
	       JasperPrint print = JasperFillManager.fillReport(jasperReport,
	               parameters, conn);
	       
	       // Ä�áº£m báº£o thÆ° má»¥c Ä‘áº§u ra tá»“n táº¡i.
	       File outDir = new File("C:/jasperoutput");
	       outDir.mkdirs();
	 
	       // PDF Exportor.
	       JRPdfExporter exporter = new JRPdfExporter();
	 
	       ExporterInput exporterInput = new SimpleExporterInput(print);
	       // ExporterInput
	       exporter.setExporterInput(exporterInput);
//	 ------------
	       String timeStamp = new SimpleDateFormat("yyyy_MM_dd_HH'"+"h"+"'_mm'"+"m"+"'_ss'"+"s"+"'").format(new Date());
	       String namefile=timeStamp + ".pdf";
//	 -----------
	       // ExporterOutput
	       OutputStreamExporterOutput exporterOutput = new SimpleOutputStreamExporterOutput(
	    		   "C:/jasperoutput/FirstJasperReport_"+namefile	    		   
	    	);
	       // Output
	       exporter.setExporterOutput(exporterOutput);
	 
	       //
	       SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
	       exporter.setConfiguration(configuration);
	       exporter.exportReport();
	       
	       JasperViewer.viewReport(print);
//	       JOptionPane.showMessageDialog(null, "Export Report Successfully.");
	 
	       System.out.print("Done!");
	}
	public void b() throws JRException {
		conn=connectDB.ConnectDb();
		try {
			JasperDesign jdesign=FXMLLoader.load(getClass().getResource("/app/report/TestReport.jrxml"));
			String sql="SELECT ware_house.wh_id,input_detail.pro_id, product.pro_name,input_detail.amount,input_detail.input_price,input_detail.input_detail_id,input_detail.input_id,ware_house.date_input FROM ware_house ,input_detail, product WHERE ware_house.input_detail_id=input_detail.input_detail_id AND input_detail.pro_id=product.pro_id";
			JRDesignQuery show= new JRDesignQuery();
			show.setText(sql);
			jdesign.setQuery(show);
			JasperReport jasperReport =    JasperCompileManager.compileReport(jdesign);
			JasperPrint print = JasperFillManager.fillReport(jasperReport,
		               null, conn);
			JasperViewer.viewReport(print);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
//	public void c() throws JRException {
//		conn=connectDB.ConnectDb();
//		try {
//			public void b() throws JRException {
//				conn=connectDB.ConnectDb();
//				try {
//					public void b() throws JRException {
//						conn=connectDB.ConnectDb();
//						try {
//							JasperDesign jdesign=FXMLLoader.load(getClass().getResource("/app/report/TestReport.jrxml"));
//							String sql="SELECT ware_house.wh_id,input_detail.pro_id, product.pro_name,input_detail.amount,input_detail.input_price,input_detail.input_detail_id,input_detail.input_id,ware_house.date_input FROM ware_house ,input_detail, product WHERE ware_house.input_detail_id=input_detail.input_detail_id AND input_detail.pro_id=product.pro_id";
//							JRDesignQuery show= new JRDesignQuery();
//							show.setText(sql);
//							jdesign.setQuery(show);
//							JasperReport jasperReport =    JasperCompileManager.compileReport(jdesign);
//							JasperPrint print = JasperFillManager.fillReport(jasperReport,
//						               null, conn);
//							JasperViewer.viewReport(print);
//							
//							
//						} catch (IOException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//						
//					}
//					
//					
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
//			}
//			
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
}
