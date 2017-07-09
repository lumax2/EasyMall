package cn.tedu.dao.impl;

import java.sql.SQLException;

import utils.JDBCUtils;
import cn.tedu.dao.OrderDao;
import cn.tedu.entity.Order;
import cn.tedu.entity.OrderItem;

public class OrderDaoImpl implements OrderDao {

	/**
	 * | Field        | Type         | Null | Key | Default           | Extra                       |
+--------------+--------------+------+-----+-------------------+-----------------------------+
| id           | varchar(100) | NO   | PRI | NULL              |                             |
| money        | double       | YES  |     | NULL              |                             |
| receiverinfo | varchar(255) | YES  |     | NULL              |                             |
| paystate     | int(11)      | YES  |     | NULL              |                             |
| ordertime    | timestamp    | NO   |     | CURRENT_TIMESTAMP | on update CURRENT_TIMESTAMP |
| user_id      | int(11)      | YES  | MUL | NULL              |                             |
+--------------+--------------+------+-----+-------------------+-----------------------------+
	 */
	public void addOrder(Order order) {
		String sql ="insert into orders(id,money,receiverinfo,paystate,ordertime,user_id) " +
				" values(?,?,?,?,?,?)";
		try {
			JDBCUtils.update(sql,order.getId(),order.getMoney(),order.getReceiverinfo(),order.getPaystate(),order.getOrdertime()
					,order.getUser_id());
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
/**
 * | Field      | Type         | Null | Key | Default | Extra |
+------------+--------------+------+-----+---------+-------+
| order_id   | varchar(100) | NO   | PRI | NULL    |       |
| product_id | varchar(100) | NO   | PRI | NULL    |       |
| buynum     | int(11)      | YES  |     | NULL    |       |
+------------+--------------+------+-----+---------+-------+
 */
	public void addOrderItem(OrderItem oi) {
		String sql ="insert into orderitem(order_id,product_id,buynum) values(?,?,?)";
		try {
			JDBCUtils.update(sql, oi.getOrder_id(),oi.getProduct_id(),oi.getBuynum());
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
