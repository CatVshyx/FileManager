package CustomElements;

import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class CustomJTextField extends JTextField {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean over;
	private Color borderColor;
	private Color borderOver;
	private Color color;
	private Color colorOver;
	private int radius = 20;
	private Color textColor;
	public CustomJTextField(int numb) {

			super(numb);
	        setColor(Color.WHITE);
	        colorOver = new Color(220,220,220);
	        borderOver = new Color(249, 26, 26);
	        borderColor = new Color(30, 136, 56);
	        textColor = new Color(128,128,128);

	        super.setForeground(textColor);

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
	        if (over) {
	        	g2.setColor(borderOver);
	        }
	        else {
	        	g2.setColor(borderColor);
	        }
	        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
	        g2.setColor(getBackground());
	        //  Border set 2 Pix
	        g2.fillRoundRect(40, 40, getWidth() - 4, getHeight() - 4, radius, radius);
	        super.paintComponent(grphcs);
	};
	
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
	public Color getBorderOver() {
		return borderOver;
	}
	public void setBorderOver(Color borderOver) {
		this.borderOver = borderOver;
	}
	
}
