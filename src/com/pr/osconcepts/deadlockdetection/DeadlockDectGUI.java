/*
 * Created by JFormDesigner on Sat Mar 19 00:33:43 ICT 2016
 */

package com.pr.osconcepts.deadlockdetection;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;

import com.pr.osconcept.bankersalgorithm.Matrix;

/**
 * @author Tráº§n Anh
 */
public class DeadlockDectGUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DeadlockDetection dl;
	public DeadlockDectGUI() {
		initComponents();
		this.numOfProcTF.setEditable(false);
		this.numOfProcTF.setBackground(this.getBackground());
		this.LogData.setEditable(false);
		this.LogData.setBackground(this.getBackground());
		this.DeadLockData.setEditable(false);
		this.DeadLockData.setBackground(this.getBackground());
		this.numOfResTF.setEditable(false);
		this.numOfResTF.setBackground(this.getBackground());
		this.DeadlockLB.setVisible(false);
		this.DeadLockData.setVisible(false);
		this.DeadLockSP.setVisible(false);
	}
	private boolean getAlloData(){
		String data = this.AlloData.getText();
		Scanner sc = new Scanner(data);
		Matrix mtmp = new Matrix();
		try{
			while (sc.hasNextLine()){
				ArrayList<Integer> arrl = new ArrayList<Integer>();
				Scanner dt = new Scanner(sc.nextLine());
				while (dt.hasNextInt()){
					int tmp = dt.nextInt();
					arrl.add(tmp);
				}
				dt.close();
				mtmp.AddRowToEnd(arrl);
			}
		}catch (Exception e){
			System.out.println(e);
			return false;
		}finally{
			
			dl.setAllo(mtmp);
			sc.close();
		}
		return true;
	}
	private boolean getReqData(){
		
		String data = this.ReqData.getText();
		Scanner sc = new Scanner(data);
		try{
			Matrix mtmp = new Matrix();
			while (sc.hasNextLine()){
				ArrayList<Integer> arrl = new ArrayList<Integer>();
				Scanner dt = new Scanner(sc.nextLine());
				while (dt.hasNextInt()){
					int tmp = dt.nextInt();
					arrl.add(tmp);
				}
				dt.close();
				mtmp.AddRowToEnd(arrl);
			}
			dl.setReq(mtmp);
		}catch (Exception e){
			return false;
		}finally{
			sc.close();
		}
		return true;
		
	}
	private void getResData(){
		ArrayList<Integer> res = new ArrayList<Integer>();
		String data = this.ResData.getText();
		String[] str = data.split(" ");
		for (int i = 0; i<str.length; i++){
			res.add(Integer.parseInt(str[i]));
		}
		dl.setRes(res);
	}
	private void getAvaiData(){
		ArrayList<Integer> avai = new ArrayList<Integer>();
		String data = this.AvaiData.getText();
		String[] str = data.split(" ");
		for (int i = 0; i<str.length; i++){
			avai.add(Integer.parseInt(str[i]));
		}
		dl.setAvai(avai);
	}
	private void getData(){
		if (!this.getAlloData()) System.out.println("Xảy ra lỗi khi lấy Allo");
		if (!this.getReqData()) System.out.println("Xảy ra lỗi khi lấy Req");
		if (this.ResData.getText().compareTo("") == 0 && this.AvaiData.getText().compareTo("")!=0){
			System.out.println("F");
			this.getAvaiData();
		}
		else this.getResData();
			
	}
	public static void main(String []args){
		try{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		}
		catch(Exception ex){}
		new DeadlockDectGUI().setVisible(true);
	}
	private void AddLogResult(){
		this.LogData.setFont(new Font("Arial", Font.PLAIN, 13));
		this.LogData.setText(dl.getLog());
		
		if (dl.getOrder().compareTo("")!=0){
			this.DeadlockLB.setVisible(true);
			this.DeadLockData.setVisible(true);
			this.DeadLockSP.setVisible(true);
			this.DeadLockData.setText(dl.getOrder());
		}	
	}
	private void AddCalculatedData(){
		this.AvaiData.setText(dl.getAvai().toString());
		this.numOfProcTF.setText(""+dl.getNum_of_process());
		this.numOfResTF.setText(dl.getNum_of_res()+"");
	}
	private void CalcBTNActionPerformed(ActionEvent e) {
		// TODO add your code here
		dl = new DeadlockDetection();
		
		this.getData();
		
		dl.AlgorithmRunning();
		this.AddCalculatedData();
		this.AddLogResult();
	}

	private void initComponents() {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		ImageIcon icon = new ImageIcon(this.getClass().getResource("/icon.png"));
		Image image = icon.getImage();
		setIconImage(image);
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Tráº§n Anh
		AlloLB = new JLabel();
		AlloDataSP = new JScrollPane();
		AlloData = new JEditorPane();
		ReqDataSP = new JScrollPane();
		ReqData = new JEditorPane();
		ReqLB = new JLabel();
		AvaiDataSP = new JScrollPane();
		AvaiData = new JTextPane();
		AvaiLB = new JLabel();
		ResLB = new JLabel();
		ResDataSP = new JScrollPane();
		ResData = new JTextPane();
		numOfProc = new JLabel();
		numOfProcTF = new JTextField();
		numOfRes = new JLabel();
		numOfResTF = new JTextField();
		CalcBTN = new JButton();
		LogDataSP = new JScrollPane();
		LogData = new JTextPane();
		DeadlockLB = new JLabel();
		DeadLockSP = new JScrollPane();
		DeadLockData = new JEditorPane();

		//======== this ========
		setTitle("Deadlock Detection");
		setResizable(false);
		Container contentPane = getContentPane();
		contentPane.setLayout(null);

		//---- AlloLB ----
		AlloLB.setText("Allocation");
		contentPane.add(AlloLB);
		AlloLB.setBounds(36, 0, AlloLB.getPreferredSize().width, 25);

		//======== AlloDataSP ========
		{
			AlloDataSP.setViewportView(AlloData);
		}
		contentPane.add(AlloDataSP);
		AlloDataSP.setBounds(5, 25, AlloDataSP.getPreferredSize().width, 125);

		//======== ReqDataSP ========
		{
			ReqDataSP.setViewportView(ReqData);
		}
		contentPane.add(ReqDataSP);
		ReqDataSP.setBounds(120, 25, ReqDataSP.getPreferredSize().width, 125);

		//---- ReqLB ----
		ReqLB.setText("Request");
		contentPane.add(ReqLB);
		ReqLB.setBounds(154, 0, ReqLB.getPreferredSize().width, 25);

		//======== AvaiDataSP ========
		{
			AvaiDataSP.setViewportView(AvaiData);
		}
		contentPane.add(AvaiDataSP);
		AvaiDataSP.setBounds(235, 70, 108, AvaiDataSP.getPreferredSize().height);

		//---- AvaiLB ----
		AvaiLB.setText("Available");
		contentPane.add(AvaiLB);
		AvaiLB.setBounds(268, 45, AvaiLB.getPreferredSize().width, 25);

		//---- ResLB ----
		ResLB.setText("Resources");
		contentPane.add(ResLB);
		ResLB.setBounds(264, 0, ResLB.getPreferredSize().width, 25);

		//======== ResDataSP ========
		{
			ResDataSP.setViewportView(ResData);
		}
		contentPane.add(ResDataSP);
		ResDataSP.setBounds(235, 25, 108, ResDataSP.getPreferredSize().height);

		//---- numOfProc ----
		numOfProc.setText("Processes");
		contentPane.add(numOfProc);
		numOfProc.setBounds(235, 100, numOfProc.getPreferredSize().width, 25);
		contentPane.add(numOfProcTF);
		numOfProcTF.setBounds(298, 100, 45, 25);

		//---- numOfRes ----
		numOfRes.setText("Resources");
		contentPane.add(numOfRes);
		numOfRes.setBounds(235, 125, numOfRes.getPreferredSize().width, 25);
		contentPane.add(numOfResTF);
		numOfResTF.setBounds(298, 125, 45, 25);

		//---- CalcBTN ----
		CalcBTN.setText("Calc");
		CalcBTN.addActionListener(e -> CalcBTNActionPerformed(e));
		contentPane.add(CalcBTN);
		CalcBTN.setBounds(new Rectangle(new Point(205, 160), CalcBTN.getPreferredSize()));

		//======== LogDataSP ========
		{
			LogDataSP.setViewportView(LogData);
		}
		contentPane.add(LogDataSP);
		LogDataSP.setBounds(5, 190, 453, 150);

		//---- DeadlockLB ----
		DeadlockLB.setText("Deadlock");
		contentPane.add(DeadlockLB);
		DeadlockLB.setBounds(385, 0, DeadlockLB.getPreferredSize().width, 25);

		//======== DeadLockSP ========
		{
			DeadLockSP.setViewportView(DeadLockData);
		}
		contentPane.add(DeadLockSP);
		DeadLockSP.setBounds(350, 25, DeadLockSP.getPreferredSize().width, 125);

		{ // compute preferred size
			Dimension preferredSize = new Dimension();
			for(int i = 0; i < contentPane.getComponentCount(); i++) {
				Rectangle bounds = contentPane.getComponent(i).getBounds();
				preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
				preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
			}
			Insets insets = contentPane.getInsets();
			preferredSize.width += insets.right;
			preferredSize.height += insets.bottom;
			contentPane.setMinimumSize(preferredSize);
			contentPane.setPreferredSize(preferredSize);
		}
		setSize(470, 375);
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - Tráº§n Anh
	private JLabel AlloLB;
	private JScrollPane AlloDataSP;
	private JEditorPane AlloData;
	private JScrollPane ReqDataSP;
	private JEditorPane ReqData;
	private JLabel ReqLB;
	private JScrollPane AvaiDataSP;
	private JTextPane AvaiData;
	private JLabel AvaiLB;
	private JLabel ResLB;
	private JScrollPane ResDataSP;
	private JTextPane ResData;
	private JLabel numOfProc;
	private JTextField numOfProcTF;
	private JLabel numOfRes;
	private JTextField numOfResTF;
	private JButton CalcBTN;
	private JScrollPane LogDataSP;
	private JTextPane LogData;
	private JLabel DeadlockLB;
	private JScrollPane DeadLockSP;
	private JEditorPane DeadLockData;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
