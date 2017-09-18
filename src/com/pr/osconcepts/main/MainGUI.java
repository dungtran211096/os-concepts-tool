/*
 * Created by JFormDesigner on Thu Mar 17 21:01:25 ICT 2016
 */

package com.pr.osconcepts.main;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.pr.osconcept.bankersalgorithm.BankerGUI;
import com.pr.osconcepts.deadlockdetection.DeadlockDectGUI;
import com.pr.osconcepts.memorymanagement.MemManagementGUI;
import com.pr.osconcepts.pagereplacement.PageReplacementGUI;
import com.pr.osconcepts.requestres.RequestResGUI;
import com.pr.osconcepts.scheduling.SchedulingAlgGUI;

/**
 * @author Tráº§n Anh
 */
public class MainGUI extends JFrame {
	private int count = 0;
	private ArrayList<Boolean> unlock;
	private int num_of_fn;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public MainGUI() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		unlock = new ArrayList<Boolean>();
		this.num_of_fn = 6;
		for (int i = 0; i<num_of_fn;i++) unlock.add(false);
		initComponents();
		this.Lock();
		this.Unlock();
		this.MoreBTN2.setVisible(false);
		this.MoreBTN3.setVisible(false);
		this.MoreBTN2.setBorderPainted(false);
		this.MoreBTN.setBorderPainted(false);
	}
	private void Lock(){
		unlock = new ArrayList<Boolean>();
		this.num_of_fn = 6;
		for (int i = 0; i<num_of_fn;i++) unlock.add(false);
	}
	private void Unlock(){
		String code="Unlock All";
		//TODO Lock or Unlock
		//code = this.getCode();
		
		this.StatusLB.setText("Status: " + code);
		switch (code){
			case "Unlock All":{
				for (int i = 0; i<unlock.size();i++){
					unlock.set(i, true);
				}
				break;
			}
			case "Get code error!":{
				JOptionPane.showMessageDialog(null,"Get unlock code failed!\nCheck your Internet connection");
				break;
			}
			case "Lock All":{
				JOptionPane.showMessageDialog(null,"All Locked\nClick Help for more information");
				break;
			}
			default:{
				String [] fn = code.split(" ");
				for (int i = 0; i<fn.length;i++){
					if (Integer.parseInt(fn[i])<this.num_of_fn)
						unlock.set(Integer.parseInt(fn[i]), true);
				}
				break;
			}
		}
	}
	public static void main(String[] args){
		
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception ex){}
		new MainGUI().setVisible(true);
	}

	private void SchedulingBTNActionPerformed(ActionEvent e) {//func 0
		if (unlock.get(0)) new SchedulingAlgGUI().setVisible(true);
		else JOptionPane.showMessageDialog(null,"Locked Function!\nClick Help for more Information");

	}
	private void BankerBTNActionPerformed(ActionEvent e) {//func 1
		if (unlock.get(1)) new BankerGUI().setVisible(true);
		else JOptionPane.showMessageDialog(null,"Locked Function!\nClick Help for more Information");
	}
	private void ResReqBTNActionPerformed(ActionEvent e) {//func 2
		if (unlock.get(2)) new RequestResGUI().setVisible(true);
		else JOptionPane.showMessageDialog(null,"Locked Function!\nClick Help for more Information");
	}

	private void DeadlockDectBTNActionPerformed(ActionEvent e) {//func 3
		if (unlock.get(3))new DeadlockDectGUI().setVisible(true);
		else JOptionPane.showMessageDialog(null,"Locked Function!\nClick Help for more Information");
		
	}
	private void MemManagementBTNActionPerformed(ActionEvent e) {//func 4
		if (unlock.get(4)) new MemManagementGUI().setVisible(true);
		else JOptionPane.showMessageDialog(null,"Locked Function!\nClick Help for more Information");
	}
	private void PageReplBTNActionPerformed(ActionEvent e) {//func 5
		if (unlock.get(5)) new PageReplacementGUI().setVisible(true);
		else JOptionPane.showMessageDialog(null,"Locked Function!\nClick Help for more Information");
	}
	private void MoreBTNActionPerformed(ActionEvent e) {
		this.RandBTN();
	}


	public static void openWebpage(URI uri) {
	    Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
	    if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
	        try {
	            desktop.browse(uri);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}

	public static void openWebpage(URL url) {
	    try {
	        openWebpage(url.toURI());
	    } catch (URISyntaxException e) {
	        e.printStackTrace();
	    }
	}
	private void ReportBugMenuItemActionPerformed(ActionEvent e) {
		try{
			MainGUI.openWebpage(new URL("https://www.facebook.com/messages/atrgb"));
		}catch (MalformedURLException e1){
			e1.printStackTrace();
		}
	}

	private void AboutMenuItemActionPerformed(ActionEvent e) {
		try{
			MainGUI.openWebpage(new URL("https://www.facebook.com/atrgb"));
		}catch (MalformedURLException e1){
			e1.printStackTrace();
		}
	}

	public boolean isNumeric(String s) {
	    return s.matches("[-+]?\\d*\\.?\\d+");
	}
	public String getCode(){
		Document doc;
		String title="";
		try{
			doc = Jsoup.connect("https://www.facebook.com/atrgb/posts/452134788329710").get();
			title = doc.title();  
		}catch (IOException e){//No Internet
			return "Get code error!";
		}
		String code="";
		String[] tmp = title.split(" ");
		for (int i = 3; i<tmp.length-2; i++){
			code = code + tmp[i] + " ";
		}
		if (code.length()>=1) code = code.substring(0, code.length()-1);
		if (this.isNumeric(tmp[3]) || code.compareTo("Unlock All")==0) return code;
		else return "Lock All";
	}

	private void MoreBTN2ActionPerformed(ActionEvent e) {
		this.RandBTN();
	}



	private void RandBTN(){
		int rd = new Random().nextInt(3)+1;
		String [] troll = {"Click","Vào","Đây","Làm","Gì","Chưa","Có","Gì","Đâu"};
		switch (rd){
			case 1:{
				this.MoreBTN.setVisible(true);
				this.MoreBTN2.setVisible(false);
				this.MoreBTN3.setVisible(false);
				this.MoreBTN.setText(troll[count]);
				this.MoreBTN.setBackground(new Color((int)(Math.random() * 0x1000000)));
				this.MoreBTN.setOpaque(true);
				this.MoreBTN.setBorderPainted(false);
				break;
			}
			case 2:{
				this.MoreBTN.setVisible(false);
				this.MoreBTN2.setVisible(true);
				this.MoreBTN3.setVisible(false);
				this.MoreBTN2.setText(troll[count]);
				this.MoreBTN2.setBackground(new Color((int)(Math.random() * 0x1000000)));
				this.MoreBTN2.setOpaque(true);
				this.MoreBTN2.setBorderPainted(false);
				break;
			}
			case 3:{
				this.MoreBTN.setVisible(false);
				this.MoreBTN2.setVisible(false);
				this.MoreBTN3.setVisible(true);
				this.MoreBTN3.setText(troll[count]);
				this.MoreBTN3.setBackground(new Color((int)(Math.random() * 0x1000000)));
				this.MoreBTN3.setOpaque(true);
				this.MoreBTN3.setBorderPainted(false);
				break;
			}
		}
		count++;
		if (count == troll.length) count = 0;
	}
	private void MoreBTN3ActionPerformed(ActionEvent e) {
		this.RandBTN();
	}



	private void initComponents() {
		ImageIcon icon = new ImageIcon(this.getClass().getResource("/icon.png"));
		Image image = icon.getImage();
		setIconImage(image);
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Tráº§n Anh
		menuBar = new JMenuBar();
		menu1 = new JMenu();
		HelpMenu = new JMenu();
		ReportBugMenuItem = new JMenuItem();
		AboutMenuItem = new JMenuItem();
		SchedulingBTN = new JButton();
		BankerBTN = new JButton();
		ResReqBTN = new JButton();
		MoreBTN = new JButton();
		DeadlockDectBTN = new JButton();
		MoreBTN2 = new JButton();
		MemManagementBTN = new JButton();
		PageReplBTN = new JButton();
		MoreBTN3 = new JButton();
		StatusLB = new JLabel();

		//======== this ========
		setTitle("Tr\u1ea7n Anh");
		setResizable(false);
		Container contentPane = getContentPane();
		contentPane.setLayout(null);

		//======== menuBar ========
		{

			//======== menu1 ========
			{
				menu1.setText("Operating System Concepts Tool                ");
			}
			menuBar.add(menu1);

			//======== HelpMenu ========
			{
				HelpMenu.setText("Help");

				//---- ReportBugMenuItem ----
				ReportBugMenuItem.setText("Report Problem");
				ReportBugMenuItem.addActionListener(e -> ReportBugMenuItemActionPerformed(e));
				HelpMenu.add(ReportBugMenuItem);

				//---- AboutMenuItem ----
				AboutMenuItem.setText("About");
				AboutMenuItem.addActionListener(e -> AboutMenuItemActionPerformed(e));
				HelpMenu.add(AboutMenuItem);
			}
			menuBar.add(HelpMenu);
		}
		setJMenuBar(menuBar);

		//---- SchedulingBTN ----
		SchedulingBTN.setText("<html>Scheduling<br />Algorithm</html>");
		SchedulingBTN.addActionListener(e -> SchedulingBTNActionPerformed(e));
		contentPane.add(SchedulingBTN);
		SchedulingBTN.setBounds(5, 5, 85, 85);

		//---- BankerBTN ----
		BankerBTN.setText("<html>Banker's<br />Algorithm</html>");
		BankerBTN.addActionListener(e -> BankerBTNActionPerformed(e));
		contentPane.add(BankerBTN);
		BankerBTN.setBounds(95, 5, 85, 85);

		//---- ResReqBTN ----
		ResReqBTN.setText("<html>Request<br />Resources</html>");
		ResReqBTN.addActionListener(e -> ResReqBTNActionPerformed(e));
		contentPane.add(ResReqBTN);
		ResReqBTN.setBounds(185, 5, 85, 85);

		//---- MoreBTN ----
		MoreBTN.setText("More...");
		MoreBTN.addActionListener(e -> MoreBTNActionPerformed(e));
		contentPane.add(MoreBTN);
		MoreBTN.setBounds(5, 185, 85, 85);

		//---- DeadlockDectBTN ----
		DeadlockDectBTN.setText("<html>Deadlock<br />Detection</html>");
		DeadlockDectBTN.setHorizontalTextPosition(SwingConstants.CENTER);
		DeadlockDectBTN.addActionListener(e -> DeadlockDectBTNActionPerformed(e));
		contentPane.add(DeadlockDectBTN);
		DeadlockDectBTN.setBounds(5, 95, 85, 85);

		//---- MoreBTN2 ----
		MoreBTN2.setText("More...");
		MoreBTN2.addActionListener(e -> MoreBTN2ActionPerformed(e));
		contentPane.add(MoreBTN2);
		MoreBTN2.setBounds(95, 185, 85, 85);

		//---- MemManagementBTN ----
		MemManagementBTN.setText("<html>Memory<br />Management</html>");
		MemManagementBTN.addActionListener(e -> MemManagementBTNActionPerformed(e));
		contentPane.add(MemManagementBTN);
		MemManagementBTN.setBounds(95, 95, 85, 85);

		//---- PageReplBTN ----
		PageReplBTN.setText("<html>Page<br />Replacement</html>");
		PageReplBTN.addActionListener(e -> PageReplBTNActionPerformed(e));
		contentPane.add(PageReplBTN);
		PageReplBTN.setBounds(185, 95, 85, 85);

		//---- MoreBTN3 ----
		MoreBTN3.setText("More...");
		MoreBTN3.addActionListener(e -> MoreBTN3ActionPerformed(e));
		contentPane.add(MoreBTN3);
		MoreBTN3.setBounds(185, 185, 85, 85);

		//---- StatusLB ----
		StatusLB.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(StatusLB);
		StatusLB.setBounds(5, 270, 265, 20);

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
		setSize(280, 340);
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - Tráº§n Anh
	private JMenuBar menuBar;
	private JMenu menu1;
	private JMenu HelpMenu;
	private JMenuItem ReportBugMenuItem;
	private JMenuItem AboutMenuItem;
	private JButton SchedulingBTN;
	private JButton BankerBTN;
	private JButton ResReqBTN;
	private JButton MoreBTN;
	private JButton DeadlockDectBTN;
	private JButton MoreBTN2;
	private JButton MemManagementBTN;
	private JButton PageReplBTN;
	private JButton MoreBTN3;
	private JLabel StatusLB;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
