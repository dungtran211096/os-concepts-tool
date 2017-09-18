/*
 * Created by JFormDesigner on Fri Apr 01 17:31:39 ICT 2016
 */

package com.pr.osconcepts.scheduling;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;

import com.pr.osconcepts.scheduling.Process;

/**
 * @author Tráº§n Anh
 */
public class SchedulingAlgGUI extends JFrame {
	private ArrayList<Process> proc;
	private static final long serialVersionUID = 1L;
	private float Throughput;
	private float AverageWaitingTime;
	private float TotalWaitingTime;
	private int mode = 1;
	private int TimeQuantum;
	private String order;
	public SchedulingAlgGUI() {
		try{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		}
		catch(Exception ex){}
		this.TimeQuantum = -1;
		initComponents();
		this.TQLabel.setVisible(false);
		this.TQText.setVisible(false);
	}
	private void Calc(){
		this.getData();
		if (proc.size()!=0){
			this.AddThroughput();
			this.AddTurnAroundTime();
			this.AddWaitingTime();
			this.AddResponseTime();
			this.AddOrder();
		}
	}
	private void AddOrder(){
		this.OrderProcess.setText(order);
	}
	public void getData(){
		if (this.mode == 4 ) TimeQuantum = Integer.parseInt(TQText.getText());
		proc = new ArrayList<Process>();
		String data = this.NhapDeBai.getText();
		Scanner sc = new Scanner(data);
		try{
			int i = 1;
			while (sc.hasNextLine()){
				int st = sc.nextInt();
				if (st!=0){
					int fn = sc.nextInt();
					Process p = new Process(st,fn,"P"+i);
					proc.add(p);
					i++;
				}
				else JOptionPane.showMessageDialog(null,"Nhập sai rồi :))");

			}
		}catch (Exception e){
			
		}finally{
			
			if (proc.size()!=0){
				switch (mode){
					case 1: {//FCFS
						FCFS F = new FCFS(proc);
						proc = F.getP();
						order = F.getOrder();
						Throughput = F.CalcThroughput();
						this.AverageWaitingTime = F.CalcAverageWaitingTime();
						this.TotalWaitingTime = this.AverageWaitingTime*F.getP().size();
						break;
					}
					case 2: {//SJN
						SJN S = new SJN(proc);
						proc = S.getP();
						order = S.getOrder();
						Throughput = S.CalcThroughput();
						this.AverageWaitingTime = S.CalcAverageWaitingTime();
						this.TotalWaitingTime = this.AverageWaitingTime * S.getP().size();
						break;
					}
					case 3: {//SRTF
						SRTF S = new SRTF(proc);
						proc = S.getP();
						order = S.getOrder();
						Throughput = S.CalcThroughput();
						this.AverageWaitingTime = S.CalcAverageWaitingTime();
						this.TotalWaitingTime = this.AverageWaitingTime * S.getP().size();
						break;
					}
					case 4: {//RR
						RR R = new RR(proc,TimeQuantum);
						proc = R.getP();
						order=R.getOrder();
						Throughput = R.CalcThroughput();
						this.AverageWaitingTime = R.CalcAverageWaitingTime();
						this.TotalWaitingTime = this.AverageWaitingTime * R.getP().size();
						break;
					}
					default: break;
				}
			}
			if (sc != null) sc.close();
		}
	}
	private void AddThroughput(){
		this.ThroughputTF.setText(""+this.Throughput);
	}
	private void AddTurnAroundTime(){
		String str = "";
		for (Process p : proc){
			str =str + p.getProcess_name() + ":" + p.getTurnAroundTime() + " - ";
		}
		str=str.substring(0, str.length()-2);
		this.TurnAroundTimeTF.setText(str);
	}
	private void AddWaitingTime(){
		String str = "";
		for (Process p : proc){
			str =str + p.getProcess_name() + ":" + p.getWaitingTime() + " - ";
		}
		str=str.substring(0, str.length()-2);
		this.WaitingTimeTF.setText(str);
		this.AverageWaitingTimeTF.setText("Tổng: " + this.TotalWaitingTime + "; Trung bình: "+ this.AverageWaitingTime);
	}
	private void AddResponseTime(){
		String str = "";
		for (Process p : proc){
			str =str + p.getProcess_name() + ":" + p.getResponseTime() + " - ";
		}
		str=str.substring(0, str.length()-2);
		this.ResponseTimeTF.setText(str);
	}
	private void NhapDeBaiKeyReleased(KeyEvent e) {
		// TODO add your code here
		this.Calc();
	}

