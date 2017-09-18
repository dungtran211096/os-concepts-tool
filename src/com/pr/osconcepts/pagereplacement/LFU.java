package com.pr.osconcepts.pagereplacement;

import java.util.ArrayList;

public class LFU extends PRAlgorithm{
	public LFU(ArrayList<Integer> od, int frs){
		super(od,frs);
		this.Running();
	}
	private void Running(){
		ArrayList<Integer> Frame = new ArrayList<Integer>();
		ArrayList<Integer> Frequently = new ArrayList<Integer>();
		
		for (int i = 0; i<Order.size(); i++){
			if (isContain(Frame,Order.get(i))==-1){//Nếu ko có trong Frame
				this.fault_order.add(Order.get(i));
				this.page_fault++;
				if (Frame.size() < this.frame_size){
					//Nếu Frame còn chỗ, chèn vào Frame
					//Đặt số lần tham chiếu = 1
					Frame.add(Order.get(i));
					Frequently.add(1);
				}
				else{//Nếu hết chỗ, thực hiện giải thuật
					int pos = findMinPos(Frequently);
					//Đẩy trang tại vị trí pos ra bộ nhớ ảo
					this.swapped_out.add(Frame.get(pos));
					
					Frame.remove(pos);
					Frequently.remove(pos);
					
					//Thêm trang mới vào Frame
					//Thêm số lần tham chiếu
					Frame.add(Order.get(i));
					Frequently.add(1);
					this.page_replaced++;
				}
			}
			else{//Nếu có trong Frame
				//Tăng số lần tham chiếu lên
				int pos = isContain(Frame,Order.get(i));
				Frequently.set(pos, Frequently.get(pos)+1);
			}
		}
	}
	private int findMinPos(ArrayList<Integer> arr){
		int minpos = 0;
		for (int i = 0; i<arr.size(); i++){
			if (arr.get(minpos) > arr.get(i)){
				minpos = i;
			}
		}
		return minpos;
	}
}
