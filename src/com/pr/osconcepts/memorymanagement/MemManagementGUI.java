/*
 * Created by JFormDesigner on Thu Mar 24 19:31:50 ICT 2016
 */

package com.pr.osconcepts.memorymanagement;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.border.*;

/**
 * @author Tráº§n Anh
 */
public class MemManagementGUI extends JFrame {
	/**
	 * 
	 */
	private int framsize;
	private int logicadd;
	private ArrayList<Integer> PT;
	private ArrayList<Integer> Valid;
	private static final long serialVersionUID = 1L;
	public MemManagementGUI() {
		PT = new ArrayList<Integer>();
		Valid = new ArrayList<Integer>();
		this.framsize = 1;
		this.logicadd = 0;
		try{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		}
		catch(Exception ex){}
		initComponents();
	}
	public static void main(String []args){
		
		new MemManagementGUI().setVisible(true);
	}
	
	//Calc EAT With Cache
	private void CacheTimeTFKeyReleased(KeyEvent e) {
		this.CalcEATWithCache();
	}
	private void MemtimeTFKeyReleased(KeyEvent e) {
		this.CalcEATWithCache();
	}
	private void HitrateTFKeyReleased(KeyEvent e) {
		this.CalcEATWithCache();
	}
	private void CalcEATWithCache(){
		if (this.CacheTimeTF.getText().compareTo("")!=0)
			if (this.MemtimeTF.getText().compareTo("")!=0)
				if (this.HitrateTF.getText().compareTo("")!=0)
					this.EATTF.setText(2*Integer.parseInt(this.MemtimeTF.getText()) + Integer.parseInt(this.CacheTimeTF.getText()) - Float.parseFloat(this.HitrateTF.getText())*Integer.parseInt(this.MemtimeTF.getText())+ "");
	
	}
	//End Calc EAT With Cache
	
	//Calc Address
	private void BaseTFKeyReleased(KeyEvent e) {
		this.LogicAddTFKeyReleased(e);
	}
	private void LimitTFKeyReleased(KeyEvent e) {
		this.LogicAddTFKeyReleased(e);
	}
	private void LogicAddTFKeyReleased(KeyEvent e) {
		if (this.BaseTF.getText().compareTo("")!=0)
			if (this.LimitTF.getText().compareTo("")!=0)
				if (this.LogicAddTF.getText().compareTo("")!=0){
					this.PhyAddTF.setText(Integer.parseInt(this.LogicAddTF.getText())+Integer.parseInt(this.BaseTF.getText())+"");
					if (Integer.parseInt(this.LogicAddTF.getText())+Integer.parseInt(this.BaseTF.getText())>=Integer.parseInt(this.BaseTF.getText())){
						if (Integer.parseInt(this.LogicAddTF.getText())+Integer.parseInt(this.BaseTF.getText())<(Integer.parseInt(this.BaseTF.getText())+Integer.parseInt(this.LimitTF.getText()))){
							this.isValidLB.setText("Valid!");
							this.isValidLB.setForeground(Color.GREEN);
						}
						else{
							this.isValidLB.setText("Invalid!");
							this.isValidLB.setForeground(Color.RED);
						}
					}
					else{
						this.isValidLB.setText("Invalid!");
						this.isValidLB.setForeground(Color.RED);
					}
				}
	}
	private void PhyAddTFKeyReleased(KeyEvent e) {
		if (this.BaseTF.getText().compareTo("")!=0)
			if (this.LimitTF.getText().compareTo("")!=0)
				if (this.PhyAddTF.getText().compareTo("")!=0){
					this.LogicAddTF.setText(Integer.parseInt(this.PhyAddTF.getText()) - Integer.parseInt(this.BaseTF.getText())+"");
				}
	}
	
	//End Calc Address
	
