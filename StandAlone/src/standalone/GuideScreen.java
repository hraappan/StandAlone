package standalone;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class GuideScreen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextArea basicGuideArea;
	JTextArea addGuideArea;
	JTextArea sumGuideArea;
	JScrollPane scrollPane;
	JButton closeBtn;
	JPanel btnPanel;
	JPanel textPanel;
	GuideScreen screen;
	int state;
	
	public GuideScreen(int state) {
		
		screen = this;
		this.state = state;
		basicGuideArea=new JTextArea();
		addGuideArea=new JTextArea();
		sumGuideArea = new JTextArea();
		
		scrollPane = new JScrollPane(basicGuideArea);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		btnPanel = new JPanel(new FlowLayout());
		textPanel = new JPanel(new GridLayout());
		closeBtn = new JButton("Sulje");
		
		addGuideArea.setEditable(false);
		basicGuideArea.setEditable(false);
		sumGuideArea.setEditable(false);
	}
	
	//Shows the guide depending on the state.
	void showGuide() {
	
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setName("Ohje");
		
		//Perustiedot
		basicGuideArea.setText("----------------------------------------------------------\n" +
								"Perustiedot ohje\n" + 
								"----------------------------------------------------------\n" +
								"\n"+
								"Perustiedoissa on mahdollista lisätä erilaisia tuotteita sekä kategorioita tietokantaan.\n" +
							
								"**********************\n"+
								"Kategorian lisääminen:\n" +
								"**********************\n"+
									"Kategorian 'Lisää' painikkeella on mahdollista lisätä kategoria tietokantaa. Painikkeen painalluksen\n"+
									"jälkeen avautuu uusi ikkuna johon kategorian nimi on mahdollista syöttää.\n" +
								"**********************\n"+
								"Kategorian poistaminen:\n" +
								"**********************\n"+
									"Kategorian 'Poista' painikkeella on mahdollista poistaa tietty kategoria tietokannasta. Valitse kategoria\n"+
								    "listasta jonka jälkeen paina 'Poista' painiketta. Tämän jälkeen järjestelmä pyytää vahvistuksen toiminnolle.\n" +
								"**********************\n"+
								"Tuotteen lisääminen:\n" +
								"**********************\n"+
									"Tuotteen 'Lisää' painikkeella on mahdollista lisätä tuote tietokantaan. Painikkeen painalluksen jälkeen avautuu \n"+
									"uusi ikkuna johon tuotteen nimi on mahdollista syöttää. Huomaa, että kategoria jonka alle tuote lisätään on \n" +
									"ensin valittava kategoria- listasta. \n" +
								"**********************\n"+
								"Tuotteen poistaminen: \n" +
								"**********************\n"+
									"Tuotteen 'Poista' painikkeella on mahdollista poistaa tietty tuote tietokannasta. Valitse tuote listasta jonka \n"+
									"jälkeen paina 'Poista' painiketta. Tämän jälkeen järjestelmä pyytää vahvistuksen toiminnolle. \n" +
								"**********************\n"+
								"Tuotteen lisääminen optioilla: \n" +
								"**********************\n"+
								"Tuotteeseen on mahdollista lisätä erilaisia optioita. Valitse listasta haluttu kategoria sekä tuote. Tämän jälkeen \n"+
								"paina 'Seuraava' ja uusi näkymä avautuu jonka avulla optioiden lisääminen on mahdollista. Huomaa, että voit valita\n" +
								"tuotteen vain 'Työasemat' sekä 'Palvelimet' kategoriasta."+
								"\n"+
								"\n");
		
		//Lisätiedot
		
		addGuideArea.setText(	"----------------------------------------------------------\n" +
								"Lisätiedot ohje\n" + 
								"----------------------------------------------------------\n" +
								"\n"+
								"Lisätiedoissa on mahdollista lisätä eri optioita tuotteille.\n" +
								"**********************\n"+
								"Optioiden lisääminen:\n"+
								"**********************\n"+
								"'Lisää' painikkeella avautuu uusi ikkuna johon on mahdollista syöttää uusi optio. Lisätyt optiot näkyvät\n" +
								"viereisestä alasvetolistasta.\n"+
								"**********************\n"+
								"Optioiden poistaminen:\n"+
								"**********************\n"+
								"Valitse poistettava optio alasvetolistasta jonka jälkeen paina painiketta 'Poista'.\n"+
								"**********************\n"+
								"Optioiden pakollisuus:\n"+
								"**********************\n"+
								"Voit myös valita optiot pakollisiksi."+
										"\n" +
									"\n");
		
		
		//Yhteenveto
		
		sumGuideArea.setText("-----------------------------------------------------------------\n"+
							"Yhteenveto ohje\n"+
							"-----------------------------------------------------------------\n"+
							"\n"+
							"Yhteenvedossa on mahdollista tarkastella tuotetta ja sen optioita joka ollaan lisäämässä tietokantaan.\n"+
							"'Tallenna' painike tallettaa tuotteen ja sen optiot tietokantaan. Voit myös palata edelliseen\n"+
							"vaiheeseen painamalla 'Takaisin' painiketta."
				);
		
		Dimension dimension = new Dimension(640, 480);
		basicGuideArea.setSize(dimension);
		
		this.setSize(dimension);
		
		
		closeBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				textPanel.removeAll();
				screen.dispose();
				
				
			}
			
		});
		
		this.setLayout(new BorderLayout());
		
		textPanel.setPreferredSize(dimension);
		btnPanel.add(closeBtn);
		
		//Changes the guide depending on the screen.
		if(state == 1)
			textPanel.add(scrollPane);
		else if(state == 2)
			textPanel.add(addGuideArea);
		else
			textPanel.add(sumGuideArea);
		
		btnPanel.add(closeBtn);
		
		this.add(textPanel, BorderLayout.CENTER);
		this.add(btnPanel, BorderLayout.SOUTH);
		
		this.setVisible(true);
		this.setEnabled(true);
		
		this.pack();
	}
}
