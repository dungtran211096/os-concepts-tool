package com.pr.osconcepts.scheduling;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;
/**
 * @author Trần Anh
 */
public class FCFS{
	private ArrayList<Process> P;
	private TreeMap<Integer,ArrayList<Process>> ProcessTree;
	private int time;
	private String order;
	
	public FCFS(ArrayList<Process> p){
		P = p;
		order = "";
		ProcessTree = new TreeMap<Integer,ArrayList<Process>>();
		this.Running();
		Collections.sort(P);
		this.CalcThroughput();
		this.CalcTurnAroundTime();
		this.CalcWaitingTime();
		this.CalcResponseTime();
	}
	
	public float CalcThroughput(){
		return (float)P.size()/time;
	}
	
	public void Running(){
		for (Process p: P){//Thêm process vào Tree, sắp xếp theo thời gian xuất hiện
			if (ProcessTree.get(p.getAppear_time())!=null){
				ProcessTree.get(p.getAppear_time()).add(p);
			}
			else{
				ArrayList<Process> tmpProcess = new ArrayList<Process>();
				tmpProcess.add(p);
				ProcessTree.put(p.getAppear_time(), tmpProcess);
			}
		}
		P.clear();
		for (int key : ProcessTree.keySet()){//Thêm lại các process đã sắp xếp vào P
			for (Process p : ProcessTree.get(key)){
				P.add(p);
			}
		}
		
		time = P.get(0).getAppear_time();
		time += P.get(0).getProcess_time();
		P.get(0).addRunning(new ProcessRunning(P.get(0).getAppear_time(), P.get(0).getProcess_time()+P.get(0).getAppear_time()));
		P.get(0).setFinish_time(time);
		for (int i = 1; i<P.size(); i++){
			if (P.get(i).getAppear_time() < time){
				// Tiến trình tiếp theo xuất hiện khi có tiến trình đang chạy
				P.get(i).addRunning(new ProcessRunning(time, P.get(i).getProcess_time()+time));
				time += P.get(i).getProcess_time();
				P.get(i).setFinish_time(time);
			}
			else{
				// xuất hiện khi các tiến trình đã chạy xong
				P.get(i).addRunning(new ProcessRunning(P.get(i).getAppear_time(), P.get(i).getProcess_time()+time));
				time = P.get(i).getAppear_time() + P.get(i).getProcess_time();
				P.get(i).setFinish_time(time);
			}
		}
		this.createOrderList();

	}
	private void createOrderList(){
		TreeMap<Integer,Process> OrderList = new TreeMap<Integer,Process>();
		for (Process p: P){
			ArrayList<ProcessRunning> pr = p.getRunning();
			for (int i = 0; i< pr.size();i++){
				OrderList.put(pr.get(i).getStart_time(), p);
			}
		}
		for (int key:OrderList.keySet()){
			Process p = OrderList.get(key);
			ArrayList<ProcessRunning> pr = p.getRunning();
			for (ProcessRunning tmp : pr){
				if (tmp.getStart_time()==key){
					order= order + p.getProcess_name() + "(" + tmp.getStart_time() + "->" + tmp.getInterrupt_time() + ")\n";
				}
			}
		}
	}
	public void CalcTurnAroundTime(){
		for (Process p:P){
			p.setTurnAroundTime(p.getFinish_time()-p.getAppear_time());
		}
	}
	
	public void CalcWaitingTime(){
		for (Process p: P){
			p.setWaitingTime(p.getRunning().get(p.getRunning().size()-1).getStart_time() - p.getAppear_time());
		}
	}
	
	public float CalcAverageWaitingTime(){
		int sumw = 0;
		for (Process p : P){
			sumw += p.getWaitingTime();
		}
		return (float)sumw/P.size();
	}
	
	public void CalcResponseTime(){
		for (Process p:P){
			p.setResponseTime(p.getRunning().get(p.getRunning().size()-1).getStart_time() - p.getAppear_time());
		}
	}

	public ArrayList<Process> getP(){
		return P;
	}

	public void setP(ArrayList<Process> p){
		P = p;
	}

	public String getOrder(){
		return order;
	}

	public void setOrder(String order){
		this.order = order;
	}
	
}