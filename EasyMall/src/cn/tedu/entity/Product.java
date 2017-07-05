package cn.tedu.entity;

/**
 * +-------------+--------------+------+-----+---------+-------+
| Field       | Type         | Null | Key | Default | Extra |
+-------------+--------------+------+-----+---------+-------+
| id          | varchar(100) | NO   | PRI | NULL    |       |
| name        | varchar(255) | YES  |     | NULL    |       |
| price       | double       | YES  |     | NULL    |       |
| category    | varchar(255) | YES  |     | NULL    |       |
| pnum        | int(11)      | YES  |     | NULL    |       |
| imgurl      | varchar(255) | YES  |     | NULL    |       |
| description | varchar(255) | YES  |     | NULL    |       |
+-------------+--------------+------+-----+---------+-------+
 * @author Hao
 *
 */
public class Product {
	
	private String id;
	private String name;
	private double price;
	private String category;
	private int pnum;
	private String imgurl;
	private String description;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getPnum() {
		return pnum;
	}
	public void setPnum(int pnum) {
		this.pnum = pnum;
	}
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	

}