	//Calc Address (Non Continous)
	private void CalcNC(){
		this.getDataforNC();
		this.p_offset.setText("(" + this.logicadd/this.framsize + ", " + this.logicadd%this.framsize + ")");
		if (this.logicadd/this.framsize < PT.size()){
			if (Valid.get(this.logicadd/this.framsize) ==1){
				this.p_offset.setForeground(Color.BLACK);
				this.PhyAddTF2.setText(PT.get(this.logicadd/this.framsize)*this.framsize + this.logicadd%this.framsize + "");
			}
			else{
				this.p_offset.setText("Not loaded");
				this.p_offset.setForeground(Color.RED);
			}
		}
		else{
			this.p_offset.setText("Invalid!");
			this.p_offset.setForeground(Color.RED);
		}
	}

	private void KBComboItemStateChanged(ItemEvent e) {
		String str = (String) this.KBCombo.getSelectedItem();
		switch (str){
			case "bytes":{
				if (this.FramsizeTF.getText().compareTo("")!=0)
					this.framsize = Integer.parseInt(this.FramsizeTF.getText());
				else JOptionPane.showMessageDialog(null,"Nhập tử tế đi thanh niên :))");
				this.CalcNC();
				break;
			}
			case "KB":{
				if (this.FramsizeTF.getText().compareTo("")!=0)
					this.framsize = Integer.parseInt(this.FramsizeTF.getText());
				else JOptionPane.showMessageDialog(null,"Nhập tử tế đi thanh niên :))");
				this.framsize = this.framsize*1024;
				this.CalcNC();
				break;
			}
			case "MB":{
				if (this.FramsizeTF.getText().compareTo("")!=0)
					this.framsize = Integer.parseInt(this.FramsizeTF.getText());
				else JOptionPane.showMessageDialog(null,"Nhập tử tế đi thanh niên :))");
				this.framsize = this.framsize*1024*1024;
				this.CalcNC();
				break;
			}
			default:{
				if (this.FramsizeTF.getText().compareTo("")!=0)
					this.framsize = Integer.parseInt(this.FramsizeTF.getText());
				else JOptionPane.showMessageDialog(null,"Nhập tử tế đi thanh niên :))");
				this.CalcNC();
				break;
			}
		}
	}

	private void PageTableDataKeyReleased(KeyEvent e) {
		this.CalcNC();
	}

	private void FramsizeTFKeyReleased(KeyEvent e) {
		this.CalcNC();
	}

	private void LogicAddTF2KeyReleased(KeyEvent e) {
		this.CalcNC();
	}

	private void getDataforNC(){
		if (this.LogicAddTF2.getText().compareTo("")!=0){
			this.logicadd = Integer.parseInt(this.LogicAddTF2.getText());
		}
		if (((String) this.KBCombo.getSelectedItem()).compareTo("bytes")==0){
			if (this.FramsizeTF.getText().compareTo("")!=0){
				this.framsize = Integer.parseInt(this.FramsizeTF.getText());
			}
		}
		if (this.PageTableData.getText().compareTo("")!=0){
			PT.clear();
			Valid.clear();
			String data = this.PageTableData.getText();
			Scanner sc = new Scanner(data);
			try{
				while (sc.hasNextLine()){
					int fr = sc.nextInt();
					int vl = sc.nextInt();
					PT.add(fr);
					Valid.add(vl);
				}
			}catch (Exception e){
				
			}finally{
				sc.close();
			}
			this.PageTF.setText(PT.size()+"");
		}
	}
	//End Calc Address (Non Continous)
	
	

	private void PFHandlingTFKeyReleased(KeyEvent e) {
		this.CalcEATWithPageFault();
	}
	private void MemtimePFTFKeyReleased(KeyEvent e) {
		this.CalcEATWithPageFault();
	}
	private void PFrateTFKeyReleased(KeyEvent e) {
		this.CalcEATWithPageFault();
	}
	private void CalcEATWithPageFault(){
		if (this.PFHandlingTF.getText().compareTo("")!=0)
			if (this.MemtimePFTF.getText().compareTo("")!=0)
				if (this.PFrateTF.getText().compareTo("")!=0){
					float EAT = Float.parseFloat(this.PFrateTF.getText())*Float.parseFloat(this.PFHandlingTF.getText()) + (1-Float.parseFloat(this.PFrateTF.getText()))*Float.parseFloat(this.MemtimePFTF.getText());
					float red = EAT/(Float.parseFloat(this.MemtimePFTF.getText()));
					this.EATPFTF.setText(String.format("%.9g%n", EAT));
					this.ReducePTF.setText(String.format("%.9g%n", red));
				}
	}

