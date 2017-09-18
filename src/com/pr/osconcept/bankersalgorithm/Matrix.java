package com.pr.osconcept.bankersalgorithm;

import java.util.ArrayList;

public class Matrix{
	private int row;
	private int col;
	private ArrayList<ArrayList<Integer>> data;
	
	public Matrix(){
		this.row = 0;
		this.col = 0;
		data = new ArrayList<ArrayList<Integer>>();
	}
	//Tạo ma trận mxn
	public Matrix(int row, int col) {
        this.row = row;
        this.col = col;
        data = new ArrayList<ArrayList<Integer>>();
    }
	
	//Tạo ma trận dựa trên ma trận có sẵn
    public Matrix(ArrayList<ArrayList<Integer>> data_to_clone){
    	row = data_to_clone.size();
    	col = data_to_clone.get(0).size();
    	this.data = new ArrayList<ArrayList<Integer>>();
    	for (int i = 0; i < row; i++){
    		ArrayList<Integer> rowtmp = new ArrayList<Integer>();
    		for (int j = 0; j < col; j++)
    			rowtmp.add(data_to_clone.get(i).get(j));
    		this.data.add(rowtmp);
    	}
    		
    }

    public void AddRowToEnd(ArrayList<Integer> row_to_add){
    	
    	this.data.add(row_to_add);
    	this.row++;
    	this.col = row_to_add.size();
    }
    public Matrix minusTo(Matrix otherMatrix){
    	if (this.row!=otherMatrix.getRow() || this.col!=otherMatrix.getCol()) return null;
    	ArrayList<ArrayList<Integer>> tmp = new ArrayList<ArrayList<Integer>>();
    	for (int i = 0; i < row; i++){
    		ArrayList<Integer> rowtmp = new ArrayList<Integer>();
    		for (int j = 0; j < col; j++)
    			rowtmp.add(this.data.get(i).get(j) - otherMatrix.getData().get(i).get(j));
    		tmp.add(rowtmp);
    	}
    	Matrix rt = new Matrix(tmp);
    	return rt;
    }
    
    public ArrayList<Integer> getRowAt(int pos){
    	return this.data.get(pos);
    }
	public int getRow(){
		return row;
	}

	public void setRow(int row){
		this.row = row;
	}

	public int getCol(){
		return col;
	}

	public void setCol(int col){
		this.col = col;
	}


	public ArrayList<ArrayList<Integer>> getData(){
		return data;
	}
	public void setData(ArrayList<ArrayList<Integer>> data){
		this.data = data;
	}
    public String getString(){
    	String rt = "";
    	for (int i = 0; i<row;i++){
    		for (int j = 0; j<data.get(i).size();j++){
    			rt = rt + data.get(i).get(j) + " ";
    		}
    		rt = rt.substring(0, rt.length()-1);
    		rt+="\n";
    	}
    	return rt;
    }
	@Override
    public String toString(){
    	String rt = "";
    	for (int i = 0; i<row;i++){
    		rt+=data.get(i).toString();
    		rt+="\n";
    	}
    	return rt;
    }
    
}
