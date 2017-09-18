package com.pr.osconcepts.scheduling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;

/**
 * @author Trần Anh
 */
public class SRTF {
	private ArrayList<Process> P;
	private TreeMap<Integer,ArrayList<Process>> ProcessTree;
	private int time;
	private String order;
	
	public SRTF(ArrayList<Process> p){
		P = p;
		ProcessTree = new TreeMap<Integer,ArrayList<Process>>();
		order = "";
		this.Running();
		Collections.sort(P);
		this.CalcTurnAroundTime();
		this.CalcWaitingTime();
		this.CalcResponseTime();
	}
	
	public float CalcThroughput(){//Tính thông lượng
		return (float)P.size()/time;
	}
	public void Running() throws ClassCastException{//Tính thời điểm chạy mỗi tiến trình
		time = 0;
		for (Process p: P){
			p.setRemainingTime(p.getProcess_time());
		}
		
		//Thêm Process vào danh sách chờ xử lí, tự động sắp xếp theo thời gian xuaats hieenj
		TreeMap<Integer,ArrayList<Process>> ProcessWaiting=new TreeMap<Integer,ArrayList<Process>>();
		for (Process p: P){
			if (ProcessWaiting.get(p.getAppear_time())!=null){
				ProcessWaiting.get(p.getAppear_time()).add(p);
			}
			else{
				ArrayList<Process> tmpProcess = new ArrayList<Process>();
				tmpProcess.add(p);
				ProcessWaiting.put(p.getAppear_time(), tmpProcess);
			}
		}
		
		//Tính thời gian chạy các Process
		int num_of_element = P.size();
		P.clear();
		while (num_of_element!=P.size()){
			//System.out.println("Waiting: " + ProcessWaiting.size() + " ; Processing: " + ProcessTree.size() );
			if (ProcessWaiting.get(time)!=null){//nếu thấy có process xuất hiện
				for (Process vd :ProcessWaiting.get(time)){
					if (ProcessTree.get(vd.getRemainingTime())!=null){
						ProcessTree.get(vd.getRemainingTime()).add(vd);
					}
					else{
						ArrayList<Process> tmpProcess2 = new ArrayList<Process>();
						tmpProcess2.add(vd);
						ProcessTree.put(vd.getRemainingTime(), tmpProcess2);
					}
				}
				ProcessWaiting.remove(time);
			}
			
			if (ProcessTree.size()!=0){//nếu danh sách đang chạy không trống
				this.RemoveEmpty(ProcessTree);
				ArrayList<Process> TreeP = ProcessTree.firstEntry().getValue();
				for (Process p:TreeP){
					if (p.getRemainingTime() <= 0){//nếu tiến trình đã chạy xong.
						if (p.getRemainingTime() == 0){
							P.add(p);
						}
						ProcessTree.remove(0);
						break;
					}
					else{//nếu chưa xong
						//Cho tiến trình chạy thêm 1 đơn vị thời gian
						p.addRunning(new ProcessRunning(time, time+1));
						//Loại tiến trình cũ ra khỏi danh sách
						ProcessTree.firstEntry().getValue().remove(p);
						//Thêm tiến trình mới vào danh sách
						Process tmp = p;	
						tmp.setRemainingTime(tmp.getRemainingTime()-1);
						if (ProcessTree.get(tmp.getRemainingTime())!=null){
							ProcessTree.get(tmp.getRemainingTime()).add(tmp);
						}
						else{
							ArrayList<Process> tmpProcess2 = new ArrayList<Process>();
							tmpProcess2.add(tmp);
							ProcessTree.put(tmp.getRemainingTime(), tmpProcess2);
						}
						time++;
						break;
					}
				}	
			}
			else time++;
		}
		
		//Tính thời gian finish các process
		for (Process p:P){
			p.setFinish_time(p.getRunning().get(p.getRunning().size()-1).getInterrupt_time());
		}
		//Gộp các ProcessRunning
		for (Process p:P){
			ArrayList<ProcessRunning> pr = p.getRunning();
			for (int i=1;i<pr.size();i++){
				if (pr.get(i).getStart_time() == pr.get(i-1).getInterrupt_time()){
					pr.get(i-1).setInterrupt_time(pr.get(i).getInterrupt_time());
					pr.remove(i);
					i--;
				}
			}
			p.setRunning(pr);
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
	public void RemoveEmpty(TreeMap<Integer,ArrayList<Process>> T){//Loại bỏ những value rỗng ra khỏi Tree
		ArrayList<Integer> removekey = new ArrayList<Integer>();
		for (int key : T.keySet()){
			if (T.get(key).size() ==0){
				removekey.add(key);
			}
		}
		for (int key : removekey) T.remove(key);
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
		for (Process p : P){
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

	public String getOrder(){
		return order;
	}

	public void setOrder(String order){
		this.order = order;
	}

}