package CustomElements;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class CustomButton extends JButton{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean over;
	private Color color;
	private Color colorOver;
	private Color colorClicked;
	private Color borderColor;
	private int radius = 0;
	
	public CustomButton(String name) {
			super.setText(name);
	        setColor(Color.WHITE);
	        colorOver = new Color(179, 250, 160);
	        colorClicked = new Color(152, 184, 144);
	        borderColor = new Color(43, 36, 81);
	        setContentAreaFilled(false);

	        addMouseListener(new MouseAdapter() {
	            @Override
	            
	            public void mouseEntered(MouseEvent me) {
	                setBackground(colorOver);
	                over = true;
	            }

	            @Override
	            public void mouseExited(MouseEvent me) {
	                setBackground(color);
	                over = false;

	            }

	            @Override
	            public void mousePressed(MouseEvent me) {
	                setBackground(colorClicked);
	            }

	            @Override
	            public void mouseReleased(MouseEvent me) {
	                if (over) {
	                    setBackground(colorOver);
	                } else {
	                    setBackground(color);
	                }
	            }
	        });
	    }
	@Override
	protected void paintComponent(Graphics grphcs){
		 Graphics2D g2 = (Graphics2D) grphcs;
	        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	        g2.setColor(borderColor);
	        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
	        g2.setColor(getBackground());
	        g2.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 4, radius, radius);
	        super.paintComponent(grphcs);
	};
	
	public Color getColorClicked() {
		return colorClicked;
	}
	public void setColorClicked(Color colorClicked) {
		this.colorClicked = colorClicked;
	}
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) {
		this.radius = radius;
	}
	public Color getBorderColor() {
		return borderColor;
	}
	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}
	public Color getColorOver() {
		return colorOver;
	}
	public void setColorOver(Color colorOver) {
		this.colorOver = colorOver;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
		setBackground(color);
	}
	public boolean isOver() {
		return over;
	}
	public void setOver(boolean over) {
		this.over = over;
	}
}
