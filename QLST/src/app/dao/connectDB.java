package app.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import app.controller.employee_controller.Bill_Manage;
import app.model.Account1;
import app.model.Bill;
import app.model.Bill_Mange;
import app.model.Inventory;
import app.model.ChangeShift;

import app.model.Product;
import app.model.Title;
import app.model.Brand1;
import app.model.Category1;
import app.model.Dashboard;
import app.model.Order_Detail;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;;

public class connectDB {
	Connection conn = null;
    public static Connection ConnectDb(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/qlst","root","");
           // JOptionPane.showMessageDialog(null, "Connection Established");
            System.out.println("ket noi database thanh cong");
            return conn;
        } catch (Exception e) {
        	e.printStackTrace();
            return null;
        }
    
    }
    
    public static void main(String[] args) {
    	// TODO Auto-generated method stub
    	Connection conn=connectDB.ConnectDb();
    	
    }
    
    public static ObservableList<Bill> getDatausers_bill() {
        Connection conn = ConnectDb();
        ObservableList<Bill> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from product");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   

                list.add(new Bill(rs.getRow(),Integer.parseInt(rs.getString("pro_id")),rs.getString("pro_name"),rs.getString("pro_brand"),rs.getString("pro_sale_price"),rs.getString("barcode"),rs.getString("pro_category")));       


            }
        } catch (Exception e) {
        	System.out.println(e);
        }
        return list;
	}
    
    
    //---------------------Inventory_employee-------------------------------
    public static ObservableList<Inventory> getDataInventory() {
        Connection conn = ConnectDb();
        ObservableList<Inventory> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT ware_house.* FROM ware_house");
            ResultSet rs = ps.executeQuery();					
            
            while (rs.next()){   
                list.add(new Inventory(
                		Integer.parseInt(rs.getString("wh_id")),
                		Integer.parseInt(rs.getString("pro_id")),
                		Integer.parseInt(rs.getString("amount_stock")),
                		Integer.parseInt(rs.getString("amount_input")),
                		Integer.parseInt(rs.getString("price_input")),
                		rs.getRow(),
                		rs.getString("pro_name"), 
                		rs.getString("date_input")
                	));    
                
            }
        } catch (Exception e) {
        	System.out.println(e);
        }
        return list;
        //SELECT ware_house.*,product.* FROM ware_house,product WHERE ware_house.pro_name=product.pro_name
        //SELECT ware_house.pro_name, product.pro_name FROM ware_house INNER JOIN product ON ware_house.pro_name = product.pro_name
	} 
    
    
  //---------------------Change_shift-------------------------------
    public static ObservableList<ChangeShift> getDataChangeShift() {
        Connection conn = ConnectDb();
        ObservableList<ChangeShift> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT change_shift.* FROM change_shift");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new ChangeShift(
                		Integer.parseInt(rs.getString("shift_id")),
                		rs.getRow(),
                		rs.getString("main_shift"), 
                		rs.getString("switch_shift"),
                		rs.getString("date_switch"), 
                		rs.getString("shift_change_date"),
                		rs.getString("emp_name"), 
                		rs.getString("emp_email")
                	));    
                
            }
        } catch (Exception e) {
        	System.out.println(e);
        }
        return list;

        
	} 
    
