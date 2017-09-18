package com.pr.osconcepts.pagereplacement;

import java.util.ArrayList;

public class Optimal extends PRAlgorithm{
	public Optimal(ArrayList<Integer> od, int frs){
		super(od,frs);
		this.Running();
	}
	
	private void Running(){
		ArrayList<Integer> Frame = new ArrayList<Integer>();
		for (int i = 0; i<Order.size(); i++){
			if (isContain(Frame,Order.get(i))==-1){//Nếu chưa có trong frame
				fault_order.add(Order.get(i));
				this.page_fault++;
				if (Frame.size() < this.frame_size){//Nếu frame còn trống
					Frame.add(Order.get(i));
				}
				else{//Nếu frame hết chỗ
					int count = 0;//đếm 
					boolean flag = false;//đánh dấu true: đã thấy page từ i+1 trở đi có trong frame
					ArrayList<Boolean> isGoingToUse = new ArrayList<Boolean>();
					for (int j = 0; j<this.frame_size; j++) isGoingToUse.add(false);
					for (int i_tmp = i+1; i_tmp<Order.size(); i_tmp++){//Tìm từ vị trí i+1
						if (isContain(Frame,Order.get(i_tmp))!=-1){//Nếu thấy trong frame
							//=> sắp được sử dụng
							flag = true;//đã thấy
							if (count < this.frame_size-1 ){
								count ++;//nếu đếm < fram_size-1 đếm tăng 1
								isGoingToUse.set(isContain(Frame,Order.get(i_tmp)), true);//đánh dấu trong frame
							}
						}
					}
					if (count <= this.frame_size-1 && flag == true){//=> đã thấy ít nhất 1 page trong frame
						this.swapped_out.add(Frame.get(FalsePos(isGoingToUse)));
						Frame.set(FalsePos(isGoingToUse), Order.get(i));
						this.page_replaced++;
						count = 0;
					}
					if (flag == false){// ko thấy page nào trong frame
						this.swapped_out.add(Frame.get(0));
						Frame.set(0, Order.get(i));
						this.page_replaced++;
					}
				}
			}
		}
	}
	private int FalsePos(ArrayList<Boolean> arr){
		for (int i = 0; i<arr.size(); i++){
			if (arr.get(i)==false) return i;
		}
		return -1;//Not contain
	}
}
