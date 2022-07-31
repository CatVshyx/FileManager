package Manager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.util.Arrays;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import CustomElements.CustomButton;


enum Answers{
	CHOOSE_FILE, CHOOSE_DIR;
}
public class SelectFrame extends JFrame implements ListSelectionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1425865511185078892L;
	private FileSelector selector = new FileSelector();
	private JLabel labelText;
	private JList<String> list;
	private JScrollPane spane;
	private JComboBox<File> rootChooser;
	SelectFrame(Answers a, Visual v){
		super("Select");


		selector.setStartPath( "C://");

		labelText = new JLabel("hello");
		labelText.setPreferredSize(new Dimension(100,30));
		CustomButton bConfirm = new CustomButton("Confirm");
		CustomButton bForward = new CustomButton("Forward");
		bConfirm.setPreferredSize(new Dimension(100,40));
		rootChooser = new JComboBox<>(FileSelector.getListRoots());
		rootChooser.addItemListener((e) -> {
			selector.setStartPath(((File)rootChooser.getSelectedItem()).getPath());
			list.setListData(selector.getList());
		});
		list = new JList<>(selector.getList());
		list.addListSelectionListener(this);
		
		list.setPreferredSize(new Dimension(200,100));
		CustomButton bBack = new CustomButton("Back");
		bBack.setPreferredSize(new Dimension(200,40));
		
		bBack.addActionListener(e -> {
			try {
				selector.back();
				list.setListData(selector.getList());
				System.out.println(Arrays.toString( selector.getList())+" path "+ selector.getPath());
				labelText.setText(new File(selector.getPath()).getName());
			} catch(NullPointerException e1) {
				selector.setPath(selector.getStack().peek());
				list.setListData(selector.getList());
				System.out.println(Arrays.toString( selector.getList())+" path "+ selector.getPath());
				labelText.setText(new File(selector.getPath()).getName());
			}
			catch (ArrayIndexOutOfBoundsException e1) {
			}
		});
		bForward.addActionListener(e -> {
			try {
				selector.forward();
				list.setListData(selector.getList());
				labelText.setText(new File(selector.getPath()).getName());
			} catch (ArrayIndexOutOfBoundsException e1) {
			}
		});
		bConfirm.addActionListener(e -> {
			File f = new File(labelText.getText());
			if (f.isFile() && a == Answers.CHOOSE_FILE) {
				System.out.println("Is file: "+f.isFile());
				v.setText(f.getPath());
				this.dispose();
			}
			else if (f.isDirectory() && a == Answers.CHOOSE_DIR) {
				System.out.println("Is dir: "+f.isDirectory());
				FileManipulator.moveFile(v.getText(), f.getPath().toString());
				this.dispose();
			}
			else { JOptionPane.showMessageDialog(null, "Choose the right file or dir"); }
		});
		bForward.setPreferredSize(new Dimension(200,40));
		JPanel workPanel = new JPanel();
		this.setLayout(new BorderLayout());
		
		spane = new JScrollPane(list);
		spane.setVerticalScrollBar(new JScrollBar(1));
		workPanel.add(rootChooser);
		workPanel.add(bBack);
		workPanel.add(bForward);
		
		workPanel.add(labelText);
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.add("Center",spane);
		mainPanel.setBackground(new Color(104,104,104));
		mainPanel.add("North", workPanel);
		mainPanel.add("South",new JPanel().add(bConfirm));
		this.setContentPane(mainPanel);

		this.setSize(600,600);
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
	}
	public void startProgram() {
		this.setVisible(true);
	}
	@Override
	public void valueChanged(ListSelectionEvent e) {
		String s = list.getSelectedValue();
		File[] myFiles = selector.getListFiles();
		if (myFiles == null) {
			list.setListData(new String[] {"null"});
			return;
		}
		for (File el : myFiles) {
			if (el.getName().equals(s)) {
				if (el.isFile()) {
					labelText.setText(el.getPath());
					return;
				}
				selector.setPath(el.getPath());
				list.setListData(selector.getList());
				labelText.setText(el.getPath());
				break;
			}
		}
		
		
		
	}
}