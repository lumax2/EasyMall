package cn.tedu.dao;


import cn.tedu.entity.Order;
import cn.tedu.entity.OrderItem;

public interface OrderDao {
	/**
	 *��order�������һ����¼
	 * @param conn 
	 * @param order ��װ������Ϣ��order����
	 */
	
	
	public void addOrder(Order order);
	
	public void addOrderItem(OrderItem oi);
	
	
	
	
}
