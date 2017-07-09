package cn.tedu.service;

import java.util.List;

import cn.tedu.entity.Order;
import cn.tedu.entity.OrderItem;
import cn.tedu.exception.MsgException;

public interface OrderService {
	/**
	 * 
	 * @param order
	 * @param oiList
	 * @throws MsgException
	 */
	void addOrder(Order order, List<OrderItem> oiList) throws MsgException;

}
