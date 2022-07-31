package CustomElements;


import javax.swing.JProgressBar;
import java.awt.Color;

public class CustomProgressBar extends JProgressBar{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Color colorBorder = new Color(64,64,64);
	Color color;
	int radius=100;
	public CustomProgressBar(int min , int max, int value) {
		super(JProgressBar.HORIZONTAL);
		super.setMinimum(min);
		super.setMaximum(max);
		super.setValue(value);
		super.setBackground(Color.GREEN);
	}
	public void setToolTipText(int total, float available) {
		super.setToolTipText("Space free: " + available+" \n Space total: " + total);
	}
}
