/*
 * Created by JFormDesigner on Fri May 20 23:23:18 ICT 2016
 */

package com.pr.osconcepts.filesystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author dung Tran
 */
public class filesystemGUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public filesystemGUI() {
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception ex){}
		initComponents();
	}

	//start //GEN-BEGIN:Contigous_block_offset
	private void file_size_tf_boKeyReleased(KeyEvent e) {
		this.CalcAdd_bo();
	}
	private void blk_size_tf_boKeyReleased(KeyEvent e) {
		this.CalcAdd_bo();
	}
	private void x_tf_boKeyReleased(KeyEvent e) {
		this.CalcAdd_bo();
	}
	private void file_size_cb_boItemStateChanged(ItemEvent e) {
		this.CalcAdd_bo();
	}
	private void blk_size_cb_boItemStateChanged(ItemEvent e) {
		this.CalcAdd_bo();
	}
	private void x_cb_boItemStateChanged(ItemEvent e) {
		this.CalcAdd_bo();
	}
	private long Convert_to_bytes(float x, String str){
		switch (str){
			case "bytes":{
				return (long) x;
			}
			case "KB":{
				return (long) (x*1024);
			}
			case "MB":{
				return (long) (x*1024*1024);
			}
			case "GB":{
				return (long) (x*1024*1024*1024);
			}
			default:{
				return (long) x;
			}
		}
	}
	private void CalcAdd_bo(){
		if (this.file_size_tf_bo.getText().compareTo("")!=0){
			if (this.blk_size_tf_bo.getText().compareTo("")!=0){
				long file_size = this.Convert_to_bytes(Float.parseFloat(this.file_size_tf_bo.getText()), (String) this.file_size_cb_bo.getSelectedItem());
				long block_size = this.Convert_to_bytes(Float.parseFloat(this.blk_size_tf_bo.getText()), (String) this.blk_size_cb_bo.getSelectedItem());
				this.max_blk_tf_bo.setText(""+file_size/block_size);
				if (this.x_tf_bo.getText().compareTo("")!=0){
					long x = this.Convert_to_bytes(Float.parseFloat(this.x_tf_bo.getText()), (String) this.x_cb_bo.getSelectedItem());
					if (x>file_size) this.result_bo.setText("x > File size");
					else{
						this.result_bo.setText("(" + x/block_size + "," + x%block_size + ")");
					}
				}
			}
		}
	}
	// end //GEN-END:Contigous_block_offset
	
	public static void main(String args[]){
		new filesystemGUI().setVisible(true);
	}

	//extend - base
	private void file_size_tf_ebKeyReleased(KeyEvent e) {
		this.CalcAdd_eb();
	}

	private void blk_size_tf_ebKeyReleased(KeyEvent e) {
		this.CalcAdd_eb();
	}

	private void x_tf_ebKeyReleased(KeyEvent e) {
		this.CalcAdd_eb();
	}

	private void ext_size_tf_ebKeyReleased(KeyEvent e) {
		this.CalcAdd_eb();
	}

	private void file_size_cb_ebItemStateChanged(ItemEvent e) {
		this.CalcAdd_eb();
	}

	private void blk_size_cb_ebItemStateChanged(ItemEvent e) {
		this.CalcAdd_eb();
	}

	private void x_cb_ebItemStateChanged(ItemEvent e) {
		this.CalcAdd_eb();
	}
	private void CalcAdd_eb(){
		if (this.file_size_tf_eb.getText().compareTo("")!=0){
			if (this.blk_size_tf_eb.getText().compareTo("")!=0){
				if (this.x_tf_eb.getText().compareTo("")!=0){
					if (this.ext_size_tf_eb.getText().compareTo("")!=0){
						long file_size = this.Convert_to_bytes(Float.parseFloat(this.file_size_tf_eb.getText()), (String) this.file_size_cb_eb.getSelectedItem());
						long block_size = this.Convert_to_bytes(Float.parseFloat(this.blk_size_tf_eb.getText()), (String) this.blk_size_cb_eb.getSelectedItem());
						long x = this.Convert_to_bytes(Float.parseFloat(this.x_tf_eb.getText()), (String) this.x_cb_eb.getSelectedItem());
						long ext_size = Long.parseLong(this.ext_size_tf_eb.getText());
						if (x>file_size) this.result_eb.setText("x > File size");
						else{
							this.result_eb.setText("(" + x/(block_size*ext_size) + "," + (x/block_size)%ext_size + "," + x%block_size + ")");
						}
					}
				}
			}
		}
	}
	
	
	

	private void file_size_tf_bo_linkedKeyReleased(KeyEvent e) {
		this.CalcAdd_bo_linked();
	}

	private void blk_size_tf_bo_linkedKeyReleased(KeyEvent e) {
		this.CalcAdd_bo_linked();
	}

	private void x_tf_bo_linkedKeyReleased(KeyEvent e) {
		this.CalcAdd_bo_linked();
	}

	private void pointer_size_tf_bo_linkedKeyReleased(KeyEvent e) {
		this.CalcAdd_bo_linked();
	}

	private void CalcAdd_bo_linked(){
		if (this.file_size_tf_bo_linked.getText().compareTo("")!=0){
			if (this.blk_size_tf_bo_linked.getText().compareTo("")!=0){
				if (this.x_tf_bo_linked.getText().compareTo("")!=0){
					if (this.pointer_size_tf_bo_linked.getText().compareTo("")!=0){
						long file_size = this.Convert_to_bytes(Float.parseFloat(this.file_size_tf_bo_linked.getText()), (String) this.file_size_cb_bo_linked.getSelectedItem());
						long block_size = this.Convert_to_bytes(Float.parseFloat(this.blk_size_tf_bo_linked.getText()), (String) this.blk_size_cb_bo_linked.getSelectedItem());
						long x = this.Convert_to_bytes(Float.parseFloat(this.x_tf_bo_linked.getText()), (String) this.x_cb_bo_linked.getSelectedItem());
						long pointer_size = this.Convert_to_bytes(Float.parseFloat(this.pointer_size_tf_bo_linked.getText()), (String) this.pointer_size_cb_bo_linked.getSelectedItem());
						if (x>file_size) this.result_bo_linked.setText("x > File size");
						else{
							this.result_bo_linked.setText("(" + x/(block_size - pointer_size) + "," + (x%(block_size - pointer_size) + pointer_size) + ")");
						}
					}
				}
			}
		}
	}

	private void file_size_cb_bo_linkedItemStateChanged(ItemEvent e) {
		this.CalcAdd_bo_linked();
	}

	private void blk_size_cb_bo_linkedItemStateChanged(ItemEvent e) {
		this.CalcAdd_bo_linked();
	}

	private void x_cb_bo_linkedItemStateChanged(ItemEvent e) {
		this.CalcAdd_bo_linked();
	}

	private void pointer_size_cb_bo_linkedItemStateChanged(ItemEvent e) {
		this.CalcAdd_bo_linked();
	}

	
	// 2-layer
	private void file_size_tf_li_linked2lvKeyReleased(KeyEvent e) {
		this.CalcAdd_li_linked2lv();
	}

	private void blk_size_tf_li_linked2lvKeyReleased(KeyEvent e) {
		this.CalcAdd_li_linked2lv();
	}

	private void file_size_cb_li_linked2lvItemStateChanged(ItemEvent e) {
		this.CalcAdd_li_linked2lv();
	}

	private void blk_size_cb_li_linked2lvItemStateChanged(ItemEvent e) {
		this.CalcAdd_li_linked2lv();
	}

	private void x_tf_li_linked2lvKeyReleased(KeyEvent e) {
		this.CalcAdd_li_linked2lv();
	}

	private void x_cb_li_linked2lvItemStateChanged(ItemEvent e) {
		this.CalcAdd_li_linked2lv();
	}

	private void pointer_size_tf_li_linked2lvKeyReleased(KeyEvent e) {
		this.CalcAdd_li_linked2lv();
	}

	private void pointer_size_cb_li_linked2lvItemStateChanged(ItemEvent e) {
		this.CalcAdd_li_linked2lv();
	}
	private void CalcAdd_li_linked2lv(){
		if (this.file_size_tf_li_linked2lv.getText().compareTo("")!=0){
			if (this.blk_size_tf_li_linked2lv.getText().compareTo("")!=0){
				if (this.x_tf_li_linked2lv.getText().compareTo("")!=0){
					if (this.pointer_size_tf_li_linked2lv.getText().compareTo("")!=0){
						long file_size = this.Convert_to_bytes(Float.parseFloat(this.file_size_tf_li_linked2lv.getText()), (String) this.file_size_cb_li_linked2lv.getSelectedItem());
						long block_size = this.Convert_to_bytes(Float.parseFloat(this.blk_size_tf_li_linked2lv.getText()), (String) this.blk_size_cb_li_linked2lv.getSelectedItem());
						long x = this.Convert_to_bytes(Float.parseFloat(this.x_tf_li_linked2lv.getText()), (String) this.x_cb_li_linked2lv.getSelectedItem());
						long pointer_size = this.Convert_to_bytes(Float.parseFloat(this.pointer_size_tf_li_linked2lv.getText()), (String) this.pointer_size_cb_li_linked2lv.getSelectedItem());
						if (x>file_size) this.result_li_linked2lv.setText("x > File size");
						else{
							long block = x / block_size;
							long num_of_pointer = block_size / pointer_size;
							this.result_li_linked2lv.setText("(" + block/num_of_pointer + "," + block%num_of_pointer + "," + x%block_size +  ")");
						}
					}
				}
			}
		}
	}

	private void file_size_tf_li_linkedKeyReleased(KeyEvent e) {
		this.CalcAdd_li_linked();
	}

	private void blk_size_tf_li_linkedKeyReleased(KeyEvent e) {
		this.CalcAdd_li_linked();
	}

	private void x_tf_li_linkedKeyReleased(KeyEvent e) {
		this.CalcAdd_li_linked();
	}

	private void pointer_size_tf_li_linkedKeyReleased(KeyEvent e) {
		this.CalcAdd_li_linked();
	}

	private void file_size_cb_li_linkedItemStateChanged(ItemEvent e) {
		this.CalcAdd_li_linked();
	}
	private void CalcAdd_li_linked(){
		if (this.file_size_tf_li_linked.getText().compareTo("")!=0){
			if (this.blk_size_tf_li_linked.getText().compareTo("")!=0){
				if (this.x_tf_li_linked.getText().compareTo("")!=0){
					if (this.pointer_size_tf_li_linked.getText().compareTo("")!=0){
						long file_size = this.Convert_to_bytes(Float.parseFloat(this.file_size_tf_li_linked.getText()), (String) this.file_size_cb_li_linked.getSelectedItem());
						long block_size = this.Convert_to_bytes(Float.parseFloat(this.blk_size_tf_li_linked.getText()), (String) this.blk_size_cb_li_linked.getSelectedItem());
						long x = this.Convert_to_bytes(Float.parseFloat(this.x_tf_li_linked.getText()), (String) this.x_cb_li_linked.getSelectedItem());
						long pointer_size = this.Convert_to_bytes(Float.parseFloat(this.pointer_size_tf_li_linked.getText()), (String) this.pointer_size_cb_li_linked.getSelectedItem());
						if (x>file_size) this.result_li_linked.setText("x > File size");
						else{
							long block = x / block_size;
							long num_of_pointer_in_block = block_size / pointer_size - 1;
							this.result_li_linked.setText("(" + block/num_of_pointer_in_block + "," + block%num_of_pointer_in_block + "," + x%block_size +  ")");
						}
					}
				}
			}
		}
	}

	private void file_size_tf_indexedKeyReleased(KeyEvent e) {
		this.CalcAdd_indexed();
	}

	private void file_size_cb_indexedItemStateChanged(ItemEvent e) {
		this.CalcAdd_indexed();
	}
	private void CalcAdd_indexed(){
		if (this.pointer_size_tf_indexed.getText().compareTo("")!=0){
			if (this.blk_size_tf_indexed.getText().compareTo("")!=0){
				long block_size = this.Convert_to_bytes(Float.parseFloat(this.blk_size_tf_indexed.getText()), (String) this.blk_size_cb_indexed.getSelectedItem());
				long pointer_size = this.Convert_to_bytes(Float.parseFloat(this.pointer_size_tf_indexed.getText()), (String) this.pointer_size_cb_indexed.getSelectedItem());
				long num_of_pointer_in_block = block_size / pointer_size;
				float file_size_max = (num_of_pointer_in_block*block_size)/1024/1024;
				this.Size_max_tf_indexed.setText(file_size_max + " MB");
				if (this.x_tf_indexed.getText().compareTo("")!=0){
					if (this.file_size_tf_indexed.getText().compareTo("")!=0){
						long file_size = this.Convert_to_bytes(Float.parseFloat(this.file_size_tf_indexed.getText()), (String) this.file_size_cb_indexed.getSelectedItem());
						long x = this.Convert_to_bytes(Float.parseFloat(this.x_tf_indexed.getText()), (String) this.x_cb_indexed.getSelectedItem());
						if (x>file_size) this.result_indexed.setText("x > File size");
						else{
							this.result_indexed.setText("(" + x/block_size + "," + x%block_size +  ")");
						}
					}
				}
			}
		}
	}
	//end
	
	
	
	private void initComponents() {
		ImageIcon icon = new ImageIcon(this.getClass().getResource("/icon.png"));
		Image image = icon.getImage();
		setIconImage(image);
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - dung Tran
		contigousAllo = new JPanel();
		block_offset = new JPanel();
		file_size_lb_bo = new JLabel();
		blk_size_lb_bo = new JLabel();
		x_lb_bo = new JLabel();
		max_blk_lb_bo = new JLabel();
		result_bo = new JLabel();
		file_size_tf_bo = new JTextField();
		blk_size_tf_bo = new JTextField();
		x_tf_bo = new JTextField();
		max_blk_tf_bo = new JTextField();
		file_size_cb_bo = new JComboBox<>();
		blk_size_cb_bo = new JComboBox<>();
		x_cb_bo = new JComboBox<>();
		extend_base = new JPanel();
		file_size_lb_eb = new JLabel();
		blk_size_lb_eb = new JLabel();
		x_lb_eb = new JLabel();
		ext_size_lb_eb = new JLabel();
		result_eb = new JLabel();
		file_size_tf_eb = new JTextField();
		blk_size_tf_eb = new JTextField();
		x_tf_eb = new JTextField();
		ext_size_tf_eb = new JTextField();
		file_size_cb_eb = new JComboBox<>();
		blk_size_cb_eb = new JComboBox<>();
		x_cb_eb = new JComboBox<>();
		linkedlistAllo = new JPanel();
		block_offset_linked = new JPanel();
		file_size_lb_bo_linked = new JLabel();
		blk_size_lb_bo_linked = new JLabel();
		x_lb_bo_linked = new JLabel();
		pointer_size_lb_bo_linked = new JLabel();
		result_bo_linked = new JLabel();
		file_size_tf_bo_linked = new JTextField();
		blk_size_tf_bo_linked = new JTextField();
		x_tf_bo_linked = new JTextField();
		pointer_size_tf_bo_linked = new JTextField();
		file_size_cb_bo_linked = new JComboBox<>();
		blk_size_cb_bo_linked = new JComboBox<>();
		x_cb_bo_linked = new JComboBox<>();
		pointer_size_cb_bo_linked = new JComboBox<>();
		linkedindex = new JPanel();
		file_size_lb_li_linked = new JLabel();
		blk_size_lb_li_linked = new JLabel();
		x_lb_li_linked = new JLabel();
		pointer_size_lb_li_linked = new JLabel();
		result_li_linked = new JLabel();
		file_size_tf_li_linked = new JTextField();
		blk_size_tf_li_linked = new JTextField();
		x_tf_li_linked = new JTextField();
		pointer_size_tf_li_linked = new JTextField();
		file_size_cb_li_linked = new JComboBox<>();
		blk_size_cb_li_linked = new JComboBox<>();
		x_cb_li_linked = new JComboBox<>();
		pointer_size_cb_li_linked = new JComboBox<>();
		twolevelindex = new JPanel();
		file_size_lb_li_linked2lv = new JLabel();
		blk_size_lb_li_linked2lv = new JLabel();
		x_lb_li_linked2lv = new JLabel();
		pointer_size_lb_li_linked2lv = new JLabel();
		result_li_linked2lv = new JLabel();
		file_size_tf_li_linked2lv = new JTextField();
		blk_size_tf_li_linked2lv = new JTextField();
		x_tf_li_linked2lv = new JTextField();
		pointer_size_tf_li_linked2lv = new JTextField();
		file_size_cb_li_linked2lv = new JComboBox<>();
		blk_size_cb_li_linked2lv = new JComboBox<>();
		x_cb_li_linked2lv = new JComboBox<>();
		pointer_size_cb_li_linked2lv = new JComboBox<>();
		twolevelindex2 = new JPanel();
		file_size_lb_li_linked2lv2 = new JLabel();
		blk_size_lb_li_linked2lv2 = new JLabel();
		x_lb_li_linked2lv2 = new JLabel();
		pointer_size_lb_li_linked2lv2 = new JLabel();
		result_indexed = new JLabel();
		file_size_tf_indexed = new JTextField();
		blk_size_tf_indexed = new JTextField();
		x_tf_indexed = new JTextField();
		pointer_size_tf_indexed = new JTextField();
		file_size_cb_indexed = new JComboBox<>();
		blk_size_cb_indexed = new JComboBox<>();
		x_cb_indexed = new JComboBox<>();
		pointer_size_cb_indexed = new JComboBox<>();
		label7 = new JLabel();
		Size_max_tf_indexed = new JTextField();

		//======== this ========
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setResizable(false);
		setTitle("File Allocation");
		Container contentPane = getContentPane();
		contentPane.setLayout(null);

		//======== contigousAllo ========
		{
			contigousAllo.setBorder(new TitledBorder("Contigous Allocation"));

			// JFormDesigner evaluation mark
			contigousAllo.setBorder(new javax.swing.border.CompoundBorder(
				new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
					"", javax.swing.border.TitledBorder.CENTER,
					javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
					java.awt.Color.red), contigousAllo.getBorder())); contigousAllo.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

			contigousAllo.setLayout(null);

			//======== block_offset ========
			{
				block_offset.setBorder(new TitledBorder("Block, Offset"));
				block_offset.setLayout(null);

				//---- file_size_lb_bo ----
				file_size_lb_bo.setText("File size");
				block_offset.add(file_size_lb_bo);
				file_size_lb_bo.setBounds(10, 20, file_size_lb_bo.getPreferredSize().width, 20);

				//---- blk_size_lb_bo ----
				blk_size_lb_bo.setText("Block size");
				block_offset.add(blk_size_lb_bo);
				blk_size_lb_bo.setBounds(10, 40, blk_size_lb_bo.getPreferredSize().width, 20);

				//---- x_lb_bo ----
				x_lb_bo.setText("x");
				block_offset.add(x_lb_bo);
				x_lb_bo.setBounds(10, 60, x_lb_bo.getPreferredSize().width, 20);

				//---- max_blk_lb_bo ----
				max_blk_lb_bo.setText("Max block");
				block_offset.add(max_blk_lb_bo);
				max_blk_lb_bo.setBounds(10, 80, max_blk_lb_bo.getPreferredSize().width, 20);

				//---- result_bo ----
				result_bo.setHorizontalAlignment(SwingConstants.CENTER);
				result_bo.setText("Result");
				block_offset.add(result_bo);
				result_bo.setBounds(20, 110, 120, 25);

				//---- file_size_tf_bo ----
				file_size_tf_bo.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						file_size_tf_boKeyReleased(e);
					}
				});
				block_offset.add(file_size_tf_bo);
				file_size_tf_bo.setBounds(67, 20, 45, file_size_tf_bo.getPreferredSize().height);

				//---- blk_size_tf_bo ----
				blk_size_tf_bo.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						blk_size_tf_boKeyReleased(e);
					}
				});
				block_offset.add(blk_size_tf_bo);
				blk_size_tf_bo.setBounds(67, 40, 45, blk_size_tf_bo.getPreferredSize().height);

				//---- x_tf_bo ----
				x_tf_bo.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						x_tf_boKeyReleased(e);
					}
				});
				block_offset.add(x_tf_bo);
				x_tf_bo.setBounds(67, 60, 45, x_tf_bo.getPreferredSize().height);

				//---- max_blk_tf_bo ----
				max_blk_tf_bo.setEditable(false);
				block_offset.add(max_blk_tf_bo);
				max_blk_tf_bo.setBounds(67, 80, 97, max_blk_tf_bo.getPreferredSize().height);

				//---- file_size_cb_bo ----
				file_size_cb_bo.setModel(new DefaultComboBoxModel<>(new String[] {
					"bytes",
					"KB",
					"MB",
					"GB"
				}));
				file_size_cb_bo.addItemListener(e -> file_size_cb_boItemStateChanged(e));
				block_offset.add(file_size_cb_bo);
				file_size_cb_bo.setBounds(new Rectangle(new Point(112, 20), file_size_cb_bo.getPreferredSize()));

				//---- blk_size_cb_bo ----
				blk_size_cb_bo.setModel(new DefaultComboBoxModel<>(new String[] {
					"bytes",
					"KB",
					"MB",
					"GB"
				}));
				blk_size_cb_bo.addItemListener(e -> blk_size_cb_boItemStateChanged(e));
				block_offset.add(blk_size_cb_bo);
				blk_size_cb_bo.setBounds(112, 40, 52, 20);

				//---- x_cb_bo ----
				x_cb_bo.setModel(new DefaultComboBoxModel<>(new String[] {
					"bytes",
					"KB",
					"MB",
					"GB"
				}));
				x_cb_bo.addItemListener(e -> x_cb_boItemStateChanged(e));
				block_offset.add(x_cb_bo);
				x_cb_bo.setBounds(112, 60, 52, 20);

				{ // compute preferred size
					Dimension preferredSize = new Dimension();
					for(int i = 0; i < block_offset.getComponentCount(); i++) {
						Rectangle bounds = block_offset.getComponent(i).getBounds();
						preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
						preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
					}
					Insets insets = block_offset.getInsets();
					preferredSize.width += insets.right;
					preferredSize.height += insets.bottom;
					block_offset.setMinimumSize(preferredSize);
					block_offset.setPreferredSize(preferredSize);
				}
			}
			contigousAllo.add(block_offset);
			block_offset.setBounds(5, 15, 170, 145);

			//======== extend_base ========
			{
				extend_base.setBorder(new TitledBorder("Extend-base"));
				extend_base.setLayout(null);

				//---- file_size_lb_eb ----
				file_size_lb_eb.setText("File size");
				extend_base.add(file_size_lb_eb);
				file_size_lb_eb.setBounds(10, 20, 37, 20);

				//---- blk_size_lb_eb ----
				blk_size_lb_eb.setText("Block size");
				extend_base.add(blk_size_lb_eb);
				blk_size_lb_eb.setBounds(10, 40, 45, 20);

				//---- x_lb_eb ----
				x_lb_eb.setText("x");
				extend_base.add(x_lb_eb);
				x_lb_eb.setBounds(10, 60, x_lb_eb.getPreferredSize().width, 20);

				//---- ext_size_lb_eb ----
				ext_size_lb_eb.setText("Extend size");
				extend_base.add(ext_size_lb_eb);
				ext_size_lb_eb.setBounds(10, 80, ext_size_lb_eb.getPreferredSize().width, 20);

				//---- result_eb ----
				result_eb.setHorizontalAlignment(SwingConstants.CENTER);
				result_eb.setText("Result");
				extend_base.add(result_eb);
				result_eb.setBounds(20, 110, 135, 25);

				//---- file_size_tf_eb ----
				file_size_tf_eb.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						file_size_tf_ebKeyReleased(e);
					}
				});
				extend_base.add(file_size_tf_eb);
				file_size_tf_eb.setBounds(70, 20, 45, 20);

				//---- blk_size_tf_eb ----
				blk_size_tf_eb.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						blk_size_tf_ebKeyReleased(e);
					}
				});
				extend_base.add(blk_size_tf_eb);
				blk_size_tf_eb.setBounds(70, 40, 45, 20);

				//---- x_tf_eb ----
				x_tf_eb.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						x_tf_ebKeyReleased(e);
					}
				});
				extend_base.add(x_tf_eb);
				x_tf_eb.setBounds(70, 60, 45, 20);

				//---- ext_size_tf_eb ----
				ext_size_tf_eb.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						ext_size_tf_ebKeyReleased(e);
					}
				});
				extend_base.add(ext_size_tf_eb);
				ext_size_tf_eb.setBounds(70, 80, 45, 20);

				//---- file_size_cb_eb ----
				file_size_cb_eb.setModel(new DefaultComboBoxModel<>(new String[] {
					"bytes",
					"KB",
					"MB",
					"GB"
				}));
				file_size_cb_eb.addItemListener(e -> file_size_cb_ebItemStateChanged(e));
				extend_base.add(file_size_cb_eb);
				file_size_cb_eb.setBounds(115, 20, 52, 20);

				//---- blk_size_cb_eb ----
				blk_size_cb_eb.setModel(new DefaultComboBoxModel<>(new String[] {
					"bytes",
					"KB",
					"MB",
					"GB"
				}));
				blk_size_cb_eb.addItemListener(e -> blk_size_cb_ebItemStateChanged(e));
				extend_base.add(blk_size_cb_eb);
				blk_size_cb_eb.setBounds(115, 40, 52, 20);

				//---- x_cb_eb ----
				x_cb_eb.setModel(new DefaultComboBoxModel<>(new String[] {
					"bytes",
					"KB",
					"MB",
					"GB"
				}));
				x_cb_eb.addItemListener(e -> x_cb_ebItemStateChanged(e));
				extend_base.add(x_cb_eb);
				x_cb_eb.setBounds(115, 60, 52, 20);

				{ // compute preferred size
					Dimension preferredSize = new Dimension();
					for(int i = 0; i < extend_base.getComponentCount(); i++) {
						Rectangle bounds = extend_base.getComponent(i).getBounds();
						preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
						preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
					}
					Insets insets = extend_base.getInsets();
					preferredSize.width += insets.right;
					preferredSize.height += insets.bottom;
					extend_base.setMinimumSize(preferredSize);
					extend_base.setPreferredSize(preferredSize);
				}
			}
			contigousAllo.add(extend_base);
			extend_base.setBounds(175, 15, 175, 145);

			{ // compute preferred size
				Dimension preferredSize = new Dimension();
				for(int i = 0; i < contigousAllo.getComponentCount(); i++) {
					Rectangle bounds = contigousAllo.getComponent(i).getBounds();
					preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
					preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
				}
				Insets insets = contigousAllo.getInsets();
				preferredSize.width += insets.right;
				preferredSize.height += insets.bottom;
				contigousAllo.setMinimumSize(preferredSize);
				contigousAllo.setPreferredSize(preferredSize);
			}
		}
		contentPane.add(contigousAllo);
		contigousAllo.setBounds(0, 0, 355, 165);

		//======== linkedlistAllo ========
		{
			linkedlistAllo.setBorder(new TitledBorder("Linked List Allocation"));
			linkedlistAllo.setLayout(null);

			//======== block_offset_linked ========
			{
				block_offset_linked.setBorder(new TitledBorder("Linked List Allocation"));
				block_offset_linked.setLayout(null);

				//---- file_size_lb_bo_linked ----
				file_size_lb_bo_linked.setText("File size");
				block_offset_linked.add(file_size_lb_bo_linked);
				file_size_lb_bo_linked.setBounds(10, 20, file_size_lb_bo_linked.getPreferredSize().width, 20);

				//---- blk_size_lb_bo_linked ----
				blk_size_lb_bo_linked.setText("Block size");
				block_offset_linked.add(blk_size_lb_bo_linked);
				blk_size_lb_bo_linked.setBounds(10, 40, blk_size_lb_bo_linked.getPreferredSize().width, 20);

				//---- x_lb_bo_linked ----
				x_lb_bo_linked.setText("x");
				block_offset_linked.add(x_lb_bo_linked);
				x_lb_bo_linked.setBounds(10, 60, x_lb_bo_linked.getPreferredSize().width, 20);

				//---- pointer_size_lb_bo_linked ----
				pointer_size_lb_bo_linked.setText("Pointer size");
				block_offset_linked.add(pointer_size_lb_bo_linked);
				pointer_size_lb_bo_linked.setBounds(10, 80, pointer_size_lb_bo_linked.getPreferredSize().width, 20);

				//---- result_bo_linked ----
				result_bo_linked.setHorizontalAlignment(SwingConstants.CENTER);
				result_bo_linked.setText("Result");
				block_offset_linked.add(result_bo_linked);
				result_bo_linked.setBounds(20, 105, 120, 25);

				//---- file_size_tf_bo_linked ----
				file_size_tf_bo_linked.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						file_size_tf_bo_linkedKeyReleased(e);
					}
				});
				block_offset_linked.add(file_size_tf_bo_linked);
				file_size_tf_bo_linked.setBounds(67, 20, 45, file_size_tf_bo_linked.getPreferredSize().height);

				//---- blk_size_tf_bo_linked ----
				blk_size_tf_bo_linked.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						blk_size_tf_bo_linkedKeyReleased(e);
					}
				});
				block_offset_linked.add(blk_size_tf_bo_linked);
				blk_size_tf_bo_linked.setBounds(67, 40, 45, blk_size_tf_bo_linked.getPreferredSize().height);

				//---- x_tf_bo_linked ----
				x_tf_bo_linked.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						x_tf_bo_linkedKeyReleased(e);
					}
				});
				block_offset_linked.add(x_tf_bo_linked);
				x_tf_bo_linked.setBounds(67, 60, 45, x_tf_bo_linked.getPreferredSize().height);

				//---- pointer_size_tf_bo_linked ----
				pointer_size_tf_bo_linked.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						pointer_size_tf_bo_linkedKeyReleased(e);
					}
				});
				block_offset_linked.add(pointer_size_tf_bo_linked);
				pointer_size_tf_bo_linked.setBounds(67, 80, 45, pointer_size_tf_bo_linked.getPreferredSize().height);

				//---- file_size_cb_bo_linked ----
				file_size_cb_bo_linked.setModel(new DefaultComboBoxModel<>(new String[] {
					"bytes",
					"KB",
					"MB",
					"GB"
				}));
				file_size_cb_bo_linked.addItemListener(e -> file_size_cb_bo_linkedItemStateChanged(e));
				block_offset_linked.add(file_size_cb_bo_linked);
				file_size_cb_bo_linked.setBounds(new Rectangle(new Point(112, 20), file_size_cb_bo_linked.getPreferredSize()));

				//---- blk_size_cb_bo_linked ----
				blk_size_cb_bo_linked.setModel(new DefaultComboBoxModel<>(new String[] {
					"bytes",
					"KB",
					"MB",
					"GB"
				}));
				blk_size_cb_bo_linked.addItemListener(e -> blk_size_cb_bo_linkedItemStateChanged(e));
				block_offset_linked.add(blk_size_cb_bo_linked);
				blk_size_cb_bo_linked.setBounds(112, 40, 52, 20);

				//---- x_cb_bo_linked ----
				x_cb_bo_linked.setModel(new DefaultComboBoxModel<>(new String[] {
					"bytes",
					"KB",
					"MB",
					"GB"
				}));
				x_cb_bo_linked.addItemListener(e -> x_cb_bo_linkedItemStateChanged(e));
				block_offset_linked.add(x_cb_bo_linked);
				x_cb_bo_linked.setBounds(112, 60, 52, 20);

				//---- pointer_size_cb_bo_linked ----
				pointer_size_cb_bo_linked.setModel(new DefaultComboBoxModel<>(new String[] {
					"bytes",
					"KB",
					"MB",
					"GB"
				}));
				pointer_size_cb_bo_linked.addItemListener(e -> pointer_size_cb_bo_linkedItemStateChanged(e));
				block_offset_linked.add(pointer_size_cb_bo_linked);
				pointer_size_cb_bo_linked.setBounds(112, 80, 52, 20);

				{ // compute preferred size
					Dimension preferredSize = new Dimension();
					for(int i = 0; i < block_offset_linked.getComponentCount(); i++) {
						Rectangle bounds = block_offset_linked.getComponent(i).getBounds();
						preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
						preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
					}
					Insets insets = block_offset_linked.getInsets();
					preferredSize.width += insets.right;
					preferredSize.height += insets.bottom;
					block_offset_linked.setMinimumSize(preferredSize);
					block_offset_linked.setPreferredSize(preferredSize);
				}
			}
			linkedlistAllo.add(block_offset_linked);
			block_offset_linked.setBounds(5, 15, 170, 135);

			//======== linkedindex ========
			{
				linkedindex.setBorder(new TitledBorder("Linked index block Allocation"));
				linkedindex.setLayout(null);

				//---- file_size_lb_li_linked ----
				file_size_lb_li_linked.setText("File size");
				linkedindex.add(file_size_lb_li_linked);
				file_size_lb_li_linked.setBounds(10, 20, 37, 20);

				//---- blk_size_lb_li_linked ----
				blk_size_lb_li_linked.setText("Block size");
				linkedindex.add(blk_size_lb_li_linked);
				blk_size_lb_li_linked.setBounds(10, 40, 45, 20);

				//---- x_lb_li_linked ----
				x_lb_li_linked.setText("x");
				linkedindex.add(x_lb_li_linked);
				x_lb_li_linked.setBounds(10, 60, x_lb_li_linked.getPreferredSize().width, 20);

				//---- pointer_size_lb_li_linked ----
				pointer_size_lb_li_linked.setText("Pointer size");
				linkedindex.add(pointer_size_lb_li_linked);
				pointer_size_lb_li_linked.setBounds(10, 80, pointer_size_lb_li_linked.getPreferredSize().width, 20);

				//---- result_li_linked ----
				result_li_linked.setHorizontalAlignment(SwingConstants.CENTER);
				result_li_linked.setText("Result");
				linkedindex.add(result_li_linked);
				result_li_linked.setBounds(20, 105, 135, 25);

				//---- file_size_tf_li_linked ----
				file_size_tf_li_linked.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						file_size_tf_li_linkedKeyReleased(e);
					}
				});
				linkedindex.add(file_size_tf_li_linked);
				file_size_tf_li_linked.setBounds(70, 20, 45, 20);

				//---- blk_size_tf_li_linked ----
				blk_size_tf_li_linked.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						blk_size_tf_li_linkedKeyReleased(e);
					}
				});
				linkedindex.add(blk_size_tf_li_linked);
				blk_size_tf_li_linked.setBounds(70, 40, 45, 20);

				//---- x_tf_li_linked ----
				x_tf_li_linked.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						x_tf_li_linkedKeyReleased(e);
					}
				});
				linkedindex.add(x_tf_li_linked);
				x_tf_li_linked.setBounds(70, 60, 45, 20);

				//---- pointer_size_tf_li_linked ----
				pointer_size_tf_li_linked.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						pointer_size_tf_li_linkedKeyReleased(e);
					}
				});
				linkedindex.add(pointer_size_tf_li_linked);
				pointer_size_tf_li_linked.setBounds(70, 80, 45, 20);

				//---- file_size_cb_li_linked ----
				file_size_cb_li_linked.setModel(new DefaultComboBoxModel<>(new String[] {
					"bytes",
					"KB",
					"MB",
					"GB"
				}));
				file_size_cb_li_linked.addItemListener(e -> file_size_cb_li_linkedItemStateChanged(e));
				linkedindex.add(file_size_cb_li_linked);
				file_size_cb_li_linked.setBounds(115, 20, 52, 20);

				//---- blk_size_cb_li_linked ----
				blk_size_cb_li_linked.setModel(new DefaultComboBoxModel<>(new String[] {
					"bytes",
					"KB",
					"MB",
					"GB"
				}));
				blk_size_cb_li_linked.addItemListener(e -> file_size_cb_li_linkedItemStateChanged(e));
				linkedindex.add(blk_size_cb_li_linked);
				blk_size_cb_li_linked.setBounds(115, 40, 52, 20);

				//---- x_cb_li_linked ----
				x_cb_li_linked.setModel(new DefaultComboBoxModel<>(new String[] {
					"bytes",
					"KB",
					"MB",
					"GB"
				}));
				x_cb_li_linked.addItemListener(e -> file_size_cb_li_linkedItemStateChanged(e));
				linkedindex.add(x_cb_li_linked);
				x_cb_li_linked.setBounds(115, 60, 52, 20);

				//---- pointer_size_cb_li_linked ----
				pointer_size_cb_li_linked.setModel(new DefaultComboBoxModel<>(new String[] {
					"bytes",
					"KB",
					"MB",
					"GB"
				}));
				pointer_size_cb_li_linked.addItemListener(e -> file_size_cb_li_linkedItemStateChanged(e));
				linkedindex.add(pointer_size_cb_li_linked);
				pointer_size_cb_li_linked.setBounds(115, 80, 52, 20);

				{ // compute preferred size
					Dimension preferredSize = new Dimension();
					for(int i = 0; i < linkedindex.getComponentCount(); i++) {
						Rectangle bounds = linkedindex.getComponent(i).getBounds();
						preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
						preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
					}
					Insets insets = linkedindex.getInsets();
					preferredSize.width += insets.right;
					preferredSize.height += insets.bottom;
					linkedindex.setMinimumSize(preferredSize);
					linkedindex.setPreferredSize(preferredSize);
				}
			}
			linkedlistAllo.add(linkedindex);
			linkedindex.setBounds(175, 15, 175, 135);

			//======== twolevelindex ========
			{
				twolevelindex.setBorder(new TitledBorder("Two-level Index"));
				twolevelindex.setLayout(null);

				//---- file_size_lb_li_linked2lv ----
				file_size_lb_li_linked2lv.setText("File size");
				twolevelindex.add(file_size_lb_li_linked2lv);
				file_size_lb_li_linked2lv.setBounds(10, 20, 37, 20);

				//---- blk_size_lb_li_linked2lv ----
				blk_size_lb_li_linked2lv.setText("Block size");
				twolevelindex.add(blk_size_lb_li_linked2lv);
				blk_size_lb_li_linked2lv.setBounds(10, 40, 45, 20);

				//---- x_lb_li_linked2lv ----
				x_lb_li_linked2lv.setText("x");
				twolevelindex.add(x_lb_li_linked2lv);
				x_lb_li_linked2lv.setBounds(10, 60, x_lb_li_linked2lv.getPreferredSize().width, 20);

				//---- pointer_size_lb_li_linked2lv ----
				pointer_size_lb_li_linked2lv.setText("Pointer size");
				twolevelindex.add(pointer_size_lb_li_linked2lv);
				pointer_size_lb_li_linked2lv.setBounds(10, 80, pointer_size_lb_li_linked2lv.getPreferredSize().width, 20);

				//---- result_li_linked2lv ----
				result_li_linked2lv.setHorizontalAlignment(SwingConstants.CENTER);
				result_li_linked2lv.setText("Result");
				twolevelindex.add(result_li_linked2lv);
				result_li_linked2lv.setBounds(20, 125, 135, 25);

				//---- file_size_tf_li_linked2lv ----
				file_size_tf_li_linked2lv.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						file_size_tf_li_linked2lvKeyReleased(e);
					}
				});
				twolevelindex.add(file_size_tf_li_linked2lv);
				file_size_tf_li_linked2lv.setBounds(70, 20, 45, 20);

				//---- blk_size_tf_li_linked2lv ----
				blk_size_tf_li_linked2lv.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						blk_size_tf_li_linked2lvKeyReleased(e);
					}
				});
				twolevelindex.add(blk_size_tf_li_linked2lv);
				blk_size_tf_li_linked2lv.setBounds(70, 40, 45, 20);

				//---- x_tf_li_linked2lv ----
				x_tf_li_linked2lv.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						x_tf_li_linked2lvKeyReleased(e);
					}
				});
				twolevelindex.add(x_tf_li_linked2lv);
				x_tf_li_linked2lv.setBounds(70, 60, 45, 20);

				//---- pointer_size_tf_li_linked2lv ----
				pointer_size_tf_li_linked2lv.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						pointer_size_tf_li_linked2lvKeyReleased(e);
					}
				});
				twolevelindex.add(pointer_size_tf_li_linked2lv);
				pointer_size_tf_li_linked2lv.setBounds(70, 80, 45, 20);

				//---- file_size_cb_li_linked2lv ----
				file_size_cb_li_linked2lv.setModel(new DefaultComboBoxModel<>(new String[] {
					"bytes",
					"KB",
					"MB",
					"GB"
				}));
				file_size_cb_li_linked2lv.addItemListener(e -> file_size_cb_li_linked2lvItemStateChanged(e));
				twolevelindex.add(file_size_cb_li_linked2lv);
				file_size_cb_li_linked2lv.setBounds(115, 20, 52, 20);

				//---- blk_size_cb_li_linked2lv ----
				blk_size_cb_li_linked2lv.setModel(new DefaultComboBoxModel<>(new String[] {
					"bytes",
					"KB",
					"MB",
					"GB"
				}));
				blk_size_cb_li_linked2lv.addItemListener(e -> blk_size_cb_li_linked2lvItemStateChanged(e));
				twolevelindex.add(blk_size_cb_li_linked2lv);
				blk_size_cb_li_linked2lv.setBounds(115, 40, 52, 20);

				//---- x_cb_li_linked2lv ----
				x_cb_li_linked2lv.setModel(new DefaultComboBoxModel<>(new String[] {
					"bytes",
					"KB",
					"MB",
					"GB"
				}));
				x_cb_li_linked2lv.addItemListener(e -> x_cb_li_linked2lvItemStateChanged(e));
				twolevelindex.add(x_cb_li_linked2lv);
				x_cb_li_linked2lv.setBounds(115, 60, 52, 20);

				//---- pointer_size_cb_li_linked2lv ----
				pointer_size_cb_li_linked2lv.setModel(new DefaultComboBoxModel<>(new String[] {
					"bytes",
					"KB",
					"MB",
					"GB"
				}));
				pointer_size_cb_li_linked2lv.addItemListener(e -> pointer_size_cb_li_linked2lvItemStateChanged(e));
				twolevelindex.add(pointer_size_cb_li_linked2lv);
				pointer_size_cb_li_linked2lv.setBounds(115, 80, 52, 20);

				{ // compute preferred size
					Dimension preferredSize = new Dimension();
					for(int i = 0; i < twolevelindex.getComponentCount(); i++) {
						Rectangle bounds = twolevelindex.getComponent(i).getBounds();
						preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
						preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
					}
					Insets insets = twolevelindex.getInsets();
					preferredSize.width += insets.right;
					preferredSize.height += insets.bottom;
					twolevelindex.setMinimumSize(preferredSize);
					twolevelindex.setPreferredSize(preferredSize);
				}
			}
			linkedlistAllo.add(twolevelindex);
			twolevelindex.setBounds(0, 150, 175, 160);

			//======== twolevelindex2 ========
			{
				twolevelindex2.setBorder(new TitledBorder("Indexed Allocation"));
				twolevelindex2.setLayout(null);

				//---- file_size_lb_li_linked2lv2 ----
				file_size_lb_li_linked2lv2.setText("File size");
				twolevelindex2.add(file_size_lb_li_linked2lv2);
				file_size_lb_li_linked2lv2.setBounds(10, 20, 37, 20);

				//---- blk_size_lb_li_linked2lv2 ----
				blk_size_lb_li_linked2lv2.setText("Block size");
				twolevelindex2.add(blk_size_lb_li_linked2lv2);
				blk_size_lb_li_linked2lv2.setBounds(10, 40, 45, 20);

				//---- x_lb_li_linked2lv2 ----
				x_lb_li_linked2lv2.setText("x");
				twolevelindex2.add(x_lb_li_linked2lv2);
				x_lb_li_linked2lv2.setBounds(10, 60, x_lb_li_linked2lv2.getPreferredSize().width, 20);

				//---- pointer_size_lb_li_linked2lv2 ----
				pointer_size_lb_li_linked2lv2.setText("Pointer size");
				twolevelindex2.add(pointer_size_lb_li_linked2lv2);
				pointer_size_lb_li_linked2lv2.setBounds(10, 80, pointer_size_lb_li_linked2lv2.getPreferredSize().width, 20);

				//---- result_indexed ----
				result_indexed.setHorizontalAlignment(SwingConstants.CENTER);
				result_indexed.setText("Result");
				twolevelindex2.add(result_indexed);
				result_indexed.setBounds(20, 125, 135, 25);

				//---- file_size_tf_indexed ----
				file_size_tf_indexed.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						file_size_tf_indexedKeyReleased(e);
					}
				});
				twolevelindex2.add(file_size_tf_indexed);
				file_size_tf_indexed.setBounds(70, 20, 45, 20);

				//---- blk_size_tf_indexed ----
				blk_size_tf_indexed.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						file_size_tf_indexedKeyReleased(e);
					}
				});
				twolevelindex2.add(blk_size_tf_indexed);
				blk_size_tf_indexed.setBounds(70, 40, 45, 20);

				//---- x_tf_indexed ----
				x_tf_indexed.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						file_size_tf_indexedKeyReleased(e);
					}
				});
				twolevelindex2.add(x_tf_indexed);
				x_tf_indexed.setBounds(70, 60, 45, 20);

				//---- pointer_size_tf_indexed ----
				pointer_size_tf_indexed.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						file_size_tf_indexedKeyReleased(e);
					}
				});
				twolevelindex2.add(pointer_size_tf_indexed);
				pointer_size_tf_indexed.setBounds(70, 80, 45, 20);

				//---- file_size_cb_indexed ----
				file_size_cb_indexed.setModel(new DefaultComboBoxModel<>(new String[] {
					"bytes",
					"KB",
					"MB",
					"GB"
				}));
				file_size_cb_indexed.addItemListener(e -> file_size_cb_indexedItemStateChanged(e));
				twolevelindex2.add(file_size_cb_indexed);
				file_size_cb_indexed.setBounds(115, 20, 52, 20);

				//---- blk_size_cb_indexed ----
				blk_size_cb_indexed.setModel(new DefaultComboBoxModel<>(new String[] {
					"bytes",
					"KB",
					"MB",
					"GB"
				}));
				blk_size_cb_indexed.addItemListener(e -> file_size_cb_indexedItemStateChanged(e));
				twolevelindex2.add(blk_size_cb_indexed);
				blk_size_cb_indexed.setBounds(115, 40, 52, 20);

				//---- x_cb_indexed ----
				x_cb_indexed.setModel(new DefaultComboBoxModel<>(new String[] {
					"bytes",
					"KB",
					"MB",
					"GB"
				}));
				x_cb_indexed.addItemListener(e -> file_size_cb_indexedItemStateChanged(e));
				twolevelindex2.add(x_cb_indexed);
				x_cb_indexed.setBounds(115, 60, 52, 20);

				//---- pointer_size_cb_indexed ----
				pointer_size_cb_indexed.setModel(new DefaultComboBoxModel<>(new String[] {
					"bytes",
					"KB",
					"MB",
					"GB"
				}));
				pointer_size_cb_indexed.addItemListener(e -> file_size_cb_indexedItemStateChanged(e));
				twolevelindex2.add(pointer_size_cb_indexed);
				pointer_size_cb_indexed.setBounds(115, 80, 52, 20);

				//---- label7 ----
				label7.setText("Size max");
				twolevelindex2.add(label7);
				label7.setBounds(10, 100, label7.getPreferredSize().width, 20);

				//---- Size_max_tf_indexed ----
				Size_max_tf_indexed.setEditable(false);
				twolevelindex2.add(Size_max_tf_indexed);
				Size_max_tf_indexed.setBounds(70, 100, 97, Size_max_tf_indexed.getPreferredSize().height);

				{ // compute preferred size
					Dimension preferredSize = new Dimension();
					for(int i = 0; i < twolevelindex2.getComponentCount(); i++) {
						Rectangle bounds = twolevelindex2.getComponent(i).getBounds();
						preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
						preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
					}
					Insets insets = twolevelindex2.getInsets();
					preferredSize.width += insets.right;
					preferredSize.height += insets.bottom;
					twolevelindex2.setMinimumSize(preferredSize);
					twolevelindex2.setPreferredSize(preferredSize);
				}
			}
			linkedlistAllo.add(twolevelindex2);
			twolevelindex2.setBounds(175, 150, 175, 160);

			{ // compute preferred size
				Dimension preferredSize = new Dimension();
				for(int i = 0; i < linkedlistAllo.getComponentCount(); i++) {
					Rectangle bounds = linkedlistAllo.getComponent(i).getBounds();
					preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
					preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
				}
				Insets insets = linkedlistAllo.getInsets();
				preferredSize.width += insets.right;
				preferredSize.height += insets.bottom;
				linkedlistAllo.setMinimumSize(preferredSize);
				linkedlistAllo.setPreferredSize(preferredSize);
			}
		}
		contentPane.add(linkedlistAllo);
		linkedlistAllo.setBounds(0, 170, 355, 315);

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
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - dung Tran
	private JPanel contigousAllo;
	private JPanel block_offset;
	private JLabel file_size_lb_bo;
	private JLabel blk_size_lb_bo;
	private JLabel x_lb_bo;
	private JLabel max_blk_lb_bo;
	private JLabel result_bo;
	private JTextField file_size_tf_bo;
	private JTextField blk_size_tf_bo;
	private JTextField x_tf_bo;
	private JTextField max_blk_tf_bo;
	private JComboBox<String> file_size_cb_bo;
	private JComboBox<String> blk_size_cb_bo;
	private JComboBox<String> x_cb_bo;
	private JPanel extend_base;
	private JLabel file_size_lb_eb;
	private JLabel blk_size_lb_eb;
	private JLabel x_lb_eb;
	private JLabel ext_size_lb_eb;
	private JLabel result_eb;
	private JTextField file_size_tf_eb;
	private JTextField blk_size_tf_eb;
	private JTextField x_tf_eb;
	private JTextField ext_size_tf_eb;
	private JComboBox<String> file_size_cb_eb;
	private JComboBox<String> blk_size_cb_eb;
	private JComboBox<String> x_cb_eb;
	private JPanel linkedlistAllo;
	private JPanel block_offset_linked;
	private JLabel file_size_lb_bo_linked;
	private JLabel blk_size_lb_bo_linked;
	private JLabel x_lb_bo_linked;
	private JLabel pointer_size_lb_bo_linked;
	private JLabel result_bo_linked;
	private JTextField file_size_tf_bo_linked;
	private JTextField blk_size_tf_bo_linked;
	private JTextField x_tf_bo_linked;
	private JTextField pointer_size_tf_bo_linked;
	private JComboBox<String> file_size_cb_bo_linked;
	private JComboBox<String> blk_size_cb_bo_linked;
	private JComboBox<String> x_cb_bo_linked;
	private JComboBox<String> pointer_size_cb_bo_linked;
	private JPanel linkedindex;
	private JLabel file_size_lb_li_linked;
	private JLabel blk_size_lb_li_linked;
	private JLabel x_lb_li_linked;
	private JLabel pointer_size_lb_li_linked;
	private JLabel result_li_linked;
	private JTextField file_size_tf_li_linked;
	private JTextField blk_size_tf_li_linked;
	private JTextField x_tf_li_linked;
	private JTextField pointer_size_tf_li_linked;
	private JComboBox<String> file_size_cb_li_linked;
	private JComboBox<String> blk_size_cb_li_linked;
	private JComboBox<String> x_cb_li_linked;
	private JComboBox<String> pointer_size_cb_li_linked;
	private JPanel twolevelindex;
	private JLabel file_size_lb_li_linked2lv;
	private JLabel blk_size_lb_li_linked2lv;
	private JLabel x_lb_li_linked2lv;
	private JLabel pointer_size_lb_li_linked2lv;
	private JLabel result_li_linked2lv;
	private JTextField file_size_tf_li_linked2lv;
	private JTextField blk_size_tf_li_linked2lv;
	private JTextField x_tf_li_linked2lv;
	private JTextField pointer_size_tf_li_linked2lv;
	private JComboBox<String> file_size_cb_li_linked2lv;
	private JComboBox<String> blk_size_cb_li_linked2lv;
	private JComboBox<String> x_cb_li_linked2lv;
	private JComboBox<String> pointer_size_cb_li_linked2lv;
	private JPanel twolevelindex2;
	private JLabel file_size_lb_li_linked2lv2;
	private JLabel blk_size_lb_li_linked2lv2;
	private JLabel x_lb_li_linked2lv2;
	private JLabel pointer_size_lb_li_linked2lv2;
	private JLabel result_indexed;
	private JTextField file_size_tf_indexed;
	private JTextField blk_size_tf_indexed;
	private JTextField x_tf_indexed;
	private JTextField pointer_size_tf_indexed;
	private JComboBox<String> file_size_cb_indexed;
	private JComboBox<String> blk_size_cb_indexed;
	private JComboBox<String> x_cb_indexed;
	private JComboBox<String> pointer_size_cb_indexed;
	private JLabel label7;
	private JTextField Size_max_tf_indexed;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
