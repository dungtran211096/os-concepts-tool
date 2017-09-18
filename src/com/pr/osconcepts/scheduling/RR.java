package com.pr.osconcepts.scheduling;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.TreeMap;
/**
 * @author Trần Anh
 */
class PQueue<E> {
	   private LinkedList<E> list = new LinkedList<E>();
	   public void enqueue(E item) {
	      list.addLast(item);
	   }
	   public E dequeue() {
	      return list.poll();
	   }
	   public boolean hasItems() {
	      return !list.isEmpty();
	   }
	   public int size() {
	      return list.size();
	   }
	   public void addItems(PQueue<? extends E> q) {
	      while (q.hasItems())
	         list.addLast(q.dequeue());
	   }
	}
public class RR {
	private ArrayList<Process> P;
	private TreeMap<Integer,ArrayList<Process>> ProcessTree;
	private int time;
	private int TimeQuantum;
	
	public RR(ArrayList<Process> p, int t){
		P = p;
		this.TimeQuantum = t;
		ProcessTree = new TreeMap<Integer,ArrayList<Process>>();
		this.Running();
		Collections.sort(P);
		this.CalcTurnAroundTime();
		this.CalcWaitingTime();
		this.CalcResponseTime();
	}
	
	public float CalcThroughput(){
		return (float)P.size()/time;
	}
	public void Running() throws ClassCastException{//done
		time = 0;	
		for (Process p: P){
			p.setRemainingTime(p.getProcess_time());
		}
		//Thêm Process vào danh sách chờ xử lí, tự động sắp xếp theo thời gian xuaats hieenj
		for (Process p: P){
			if (ProcessTree.get(p.getAppear_time())!=null){
				ProcessTree.get(p.getAppear_time()).add(p);
			}
			else{
				ArrayList<Process> tmpProcess = new ArrayList<Process>();
				tmpProcess.add(p);
				ProcessTree.put(p.getAppear_time(), tmpProcess);
			}
		}
		time = ProcessTree.firstEntry().getValue().get(0).getAppear_time();
		//chèn các process vào hàng đợi
		PQueue<Process> ProcessQueue = new PQueue<Process>();
		for (int key: ProcessTree.keySet()){
			ArrayList<Process> tmp = ProcessTree.get(key);
			for (Process p : tmp){
				ProcessQueue.enqueue(p);
			}
		}
		
		//Tính thời gian chạy các Process
		P.clear();//Xóa danh sách cũ
		while (ProcessQueue.hasItems()){
			Process p = ProcessQueue.dequeue();
			if (p.getRemainingTime() <=0){//chạy xong
				if (p.getRemainingTime() == 0){
					P.add(p);//Thêm process vào danh sách
				}
			}
			else{//chưa chạy xong
				if (p.getRemainingTime() <= TimeQuantum){//chạy lần cuối
					p.addRunning(new ProcessRunning(time, time + p.getRemainingTime()));
					time+= p.getRemainingTime();
					p.setRemainingTime(0);
				}
				else{
					p.addRunning(new ProcessRunning(time, time + TimeQuantum));
					time+= TimeQuantum;
					p.setRemainingTime(p.getRemainingTime() - TimeQuantum);
				}
				ProcessQueue.enqueue(p);
			}
		}
		
		//Tính thời gian finish các process
		for (Process p:P){
			p.setFinish_time(p.getRunning().get(p.getRunning().size()-1).getInterrupt_time());
		}
	}
	
	public void CalcTurnAroundTime(){
		for (Process p:P){
			p.setTurnAroundTime(p.getFinish_time() - p.getAppear_time());
		}
	}
	
	public void CalcWaitingTime(){
		for (Process p: P){
			p.setWaitingTime(p.getFinish_time() - p.getProcess_time() - p.getAppear_time());
		}
	}
	
	public float CalcAverageWaitingTime(){
		int sumw = 0;
		for (Process p: P){
			sumw += p.getWaitingTime();
		}
		return (float)sumw/P.size();
	}
	
	public void CalcResponseTime(){
		for (Process p:P){
			p.setResponseTime(p.getRunning().get(0).getStart_time() - p.getAppear_time());
		}
	}

	public ArrayList<Process> getP(){
		return P;
	}

	public void setP(ArrayList<Process> p){
		P = p;
	}

	public TreeMap<Integer, ArrayList<Process>> getProcessTree(){
		return ProcessTree;
	}

	public void setProcessTree(TreeMap<Integer, ArrayList<Process>> hashProcess){
		ProcessTree = hashProcess;
	}

}