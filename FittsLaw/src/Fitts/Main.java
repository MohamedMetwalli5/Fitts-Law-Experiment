package Fitts;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Main {

    private static JFrame f = new JFrame("Fitts's Law");
    private static JPanel p = new JPanel();
    private static JButton bLeft;
    private static JButton bRight;
    private static JButton center;
    
    private static int dLeft =230;
    private static int dRight = 430;
    private static int w = 150; //the Width
    private static List<Integer> ld = new ArrayList<>();
    private static List<Integer> lw = new ArrayList<>();
    private static List<Float> ID = new ArrayList<>();
    private static List<Long> MT = new ArrayList<>();
    private static List<Float> WOverD = new ArrayList<>();
    
    private static LocalTime from;
    private static LocalTime to; 
    private static Duration duration;
    
    public static void main(String[]args){
        
	    f.setVisible(true);
	    f.setSize(800,800);
	    f.setResizable(false);
	    p.setBackground(Color.cyan);
	    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
	    bLeft = new JButton("L");
    	bRight = new JButton("R");
    	center = new JButton("*");
    	JLabel title = new JLabel("Form Left to Right");
    	title.setBounds(300, 200, 100, 100);
    	title.setForeground(Color.magenta);
    	title.setFont(new Font("serif",Font.PLAIN,40));
    	bLeft.setBounds(dLeft, 400, w, 50);
    	bLeft.setBackground(Color.green);
    	bRight.setBounds(dRight, 400, w, 50);
    	bRight.setBackground(Color.green);
    	center.setBounds(375, 400, 50, 50);
    	center.setBackground(Color.red);
	    p.add(bLeft);
	    p.add(bRight);
	    p.add(center);
	    p.add(title);
	    f.add(p);       
	    
	    bLeft.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent e) {        
	        	bLeft.setBounds(dLeft, 400, w, 50);
	        	ld.add(dLeft);
	        	dLeft -= 30;
	        	w -= 10; // decreasing the width
	        	from = LocalTime.now();	
	        }
	    });
	    
	    bRight.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent e) {       
	        	bRight.setBounds(dRight, 400, w, 50);
	        	dRight += 30;
	        	lw.add(w);
	        	w -= 10; // decreasing the width
	        	to = LocalTime.now();
                duration = Duration.between(from, to);
	            if(w > 0) {
	                Float idItem = (float) ( Math.log( (2*Math.abs((350-dLeft)))/w ) / Math.log(2) );
	                ID.add(idItem);
                	MT.add(duration.toMillis());
                	WOverD.add((float) (w/(350-dLeft)));
                	System.out.println("ID is : " + idItem +"    ------>   " + "MT is : " +  duration.toMillis() + "    ------>   " + "(W/D)% is : " + ((float)w/(float)(350-dLeft))*100 + "%");
	            }

	        }
	    });
	    
	    
	    center.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent e) {       
	        	center.setBounds(390, 400, 20, 20);
	        	
	        }
	    });
    }  
}