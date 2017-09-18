package com.pr.osconcepts.pagereplacement;

import java.util.ArrayList;

public class PRAlgorithm{
	protected ArrayList<Integer> Order;
	protected int frame_size;
	protected int page_fault;
	protected int page_replaced;
	protected ArrayList<Integer> swapped_out;
	protected ArrayList<Integer> fault_order;
	public PRAlgorithm(ArrayList<Integer> od, int frs){
		this.Order = od;
		this.frame_size = frs;
		this.page_fault = 0;
		this.page_replaced = 0;
		swapped_out = new ArrayList<Integer>();
		fault_order = new ArrayList<Integer>();
	}
	protected int isContain(ArrayList<Integer> arr, int val){
		for (int i = 0; i<arr.size(); i++){
			if (arr.get(i)==val) return i;
		}
		return -1;//Not contain
	}
	public int getPage_fault(){
		return page_fault;
	}

	public void setPage_fault(int page_fault){
		this.page_fault = page_fault;
	}

	public int getPage_replaced(){
		return page_replaced;
	}

	public void setPage_replaced(int page_replaced){
		this.page_replaced = page_replaced;
	}

	public ArrayList<Integer> getSwapped_out(){
		return swapped_out;
	}

	public void setSwapped_out(ArrayList<Integer> swapped_out){
		this.swapped_out = swapped_out;
	}

	public ArrayList<Integer> getFault_order(){
		return fault_order;
	}

	public void setFault_order(ArrayList<Integer> fault_order){
		this.fault_order = fault_order;
	}

	public int getFrame_size(){
		return frame_size;
	}

	public void setFrame_size(int frame_size){
		this.frame_size = frame_size;
	}

	public ArrayList<Integer> getOrder(){
		return Order;
	}

	public void setOrder(ArrayList<Integer> order){
		Order = order;
	}
	
}
