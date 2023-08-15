import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;


class GUI extends JFrame {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** Dette er selve oppgavebrettet */
	private Brett brett;
	/** Knappegitteret vaart */
	private JButton [][] gitter;
	/** Rammen skal ha tre knapper overst for knappegitteret */
	private JButton losningknapp, antlosningerknapp, aapnefilknapp;
	/** Rammen bestaar av to paneler, en nedre- og ovrepanel */
	private JPanel ovrepanel, nedrepanel;

	/** Konstruktoren tar inn oppgavebrettet og lager et JFrame-objekt
	 * der objektet bestaar av to paneler: det ovre panelet skal ha
	 * tre knapper, mens det nedre panelet skal ha knappegitteret
	 * (sudokubrettet)
	 *  
	 */
	public GUI(Brett brett) {
		super("Sudoku");
		this.brett = brett;
		gitter = new JButton[brett.hentAntRad()][brett.hentAntKol()]; // henter inn dimensjonene paa gitteret	
		
		/* Lager oss det nedre panel med tre knapper, og hver knapp har en lytter */
		ovrepanel = new JPanel();
		ovrepanel.setLayout(new FlowLayout());
		
		aapnefilknapp = new JButton("Open File");
		aapnefilknapp.addActionListener(new Lytter());
		ovrepanel.add(aapnefilknapp);
		
		losningknapp = new JButton("Find Solution(s)");
		losningknapp.addActionListener(new Lytter());
		ovrepanel.add(losningknapp);

		antlosningerknapp = new JButton("Number of Solutions");
		antlosningerknapp.addActionListener(new Lytter());
		ovrepanel.add(antlosningerknapp);
				
		/* Lager oss det ovre panelet med knappegitteret, der hver knapp skal ha en tastelytter */
		nedrepanel = new JPanel();
		nedrepanel.setLayout(new GridLayout(brett.hentAntRad(), brett.hentAntKol()));
		
		/* Looper gjennom alle rutene i brettet, og skiller mellom tomme ruter og utfylte ruter
		 * og oppretter tilhorende knapper */
		for (int i = 0; i < brett.hentAntRad(); i++) {
			for (int j = 0; j < brett.hentAntKol(); j++) {
				if (brett.hentRute(i,j).erTomRute()) { // her sjekker vi om ruten er tom
					gitter[i][j] = new JButton("");
					gitter[i][j].setFont(new Font("SansSerif", Font.BOLD, 32));
					/* Dette er for aa markere kantene til boksene i gitteret */
					if (i%brett.hentAntRadBoks() == 0 && j%brett.hentAntKolBoks() == 0) {
						gitter[i][j].setBorder(BorderFactory.createMatteBorder(4, 4, 1, 1, Color.black));
					}
					else if (i%brett.hentAntRadBoks() == 0) {
						gitter[i][j].setBorder(BorderFactory.createMatteBorder(4, 1, 1, 1, Color.black));
					}
					else if (j%brett.hentAntKolBoks() == 0) {
						gitter[i][j].setBorder(BorderFactory.createMatteBorder(1, 4, 1, 1, Color.black));
					}
					gitter[i][j].addKeyListener(new TastLytter(gitter[i][j],i,j)); // tomme ruter skal ha en funksjon, tastelytter
					nedrepanel.add(gitter[i][j]);
				}
				else {
					/* Her konverterer vi int-verdien av ruten til char, for saa til String for JButton-parameter */
					gitter[i][j] = new JButton(Character.toString(Character.forDigit(brett.hentRute(i, j).hentRuteVerdi(), brett.hentAntRad()+1 )));
					gitter[i][j].setFont(new Font("SansSerif", Font.BOLD, 32));
					/* Dette er for aa markere kantene til boksene i gitteret */
					if (i%brett.hentAntRadBoks() == 0 && j%brett.hentAntKolBoks() == 0) {
						gitter[i][j].setBorder(BorderFactory.createMatteBorder(4, 4, 1, 1, Color.black));
					}
					else if (i%brett.hentAntRadBoks() == 0) {
						gitter[i][j].setBorder(BorderFactory.createMatteBorder(4, 1, 1, 1, Color.black));
					}
					else if (j%brett.hentAntKolBoks() == 0) {
						gitter[i][j].setBorder(BorderFactory.createMatteBorder(1, 4, 1, 1, Color.black));
					}					
					nedrepanel.add(gitter[i][j]); // ingen funksjon for de utfylte rutene, ingen tastelytter trengs
				}
			}
		}
		
		Container lerret = getContentPane();
		lerret.add(ovrepanel, BorderLayout.NORTH);
		lerret.add(nedrepanel, BorderLayout.SOUTH);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	} // konstruktor slutt
	
