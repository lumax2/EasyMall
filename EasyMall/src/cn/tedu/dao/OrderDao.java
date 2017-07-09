package cn.tedu.dao;

import cn.tedu.entity.Order;
import cn.tedu.entity.OrderItem;

public interface OrderDao {
	/**
	 *向order表中添加一条记录
	 * @param order 封装订单信息的order对象
	 */
	public void addOrder(Order order);
	
	
	
	public void addOrderItem(OrderItem oi);
}
