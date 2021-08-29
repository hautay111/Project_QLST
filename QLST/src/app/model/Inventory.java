package app.model;

public class Inventory {
	int wh_id,pro_id,amount_stock,amount_input,price_input,no;
	String pro_name,date_input;
	public Inventory(int wh_id, int pro_id, int amount_stock, int amount_input, int price_input, int no, String pro_name, String date_input) {
		this.wh_id = wh_id;
		this.pro_id = pro_id;
		this.amount_stock = amount_stock;
		this.amount_input = amount_input;
		this.price_input = price_input;
		this.no = no;
		this.pro_name = pro_name;
		this.date_input = date_input;

	}
	public int getWh_id() {
		return wh_id;
	}
	public void setWh_id(int wh_id) {
		this.wh_id = wh_id;
	}
	public int getPro_id() {
		return pro_id;
	}
	public void setPro_id(int pro_id) {
		this.pro_id = pro_id;
	}
	public int getAmount_stock() {
		return amount_stock;
	}
	public void setAmount_stock(int amount_stock) {
		this.amount_stock = amount_stock;
	}
	public int getAmount_input() {
		return amount_input;
	}
	public void setAmount_input(int amount_input) {
		this.amount_input = amount_input;
	}
	public int getPrice_input() {
		return price_input;
	}
	public void setPrice_input(int price_input) {
		this.price_input = price_input;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getPro_name() {
		return pro_name;
	}
	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}
	public String getDate_input() {
		return date_input;
	}
	public void setDate_input(String date_input) {
		this.date_input = date_input;
	}
}
