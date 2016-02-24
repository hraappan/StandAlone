/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package standalone;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


/**
 *
 * @author Hannu Raappana
 */
public class StandAlone extends JFrame {

    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		//Basic variables.
        JFrame myFrame;
        JMenu myMenu;
        JMenuBar myMenuBar;
        JMenuItem myMenuItem;
        JButton lisa;
        JButton perus;
        JButton yhteen;
        JButton seuraava;
        JButton peruuta;
        AddScreen screen;
        AdditionalScreen adscreen = null;
        SummaryScreen sumscreen = null;
        
        //State variable to detect between different screens.
        int state;
        
       
        //Basic components for buildint the ui.
        
        //For menubar
        JPanel menuPanel = new JPanel(new FlowLayout());
        
        //Upper part of the ui,
        JPanel upperpane = new JPanel(new GridLayout());
        //Lower part of the ui,
        JPanel lowerpane = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        
        //Initialize the ui on start.
        public void initUI() {
            //State is 1 at the start.
            state = 1;
            
            //Set the name and the size of the ui screen.
            myFrame = new JFrame("Admin console");
        
        
            myFrame.setSize(640, 480);
	        myFrame.setResizable(false);
	        
	        //User layout for the screen.
	        myFrame.setLayout(new BorderLayout());
	        
	      //Menu for the guide.
	        myMenuBar = new JMenuBar();
	       
	        myMenu = new JMenu("Menu");
	        myMenuBar.add(myMenu);
	        myMenuItem = new JMenuItem("Ohje");
	        myMenu.add(myMenuItem);
	        
	        	myMenuItem.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// show the guide depending on the state.
						GuideScreen guide = new GuideScreen(state);
						guide.showGuide();
					}
		        	
		        	
		        	
		        });

        //Default close operation.
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen = new AddScreen();
    
        
       
        //Add the components for the upper part.
        perus = new JButton("Perustiedot");
        lisa = new JButton("Lisätiedot");
        yhteen = new JButton("Yhteenveto");
        lisa.setEnabled(false);
        yhteen.setEnabled(false);
        
        menuPanel.add(myMenuBar);
        upperpane.add(perus);
        upperpane.add(lisa);
        upperpane.add(yhteen);
        
        seuraava = new JButton("Seuraava");
            seuraava.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                	
                	//Chance the screen according to state.
                    if(state == 1) {
                        
                        String category=null;
                        
                        if(screen.getCategory() != null) {
	                        category = screen.getCategory();
	                        System.out.println("DEBUG: "+category);
	                        
	                        System.out.print("DEBUG:" +screen.getProduct());
	                        
	                       
                        }
                        
                        if(category != null && (category.equals("Työasemat") || category.equals("Palvelimet"))) {
	                        showAdditionalInformation();
	                        perus.setEnabled(false);
	                        lisa.setEnabled(true);
	                        peruuta.setText("Takaisin");
	                        myFrame.remove(screen.uiPanel);
	                        
                        }
                        
                        else {
                            JOptionPane.showMessageDialog(
                            myFrame, "Voit valita lisättävän tuotteen vain seuraavista kategorioista: Työasemat, Palvelimet.");
                            state--;
                        }
                        
                      
                        
                       
                }
                    //If state is 2, show summary screen.
                    if(state == 2) {
                        lisa.setEnabled(false);
                        yhteen.setEnabled(true);
                        seuraava.setText("Tallenna");
                        adscreen.myPanel.setVisible(false);
                        myFrame.remove(adscreen);
                        showSummary();
                        
                    }
                    
                    //If state is 3, go back to first screen if 'tallenna' is pressed.
                    if(state == 3) {
                        DataBaseHelper data = new DataBaseHelper();
                        data.defineProduct(adscreen.getDevice(), adscreen.getMemory(), adscreen.getHarddrive(), adscreen.getOs(), adscreen);
                        data = null;
                        state=0;
                        yhteen.setEnabled(false);
                        perus.setEnabled(true);
                        seuraava.setText("Seuraava");
                        peruuta.setText("Peruuta");
                        sumscreen.myPanel.setVisible(false);
                        myFrame.remove(sumscreen);
                        myFrame.add(screen.uiPanel);
                        screen.uiPanel.setVisible(true);
                        
                        
                        
                    }
                    
                    state++;
                }
            
            });
            
            //Peruuta button and its actions.
        peruuta = new JButton("Peruuta");
            peruuta.addActionListener(new ActionListener() {
                
                public void actionPerformed(ActionEvent e) {
                    
                    if(state == 1) {
                        
                        System.exit(0);
                        
                    }
                    if(state == 2) {
                        myFrame.add(screen.uiPanel);
                        screen.uiPanel.setVisible(true);
                        adscreen.myPanel.setVisible(false);
                        lisa.setEnabled(false);
                        perus.setEnabled(true);
                        peruuta.setText("Peruuta");
                      
                        
              
                    }
                    
                    if(state == 3) {
                        sumscreen.myPanel.setVisible(false);
                        adscreen.myPanel.setVisible(true);
                        lisa.setEnabled(true);
                        yhteen.setEnabled(false);
                        seuraava.setText("Seuraava");
               
                    }
                    state--;
                }
            });
            //Add buttons to lowerpane.
        lowerpane.add(peruuta);
        lowerpane.add(seuraava);
        lowerpane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(80, 10, 0, 0)));

        //Add different panes to mainframe.

        myFrame.add(upperpane, BorderLayout.NORTH);
        myFrame.add(menuPanel, BorderLayout.WEST);
        myFrame.add(screen.uiPanel, BorderLayout.CENTER);
        myFrame.add(lowerpane, BorderLayout.SOUTH);
        myFrame.setVisible(true);
        
        }
        
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
       StandAlone standAlone = new StandAlone();
       standAlone.initUI();
       
    }
    
       //Shows the lisÃ¤tiedot screen.
        public void showAdditionalInformation() {
             
             screen.uiPanel.setVisible(false);
             adscreen = new AdditionalScreen(screen.getProduct());
             //myFrame.remove(screen.uipanel);
             myFrame.add(adscreen.myPanel, BorderLayout.CENTER);
             adscreen.setVisible(true);
            
         
             
        }
        
        //Shows the yhteenveto screen.
        public void showSummary() {
            screen.uiPanel.setVisible(false);
            adscreen.myPanel.setVisible(false);
            sumscreen = new SummaryScreen(screen.getProduct(), adscreen.getMemory(), adscreen.getHarddrive(), adscreen.getOs());
            myFrame.add(sumscreen.myPanel, BorderLayout.CENTER);
            sumscreen.myPanel.setVisible(true);
          
        }
        
  
    
}
