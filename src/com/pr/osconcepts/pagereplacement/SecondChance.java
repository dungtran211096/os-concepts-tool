package com.pr.osconcepts.pagereplacement;

import java.util.ArrayList;

public class SecondChance extends PRAlgorithm{
	private int ctrl;
	private ArrayList<Integer> RefBits;
	public SecondChance(ArrayList<Integer> od, int frs, int ctrl){
		super(od,frs);
		this.ctrl = ctrl;
		this.Running();
	}
	
	private void Running(){
		ArrayList<Integer> Frame = new ArrayList<Integer>();
		RefBits = new ArrayList<Integer>();
		for (int i = 0; i<Order.size(); i++){
			if (isContain(Frame,Order.get(i))==-1){//Nếu ko có trong Frame
				this.fault_order.add(Order.get(i));
				this.page_fault++;
				if (Frame.size() < this.frame_size){
					//Nếu Frame còn chỗ, tải vào Frame
					//Tăng số lần tham chiếu lên
					Frame.add(Order.get(i));
					RefBits.add(0);//Nếu trang đc tải vào frame, đặt bit tham chiếu = 0
				}
				else{//Nếu hết chỗ, thực hiện giải thuật
					//Đẩy trang tại vị trí pos ra bộ nhớ ảo
					while (RefBits.get(ctrl) == 1){//di chuyển con trỏ cho đến khi thấy RefBits == 0
						RefBits.set(ctrl, 0);
						this.nextCtrl();
					}
					swapped_out.add(Frame.get(ctrl));
					Frame.set(ctrl,Order.get(i));//tải trang mới vào frame thay thế trang hiện tại
					RefBits.set(ctrl, 0);//đặt bit tham chiếu = 0
					this.page_replaced++;
				}
			}
			else{//Nếu có trong Frame
				int pos = isContain(Frame,Order.get(i));
				RefBits.set(pos, 1); // trang đc tham chiếu lại => bit tham chiếu  = 1
			}
		}
	}
	private void nextCtrl(){
		ctrl ++;
		if (ctrl == this.frame_size) ctrl = 0;
	}
}
