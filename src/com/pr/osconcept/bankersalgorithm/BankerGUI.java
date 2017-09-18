/*
 * Created by JFormDesigner on Thu Mar 17 22:50:04 ICT 2016
 */

package com.pr.osconcept.bankersalgorithm;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;


/**
 * @author Tráº§n Anh
 */
public class BankerGUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BankersAlgorithm bk;

	public BankerGUI() {
		initComponents();
		this.numOfProcTF.setEditable(false);
		this.numOfProcTF.setBackground(this.getBackground());
		this.NeedData.setEditable(false);
		this.NeedData.setBackground(this.getBackground());
		this.LogData.setEditable(false);
		this.LogData.setBackground(this.getBackground());
		this.OrderTF.setEditable(false);
		this.OrderTF.setBackground(this.getBackground());
		this.numOfResTF.setEditable(false);
		this.numOfResTF.setBackground(this.getBackground());

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
			
			bk.setAllo(mtmp);
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
			bk.setMax(mtmp);
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
		bk.setRes(res);
	}
	private void getAvaiData(){
		ArrayList<Integer> avai = new ArrayList<Integer>();
		String data = this.AvaiData.getText();
		String[] str = data.split(" ");
		for (int i = 0; i<str.length; i++){
			avai.add(Integer.parseInt(str[i]));
		}
		bk.setAvai(avai);
	}
	private void getData(){
		if (!this.getAlloData()) System.out.println("Xảy ra lỗi khi lấy Allo");
		if (!this.getMaxData()) System.out.println("Xảy ra lỗi khi lấy Max");
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
		new BankerGUI().setVisible(true);
	}
	private void AddLogResult(){
		this.LogData.setText(bk.getLog());
		if (bk.isSafe()){
			this.SafeOrNotLB.setFont(this.LogData.getFont().deriveFont(20.0f));
			this.SafeOrNotLB.setText("SAFE!!!");
			this.SafeOrNotLB.setForeground(Color.GREEN);
		}
		else{
			this.SafeOrNotLB.setFont(this.LogData.getFont().deriveFont(20.0f));
			this.SafeOrNotLB.setText("NOT SAFE!!!");
			this.SafeOrNotLB.setForeground(Color.RED);
		}
		this.OrderTF.setText(bk.getOrder());
	}
	private void AddCalculatedData(){
		this.NeedData.setText(bk.getNeed().toString());
		this.AvaiData.setText(bk.getAvai().toString());
		this.numOfProcTF.setText(""+bk.getNum_of_process());
		this.numOfResTF.setText(bk.getNum_of_res()+"");
	}
	private void CalcBTNActionPerformed(ActionEvent e) {
		bk = new BankersAlgorithm();
		this.getData();
		
		// TODO add your code here
		bk.AlgorithmRunning();
		this.AddCalculatedData();
		this.AddLogResult();
	}



	private void initComponents() {
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
		SafeOrNotLB = new JLabel();
		OrderLB = new JLabel();
		OrderTF = new JTextField();

		//======== this ========
		setTitle("Banker's Algorithm");
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		Container contentPane = getContentPane();
		contentPane.setLayout(null);

		//---- AlloLB ----
		AlloLB.setText("Allocation");
		contentPane.add(AlloLB);
		AlloLB.setBounds(36, 0, AlloLB.getPreferredSize().width, 25);

		//======== AlloDataSP ========
		{

			//---- AlloData ----
			AlloData.setText("0 1 0\n2 0 0\n3 0 3\n2 1 1\n0 0 2");
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
			NeedDataSP.setViewportView(NeedData);
		}
		contentPane.add(NeedDataSP);
		NeedDataSP.setBounds(350, 25, NeedDataSP.getPreferredSize().width, 125);

		//---- MaxLB ----
		MaxLB.setText("Max");
		contentPane.add(MaxLB);
		MaxLB.setBounds(164, 0, MaxLB.getPreferredSize().width, 25);

		//---- NeedLB ----
		NeedLB.setText("Need");
		contentPane.add(NeedLB);
		NeedLB.setBounds(390, 0, NeedLB.getPreferredSize().width, 25);

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

			//---- LogData ----
			LogData.setFont(new Font("Arial", Font.PLAIN, 13));
			LogDataSP.setViewportView(LogData);
		}
		contentPane.add(LogDataSP);
		LogDataSP.setBounds(5, 190, 453, 150);

		//---- SafeOrNotLB ----
		SafeOrNotLB.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(SafeOrNotLB);
		SafeOrNotLB.setBounds(156, 380, 150, 30);

		//---- OrderLB ----
		OrderLB.setText("Safe-sequence");
		OrderLB.setLabelFor(OrderTF);
		contentPane.add(OrderLB);
		OrderLB.setBounds(5, 355, OrderLB.getPreferredSize().width, 25);
		contentPane.add(OrderTF);
		OrderTF.setBounds(85, 355, 373, 25);

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
		setSize(470, 440);
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
	private JLabel SafeOrNotLB;
	private JLabel OrderLB;
	private JTextField OrderTF;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
