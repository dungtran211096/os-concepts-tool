/*
 * Created by JFormDesigner on Fri Apr 01 21:40:23 ICT 2016
 */

package com.pr.osconcepts.pagereplacement;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.border.*;

/**
 * @author Trần Anh
 */
public class PageReplacementGUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Integer> order;
	private int page_fault;
	private int page_replaced;
	private int mode;
	private ArrayList<Integer> swapped_out;
	private ArrayList<Integer> fault_order;
	public PageReplacementGUI() {
		order = new ArrayList<Integer>();
		swapped_out = new ArrayList<Integer>();
		fault_order = new ArrayList<Integer>();
		mode = 1;
		initComponents();
	}

	private void FrameSizeTFKeyReleased(KeyEvent e) {
		this.getData();
		this.AddData();
	}

	private void OrderTFKeyReleased(KeyEvent e) {
		this.getData();
		this.AddData();
	}

	private void HeadTFKeyReleased(KeyEvent e) {
		this.getData();
		this.AddData();
	}


	private void getData(){
		if (this.FrameSizeTF.getText().compareTo("")!=0){
			if (this.OrderTF.getText().compareTo("")!=0){
				order.clear();
				String data = this.OrderTF.getText();
				Scanner sc = new Scanner(data);
				try{
					while (sc.hasNextInt()){
						int tmp = sc.nextInt();
						order.add(tmp);
					}
				}catch (Exception e2){
				}finally{
					sc.close();
				}
				switch (mode){
					case 1:{
						FIFO f = new FIFO(order, Integer.parseInt(this.FrameSizeTF.getText()));
						this.page_fault = f.getPage_fault();
						this.page_replaced = f.getPage_replaced();
						this.swapped_out = f.getSwapped_out();
						this.fault_order = f.getFault_order();
						break;
					}
					case 2:{
						LRU l = new LRU(order, Integer.parseInt(this.FrameSizeTF.getText()));
						this.page_fault = l.getPage_fault();
						this.page_replaced = l.getPage_replaced();
						this.swapped_out = l.getSwapped_out();
						this.fault_order = l.getFault_order();
						break;
					}
					case 3:{
						Optimal o = new Optimal(order, Integer.parseInt(this.FrameSizeTF.getText()));
						this.page_fault = o.getPage_fault();
						this.page_replaced = o.getPage_replaced();
						this.swapped_out = o.getSwapped_out();
						this.fault_order = o.getFault_order();
						break;
					}
					case 4:{
						LFU l = new LFU(order, Integer.parseInt(this.FrameSizeTF.getText()));
						this.page_fault = l.getPage_fault();
						this.page_replaced = l.getPage_replaced();
						this.swapped_out = l.getSwapped_out();
						this.fault_order = l.getFault_order();
						break;
					}
					case 5:{
						int ctrl = 0;
						try{
							ctrl = Integer.parseInt(this.HeadTF.getText());
						}
						catch(Exception e){
							ctrl = 0;
						}
						SecondChance s = new SecondChance(order, Integer.parseInt(this.FrameSizeTF.getText()),ctrl);
						this.page_fault = s.getPage_fault();
						this.page_replaced = s.getPage_replaced();
						this.swapped_out = s.getSwapped_out();
						this.fault_order = s.getFault_order();
						break;
					}
				}

			}
		}
	}

	private void AddData(){
		this.PFTF.setText(this.page_fault+"");
		this.PRTF.setText(this.page_replaced+"");
		this.SwappedOutTF.setText(this.swapped_out.toString());
		this.FtedPageTF.setText(this.fault_order.toString());
	}

	private void FIFOBTNItemStateChanged(ItemEvent e) {
		this.HeadLB.setVisible(false);
		this.HeadTF.setVisible(false);
		this.mode = 1;
		this.getData();
		this.AddData();
	}

	private void LRUBTNItemStateChanged(ItemEvent e) {
		this.HeadLB.setVisible(false);
		this.HeadTF.setVisible(false);
		this.mode = 2;
		this.getData();
		this.AddData();
	}

	private void OptimalBTNItemStateChanged(ItemEvent e) {
		this.HeadLB.setVisible(false);
		this.HeadTF.setVisible(false);
		this.mode = 3;
		this.getData();
		this.AddData();
	}

	private void LFUBTNItemStateChanged(ItemEvent e) {
		this.HeadLB.setVisible(false);
		this.HeadTF.setVisible(false);
		this.mode = 4;
		this.getData();
		this.AddData();
	}

	private void SecondChanceBTNItemStateChanged(ItemEvent e) {
		this.HeadLB.setVisible(true);
		this.HeadTF.setVisible(true);
		this.mode = 5;
		this.getData();
		this.AddData();
	}

	private void initComponents() {
		ImageIcon icon = new ImageIcon(this.getClass().getResource("/icon.png"));
		Image image = icon.getImage();
		setIconImage(image);
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - dung Tran
		DataPn = new JPanel();
		FrameSizeLB = new JLabel();
		FrameSizeTF = new JTextField();
		OrderLB = new JLabel();
		OrderTF = new JTextField();
		HeadLB = new JLabel();
		HeadTF = new JTextField();
		AnsPn = new JPanel();
		PRLB = new JLabel();
		PFLB = new JLabel();
		PFTF = new JTextField();
		PRTF = new JTextField();
		SwappedOutLB = new JLabel();
		SwappedOutTF = new JTextField();
		FtedPageLB = new JLabel();
		FtedPageTF = new JTextField();
		SCAnsPn = new JPanel();
		AnsRefLB = new JLabel();
		AnsRefTF = new JTextField();
		ModePn = new JPanel();
		FIFOBTN = new JRadioButton();
		LRUBTN = new JRadioButton();
		OptimalBTN = new JRadioButton();
		LFUBTN = new JRadioButton();
		SecondChanceBTN = new JRadioButton();

		//======== this ========
		setTitle("Page Replacement");
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		Container contentPane = getContentPane();
		contentPane.setLayout(null);

		//======== DataPn ========
		{
			DataPn.setBorder(new TitledBorder("Data"));

			// JFormDesigner evaluation mark
			DataPn.setBorder(new javax.swing.border.CompoundBorder(
				new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
					"", javax.swing.border.TitledBorder.CENTER,
					javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
					java.awt.Color.red), DataPn.getBorder())); DataPn.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

			DataPn.setLayout(null);

			//---- FrameSizeLB ----
			FrameSizeLB.setText("Frame");
			DataPn.add(FrameSizeLB);
			FrameSizeLB.setBounds(10, 15, FrameSizeLB.getPreferredSize().width, 20);

			//---- FrameSizeTF ----
			FrameSizeTF.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					FrameSizeTFKeyReleased(e);
				}
			});
			DataPn.add(FrameSizeTF);
			FrameSizeTF.setBounds(45, 15, 25, FrameSizeTF.getPreferredSize().height);

			//---- OrderLB ----
			OrderLB.setText("Order");
			DataPn.add(OrderLB);
			OrderLB.setBounds(10, 40, OrderLB.getPreferredSize().width, 20);

			//---- OrderTF ----
			OrderTF.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					OrderTFKeyReleased(e);
				}
			});
			DataPn.add(OrderTF);
			OrderTF.setBounds(45, 40, 405, OrderTF.getPreferredSize().height);

			//---- HeadLB ----
			HeadLB.setText("Pointer at frame");
			HeadLB.setVisible(false);
			DataPn.add(HeadLB);
			HeadLB.setBounds(80, 15, HeadLB.getPreferredSize().width, 20);

			//---- HeadTF ----
			HeadTF.setVisible(false);
			HeadTF.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					HeadTFKeyReleased(e);
				}
			});
			DataPn.add(HeadTF);
			HeadTF.setBounds(160, 15, 25, HeadTF.getPreferredSize().height);

			{ // compute preferred size
				Dimension preferredSize = new Dimension();
				for(int i = 0; i < DataPn.getComponentCount(); i++) {
					Rectangle bounds = DataPn.getComponent(i).getBounds();
					preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
					preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
				}
				Insets insets = DataPn.getInsets();
				preferredSize.width += insets.right;
				preferredSize.height += insets.bottom;
				DataPn.setMinimumSize(preferredSize);
				DataPn.setPreferredSize(preferredSize);
			}
		}
		contentPane.add(DataPn);
		DataPn.setBounds(5, 5, 455, 80);

		//======== AnsPn ========
		{
			AnsPn.setBorder(new TitledBorder("Result"));
			AnsPn.setLayout(null);

			//---- PRLB ----
			PRLB.setText("Page Replaced");
			AnsPn.add(PRLB);
			PRLB.setBounds(200, 20, PRLB.getPreferredSize().width, 20);

			//---- PFLB ----
			PFLB.setText("Page Fault");
			AnsPn.add(PFLB);
			PFLB.setBounds(10, 20, PFLB.getPreferredSize().width, 20);

			//---- PFTF ----
			PFTF.setEditable(false);
			AnsPn.add(PFTF);
			PFTF.setBounds(80, 20, 30, PFTF.getPreferredSize().height);

			//---- PRTF ----
			PRTF.setEditable(false);
			AnsPn.add(PRTF);
			PRTF.setBounds(280, 20, 30, 20);

			//---- SwappedOutLB ----
			SwappedOutLB.setText("Swapped out");
			AnsPn.add(SwappedOutLB);
			SwappedOutLB.setBounds(10, 45, SwappedOutLB.getPreferredSize().width, 20);

			//---- SwappedOutTF ----
			SwappedOutTF.setEditable(false);
			AnsPn.add(SwappedOutTF);
			SwappedOutTF.setBounds(80, 45, 230, SwappedOutTF.getPreferredSize().height);

			//---- FtedPageLB ----
			FtedPageLB.setText("Faulted Pages");
			AnsPn.add(FtedPageLB);
			FtedPageLB.setBounds(10, 70, FtedPageLB.getPreferredSize().width, 20);

			//---- FtedPageTF ----
			FtedPageTF.setEditable(false);
			AnsPn.add(FtedPageTF);
			FtedPageTF.setBounds(80, 70, 230, 20);

			//======== SCAnsPn ========
			{
				SCAnsPn.setVisible(false);
				SCAnsPn.setLayout(null);

				//---- AnsRefLB ----
				AnsRefLB.setText("<html>Reference </br> bits after </html>");
				SCAnsPn.add(AnsRefLB);
				AnsRefLB.setBounds(0, 0, 65, 35);

				//---- AnsRefTF ----
				AnsRefTF.setEditable(false);
				SCAnsPn.add(AnsRefTF);
				AnsRefTF.setBounds(70, 7, 230, AnsRefTF.getPreferredSize().height);

				{ // compute preferred size
					Dimension preferredSize = new Dimension();
					for(int i = 0; i < SCAnsPn.getComponentCount(); i++) {
						Rectangle bounds = SCAnsPn.getComponent(i).getBounds();
						preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
						preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
					}
					Insets insets = SCAnsPn.getInsets();
					preferredSize.width += insets.right;
					preferredSize.height += insets.bottom;
					SCAnsPn.setMinimumSize(preferredSize);
					SCAnsPn.setPreferredSize(preferredSize);
				}
			}
			AnsPn.add(SCAnsPn);
			SCAnsPn.setBounds(new Rectangle(new Point(10, 90), SCAnsPn.getPreferredSize()));

			{ // compute preferred size
				Dimension preferredSize = new Dimension();
				for(int i = 0; i < AnsPn.getComponentCount(); i++) {
					Rectangle bounds = AnsPn.getComponent(i).getBounds();
					preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
					preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
				}
				Insets insets = AnsPn.getInsets();
				preferredSize.width += insets.right;
				preferredSize.height += insets.bottom;
				AnsPn.setMinimumSize(preferredSize);
				AnsPn.setPreferredSize(preferredSize);
			}
		}
		contentPane.add(AnsPn);
		AnsPn.setBounds(145, 90, 315, 155);

		//======== ModePn ========
		{
			ModePn.setBorder(new TitledBorder("Algorithms"));
			ModePn.setLayout(null);

			//---- FIFOBTN ----
			FIFOBTN.setText("First In First Out");
			FIFOBTN.setSelected(true);
			FIFOBTN.addItemListener(e -> FIFOBTNItemStateChanged(e));
			ModePn.add(FIFOBTN);
			FIFOBTN.setBounds(new Rectangle(new Point(5, 20), FIFOBTN.getPreferredSize()));

			//---- LRUBTN ----
			LRUBTN.setText("Least Recently Use");
			LRUBTN.addItemListener(e -> LRUBTNItemStateChanged(e));
			ModePn.add(LRUBTN);
			LRUBTN.setBounds(new Rectangle(new Point(5, 45), LRUBTN.getPreferredSize()));

			//---- OptimalBTN ----
			OptimalBTN.setText("Optimal");
			OptimalBTN.addItemListener(e -> OptimalBTNItemStateChanged(e));
			ModePn.add(OptimalBTN);
			OptimalBTN.setBounds(new Rectangle(new Point(5, 70), OptimalBTN.getPreferredSize()));

			//---- LFUBTN ----
			LFUBTN.setText("Least Frequently Use");
			LFUBTN.addItemListener(e -> LFUBTNItemStateChanged(e));
			ModePn.add(LFUBTN);
			LFUBTN.setBounds(new Rectangle(new Point(5, 95), LFUBTN.getPreferredSize()));

			//---- SecondChanceBTN ----
			SecondChanceBTN.setText("Second Chance");
			SecondChanceBTN.setToolTipText("X\u1ea5p x\u1ec9 LRU, \u0110\u1ed3ng h\u1ed3");
			SecondChanceBTN.addItemListener(e -> SecondChanceBTNItemStateChanged(e));
			ModePn.add(SecondChanceBTN);
			SecondChanceBTN.setBounds(new Rectangle(new Point(5, 120), SecondChanceBTN.getPreferredSize()));

			{ // compute preferred size
				Dimension preferredSize = new Dimension();
				for(int i = 0; i < ModePn.getComponentCount(); i++) {
					Rectangle bounds = ModePn.getComponent(i).getBounds();
					preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
					preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
				}
				Insets insets = ModePn.getInsets();
				preferredSize.width += insets.right;
				preferredSize.height += insets.bottom;
				ModePn.setMinimumSize(preferredSize);
				ModePn.setPreferredSize(preferredSize);
			}
		}
		contentPane.add(ModePn);
		ModePn.setBounds(5, 90, 140, 155);

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
		setSize(470, 280);
		setLocationRelativeTo(getOwner());

		//---- ModeBTN ----
		ButtonGroup ModeBTN = new ButtonGroup();
		ModeBTN.add(FIFOBTN);
		ModeBTN.add(LRUBTN);
		ModeBTN.add(OptimalBTN);
		ModeBTN.add(LFUBTN);
		ModeBTN.add(SecondChanceBTN);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - dung Tran
	private JPanel DataPn;
	private JLabel FrameSizeLB;
	private JTextField FrameSizeTF;
	private JLabel OrderLB;
	private JTextField OrderTF;
	private JLabel HeadLB;
	private JTextField HeadTF;
	private JPanel AnsPn;
	private JLabel PRLB;
	private JLabel PFLB;
	private JTextField PFTF;
	private JTextField PRTF;
	private JLabel SwappedOutLB;
	private JTextField SwappedOutTF;
	private JLabel FtedPageLB;
	private JTextField FtedPageTF;
	private JPanel SCAnsPn;
	private JLabel AnsRefLB;
	private JTextField AnsRefTF;
	private JPanel ModePn;
	private JRadioButton FIFOBTN;
	private JRadioButton LRUBTN;
	private JRadioButton OptimalBTN;
	private JRadioButton LFUBTN;
	private JRadioButton SecondChanceBTN;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
