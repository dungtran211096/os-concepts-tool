package com.pr.osconcepts.requestres;

import java.util.ArrayList;

import com.pr.osconcept.bankersalgorithm.BankersAlgorithm;
import com.pr.osconcept.bankersalgorithm.Matrix;

public class RequestResources{
	private Matrix Req;
	private BankersAlgorithm bank;
	private ArrayList<Integer> ProcessOrder;
	private int num_of_process;
	private int num_of_res;
	private String log;
	private int pos;
	private ArrayList<Integer> BackupAvai;
	
	public RequestResources(){
		Req = new Matrix(this.num_of_process, this.num_of_res);
		bank = new BankersAlgorithm(this.num_of_process, this.num_of_res);
		ProcessOrder = new ArrayList<Integer>();
		setBackupAvai(new ArrayList<Integer>());
		log = "";
	}

	public void ReqResRunning(){
		bank.CalcNeed();
		if (bank.getAvai().size() == 0) bank.CalcAvai();
		this.setBackupAvai(new ArrayList<Integer>(bank.getAvai()));
		
		this.num_of_process = bank.getAllo().getRow();
		this.num_of_res = bank.getAllo().getCol();
		this.fillReq();
		for (int i = 0; i<this.ProcessOrder.size(); i++){
			log = log + "*Tiến trình P" + ProcessOrder.get(i) + " yêu cầu tài nguyên " + this.Req.getRowAt(ProcessOrder.get(i)).toString() + "\n";
			this.Step1(i);
		}
	}
	private void fillReq(){
		ArrayList<Integer> arrtmp= new ArrayList<Integer>();
		for (int i=0; i<this.num_of_res; i++) arrtmp.add(0);
		Matrix Reqtmp = new Matrix();
		for (int i = 0; i<this.num_of_process; i++){
			int count = 0;
			for (int j = 0; j<this.ProcessOrder.size(); j++){
				if (i == this.ProcessOrder.get(j)){
					Reqtmp.AddRowToEnd(Req.getRowAt(j));
				}
				else count ++;
			}
			if (count == this.ProcessOrder.size()) Reqtmp.AddRowToEnd(arrtmp);
			System.out.println("Test");
			System.out.println(Reqtmp.toString());
		}
		Req=Reqtmp;
	}
	private void Step1(int i){
		this.pos = ProcessOrder.get(pos);
		if (this.isBelowEquals(this.Req.getRowAt(pos), bank.getNeed().getRowAt(pos))){
			log = log + "1. Req[" + pos + "] <= Need[" + pos + "]\n";
			this.Step2();
		}
		else{
			log = log + "1. Đã xảy ra lỗi\n";
		}
	}
	private void Step2(){
		if (this.isBelowEquals(this.Req.getRowAt(pos), bank.getAvai())){
			log = log + "2. Req[" + pos + "] <= Avai\n";
			this.Step3();
		}
		else{
			log = log + "Tiến trình " + pos + " chờ\n";
		}
	}
	
	private void Step3(){
		bank.setAvai(this.minusArr(bank.getAvai(), this.Req.getRowAt(pos)));
		
		ArrayList<ArrayList<Integer>> tmp = new ArrayList<ArrayList<Integer>>(bank.getAllo().getData());
		tmp.set(pos, new ArrayList<Integer>(this.plusArr(bank.getAllo().getRowAt(pos), this.Req.getRowAt(pos))));
		Matrix set = new Matrix(tmp);
		bank.setAllo(set);
		
		tmp = new ArrayList<ArrayList<Integer>>(bank.getNeed().getData());
		tmp.set(pos, this.minusArr(bank.getNeed().getRowAt(pos), this.Req.getRowAt(pos)));
		set = new Matrix(tmp);
		bank.setNeed(set);
		
		log = log + "3. Avai = Avai - Req[" + pos + "] = " + bank.getAvai().toString()+"\n";
		log = log + "   -Allo[" + pos +"] = Allo[" + pos +"] + Req[" + pos + "] = " + bank.getAllo().getRowAt(pos).toString()+"\n";
		log = log + "   -Need[" + pos +"] = Need[" + pos +"] - Req[" + pos + "] = " + bank.getNeed().getRowAt(pos).toString()+"\n";
		bank.AlgorithmRunning();
		
		
		if (bank.isSafe()){
			log = log + "  *Gọi Banker's Algorithm => An toàn\n";
			log = log + "   =>P" + pos +" được cấp phát\n";
		}
		else{
			log = log + "  *Gọi Banker's Algorithm => Không an toàn\n   =>khôi phục tài nguyên\n";
			bank.setAvai(this.plusArr(bank.getAvai(), this.Req.getRowAt(pos)));
			
			tmp = new ArrayList<ArrayList<Integer>>(bank.getAllo().getData());
			tmp.set(pos, new ArrayList<Integer>(this.minusArr(bank.getAllo().getRowAt(pos), this.Req.getRowAt(pos))));
			set = new Matrix(tmp);
			bank.setAllo(set);
			
			tmp = new ArrayList<ArrayList<Integer>>(bank.getNeed().getData());
			tmp.set(pos, this.plusArr(bank.getNeed().getRowAt(pos), this.Req.getRowAt(pos)));
			set = new Matrix(tmp);
			
			bank.setNeed(set);			log = log + "   -Avai = Avai + Req[" + pos + "] = " + bank.getAvai().toString()+"\n";
			log = log + "   -Allo[" + pos +"] = Allo[" + pos +"] - Req[" + pos + "] = " + bank.getAllo().getRowAt(pos).toString()+"\n";
			log = log + "   -Need[" + pos +"] = Need[" + pos +"] + Req[" + pos + "] = " + bank.getNeed().getRowAt(pos).toString()+"\n";
		
		}
	}
	private ArrayList<Integer> plusArr(ArrayList<Integer> src, ArrayList<Integer> dst){
		ArrayList<Integer> rt = new ArrayList<Integer>();
		for (int i = 0; i< src.size(); i++){
			rt.add(src.get(i)+dst.get(i));
		}
		return rt;
	}
	private ArrayList<Integer> minusArr(ArrayList<Integer> src, ArrayList<Integer> dst){
		ArrayList<Integer> rt = new ArrayList<Integer>();
		for (int i = 0; i< src.size(); i++){
			rt.add(src.get(i)-dst.get(i));
		}
		return rt;
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
	public Matrix getReq(){
		return Req;
	}
	public void setReq(Matrix req){
		Req = req;
	}
	public BankersAlgorithm getBank(){
		return bank;
	}
	public void setBank(BankersAlgorithm bank){
		this.bank = bank;
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
	public String getLog(){
		return log;
	}
	public void setLog(String log){
		this.log = log;
	}
	public int getPos(){
		return pos;
	}
	public void setPos(int pos){
		this.pos = pos;
	}

	public ArrayList<Integer> getProcessOrder(){
		return ProcessOrder;
	}

	public void setProcessOrder(ArrayList<Integer> processOrder){
		ProcessOrder = processOrder;
	}

	public ArrayList<Integer> getBackupAvai(){
		return BackupAvai;
	}

	public void setBackupAvai(ArrayList<Integer> backupAvai){
		BackupAvai = backupAvai;
	}

}