//    public static ObservableList<Bill> getDatausers_bill() {
//        Connection conn = ConnectDb();
//        ObservableList<Bill> list = FXCollections.observableArrayList();
//        try {
//            PreparedStatement ps = conn.prepareStatement("select product.pro_name,product.pro_brand, brand.brand_name ,product.pro_sale_price,product.barcode from product INNER JOIN brand ON product.brand_id = brand.brand_id");
//            ResultSet rs = ps.executeQuery();
//            
//            while (rs.next()){   
//                list.add(new Bill(rs.getString("pro_name"),rs.getString("brand_name"),rs.getString("pro_sale_price"),rs.getString("barcode")));       
//            }
//        } catch (Exception e) {
//        	System.out.println(e);
//        }
//        return list;
//	}
//---------------------account-------------------------------
    public static ObservableList<Account1> getDataAccount1() {
        Connection conn = ConnectDb();
        ObservableList<Account1> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT employee.*,title.* FROM employee,title WHERE employee.title_id=title.title_id ORDER BY employee.emp_id DESC");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new Account1(
                		Integer.parseInt(rs.getString("emp_id")),
                		Integer.parseInt(rs.getString("emp_status")),
                		rs.getRow(),
                		rs.getString("emp_name"), 
                		rs.getString("emp_email"),
                		rs.getString("emp_phone"), 
                		rs.getString("emp_address"),  
                		rs.getString("emp_gender"),
                		rs.getString("title_name"),
                		rs.getString("emp_user"), 
                		rs.getString("emp_pass")
                	));    
                
            }
        } catch (Exception e) {
        	System.out.println(e);
        }
        return list;

        
	} 
  //---------------------Category-brand-------------------------------
    public static ObservableList<Category1> getDataCategory1() {
        Connection conn = ConnectDb();
        ObservableList<Category1> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT category.* FROM category");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new Category1(
                		Integer.parseInt(rs.getString("cat_id")), 
                		rs.getRow(),
                		rs.getString("cat_name")
                	));    
                
            }
        } catch (Exception e) {
        	System.out.println(e);
        }
        return list;
        
	}    
    public static ObservableList<Brand1> getDataBrand1() {
        Connection conn = ConnectDb();
        ObservableList<Brand1> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT brand.* FROM brand");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new Brand1( 
                		Integer.parseInt(rs.getString("brand_id")),
                		rs.getRow(),
                		rs.getString("brand_name")
                	));    
                
            }
        } catch (Exception e) {
        	System.out.println(e);
        }
        return list;
        
	}
    
    public static ObservableList<Title> getDataTitle() {
        Connection conn = ConnectDb();
        ObservableList<Title> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM title");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new Title(
                		Integer.parseInt(rs.getString("title_id")),
                		rs.getRow(), 
                		rs.getString("title_name"), 
                		rs.getString("description")
                	));    
                
            }
        } catch (Exception e) {
        	System.out.println(e);
        }
        return list;
        
	}
    
    public static ObservableList<Product> getDataProduct(){
        Connection conn = ConnectDb();
        ObservableList<Product> list = FXCollections.observableArrayList();
        try {
//            PreparedStatement ps = conn.prepareStatement("select product.pro_id, product.pro_name,product.pro_expiry,product.pro_unit,product.pro_category,product.pro_brand ,product.pro_sale_price,product.barcode from product INNER JOIN brand ON product.brand_id = brand.brand_id");
        	PreparedStatement ps = conn.prepareStatement("select * from product");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new Product(Integer.parseInt(rs.getString("pro_id")),rs.getRow(),rs.getString("pro_sale_price"),rs.getString("barcode"),rs.getString("pro_name"),rs.getString("pro_expiry"),rs.getString("pro_unit"),rs.getString("pro_brand"),rs.getString("pro_category")));       
            }
        } catch (Exception e) {
        	System.out.println(e);
        }
        return list;
    }


    public static ObservableList<Order_Detail> getDataOrder_detail(){
        Connection conn = ConnectDb();
        ObservableList<Order_Detail> list = FXCollections.observableArrayList();
        try {
//            PreparedStatement ps = conn.prepareStatement("select product.pro_id, product.pro_name,product.pro_expiry,product.pro_unit,product.pro_category,product.pro_brand ,product.pro_sale_price,product.barcode from product INNER JOIN brand ON product.brand_id = brand.brand_id");
        	PreparedStatement ps = conn.prepareStatement("select * from orders_detail where order_id = order_id");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new Order_Detail(rs.getRow(),Integer.parseInt(rs.getString("order_id")),Integer.parseInt(rs.getString("quantity")),Integer.parseInt(rs.getString("price")),rs.getString("name"),Integer.parseInt(rs.getString("total"))));       
            }
        } catch (Exception e) {
        	System.out.println(e);
        }
        return list;
    }
 
    //----------------------RUN OUT--------------------
    public static ObservableList<Dashboard> getDataRunOut() {
        Connection conn = ConnectDb();
        ObservableList<Dashboard> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT ware_house.*,product.pro_name,amount_stock+amount_input AS \"total_amount\" FROM ware_house,product WHERE amount_stock+amount_input<100 AND ware_house.pro_id=product.pro_id"
            		+ "");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new Dashboard(
                		Integer.parseInt(rs.getString("pro_id")), 
                		Integer.parseInt(rs.getString("total_amount")), 
                		rs.getRow(),
                		rs.getString("pro_name")
                	));    
                
            }
        } catch (Exception e) {
        	System.out.println(e);
        }
        return list;
        
	} 
    
    public static ObservableList<Bill_Mange> getBill_manage() {
        Connection conn = ConnectDb();
        ObservableList<Bill_Mange> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT orders.order_id,orders.total_price,orders.point,orders.discount,orders_detail.name,orders_detail.quantity,orders_detail.price,orders_detail.total,orders.time FROM orders INNER JOIN orders_detail ON orders.order_id = orders_detail.order_id");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new Bill_Mange(
                		rs.getRow(),
                		rs.getRow(),
                		Integer.parseInt(rs.getString("order_id")),
                		Integer.parseInt(rs.getString("total_price")),
                		Integer.parseInt(rs.getString("point")),
                		Integer.parseInt(rs.getString("discount")),              		
                		Integer.parseInt(rs.getString("quantity")),
                		Integer.parseInt(rs.getString("price")),
                		Integer.parseInt(rs.getString("total")),
                		rs.getString("name"),
                		rs.getString("time")
                	));    
                
            }
        } catch (Exception e) {
        	System.out.println(e);
        }
        return list;
        
	}
    
    
    
    
    
}
