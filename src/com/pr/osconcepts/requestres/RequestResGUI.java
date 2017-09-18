/*
 * Created by JFormDesigner on Fri Mar 18 10:39:25 ICT 2016
 */

package com.pr.osconcepts.requestres;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;
import com.pr.osconcept.bankersalgorithm.Matrix;

/**
 * @author Tráº§n Anh
 */
public class RequestResGUI extends JFrame {
	/**
	 * 
	 */
	private RequestResources reqres;
	private static final long serialVersionUID = 1L;
	public RequestResGUI() {
		initComponents();
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
			reqres.getBank().setAllo(mtmp);
			sc.close();
		}
		return true;
	}
	private boolean getMaxData(){
		
		String data = this.MaxData.getText();
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
			reqres.getBank().setMax(mtmp);
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
		reqres.getBank().setRes(res);
	}
	private void getAvaiData(){
		ArrayList<Integer> avai = new ArrayList<Integer>();
		String data = this.AvaiData.getText();
		String[] str = data.split(" ");
		for (int i = 0; i<str.length; i++){
			avai.add(Integer.parseInt(str[i]));
		}
		reqres.getBank().setAvai(avai);
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
			reqres.setReq(mtmp);
		}catch (Exception e){
			return false;
		}finally{
			sc.close();
		}
		return true;
		
	}
	private boolean getProcessOrder(){
		String data = this.ProcessReqOrderData.getText();
		Scanner sc = new Scanner(data);
		try{
			ArrayList<Integer> tmp = new ArrayList<Integer>();
			while (sc.hasNextLine()){
				int num = Integer.parseInt(sc.nextLine());
				tmp.add(num);
			}
			reqres.setProcessOrder(tmp);
		}catch (Exception e){
			return false;
		}finally{
			sc.close();
		}
		return true;
	}
	private void getData(){
		if (!this.getAlloData()) System.out.println("Xảy ra lỗi khi lấy Allo");
		if (!this.getMaxData()) System.out.println("Xảy ra lỗi khi lấy Max");
		
		if (this.ResData.getText().compareTo("") == 0 && this.AvaiData.getText().compareTo("")!=0){
			System.out.println("F");
			this.getAvaiData();
		}
		else this.getResData();
		
		if (!this.getProcessOrder()) System.out.println("Xảy ra lỗi khi lấy Order");
		if (!this.getReqData()) System.out.println("Xảy ra lỗi khi lấy Req");
	}
	public static void main(String []args){
		try{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		}
		catch(Exception ex){}
		new RequestResGUI().setVisible(true);
	}
	private void AddLogResult(){
		this.LogData.setFont(new Font("Arial", Font.PLAIN, 13));
		this.LogData.setText(reqres.getLog());
	}
	private void AddCalculatedData(){
		this.NeedData.setText(reqres.getBank().getNeed().toString());
		this.AvaiData.setText(reqres.getBank().getAvai().toString());
		this.numOfProcTF.setText(""+reqres.getBank().getNum_of_process());
		this.numOfResTF.setText(reqres.getBank().getNum_of_res()+"");
		this.BackupAvai.setText(reqres.getBackupAvai().toString());
	}
	private void CalcBTNActionPerformed(ActionEvent e) {
		// TODO add your code here
		reqres = new RequestResources();
		this.getData();
		//reqres.getBank().AlgorithmRunning();
		reqres.ReqResRunning();
		this.AddCalculatedData();
		this.AddLogResult();
		
	}

	private void NextBTNActionPerformed(ActionEvent e) {
		// TODO add your code here
		this.AlloData.setText(reqres.getBank().getAllo().getString());
		this.MaxData.setText(reqres.getBank().getMax().getString());
		this.AvaiData.setText(null);
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
		MaxDataSP = new JScrollPane();
		MaxData = new JEditorPane();
		NeedDataSP = new JScrollPane();
		NeedData = new JEditorPane();
		MaxLB = new JLabel();
		NeedLB = new JLabel();
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
		ReqLB = new JLabel();
		ReqDataSP = new JScrollPane();
		ReqData = new JEditorPane();
		ProcessReqOrderSP = new JScrollPane();
		ProcessReqOrderData = new JEditorPane();
		label1 = new JLabel();
		NextBTN = new JButton();
		AvaiDataSP2 = new JScrollPane();
		BackupAvai = new JTextPane();

		//======== this ========
		setTitle("Request Resources");
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

		//======== MaxDataSP ========
		{
			MaxDataSP.setViewportView(MaxData);
		}
		contentPane.add(MaxDataSP);
		MaxDataSP.setBounds(120, 25, MaxDataSP.getPreferredSize().width, 125);

		//======== NeedDataSP ========
		{

			//---- NeedData ----
			NeedData.setEditable(false);
			NeedData.setBackground(new Color(240, 240, 240));
			NeedDataSP.setViewportView(NeedData);
		}
		contentPane.add(NeedDataSP);
		NeedDataSP.setBounds(382, 190, NeedDataSP.getPreferredSize().width, 125);

		//---- MaxLB ----
		MaxLB.setText("Max");
		contentPane.add(MaxLB);
		MaxLB.setBounds(164, 0, MaxLB.getPreferredSize().width, 25);

		//---- NeedLB ----
		NeedLB.setText("Need");
		contentPane.add(NeedLB);
		NeedLB.setBounds(390, 165, NeedLB.getPreferredSize().width, 25);

		//======== AvaiDataSP ========
		{
			AvaiDataSP.setViewportView(AvaiData);
		}
		contentPane.add(AvaiDataSP);
		AvaiDataSP.setBounds(380, 70, 108, AvaiDataSP.getPreferredSize().height);

		//---- AvaiLB ----
		AvaiLB.setText("Available");
		contentPane.add(AvaiLB);
		AvaiLB.setBounds(415, 45, AvaiLB.getPreferredSize().width, 25);

		//---- ResLB ----
		ResLB.setText("Resources");
		contentPane.add(ResLB);
		ResLB.setBounds(410, 0, ResLB.getPreferredSize().width, 25);

		//======== ResDataSP ========
		{
			ResDataSP.setViewportView(ResData);
		}
		contentPane.add(ResDataSP);
		ResDataSP.setBounds(380, 25, 108, ResDataSP.getPreferredSize().height);

		//---- numOfProc ----
		numOfProc.setText("Processes");
		contentPane.add(numOfProc);
		numOfProc.setBounds(380, 100, numOfProc.getPreferredSize().width, 25);

		//---- numOfProcTF ----
		numOfProcTF.setBackground(new Color(240, 240, 240));
		numOfProcTF.setEditable(false);
		contentPane.add(numOfProcTF);
		numOfProcTF.setBounds(445, 100, 45, 25);

		//---- numOfRes ----
		numOfRes.setText("Resources");
		contentPane.add(numOfRes);
		numOfRes.setBounds(380, 125, numOfRes.getPreferredSize().width, 25);

		//---- numOfResTF ----
		numOfResTF.setBackground(new Color(240, 240, 240));
		numOfResTF.setEditable(false);
		contentPane.add(numOfResTF);
		numOfResTF.setBounds(445, 125, 45, 25);

		//---- CalcBTN ----
		CalcBTN.setText("Calc");
		CalcBTN.addActionListener(e -> CalcBTNActionPerformed(e));
		contentPane.add(CalcBTN);
		CalcBTN.setBounds(new Rectangle(new Point(145, 160), CalcBTN.getPreferredSize()));

		//======== LogDataSP ========
		{

			//---- LogData ----
			LogData.setBackground(new Color(240, 240, 240));
			LogData.setEditable(false);
			LogDataSP.setViewportView(LogData);
		}
		contentPane.add(LogDataSP);
		LogDataSP.setBounds(5, 190, 370, 150);

		//---- ReqLB ----
		ReqLB.setText("Request");
		contentPane.add(ReqLB);
		ReqLB.setBounds(299, 0, ReqLB.getPreferredSize().width, 25);

		//======== ReqDataSP ========
		{
			ReqDataSP.setViewportView(ReqData);
		}
		contentPane.add(ReqDataSP);
		ReqDataSP.setBounds(265, 25, 108, 125);

		//======== ProcessReqOrderSP ========
		{
			ProcessReqOrderSP.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			ProcessReqOrderSP.setViewportView(ProcessReqOrderData);
		}
		contentPane.add(ProcessReqOrderSP);
		ProcessReqOrderSP.setBounds(235, 25, 30, 125);

		//---- label1 ----
		label1.setText("Order");
		contentPane.add(label1);
		label1.setBounds(236, 0, label1.getPreferredSize().width, 25);

		//---- NextBTN ----
		NextBTN.setText("Next");
		NextBTN.addActionListener(e -> NextBTNActionPerformed(e));
		contentPane.add(NextBTN);
		NextBTN.setBounds(new Rectangle(new Point(200, 160), NextBTN.getPreferredSize()));

		//======== AvaiDataSP2 ========
		{

			//---- BackupAvai ----
			BackupAvai.setEditable(false);
			BackupAvai.setBackground(new Color(240, 240, 240));
			AvaiDataSP2.setViewportView(BackupAvai);
		}
		contentPane.add(AvaiDataSP2);
		AvaiDataSP2.setBounds(382, 320, 108, 22);

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
		setSize(505, 380);
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - Tráº§n Anh
	private JLabel AlloLB;
	private JScrollPane AlloDataSP;
	private JEditorPane AlloData;
	private JScrollPane MaxDataSP;
	private JEditorPane MaxData;
	private JScrollPane NeedDataSP;
	private JEditorPane NeedData;
	private JLabel MaxLB;
	private JLabel NeedLB;
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
	private JLabel ReqLB;
	private JScrollPane ReqDataSP;
	private JEditorPane ReqData;
	private JScrollPane ProcessReqOrderSP;
	private JEditorPane ProcessReqOrderData;
	private JLabel label1;
	private JButton NextBTN;
	private JScrollPane AvaiDataSP2;
	private JTextPane BackupAvai;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
