package cn.tedu.entity;

import java.util.Date;

public class Order {
	
	/**
	 * 
	 */
	private String id;//����ID
	private double money;//�������
	private String receiverinfo;//�ռ�����Ϣ
	private int paystate;//֧��״̬ 0:��ʾδ֧��,1��ʾ�Ѿ�֧��
	private Date ordertime;//���������ʱ��:??----------------------
	private int user_id;//�û�id
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public String getReceiverinfo() {
		return receiverinfo;
	}
	public void setReceiverinfo(String receiverinfo) {
		this.receiverinfo = receiverinfo;
	}
	public int getPaystate() {
		return paystate;
	}
	public void setPaystate(int paystate) {
		this.paystate = paystate;
	}
	public Date getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
}
