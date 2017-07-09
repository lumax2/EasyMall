package cn.tedu.service.impl;

import java.util.List;

import cn.tedu.dao.OrderDao;
import cn.tedu.dao.ProdDao;
import cn.tedu.entity.Order;
import cn.tedu.entity.OrderItem;
import cn.tedu.entity.Product;
import cn.tedu.exception.MsgException;
import cn.tedu.factory.BasicFactory;
import cn.tedu.service.OrderService;

public class OrderServiceImpl implements OrderService{

	private OrderDao orderDao =BasicFactory.getFactory().getInstance(OrderDao.class);
	private ProdDao prodDao = BasicFactory.getFactory().getInstance(ProdDao.class);
	public void addOrder(Order order, List<OrderItem> oiList)
			throws MsgException {
		
		//1、向orders表添加一条记录
		orderDao.addOrder(order);
		//2、循环遍历
		for (OrderItem oi : oiList) {
			//3判断库存是否充足
			Product prod = prodDao.findProdById(oi.getProduct_id());
			if(prod.getPnum()<oi.getBuynum()){//不足
				throw new MsgException(prod.getId()+","+prod.getName()+",pnum:"+prod.getPnum()+":库存不足");
			}
			//充足,修改商品库存、添加订单项目
			prodDao.changePnum(prod.getId(), prod.getPnum()-oi.getBuynum());
			orderDao.addOrderItem(oi);
		}
	}

}
