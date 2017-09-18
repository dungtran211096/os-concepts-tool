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
	private String order;
	
	public SJN(ArrayList<Process> p){
		P = p;
		order = "";
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
		time = ProcessWaiting.firstEntry().getValue().get(0).getAppear_time();
		//Tính thời gian chạy các Process
		int num_of_element = P.size();
		P.clear();
		int save_time = 0;
		while (num_of_element!=P.size()){
			SearchProcessNAdd(ProcessTree, ProcessWaiting,save_time, time);
			this.RemoveEmpty(ProcessTree);
			if (ProcessTree.size()!=0){//nếu danh sách đang chạy không trống
				ArrayList<Process> TreeP = ProcessTree.firstEntry().getValue();
				//Cho tiến trình chạy thêm 1 đơn vị thời gian
				TreeP.get(0).addRunning(new ProcessRunning(time, time+TreeP.get(0).getProcess_time()));
				//Loại tiến trình ddax chayj ra khỏi danh sách
				P.add(TreeP.get(0));
				time+=TreeP.get(0).getProcess_time();
				TreeP.remove(0);
				
				ProcessTree.remove(ProcessTree.firstKey());
				if (TreeP.size()!=0) ProcessTree.put(TreeP.get(TreeP.size()-1).getProcess_time(), TreeP);
				
				SearchProcessNAdd(ProcessTree, ProcessWaiting,save_time, time);
				save_time = time;
			}
			else{//nếu ko thấy, tăng thời gian để xem có tiến trình xuất hiện ko
				time++;
			}
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
	private void SearchProcessNAdd(TreeMap<Integer,ArrayList<Process>> DST,TreeMap<Integer,ArrayList<Process>> SRC , int start, int end ){
		for (int i = start; i<=end; i++){//Trong khoảng từ thời gian cũ đến thời gian mới, nếu có process xuất hiện thì thêm vào danh sách
			if (SRC.get(i)!=null){//nếu thấy có process xuất hiện
				for (Process vd :SRC.get(i)){
					if (DST.get(vd.getProcess_time())!=null){
						DST.get(vd.getProcess_time()).add(vd);
					}
					else{
						ArrayList<Process> tmpProcess2 = new ArrayList<Process>();
						tmpProcess2.add(vd);
						DST.put(vd.getProcess_time(), tmpProcess2);
					}
				}
				SRC.remove(i);
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

	public String getOrder(){
		return order;
	}

	public void setOrder(String order){
		this.order = order;
	}

}