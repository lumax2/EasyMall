package cn.tedu.service.impl;

import java.util.List;

import utils.TranManager;

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
		

	
		try {
		TranManager.startTran();
			
		//1����orders�����һ����¼
		orderDao.addOrder(order);
		//2��ѭ������
		for (OrderItem oi : oiList) {
			//3�жϿ���Ƿ����
			Product prod = prodDao.findProdById(oi.getProduct_id());
			if(prod.getPnum()<oi.getBuynum()){//����
				throw new MsgException(prod.getId()+","+prod.getName()+",pnum:"+prod.getPnum()+":��治��");
			}
			//����,�޸���Ʒ��桢��Ӷ�����Ŀ
			prodDao.changePnum(prod.getId(), prod.getPnum()-oi.getBuynum());
			orderDao.addOrderItem(oi);
		}
		TranManager.commitTran();
		
		} catch (MsgException e) {
			e.printStackTrace();
			
			TranManager.rollbackTran();
			
		}finally{
			TranManager.release();
		}
			
				
						
				
					
				
			
	}
	

}
