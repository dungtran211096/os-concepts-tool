package com.pr.osconcepts.scheduling;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;
/**
 * @author Trần Anh
 */

public class SJN {
	private ArrayList<Process> P;
	private TreeMap<Integer,ArrayList<Process>> Process_sorted_by_time;
	private TreeMap<Integer,ArrayList<Process>> ProcessTree;
	private int time;
	
	public SJN(ArrayList<Process> p){
		P = p;
		Process_sorted_by_time = new TreeMap<Integer,ArrayList<Process>>();
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
	public void Running() throws ClassCastException{
		time = 0;
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
		int save_time = 0;
		while (num_of_element!=P.size()){
			
			//System.out.println("Waiting: " + ProcessWaiting.size() + " ; Processing: " + ProcessTree.size() );
			for (int i = save_time; i<=time; i++){//Trong khoảng từ thời gian cũ đến thời gian mới, nếu có process xuất hiện thì thêm vào danh sách
				if (ProcessWaiting.get(i)!=null){//nếu thấy có process xuất hiện
					for (Process vd :ProcessWaiting.get(i)){
						if (ProcessTree.get(vd.getProcess_time())!=null){
							ProcessTree.get(vd.getProcess_time()).add(vd);
						}
						else{
							ArrayList<Process> tmpProcess2 = new ArrayList<Process>();
							tmpProcess2.add(vd);
							ProcessTree.put(vd.getProcess_time(), tmpProcess2);
						}
					}
					ProcessWaiting.remove(i);
				}
			}
			save_time = time;
			this.RemoveEmpty(ProcessTree);
			if (ProcessTree.size()!=0){//nếu danh sách đang chạy không trống
				ArrayList<Process> TreeP = ProcessTree.firstEntry().getValue();
				for (Process p:TreeP){
					//Cho tiến trình chạy thêm 1 đơn vị thời gian
					p.addRunning(new ProcessRunning(time, time+p.getProcess_time()));
					//Loại tiến trình ddax chayj ra khỏi danh sách
					P.add(p);
					time+=p.getProcess_time();
					//ProcessTree.firstEntry().getValue().remove(p);
				}
				ProcessTree.remove(ProcessTree.firstKey());
			}
			
		}
		//Tính thời gian finish các process
		for (Process p:P){
			p.setFinish_time(p.getRunning().get(p.getRunning().size()-1).getInterrupt_time());
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
			p.setTurnAroundTime(p.getFinish_time()-p.getAppear_time());
		}
	}
	
	public void CalcWaitingTime(){
		for (Process p : P){
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

	public TreeMap<Integer, ArrayList<Process>> getProcess_sorted_by_time(){
		return Process_sorted_by_time;
	}

	public void setProcess_sorted_by_time(TreeMap<Integer, ArrayList<Process>> hashProcess){
		Process_sorted_by_time = hashProcess;
	}

}