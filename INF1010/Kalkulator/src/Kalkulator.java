import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Kalkulator extends JFrame {
	private JButton add, subtract, multiply, divide, calculate, clear;
	private JButton [] tallknapp = new JButton[10];
	private JTextField tekstfelt;
	private JPanel panel1, panel2, panel3;
	
	public Kalkulator() {
		super("Kalkulator");
		
		panel1 = new JPanel();
		panel1.setLayout(new FlowLayout());
		tekstfelt = new JTextField(10);
		panel1.add(tekstfelt);
		
		panel2 = new JPanel();
		panel2.setLayout(new GridLayout(4,3));
		
		Lytter knappelytter = new Lytter();
		
		for (int i = 0; i <= 9; i++) {
			tallknapp[i] = new JButton(Integer.toString(i));
			tallknapp[i].addActionListener(knappelytter);
			panel2.add(tallknapp[i]);
		}
		
		calculate = new JButton("=");
		clear = new JButton("C");
		calculate.addActionListener(knappelytter);
		clear.addActionListener(knappelytter);
		panel2.add(calculate);
		panel2.add(clear);
		
		panel3 = new JPanel();
		panel3.setLayout(new GridLayout(4,1));
		
		add = new JButton("+");
		subtract = new JButton("-");
		multiply = new JButton("*");
		divide = new JButton("/");
		add.addActionListener(knappelytter);
		subtract.addActionListener(knappelytter);
		multiply.addActionListener(knappelytter);
		divide.addActionListener(knappelytter);
		panel3.add(add);
		panel3.add(subtract);
		panel3.add(multiply);
		panel3.add(divide);

		Container lerret = getContentPane();
		lerret.add(panel1, BorderLayout.NORTH);
		lerret.add(panel2, BorderLayout.CENTER);
		lerret.add(panel3, BorderLayout.EAST);		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(200,200);
		setVisible(true);
	}
	
	private class Lytter implements ActionListener {
		private double temptall1, temptall2, resultat;
		private boolean addbool, subtractbool, multiplybool, dividebool;
		
		public void actionPerformed(ActionEvent e) {
			
			for (int i = 0; i < tallknapp.length; i++) {
				if(e.getSource() == tallknapp[i]) {
					tekstfelt.setText(tekstfelt.getText() + tallknapp[i].getText());
				}
			}
			
			if(e.getSource() == add) {
				temptall1 = Double.parseDouble(tekstfelt.getText());
				addbool = true;
				tekstfelt.setText("");
			} else if(e.getSource() == subtract) {
				temptall1 = Double.parseDouble(tekstfelt.getText());
				subtractbool = true;
				tekstfelt.setText("");
			} else if(e.getSource() == multiply) {
				temptall1 = Double.parseDouble(tekstfelt.getText());
				multiplybool = true;
				tekstfelt.setText("");
			} else if(e.getSource() == divide) {
				temptall1 = Double.parseDouble(tekstfelt.getText());
				dividebool = true;
				tekstfelt.setText("");
			} else if(e.getSource() == clear) {
					temptall1 = 0; temptall2 = 0;
					resultat = 0;
					tekstfelt.setText("");
			} else if (e.getSource() == calculate) {
				temptall2 = Double.parseDouble(tekstfelt.getText());
				
				if (addbool == true) {
					resultat = temptall1 + temptall2;
				} else if (subtractbool) {
					resultat = temptall1 - temptall2;
				} else if (multiplybool) {
					resultat = temptall1*temptall2;
				} else if (dividebool) {
					if (temptall2 != 0) 
						resultat = temptall1/temptall2;
					else
						try {throw new Unntak("Zero-division");} 
						catch (Unntak e1) {e1.printStackTrace();}
				}
								
				addbool = false; 
				subtractbool = false;
				multiplybool = false;
				dividebool = false; 
				
				tekstfelt.setText("" + resultat);
			}
				
		}
	}
	
}