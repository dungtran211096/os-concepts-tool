package com.pr.osconcepts.scheduling;
import java.util.ArrayList;
import java.util.Comparator;
/**
 * @author Trần Anh
 */
public class Process implements Comparator<Process>,Comparable<Process>{
	private int appear_time = 0;//Get the time that process appear
	private int process_time = 0;
	private String process_name;
	private ArrayList<ProcessRunning> running;//Stogare each running time of process
	private int finish_time;
	private int RemainingTime;
	private int WaitingTime;
	private int TurnAroundTime;
	private int ResponseTime;

	public Process(){
		running = new ArrayList<ProcessRunning>();
	}
	public Process(int tg, int tgxh, String name){
		appear_time = tgxh;
		process_time = tg;
		process_name = name;
		running = new ArrayList<ProcessRunning>();

	}

	public int getAppear_time(){
		return appear_time;
	}

	public void setAppear_time(int appear_time){
		this.appear_time = appear_time;
	}

	public int getProcess_time(){
		return process_time;
	}

	public void setProcess_time(int process_time){
		this.process_time = process_time;
	}

	public ArrayList<ProcessRunning> getRunning(){
		return running;
	}

	public void addRunning(ProcessRunning running){
		this.running.add(running);
	}

	public int getFinish_time(){
		return finish_time;
	}

	public void setFinish_time(int finish_time){
		this.finish_time = finish_time;
	}

	public String getProcess_name(){
		return process_name;
	}
	public void setProcess_name(String process_name){
		this.process_name = process_name;
	}
	
    public int getWaitingTime(){
		return WaitingTime;
	}
	public void setWaitingTime(int waitingTime){
		WaitingTime = waitingTime;
	}
	public int getTurnAroundTime(){
		return TurnAroundTime;
	}
	public void setTurnAroundTime(int turnAroundTime){
		TurnAroundTime = turnAroundTime;
	}
	public int getResponseTime(){
		return ResponseTime;
	}
	public void setResponseTime(int responseTime){
		ResponseTime = responseTime;
	}
	
	public int getRemainingTime(){
		return RemainingTime;
	}
	public void setRemainingTime(int remainingTime){
		RemainingTime = remainingTime;
	}

	@Override
	public int compare(Process o1, Process o2){
		return o1.getProcess_name().compareTo(o2.getProcess_name());
	}
	@Override
	public int compareTo(Process arg0){
		return this.process_name.compareTo(arg0.getProcess_name());
	}
}