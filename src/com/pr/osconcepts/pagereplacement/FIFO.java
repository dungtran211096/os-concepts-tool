package com.pr.osconcepts.pagereplacement;

import java.util.ArrayList;

public class FIFO extends PRAlgorithm{
	public FIFO(ArrayList<Integer> od, int frs){
		super(od,frs);
		this.Running();
	}
	private void Running(){
		ArrayList<Integer> Frame = new ArrayList<Integer>();
		for (int i = 0; i<Order.size(); i++){
			if (isContain(Frame,Order.get(i))==-1){
				this.fault_order.add(Order.get(i));
				this.page_fault++;
				if (Frame.size() < this.frame_size){//Nếu Frame còn chỗ, chèn vào Frame
					Frame.add(Order.get(i));
				}
				else{//Nếu hết chỗ, thực hiện giải thuật
					this.swapped_out.add(Frame.get(0));
					Frame.remove(0);
					Frame.add(Order.get(i));
					this.page_replaced++;
				}
			}
		}
	}
}