	private void initComponents() {
		ImageIcon icon = new ImageIcon(this.getClass().getResource("/icon.png"));
		Image image = icon.getImage();
		setIconImage(image);
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - dung Tran
		ContinousLayout = new JPanel();
		BaseLB = new JLabel();
		BaseTF = new JTextField();
		LimitLB = new JLabel();
		LimitTF = new JTextField();
		LogicAddLB = new JLabel();
		LogicAddTF = new JTextField();
		PhyAddLB = new JLabel();
		PhyAddTF = new JTextField();
		isValidLB = new JLabel();
		NonContLayout = new JPanel();
		label1 = new JLabel();
		LogicAddLB2 = new JLabel();
		LogicAddTF2 = new JTextField();
		PhyAddLB2 = new JLabel();
		PhyAddTF2 = new JTextField();
		FramsizeLB = new JLabel();
		FramsizeTF = new JTextField();
		PageLB = new JLabel();
		PageTF = new JTextField();
		label2 = new JLabel();
		p_offset = new JTextField();
		KBCombo = new JComboBox<>();
		PTSP = new JScrollPane();
		PageTableData = new JEditorPane();
		AddTransCacheLayout = new JPanel();
		CachetimeLB = new JLabel();
		CacheTimeTF = new JTextField();
		MemtimeLB = new JLabel();
		MemtimeTF = new JTextField();
		HitrateLB = new JLabel();
		HitrateTF = new JTextField();
		EATLB = new JLabel();
		EATTF = new JTextField();
		EATLayout2 = new JPanel();
		PFHandlingLB = new JLabel();
		PFHandlingTF = new JTextField();
		MemtimePFLB = new JLabel();
		MemtimePFTF = new JTextField();
		PFrateLB = new JLabel();
		PFrateTF = new JTextField();
		EATPFLb = new JLabel();
		EATPFTF = new JTextField();
		ReducePLB = new JLabel();
		ReducePTF = new JTextField();

		//======== this ========
		setTitle("Memory Management");
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		Container contentPane = getContentPane();
		contentPane.setLayout(null);

		//======== ContinousLayout ========
		{
			ContinousLayout.setBorder(new TitledBorder("Continous"));

			// JFormDesigner evaluation mark
			ContinousLayout.setBorder(new javax.swing.border.CompoundBorder(
				new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
					"", javax.swing.border.TitledBorder.CENTER,
					javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
					java.awt.Color.red), ContinousLayout.getBorder())); ContinousLayout.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

			ContinousLayout.setLayout(null);

			//---- BaseLB ----
			BaseLB.setText("Base");
			ContinousLayout.add(BaseLB);
			BaseLB.setBounds(10, 20, BaseLB.getPreferredSize().width, 20);

			//---- BaseTF ----
			BaseTF.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					BaseTFKeyReleased(e);
				}
			});
			ContinousLayout.add(BaseTF);
			BaseTF.setBounds(45, 20, 50, BaseTF.getPreferredSize().height);

			//---- LimitLB ----
			LimitLB.setText("Limit");
			ContinousLayout.add(LimitLB);
			LimitLB.setBounds(10, 45, LimitLB.getPreferredSize().width, 20);

			//---- LimitTF ----
			LimitTF.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					LimitTFKeyReleased(e);
				}
			});
			ContinousLayout.add(LimitTF);
			LimitTF.setBounds(45, 45, 50, LimitTF.getPreferredSize().height);

			//---- LogicAddLB ----
			LogicAddLB.setText("Logical Address");
			ContinousLayout.add(LogicAddLB);
			LogicAddLB.setBounds(10, 75, LogicAddLB.getPreferredSize().width, 20);

			//---- LogicAddTF ----
			LogicAddTF.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					LogicAddTFKeyReleased(e);
				}
			});
			ContinousLayout.add(LogicAddTF);
			LogicAddTF.setBounds(100, 75, 50, 20);

			//---- PhyAddLB ----
			PhyAddLB.setText("Physical Address");
			ContinousLayout.add(PhyAddLB);
			PhyAddLB.setBounds(10, 100, PhyAddLB.getPreferredSize().width, 20);

			//---- PhyAddTF ----
			PhyAddTF.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					PhyAddTFKeyReleased(e);
				}
			});
			ContinousLayout.add(PhyAddTF);
			PhyAddTF.setBounds(100, 100, 50, 20);
			ContinousLayout.add(isValidLB);
			isValidLB.setBounds(15, 125, 140, 20);

			{ // compute preferred size
				Dimension preferredSize = new Dimension();
				for(int i = 0; i < ContinousLayout.getComponentCount(); i++) {
					Rectangle bounds = ContinousLayout.getComponent(i).getBounds();
					preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
					preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
				}
				Insets insets = ContinousLayout.getInsets();
				preferredSize.width += insets.right;
				preferredSize.height += insets.bottom;
				ContinousLayout.setMinimumSize(preferredSize);
				ContinousLayout.setPreferredSize(preferredSize);
			}
		}
		contentPane.add(ContinousLayout);
		ContinousLayout.setBounds(0, 5, 215, 150);

		//======== NonContLayout ========
		{
			NonContLayout.setBorder(new TitledBorder("Non-Continous"));
			NonContLayout.setLayout(null);

			//---- label1 ----
			label1.setText("Page Table");
			NonContLayout.add(label1);
			label1.setBounds(16, 10, label1.getPreferredSize().width, 20);

			//---- LogicAddLB2 ----
			LogicAddLB2.setText("Logical Address");
			NonContLayout.add(LogicAddLB2);
			LogicAddLB2.setBounds(80, 60, 74, 20);

			//---- LogicAddTF2 ----
			LogicAddTF2.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					LogicAddTF2KeyReleased(e);
				}
			});
			NonContLayout.add(LogicAddTF2);
			LogicAddTF2.setBounds(165, 60, 50, 20);

			//---- PhyAddLB2 ----
			PhyAddLB2.setText("Physical Address");
			NonContLayout.add(PhyAddLB2);
			PhyAddLB2.setBounds(80, 90, 80, 20);

			//---- PhyAddTF2 ----
			PhyAddTF2.setEditable(false);
			NonContLayout.add(PhyAddTF2);
			PhyAddTF2.setBounds(165, 90, 50, 20);

			//---- FramsizeLB ----
			FramsizeLB.setText("Frame Size");
			NonContLayout.add(FramsizeLB);
			FramsizeLB.setBounds(80, 30, 74, 20);

			//---- FramsizeTF ----
			FramsizeTF.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					FramsizeTFKeyReleased(e);
				}
			});
			NonContLayout.add(FramsizeTF);
			FramsizeTF.setBounds(165, 30, 50, 20);

			//---- PageLB ----
			PageLB.setText("Pages");
			NonContLayout.add(PageLB);
			PageLB.setBounds(80, 120, 74, 20);

			//---- PageTF ----
			PageTF.setEditable(false);
			NonContLayout.add(PageTF);
			PageTF.setBounds(165, 120, 50, 20);

			//---- label2 ----
			label2.setText(">>");
			label2.setFont(new Font("Arial", Font.PLAIN, 12));
			NonContLayout.add(label2);
			label2.setBounds(215, 60, 20, 20);

			//---- p_offset ----
			p_offset.setEditable(false);
			NonContLayout.add(p_offset);
			p_offset.setBounds(230, 60, 63, 20);

			//---- KBCombo ----
			KBCombo.setModel(new DefaultComboBoxModel<>(new String[] {
				"bytes",
				"KB",
				"MB"
			}));
			KBCombo.addItemListener(e -> KBComboItemStateChanged(e));
			NonContLayout.add(KBCombo);
			KBCombo.setBounds(new Rectangle(new Point(230, 30), KBCombo.getPreferredSize()));

			//======== PTSP ========
			{
				PTSP.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

				//---- PageTableData ----
				PageTableData.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						PageTableDataKeyReleased(e);
					}
				});
				PTSP.setViewportView(PageTableData);
			}
			NonContLayout.add(PTSP);
			PTSP.setBounds(10, 30, 65, 110);
		}
		contentPane.add(NonContLayout);
		NonContLayout.setBounds(220, 5, 297, 150);

		//======== AddTransCacheLayout ========
		{
			AddTransCacheLayout.setBorder(new TitledBorder("Address Translation With Cache"));
			AddTransCacheLayout.setLayout(null);

			//---- CachetimeLB ----
			CachetimeLB.setText("Cache Access Time");
			AddTransCacheLayout.add(CachetimeLB);
			CachetimeLB.setBounds(10, 20, 105, 20);

			//---- CacheTimeTF ----
			CacheTimeTF.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					CacheTimeTFKeyReleased(e);
				}
			});
			AddTransCacheLayout.add(CacheTimeTF);
			CacheTimeTF.setBounds(130, 20, 80, CacheTimeTF.getPreferredSize().height);

			//---- MemtimeLB ----
			MemtimeLB.setText("Memory Access Time");
			AddTransCacheLayout.add(MemtimeLB);
			MemtimeLB.setBounds(10, 45, 105, 20);

			//---- MemtimeTF ----
			MemtimeTF.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					MemtimeTFKeyReleased(e);
				}
			});
			AddTransCacheLayout.add(MemtimeTF);
			MemtimeTF.setBounds(130, 45, 80, 20);

			//---- HitrateLB ----
			HitrateLB.setText("Hit rate");
			AddTransCacheLayout.add(HitrateLB);
			HitrateLB.setBounds(10, 70, 105, 20);

			//---- HitrateTF ----
			HitrateTF.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					HitrateTFKeyReleased(e);
				}
			});
			AddTransCacheLayout.add(HitrateTF);
			HitrateTF.setBounds(130, 70, 80, 20);

			//---- EATLB ----
			EATLB.setText("EAT");
			AddTransCacheLayout.add(EATLB);
			EATLB.setBounds(10, 105, 105, 20);

			//---- EATTF ----
			EATTF.setEditable(false);
			AddTransCacheLayout.add(EATTF);
			EATTF.setBounds(130, 105, 80, 20);

			{ // compute preferred size
				Dimension preferredSize = new Dimension();
				for(int i = 0; i < AddTransCacheLayout.getComponentCount(); i++) {
					Rectangle bounds = AddTransCacheLayout.getComponent(i).getBounds();
					preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
					preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
				}
				Insets insets = AddTransCacheLayout.getInsets();
				preferredSize.width += insets.right;
				preferredSize.height += insets.bottom;
				AddTransCacheLayout.setMinimumSize(preferredSize);
				AddTransCacheLayout.setPreferredSize(preferredSize);
			}
		}
		contentPane.add(AddTransCacheLayout);
		AddTransCacheLayout.setBounds(0, 160, 215, 140);

		//======== EATLayout2 ========
		{
			EATLayout2.setBorder(new TitledBorder("Page Fault Handling"));
			EATLayout2.setLayout(null);

			//---- PFHandlingLB ----
			PFHandlingLB.setText("Page Fault Handling Time");
			EATLayout2.add(PFHandlingLB);
			PFHandlingLB.setBounds(10, 20, PFHandlingLB.getPreferredSize().width, 20);

			//---- PFHandlingTF ----
			PFHandlingTF.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					PFHandlingTFKeyReleased(e);
				}
			});
			EATLayout2.add(PFHandlingTF);
			PFHandlingTF.setBounds(135, 20, 80, PFHandlingTF.getPreferredSize().height);

			//---- MemtimePFLB ----
			MemtimePFLB.setText("Memory Access Time");
			EATLayout2.add(MemtimePFLB);
			MemtimePFLB.setBounds(10, 45, 105, 20);

			//---- MemtimePFTF ----
			MemtimePFTF.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					MemtimePFTFKeyReleased(e);
				}
			});
			EATLayout2.add(MemtimePFTF);
			MemtimePFTF.setBounds(135, 45, 80, 20);

			//---- PFrateLB ----
			PFrateLB.setText("Page Fault rate");
			EATLayout2.add(PFrateLB);
			PFrateLB.setBounds(10, 70, 105, 20);

			//---- PFrateTF ----
			PFrateTF.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					PFrateTFKeyReleased(e);
				}
			});
			EATLayout2.add(PFrateTF);
			PFrateTF.setBounds(135, 70, 80, 20);

			//---- EATPFLb ----
			EATPFLb.setText("EAT");
			EATLayout2.add(EATPFLb);
			EATPFLb.setBounds(10, 105, EATPFLb.getPreferredSize().width, 20);

			//---- EATPFTF ----
			EATPFTF.setEditable(false);
			EATLayout2.add(EATPFTF);
			EATPFTF.setBounds(35, 105, 70, 20);

			//---- ReducePLB ----
			ReducePLB.setText("Reduce Performance");
			EATLayout2.add(ReducePLB);
			ReducePLB.setBounds(115, 105, ReducePLB.getPreferredSize().width, 20);

			//---- ReducePTF ----
			ReducePTF.setEditable(false);
			EATLayout2.add(ReducePTF);
			ReducePTF.setBounds(220, 105, 70, ReducePTF.getPreferredSize().height);

			{ // compute preferred size
				Dimension preferredSize = new Dimension();
				for(int i = 0; i < EATLayout2.getComponentCount(); i++) {
					Rectangle bounds = EATLayout2.getComponent(i).getBounds();
					preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
					preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
				}
				Insets insets = EATLayout2.getInsets();
				preferredSize.width += insets.right;
				preferredSize.height += insets.bottom;
				EATLayout2.setMinimumSize(preferredSize);
				EATLayout2.setPreferredSize(preferredSize);
			}
		}
		contentPane.add(EATLayout2);
		EATLayout2.setBounds(220, 160, 297, 140);

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
		setSize(530, 335);
		setLocationRelativeTo(null);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - dung Tran
	private JPanel ContinousLayout;
	private JLabel BaseLB;
	private JTextField BaseTF;
	private JLabel LimitLB;
	private JTextField LimitTF;
	private JLabel LogicAddLB;
	private JTextField LogicAddTF;
	private JLabel PhyAddLB;
	private JTextField PhyAddTF;
	private JLabel isValidLB;
	private JPanel NonContLayout;
	private JLabel label1;
	private JLabel LogicAddLB2;
	private JTextField LogicAddTF2;
	private JLabel PhyAddLB2;
	private JTextField PhyAddTF2;
	private JLabel FramsizeLB;
	private JTextField FramsizeTF;
	private JLabel PageLB;
	private JTextField PageTF;
	private JLabel label2;
	private JTextField p_offset;
	private JComboBox<String> KBCombo;
	private JScrollPane PTSP;
	private JEditorPane PageTableData;
	private JPanel AddTransCacheLayout;
	private JLabel CachetimeLB;
	private JTextField CacheTimeTF;
	private JLabel MemtimeLB;
	private JTextField MemtimeTF;
	private JLabel HitrateLB;
	private JTextField HitrateTF;
	private JLabel EATLB;
	private JTextField EATTF;
	private JPanel EATLayout2;
	private JLabel PFHandlingLB;
	private JTextField PFHandlingTF;
	private JLabel MemtimePFLB;
	private JTextField MemtimePFTF;
	private JLabel PFrateLB;
	private JTextField PFrateTF;
	private JLabel EATPFLb;
	private JTextField EATPFTF;
	private JLabel ReducePLB;
	private JTextField ReducePTF;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
