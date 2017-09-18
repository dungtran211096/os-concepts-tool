package com.pr.osconcepts.scheduling;
/**
 * @author Trần Anh
 */
public class ProcessRunning{
	private int start_time;
	private int interrupt_time;
	
	public ProcessRunning(int s, int i){
		start_time = s;
		interrupt_time = i;
	}
	public int getStart_time(){
		return start_time;
	}
	public void setStart_time(int start_time){
		this.start_time = start_time;
	}
	public int getInterrupt_time(){
		return interrupt_time;
	}
	public void setInterrupt_time(int interrupt_time){
		this.interrupt_time = interrupt_time;
	}
	
	
}