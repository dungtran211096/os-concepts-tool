package com.pr.osconcept.bankersalgorithm;

import java.util.ArrayList;

public class BankersAlgorithm{
	private int num_of_process;
	private int num_of_res;
	
	private Matrix Max; //Lưu trữ thông tin đăng kí tài nguyên từng tiến trình
	private Matrix Allo;//Tình trạng phân phối tài nguyên hiện tại
	private ArrayList<Integer> Avai;//Các tài nguyên còn rỗi
	private Matrix Need;//=Max - Allo: Các tiến trình cần bao nhiêu tài nguyên từng loại
	private ArrayList<Integer> Work;//Lưu lại Allo, giả định chạy
	private ArrayList<Boolean> Finish;//Tiến trình có thể kết thúc được hay không
	private boolean isSafe;
	private ArrayList<Integer> res;
	private String log;
	private String order;
	
	public BankersAlgorithm(){
		//Initialize
		Max = new Matrix(this.num_of_process,this.num_of_res);
		Allo = new Matrix(this.num_of_process,this.num_of_res);
		Avai = new ArrayList<Integer>();
		Work = new ArrayList<Integer>();
		Finish = new ArrayList<Boolean>();
		res = new ArrayList<Integer>();
		log = "";
		order = "";
	}
	public BankersAlgorithm(int n, int m){
		this.num_of_process = n;
		this.num_of_res = m;
		Max = new Matrix(this.num_of_process,this.num_of_res);
		Allo = new Matrix(this.num_of_process,this.num_of_res);
		Avai = new ArrayList<Integer>();
		Work = new ArrayList<Integer>();
		Finish = new ArrayList<Boolean>();
		res = new ArrayList<Integer>();
		log = "";
		order = "";
	}
	public void CalcNeed(){
		Need = new Matrix(Max.minusTo(Allo).getData());
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
		this.CalcNeed();
		//this.CalcAvai();
		if (this.Avai.size() == 0) this.CalcAvai();
		this.CalcNumber();
		this.Step1();
		if (order.compareTo("")!=0) order = order.substring(0, order.length()-1);
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
		for (int n = 0; n<Work.size(); n++){//Work = Work + Allo[i]
			Work.set(n, Work.get(n)+Allo.getRowAt(pos).get(n));
		}
		log = log + "3. Work += Allo[" + pos + "] = " + Work.toString() + "\n";
		order = order + "P" + pos + ">";
		this.setFinishable(pos);//Finish[i] = true
		this.Step2();//quay lại bước 2
	}
	private void Step2(){
		System.out.println("Call Step2");
		int count = 0;
		for (int i = 0; i<this.num_of_process;i++){
			if (Finish.get(i) == false && this.isBelowEquals(Need.getRowAt(i), Work)){
				log = log + "2. Tìm i = " + i + " thỏa mãn\n";
				this.Step3(i);
				break;
			}
			else count ++;
		}
		if (count == this.num_of_process){
			log = log + "Không tìm thấy i thỏa mãn\n";
			this.Step4();
		}

	}
	private void Step1(){
		//1Khởi tạo
		System.out.println("Call Step1");
		Work = new ArrayList<Integer>(Avai);
		for (int i = 0; i<this.num_of_process;i++) Finish.add(false);
		log = log + "1. Khởi tạo Work = Avai = " + Avai.toString() + "; Finish[i]=false\n";
		this.Step2();
	}
	private void Step4(){
		System.out.println("Call Step4");
		if (this.isFullTrue()){
			log = log + "4. Finish = true với mọi i => Hệ thống an toàn\n";
			this.isSafe = true;
			System.out.println("SAFE");
		}
		else{
			log = log + "4.  Có bế tắc => Hệ thống không an toàn\n";
			this.isSafe =  false;
			System.out.println("NOT SAFE");
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
	public Matrix getMax(){
		return Max;
	}
	public void setMax(Matrix max){
		Max = max;
	}
	public Matrix getAllo(){
		return Allo;
	}
	public void setAllo(Matrix allo){
		Allo = allo;
	}

	public Matrix getNeed(){
		return Need;
	}
	public void setNeed(Matrix need){
		Need = need;
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
	public boolean isSafe(){
		return isSafe;
	}
	public void setSafe(boolean isSafe){
		this.isSafe = isSafe;
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
		return order;
	}
	public void setOrder(String order){
		this.order = order;
	}
	
}