	/** Indre klasse som tar for seg funksjoner for knappene i det ovre panelet, knappelytter */
	private class Lytter implements ActionListener {
		private int antlosninger;
		/** Metoden lytter etter knappetrykk og gir hver knapp en funksjon 
		 * 
		 * @param e dette objektet registrerer knappetrykket
		 * 
		 */
		public void actionPerformed(ActionEvent e) {
			/* Dersom knappetrykket skjer paa losningknappen, skal vi ta ut en losning fra beholderen og vise den */
			if (e.getSource() == losningknapp) {
				antlosninger = brett.hentSudokuBeholder().hentAntallLosningerIBeholder();
				Brett losning = brett.hentSudokuBeholder().taUt();
				if (antlosninger > 0)
					new GitterNxN(losning);
				else
					JOptionPane.showMessageDialog(null,
							"There are  no more solutions left to show",
							"Number of Solutions", JOptionPane.INFORMATION_MESSAGE);
			}
			/* Dersom knappetrykket skjer paa antlosningerknapp, skal vi vise hvor mange losninger det er igjen i beholderen */ 
			else if (e.getSource() == antlosningerknapp) {
				JOptionPane.showMessageDialog(null, 
						"There are " + brett.hentSudokuBeholder().hentAntallLosninger() + " solution(s)",
						"Number of Solutions", JOptionPane.INFORMATION_MESSAGE);
			}
			/* Dersom knappetrykket skjer paa aapnefilknapp, bruker vi JFileChooser for aa finne en ny oppgavefil */
			else if (e.getSource() == aapnefilknapp) {
				JFileChooser velger = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("text only", "txt");
				velger.setFileFilter(filter);
				int resultat = velger.showOpenDialog(getParent());
				if (resultat == JFileChooser.APPROVE_OPTION) {
					String [] filnavn = {velger.getSelectedFile().getName()};
					new Leser(filnavn);
				}
				else {
					System.out.println("Open command cancelled by user.");
				}
			}
		}
	} // slutt Lytter
	
	/** En indre klasse som tar for seg funksjonene til knappene i det nedre panelet, tastelytter */
	private class TastLytter extends KeyAdapter {
		/** Verdi for rute */
		private int v, i, j;
		private JButton button;
		
		public TastLytter(JButton button, int i, int j) {
			this.button = button;
			this.i = i; this.j = j;
		} 
		
		/** Metode som registrerer tastetrykket 
		 * 
		 * @param e dette objektet registrerer tastehendelsen
		 * 
		 */
		public void keyTyped(KeyEvent e) {
			/* Looper gjennom alle rutene i brettet */			
			if (e.getSource() == button && brett.hentRute(i,j).erTomRute()) { // interessert i kun de tomme rutene
				v = Character.getNumericValue(e.getKeyChar()); // konverterer tasteverdien char om til int
				if (v <= brett.hentAntRad() && v >= 1) { // sjekker om verdien er innenfor verdigrensen i brettet
					if(brett.hentRute(i,j).settInnVerdi(v)) { // dersom verdien ikke eksisterer i rutens rad, kolonne eller boks
						brett.hentRute(i,j).settInnVerdi(v);
						button.setForeground(Color.gray);
						button.setFont(new Font("SansSerif", Font.BOLD, 32));
						button.setText(Character.toString(e.getKeyChar()));
					} else { // dersom verdien allerede eksisterer i rutens rad, kolonne eller boks
						button.setForeground(Color.red);
						button.setFont(new Font("SansSerif", Font.BOLD, 32));
						button.setText(Character.toString(e.getKeyChar()));
					}
				}
				else if(e.getKeyChar() == 8) { // funksjon for backspace
					brett.hentRute(i,j).tilbakestillVerdi();
					button.setText("");
				} else { // Dialogboks dukker opp naar verdien er utenfor verdigrensen i brettet
					JOptionPane.showMessageDialog(null, 
					"The value " + Character.toString(e.getKeyChar()) + " exceeds the limit on this board",
					"Error: Value not valid", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}	
	}
		
}