	public static void main(String[] args){

		new SchedulingAlgGUI().setVisible(true);
		
	}


	private void TQTextKeyReleased(KeyEvent e) {
		// TODO add your code here
		this.Calc();
	}

	private void FCFSButtonItemStateChanged(ItemEvent e) {
		this.TQLabel.setVisible(false);
		this.TQText.setVisible(false);
		this.mode = 1;
		this.Calc();
	}

	private void SJNButtonItemStateChanged(ItemEvent e) {
		this.TQLabel.setVisible(false);
		this.TQText.setVisible(false);
		this.mode = 2;
		this.Calc();
	}

	private void SRTFButtonItemStateChanged(ItemEvent e) {
		this.TQLabel.setVisible(false);
		this.TQText.setVisible(false);
		this.mode = 3;
		this.Calc();
	}

	private void RRButtonItemStateChanged(ItemEvent e) {
		this.TQLabel.setVisible(true);
		this.TQText.setVisible(true);
		this.mode = 4;
		if (this.TimeQuantum != -1)
			this.Calc();
	}

	private void NhapDeBaiMouseClicked(MouseEvent e) {
		if (this.NhapDeBai.getText().compareTo("Burst  Appear[Ent]")==0){
			this.NhapDeBai.setText("");
		}
	}

	private void initComponents() {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		ImageIcon icon = new ImageIcon(this.getClass().getResource("/icon.png"));
		Image image = icon.getImage();
		setIconImage(image);
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Tráº§n Anh
		FCFSButton = new JRadioButton();
		SJNButton = new JRadioButton();
		SRTFButton = new JRadioButton();
		RRButton = new JRadioButton();
		debaiSP = new JScrollPane();
		NhapDeBai = new JEditorPane();
		OrderSP = new JScrollPane();
		OrderProcess = new JEditorPane();
		ThroughputLabel = new JLabel();
		ThroughputTF = new JTextField();
		TurnAroundTimeLabel = new JLabel();
		TurnAroundTimeTF = new JTextField();
		WaitingTimeLabel = new JLabel();
		WaitingTimeTF = new JTextField();
		ResponseTimeLabel = new JLabel();
		ResponseTimeTF = new JTextField();
		AverageWaitingTimeTF = new JTextField();
		TQText = new JTextField();
		TQLabel = new JLabel();

		//======== this ========
		setResizable(false);
		setTitle("Scheduling Algorithm");
		Container contentPane = getContentPane();
		contentPane.setLayout(null);

		//---- FCFSButton ----
		FCFSButton.setText("First Come First Served");
		FCFSButton.setSelected(true);
		FCFSButton.addItemListener(e -> FCFSButtonItemStateChanged(e));
		contentPane.add(FCFSButton);
		FCFSButton.setBounds(new Rectangle(new Point(5, 10), FCFSButton.getPreferredSize()));

		//---- SJNButton ----
		SJNButton.setText("<html>Shortest Job Next<br/>(Non-preemptive SJF)</html>");
		SJNButton.addItemListener(e -> SJNButtonItemStateChanged(e));
		contentPane.add(SJNButton);
		SJNButton.setBounds(new Rectangle(new Point(5, 35), SJNButton.getPreferredSize()));

		//---- SRTFButton ----
		SRTFButton.setText("<html>Shortest Remaining Time First<br/>(Preemptive SJF)<html>");
		SRTFButton.addItemListener(e -> SRTFButtonItemStateChanged(e));
		contentPane.add(SRTFButton);
		SRTFButton.setBounds(new Rectangle(new Point(5, 70), SRTFButton.getPreferredSize()));

		//---- RRButton ----
		RRButton.setText("Round Robin");
		RRButton.addItemListener(e -> RRButtonItemStateChanged(e));
		contentPane.add(RRButton);
		RRButton.setBounds(new Rectangle(new Point(5, 110), RRButton.getPreferredSize()));

		//======== debaiSP ========
		{

			//---- NhapDeBai ----
			NhapDeBai.setText("Burst  Appear[Ent]");
			NhapDeBai.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					NhapDeBaiKeyReleased(e);
				}
			});
			NhapDeBai.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					NhapDeBaiMouseClicked(e);
				}
			});
			debaiSP.setViewportView(NhapDeBai);
		}
		contentPane.add(debaiSP);
		debaiSP.setBounds(205, 10, debaiSP.getPreferredSize().width, 125);

		//======== OrderSP ========
		{

			//---- OrderProcess ----
			OrderProcess.setEditable(false);
			OrderProcess.setBackground(new Color(240, 240, 240));
			OrderProcess.setText("Order");
			OrderSP.setViewportView(OrderProcess);
		}
		contentPane.add(OrderSP);
		OrderSP.setBounds(315, 10, OrderSP.getPreferredSize().width, 125);

		//---- ThroughputLabel ----
		ThroughputLabel.setText("<html>Th\u00f4ng l\u01b0\u1ee3ng<br/>Throughput</html>");
		contentPane.add(ThroughputLabel);
		ThroughputLabel.setBounds(5, 145, ThroughputLabel.getPreferredSize().width, 30);

		//---- ThroughputTF ----
		ThroughputTF.setBackground(new Color(240, 240, 240));
		ThroughputTF.setEditable(false);
		contentPane.add(ThroughputTF);
		ThroughputTF.setBounds(110, 150, 315, 25);

		//---- TurnAroundTimeLabel ----
		TurnAroundTimeLabel.setText("<html>Th\u1eddi gian ho\u00e0n thi\u1ec7n<br/>Turn Around Time</html>");
		contentPane.add(TurnAroundTimeLabel);
		TurnAroundTimeLabel.setBounds(5, 180, TurnAroundTimeLabel.getPreferredSize().width, 30);

		//---- TurnAroundTimeTF ----
		TurnAroundTimeTF.setBackground(new Color(240, 240, 240));
		TurnAroundTimeTF.setEditable(false);
		contentPane.add(TurnAroundTimeTF);
		TurnAroundTimeTF.setBounds(110, 185, 315, 25);

		//---- WaitingTimeLabel ----
		WaitingTimeLabel.setText("<html>Th\u1eddi gian ch\u1edd \u0111\u1ee3i<br/>Waiting Time</html>");
		contentPane.add(WaitingTimeLabel);
		WaitingTimeLabel.setBounds(5, 230, WaitingTimeLabel.getPreferredSize().width, 30);

		//---- WaitingTimeTF ----
		WaitingTimeTF.setBackground(new Color(240, 240, 240));
		WaitingTimeTF.setEditable(false);
		contentPane.add(WaitingTimeTF);
		WaitingTimeTF.setBounds(110, 220, 315, 25);

		//---- ResponseTimeLabel ----
		ResponseTimeLabel.setText("<html>Th\u1eddi gian ph\u1ea3n h\u1ed3i<br/>Response Time</html>");
		contentPane.add(ResponseTimeLabel);
		ResponseTimeLabel.setBounds(5, 275, ResponseTimeLabel.getPreferredSize().width, 30);

		//---- ResponseTimeTF ----
		ResponseTimeTF.setBackground(new Color(240, 240, 240));
		ResponseTimeTF.setEditable(false);
		contentPane.add(ResponseTimeTF);
		ResponseTimeTF.setBounds(110, 280, 315, 25);

		//---- AverageWaitingTimeTF ----
		AverageWaitingTimeTF.setBackground(new Color(240, 240, 240));
		AverageWaitingTimeTF.setEditable(false);
		contentPane.add(AverageWaitingTimeTF);
		AverageWaitingTimeTF.setBounds(110, 245, 315, 25);

		//---- TQText ----
		TQText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				TQTextKeyReleased(e);
			}
		});
		contentPane.add(TQText);
		TQText.setBounds(170, 110, 30, 25);

		//---- TQLabel ----
		TQLabel.setText("Time Quantum");
		contentPane.add(TQLabel);
		TQLabel.setBounds(95, 110, 70, 25);

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
		setSize(440, 345);
		setLocationRelativeTo(getOwner());

		//---- AlgorithmSelector ----
		ButtonGroup AlgorithmSelector = new ButtonGroup();
		AlgorithmSelector.add(FCFSButton);
		AlgorithmSelector.add(SJNButton);
		AlgorithmSelector.add(SRTFButton);
		AlgorithmSelector.add(RRButton);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - Tráº§n Anh
	private JRadioButton FCFSButton;
	private JRadioButton SJNButton;
	private JRadioButton SRTFButton;
	private JRadioButton RRButton;
	private JScrollPane debaiSP;
	private JEditorPane NhapDeBai;
	private JScrollPane OrderSP;
	private JEditorPane OrderProcess;
	private JLabel ThroughputLabel;
	private JTextField ThroughputTF;
	private JLabel TurnAroundTimeLabel;
	private JTextField TurnAroundTimeTF;
	private JLabel WaitingTimeLabel;
	private JTextField WaitingTimeTF;
	private JLabel ResponseTimeLabel;
	private JTextField ResponseTimeTF;
	private JTextField AverageWaitingTimeTF;
	private JTextField TQText;
	private JLabel TQLabel;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
