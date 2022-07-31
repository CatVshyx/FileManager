package Manager;
import java.awt.*;
import javax.swing.*;

import CustomElements.CustomButton;
import CustomElements.CustomJTextField;
import CustomElements.CustomProgressBar;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;


public class Visual extends JFrame  {
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private JLabel label,labelName,labelNothing;
		private JPanel  mainPanel, TODOPanel,headerPanel,sidePanel;
		private CustomJTextField txField;
		private CustomButton bRead, bInfo , bSel , bMove, bDelete;
		static Color sideColor = new Color(80,80,80);
		static Color headerColor = new Color(80,80,80);
		static Color mainColor = new Color(48,48,48);
		public Visual(){
			this.setLayout(null);
			
			
			mainPanel = new JPanel(null);
			setHeader();
			setTODOPanel();
			setSidebar();
			mainPanel.add(sidePanel);
			mainPanel.add(headerPanel);
			mainPanel.add(TODOPanel);
			mainPanel.setBackground(mainColor);
			
			this.setContentPane(mainPanel);
			
			this.setSize(625,500);
			this.setResizable(false);
			this.setLocationRelativeTo(null);
			
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		}
		public void startProgram() {
			this.setVisible(true); 
		}
		private void setSidebar() {
			sidePanel = new JPanel(null);
			File[] roots = File.listRoots();
			sidePanel.setLayout(new GridLayout(roots.length%2,1,5,5));
			for (File root : roots) {
				JPanel p = new JPanel(new FlowLayout());
				
				p.add(new JLabel(root.getAbsolutePath()));
				
				int max = (int)  (root.getTotalSpace()/ (1048576*1024));
				int value = (int)  (root.getUsableSpace()/ (1048576*1024));
				float freeSpace = (float) root.getFreeSpace()/(1048576*1024);
				freeSpace = Math.round(freeSpace*100.0)/100.0f;
				
				CustomProgressBar pg = new CustomProgressBar(0, max, value);
				pg.setToolTipText(max,freeSpace);
				
				p.add(pg);
				p.add( new JLabel("Space:" + freeSpace + " GB"));
				sidePanel.add(p);
			}
			sidePanel.setBackground(sideColor);
			sidePanel.setBounds(0,305,280,100);
		}
		private void setHeader() {
			label = new JLabel("File Name");
			label.setForeground(Color.WHITE);
			txField = new CustomJTextField(20);
			bSel = new CustomButton("Select a file");
	
			headerPanel = new JPanel (new FlowLayout());
			headerPanel.setBackground(headerColor);
			headerPanel.setBounds(0,0,600,60);
			
			txField.setPreferredSize(new Dimension(250,20));
			bSel.setPreferredSize(new Dimension(200,20));

			
			bSel.addActionListener(e -> { this.startSelectFrame(); });

			headerPanel.add(label);
			headerPanel.add(txField);
			headerPanel.add(bSel);  
		}
		public String getText() {
			return txField.getText();
		}
		public void setText(String text) {
			txField.setText(text);
		}
		private void startSelectFrame() {
			 SelectFrame	sFrame = new SelectFrame(Answers.CHOOSE_FILE,this);
			 sFrame.startProgram();
		}
		private void setTODOPanel() {
			labelName = new JLabel("Things with file:");
			labelName.setForeground(Color.WHITE);
			labelNothing = new JLabel();
			bRead = new CustomButton("Read");
			bInfo = new CustomButton("Info");
			bMove = new CustomButton("Move File");
			bDelete = new CustomButton("Delete");
			
			TODOPanel = new JPanel(new GridLayout(3,2,5,5));
			TODOPanel.setBounds(0,50,350,250);
			TODOPanel.setBackground(new Color(72, 57, 71));
			
			labelName.setBounds(100,250,40,40);
			labelName.setBackground(Color.WHITE);
			bRead.setPreferredSize(new Dimension(150,40));
			bInfo.setPreferredSize(new Dimension(150,40));
			bMove.setPreferredSize(new Dimension(150,40));
			bDelete.setPreferredSize(new Dimension(150,40));
			
			
			bInfo.addActionListener( e -> { 
				File f = new File(txField.getText());
				if(f.exists()) {
					StringBuilder info = new StringBuilder();
					info.append("FILE INFORMATION \n");
					info.append("name: " +  f.getName()+" \n");
					String length = (f.length() < 1000000) ? (float)  (f.length()/1024) +" KB"  :  (float) (f.length()/1000000) + " MB";
					info.append("size: " + length +" \n");
					Calendar c = Calendar.getInstance();
					c.setTime(new Date(f.lastModified()));
					info.append("last modified: "+ c.get(Calendar.DATE)+"."+ c.get(Calendar.MONTH)+"."+c.get(Calendar.YEAR)+" \n");
					JOptionPane.showMessageDialog(null ,info);
					return;
				}
				JOptionPane.showMessageDialog(null, "choose file");
			});
			bRead.addActionListener(e -> {
				try {
					FileManipulator.readFile(txField.getText());
				} catch (IOException e1) {
					e1.printStackTrace();
			}});
			
			bMove.addActionListener(e -> {
				if( txField.getText().isEmpty() || !new File(txField.getText()).exists() ) {JOptionPane.showMessageDialog(null, "Choose the right file or dir");
					return;
				}
				this.startSelectFrame();
			});
			bDelete.addActionListener(e -> { new File(txField.getText()).delete();});
			
			
			TODOPanel.add(labelName); 
			TODOPanel.add(labelNothing);
			
			TODOPanel.add(bRead);  
			TODOPanel.add(bInfo); 
			TODOPanel.add(bMove);  
			TODOPanel.add(bDelete); 
			
			
		}
		
}

