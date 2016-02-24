/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package standalone;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Hannu Raappana
 */
public class SummaryScreen extends JPanel {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JPanel myPanel=null;
        
        JPanel devicetop=null;
        JPanel memorytop=null;
        JPanel harddrivetop=null;
        JPanel ostop=null;
        
        JLabel devicelbl=null;
        JLabel memorylbl=null;
        JLabel harddrivelbl=null;
        JLabel oslbl=null;
        
        String device;
        Vector <String>os;
        Vector <String>memory;
        Vector <String>harddrive;

    public SummaryScreen(String device, Vector <String> memory, Vector<String> harddrive, Vector<String> os) {
        
        //Panels
        myPanel = new JPanel();
        devicetop = new JPanel();
        memorytop = new JPanel();
        harddrivetop = new JPanel();
        ostop = new JPanel();
        
        //setting variables.
        this.device = device;
        System.out.print(device);
        this.os = os;
        this.memory = memory;
        this.harddrive = harddrive;
        
        showUI();
    }
    
    
    //Show the ui
    public void showUI() {
        //Labels for the options.
        devicetop.add(new JLabel("Tuote:"));
        ostop.add(new JLabel("Käyttöjärjestelmä:"));
        memorytop.add(new JLabel("Muisti:"));
        harddrivetop.add(new JLabel("Kiintolevy:"));
       
        
        //Labels for the selected items.
        this.devicelbl = new JLabel(device);
        
        
        //List all product options.
        String operatingSystems = "";
        for(int i = 0; i< os.size(); i++) {
        	
        	operatingSystems += os.get(i).toString() + "; ";
        }
        this.oslbl = new JLabel(operatingSystems);
        
        String memoryTypes = "";
        for(int i = 0; i< memory.size(); i++) {
        	
        	memoryTypes += memory.get(i).toString() + "; ";
        }
        
        this.memorylbl = new JLabel(memoryTypes);
        
        String harddriveTypes = "";
        for(int i = 0; i< harddrive.size(); i++) {
        	
        	harddriveTypes += harddrive.get(i).toString() + "; ";
        }
        
        this.harddrivelbl = new JLabel(harddriveTypes);
        
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        myPanel.setLayout(layout);
        
//Place objects on the brigbaglayout.        
c.ipady = 0;       //reset to default
c.ipadx = 0;
c.weightx = 0.5;
c.weighty = 0.0;   //request any extra vertical space
c.insets = new Insets(0,0,0,0);  //top padding
c.gridx = 1;       
c.gridwidth = 1;   //columns wide
c.gridy = 0;       //
myPanel.add(devicetop,c);

c.ipadx = 0;
c.ipady = 0;       //reset to default
c.weighty = 0.5;   //request any extra vertical space
c.weightx = 0.0;
c.insets = new Insets(0,0,0,0);  //top padding
c.gridx = 2;       //
c.gridwidth = 15;   //columns wide
c.gridy = 0;       //
myPanel.add(devicelbl,c);

c.ipady = 0;       //reset to default
c.ipadx = 0;
c.weightx = 0.0;
c.weighty = 0.0;   //request any extra vertical spac
c.insets = new Insets(0,0,0,0);  //top padding
c.gridx = 1;       
c.gridwidth = 1;   //columns wide
c.gridy = 1;       //
myPanel.add(memorytop,c);

c.ipady = 0;       //reset to default
c.weighty = 0.5;   //request any extra vertical space
c.weightx = 0.0;
c.insets = new Insets(0,0,0,0);  //top padding
c.gridx = 2;       //
c.gridwidth = 1;   //columns wide
c.gridy = 1;       //
myPanel.add(memorylbl,c);

c.ipady = 0;       //reset to default
c.ipadx = 0;
c.weightx = 0.0;
c.weighty = 0.0;   //request any extra vertical space
c.insets = new Insets(0,0,0,0);  //top padding
c.gridx = 1;       
c.gridwidth = 1;   //columns wide
c.gridy = 2;       //
myPanel.add(harddrivetop,c);

c.ipady = 0;       //reset to default
c.weighty = 0.5;   //request any extra vertical space
c.weightx = 0.5;
c.insets = new Insets(0,0,0,0);  //top padding
c.gridx = 2;       //
c.gridwidth = 1;   //columns wide
c.gridy = 2;       //
myPanel.add(harddrivelbl,c);

c.ipady = 0;       //reset to default
c.ipadx = 0;
c.weightx = 0.0;
c.weighty = 0.0;   //request any extra vertical space
c.insets = new Insets(0,0,0,0);  //top padding
c.gridx = 1;       
c.gridwidth = 1;   //columns wide
c.gridy = 3;       //
myPanel.add(ostop,c);

c.ipady = 0;       //reset to default
c.weighty = 0.5;   //request any extra vertical space
c.weightx = 0.5;
c.insets = new Insets(0,0,0,0);  //top padding
c.gridx = 2;       //
c.gridwidth = 1;   //columns wide
c.gridy = 3;       //
myPanel.add(oslbl,c);
        
    }
}
