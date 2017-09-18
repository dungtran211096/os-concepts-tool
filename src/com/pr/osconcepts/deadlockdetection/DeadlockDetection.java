package com.pr.osconcepts.deadlockdetection;

import java.util.ArrayList;

import com.pr.osconcept.bankersalgorithm.Matrix;

public class DeadlockDetection{
	private int num_of_process;
	private int num_of_res;
	
	private Matrix Req; //Lưu trữ thông tin đăng kí tài nguyên từng tiến trình
	private Matrix Allo;//Tình trạng phân phối tài nguyên hiện tại
	private ArrayList<Integer> Avai;//Các tài nguyên còn rỗi
	private ArrayList<Integer> Work;//Lưu lại Allo, giả định chạy
	private ArrayList<Boolean> Finish;//Tiến trình có thể kết thúc được hay không
	private boolean isDeadlock;
	private ArrayList<Integer> res;
	private String log;
	private String deadlock;
	
	public DeadlockDetection(){
		//Initialize
		Req = new Matrix(this.num_of_process,this.num_of_res);
		Allo = new Matrix(this.num_of_process,this.num_of_res);
		Avai = new ArrayList<Integer>();
		Work = new ArrayList<Integer>();
		Finish = new ArrayList<Boolean>();
		res = new ArrayList<Integer>();
		log = "";
		deadlock = "";
	}
	public DeadlockDetection(int n, int m){
		this.num_of_process = n;
		this.num_of_res = m;
		Req = new Matrix(this.num_of_process,this.num_of_res);
		Allo = new Matrix(this.num_of_process,this.num_of_res);
		Avai = new ArrayList<Integer>();
		Work = new ArrayList<Integer>();
		Finish = new ArrayList<Boolean>();
		res = new ArrayList<Integer>();
		log = "";
		deadlock = "";
	}

	public void CalcAvai(){
		Avai=new ArrayList<Integer>(res);
		for (int i = 0; i<this.Allo.getRow(); i++){
			for (int j=0; j<Allo.getCol(); j++){
				Avai.set(j, Avai.get(j)-Allo.getData().get(i).get(j));
			}
		}
	}
	private void CalcNumber(){
    	this.num_of_process = Allo.getRow();
    	this.num_of_res = Allo.getCol();
	}
	public void AlgorithmRunning(){
		if (this.Avai.size() == 0) this.CalcAvai();
		this.CalcNumber();
		this.Step1();
		if (deadlock.compareTo("")!=0) deadlock = deadlock.substring(0, deadlock.length()-2);
	}
	public boolean isBelowEquals(ArrayList<Integer> A,ArrayList<Integer> B){
		if (A.size() != B.size()) return false;
		int count = 0;
		for (int i=0;i<A.size();i++){
			if (A.get(i) <= B.get(i)) count++;
		}
		if (count == A.size()) return true;
		return false;
	}
	public void setFinishable(int pos){
		this.Finish.set(pos, true);
	}
	private void Step3(int pos){
		System.out.println("Call Step3");
		this.setFinishable(pos);
		for (int n = 0; n<Work.size(); n++){//Work = Work + Allo[i]
			Work.set(n, Work.get(n)+Allo.getRowAt(pos).get(n));
		}
		
		log = log + "3. Work = " + Work.toString() + "\n";

		this.Step2();//quay lại bước 2
	}
	private void Step2(){
		System.out.println("Call Step2");
		int count = 0;
		for (int i = 0; i<this.num_of_process;i++){
			if (Finish.get(i) == false && this.isBelowEquals(Req.getRowAt(i), Work)){
				
				log = log + "2. Tìm thấy i = " + i + " thỏa mãn\n";
				
				this.Step3(i);
				break;
			}
			else count ++;
		}
		if (count == this.num_of_process){
			log = log + "2. Không tìm thấy i thỏa mãn\n";
			this.Step4();
		}
	}
	private void Step1(){
		//1Khởi tạo
		System.out.println("Call Step1");
		Work = new ArrayList<Integer>(Avai);
		for (int i = 0; i<this.num_of_process;i++){
			if (!this.isFullZero(this.Allo.getRowAt(i)) || !this.isFullZero(this.Req.getRowAt(i))){
				Finish.add(false);
			}
			else Finish.add(true);
		}
		log = log + "1. Khởi tạo Work = Avai = " + Avai.toString() + ";\n Finish = " + Finish.toString() + "\n";
		this.Step2();
	}
	private void Step4(){
		System.out.println("Call Step4");
		if (this.isFullTrue()){
			log = log + "4. Finish = true với mọi i => Hệ thống không bế tắc\n";
			this.isDeadlock= false;
		}
		else{
			log = log + "4.  Có bế tắc\n";
			log = log + Finish.toString() + "\n";
			for (int i = 0; i<Finish.size();i++){
				if (Finish.get(i) == false){
					deadlock = deadlock + "P" + i + "; ";
				}
			}
			log = log + "\n";
			this.isDeadlock =  true;
		}
	}
	private boolean isFullTrue(){
		int count = 0;
		for (int i=0;i<this.Finish.size();i++){
			if (this.Finish.get(i)==true) count++;
		}
		if (count == this.Finish.size()) return true;
		return false;
	}
	private boolean isFullZero(ArrayList<Integer> arr){
		int count = 0;
		for (int i=0;i<arr.size();i++){
			if (arr.get(i)==0) count++;
		}
		if (count == arr.size()) return true;
		return false;
	}
	public void readData(){
		
	}

	public int getNum_of_process(){
		return num_of_process;
	}
	public void setNum_of_process(int num_of_process){
		this.num_of_process = num_of_process;
	}
	public int getNum_of_res(){
		return num_of_res;
	}
	public void setNum_of_res(int num_of_res){
		this.num_of_res = num_of_res;
	}
	public Matrix getReq(){
		return Req;
	}
	public void setReq(Matrix max){
		Req = max;
	}
	public Matrix getAllo(){
		return Allo;
	}
	public void setAllo(Matrix allo){
		Allo = allo;
	}

	public ArrayList<Integer> getAvai(){
		return Avai;
	}
	public void setAvai(ArrayList<Integer> avai){
		Avai = avai;
	}
	public ArrayList<Integer> getWork(){
		return Work;
	}
	public void setWork(ArrayList<Integer> work){
		Work = work;
	}
	public ArrayList<Boolean> getFinish(){
		return Finish;
	}
	public void setFinish(ArrayList<Boolean> finish){
		Finish = finish;
	}

	public boolean isDeadlock(){
		return isDeadlock;
	}
	public void setDeadlock(boolean isDeadlock){
		this.isDeadlock = isDeadlock;
	}
	public String getDeadlock(){
		return deadlock;
	}
	public void setDeadlock(String deadlock){
		this.deadlock = deadlock;
	}
	public ArrayList<Integer> getRes(){
		return res;
	}
	public void setRes(ArrayList<Integer> res){
		this.res = res;
	}
	public String getLog(){
		return log;
	}
	public void setLog(String log){
		this.log = log;
	}
	public String getOrder(){
		return deadlock;
	}
	public void setOrder(String deadlock){
		this.deadlock = deadlock;
	}
	
}
