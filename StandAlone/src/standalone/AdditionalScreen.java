/*
 * To change this license header, choosCmbe License Headers in Project Properties.
 * To change this template file, choosCmbe Tools | Templates
 * and open the template in the editor.
 */
package standalone;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JComboBox;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.util.Vector;

import javax.swing.JCheckBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author Hannu Raappana
 */


public class AdditionalScreen extends JPanel {
        
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		//Basic variables and objects.
        JComboBox <String> memoryCmb;
        JComboBox <String>harddriveCmb;
        JComboBox <String>osCmb;
        JPanel catLblListPanel;
        JPanel itemLblListPanel;
        JPanel osCmbListPanel;
        JLabel optionLbl=null;
        JLabel itemLbl=null;
        JPanel myPanel=null;
        String device=null;
        JButton addMemBtnbtn;
        JButton addHddBtn;
        JButton addOsBtn;
        JButton delMemBtn;
        JButton delHddBtn;
        JButton delOsBtn;
        
        //checkbox
        JCheckBox memCheckBox;
        JCheckBox osCheckBox;
        JCheckBox hardCheckBox;
        
        JList <String>hardlist;
        JList <String>osCmbList;
        JList <String>memList;
        
        Vector <String>hardDrives;
        Vector <String>operatingSystems;
        Vector <String>memoryTypes;
        
                
    
        
        //Constructor
    public AdditionalScreen(String product) {
       
        optionLbl = new JLabel();
        itemLbl = new JLabel();
        itemLblListPanel = new JPanel();
        osCmbListPanel = new JPanel();
        catLblListPanel = new JPanel();
        addMemBtnbtn = new JButton("Lisää");
        addHddBtn = new JButton("Lisää");
        addOsBtn = new JButton("Lisää");
        delMemBtn = new JButton("Poista");
        delHddBtn = new JButton("Poista");
        delOsBtn = new JButton("Poista");
        
        memoryTypes = new Vector <String>();
        operatingSystems = new Vector<String>();
        hardDrives = new Vector<String>();
        
        memCheckBox = new JCheckBox("Pakollinen");
        osCheckBox = new JCheckBox("Pakollinen");
        hardCheckBox = new JCheckBox("Pakollinen");
        
        hardCheckBox.setSelected(true);
        memCheckBox.setSelected(true);
        osCheckBox.setSelected(true);
        
        this.device = product;
        
        optionLbl.setText(product);
       
        //Lists for the commboboxes.
        osCmbList = new JList<String>();
        memList = new JList<String>();
        hardlist = new JList<String>();
        
        osCmb = new JComboBox<String>();
        memoryCmb = new JComboBox<String>();
        harddriveCmb = new JComboBox<String>();
       
        
        DefaultComboBoxModel <String> memmodel = new DefaultComboBoxModel<String>(memoryTypes);
        DefaultComboBoxModel <String>osmodel = new DefaultComboBoxModel<String>(operatingSystems);
        DefaultComboBoxModel <String>hardmodel = new DefaultComboBoxModel<String>(hardDrives);
        memList.setModel(memmodel);
        osCmbList.setModel(osmodel);
        hardlist.setModel(hardmodel);
       
        osCmb.setModel(osmodel);
        memoryCmb.setModel(memmodel);
        harddriveCmb.setModel(hardmodel);
        osCmb.setPreferredSize(new Dimension(120,25));
        memoryCmb.setPreferredSize(new Dimension(120,25));
        harddriveCmb.setPreferredSize(new Dimension(120,25));
    
        showUI();   
    }
    
  
    
    
    //Get added memories for the product.
    public Vector <String> getMemory() {
    	
    	return memoryTypes;
 	  
    }
    
    //Get added os:s for the product.
     public Vector <String>getOs() {

    	 return operatingSystems;
    	 	
    }
     
     //Get added harddrives for the product.
      public Vector <String>getHarddrive() {
    	 
    	  return hardDrives;
    }
      
      
      //Get device.
      public String getDevice() {
         return device;
      }
    
    
    
      //Additional screen ui.
    public void showUI() {
      
       myPanel = new JPanel(new FlowLayout());
       JPanel memListPane=new JPanel();
       JPanel hardListPane=new JPanel();
       JPanel osListpane = new JPanel();
       catLblListPanel.add(new JLabel("Muisti: "));
       itemLblListPanel.add(new JLabel("Kiintolevy: "));
       osCmbListPanel.add(new JLabel("Käyttöjärjestelmä"));
  
        osListpane.add(osCmb);
        hardListPane.add(harddriveCmb);
        memListPane.add(memoryCmb);
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        myPanel.setLayout(layout);
       
       
      
       //Location on gridbaglayout for different ui- objects.       
c.ipady = 0;       //reset to default
c.ipadx = 0;
c.weightx = 0.5;
c.weighty = 0.0;   //request any extra vertical space
c.insets = new Insets(0,0,0,0);  //padding
c.gridx = 0;       
c.gridwidth = 1;   //columns wide
c.gridy = 0;       //
myPanel.add(catLblListPanel,c);

c.ipadx = 0;
c.ipady = 0;       //reset to default
c.weighty = 0.0;   //request any extra vertical space
c.weightx = 0.0;
c.insets = new Insets(0,160,0,0);  //padding
c.gridx = 0;       //
c.gridwidth = 0;   //columns wide
c.gridy = 0;       //
myPanel.add(memListPane,c);

c.ipadx = 0;
c.ipady = 0;       //reset to default
c.weighty = 0.0;   //request any extra vertical space
c.weightx = 0.0;
c.insets = new Insets(0,0,0,0);  //top padding
c.gridx = 2;       //
c.gridwidth = 0;   //columns wide
c.gridy = 0;       //
myPanel.add(addMemBtnbtn, c);

addMemBtnbtn.addActionListener(new ActionListener() {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String option;
		option = JOptionPane.showInputDialog(myPanel, "Lisää uusi vaihtoehto.");
		memoryTypes.add(option);
		memList.setListData(memoryTypes);	
		memoryCmb.add(memList);
		memoryCmb.addItem(option);
		
		//Because of a little bug when adding the jlist to the combobox (doesnt show the options).
		memoryCmb.removeItem(option);
		
		memoryCmb.revalidate();
		memoryCmb.repaint();
	}
	
});

c.ipadx = 0;
c.ipady = 0;       //reset to default
c.weighty = 0.0;   //request any extra vertical space
c.weightx = 0.0;
c.insets = new Insets(0,150,0,0);  //top padding
c.gridx = 2;       //
c.gridwidth = 0;   //columns wide
c.gridy = 0;       //
myPanel.add(delMemBtn, c);


//Action listener for delmembtn
delMemBtn.addActionListener(new ActionListener() {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		int index = memoryCmb.getSelectedIndex();
			if(index != -1) {
				memoryTypes.remove(index);
				memList.setListData(memoryTypes);

				memoryCmb.setSelectedIndex(-1);
				memoryCmb.revalidate();
				memoryCmb.repaint();
			}
			
			else {
				

        		JOptionPane.showMessageDialog(
                        myPanel, "Sinun on valittava poistettava vaihtoehto.");
			}
	
	}
	
});


c.ipadx = 0;
c.ipady = 0;       //reset to default
c.weighty = 0.0;   //request any extra vertical space
c.weightx = 0.0;
c.insets = new Insets(60,0,0,0);  //top padding
c.gridx = 2;       //
c.gridwidth = 0;   //columns wide
c.gridy = 0;       //
myPanel.add(memCheckBox,c);

c.ipady = 0;       //reset to default
c.ipadx = 0;
c.weightx = 0.0;
c.weighty = 0.0;   //request any extra vertical spac
c.insets = new Insets(0,0,0,0);  //top padding
c.gridx = 0;       
c.gridwidth = 1;   //columns wide
c.gridy = 1;       //
myPanel.add(itemLblListPanel,c);

c.ipady = 0;       //reset to default
c.weighty = 0.0;   //request any extra vertical space
c.weightx = 0.0;
c.insets = new Insets(0,160,0,0);  //top padding
c.gridx = 0;       //
c.gridwidth = 0;   //columns wide
c.gridy = 1;       //
myPanel.add(hardListPane,c);

c.ipadx = 0;
c.ipady = 0;       //reset to default
c.weighty = 0.0;   //request any extra vertical space
c.weightx = 0.0;
c.insets = new Insets(0,0,0,0);  //padding
c.gridx = 2;       //
c.gridwidth = 0;   //columns wide
c.gridy = 1;       //
myPanel.add(addHddBtn, c);

addHddBtn.addActionListener(new ActionListener() {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String option;
		option = JOptionPane.showInputDialog(myPanel, "Lisää uusi vaihtoehto.");
		hardDrives.add(option);
		hardlist.setListData(hardDrives);	
		harddriveCmb.add(hardlist);
		harddriveCmb.addItem(option);
		
		//Because of a little bug when adding the jlist to the combobox (doesnt show the options).
		harddriveCmb.removeItem(option);
		
		harddriveCmb.revalidate();
		harddriveCmb.repaint();
	}
	
});

c.ipadx = 0;
c.ipady = 0;       //reset to default
c.weighty = 0.0;   //request any extra vertical space
c.weightx = 0.0;
c.insets = new Insets(0,150,0,0);  //padding
c.gridx = 2;       //
c.gridwidth = 0;   //columns wide
c.gridy = 1;       //
myPanel.add(delHddBtn, c);

delHddBtn.addActionListener(new ActionListener() {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		int index = harddriveCmb.getSelectedIndex();
			if(index != -1) {
				hardDrives.remove(index);
				hardlist.setListData(hardDrives);

				harddriveCmb.setSelectedIndex(-1);
				harddriveCmb.revalidate();
				harddriveCmb.repaint();
			}
			
			else {
				

        		JOptionPane.showMessageDialog(
                        myPanel, "Sinun on valittava poistettava vaihtoehto.");
			}
	
	}
	
});

c.ipady = 0;       //reset to default
c.weighty = 0.0;   //request any extra vertical space
c.weightx = 0.0;
c.insets = new Insets(60,0,0,0);  //top padding
c.gridx = 2;       //
c.gridwidth = 1;   //columns wide
c.gridy = 1;       //
myPanel.add(hardCheckBox,c);

c.ipady = 0;       //reset to default
c.ipadx = 0;
c.weightx = 0.0;
c.weighty = 0.0;   //request any extra vertical space
c.insets = new Insets(0,0,0,0);  //top padding
c.gridx = 0;       
c.gridwidth = 1;   //columns wide
c.gridy = 2;       //
myPanel.add(osCmbListPanel,c);

c.ipady = 0;       //reset to default
c.weighty = 0.0;   //request any extra vertical space
c.weightx = 0.0;
c.insets = new Insets(0,160,0,0);  //top padding
c.gridx = 0;       //
c.gridwidth = 0;   //columns wide
c.gridy = 2;       //
myPanel.add(osCmb,c);

c.ipadx = 0;
c.ipady = 0;       //reset to default
c.weighty = 0.0;   //request any extra vertical space
c.weightx = 0.0;
c.insets = new Insets(0,0,0,0);  //top padding
c.gridx = 2;       //
c.gridwidth = 0;   //columns wide
c.gridy = 2;       //
myPanel.add(addOsBtn, c);

addOsBtn.addActionListener(new ActionListener() {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String option;
		option = JOptionPane.showInputDialog(myPanel, "Lisää uusi vaihtoehto.");
		operatingSystems.add(option);
		osCmbList.setListData(operatingSystems);	
		osCmb.add(osCmbList);
		osCmb.addItem(option);
		
		//Because of a little bug when adding the jlist to the combobox (doesnt show the options).
		osCmb.removeItem(option);
		
		osCmb.revalidate();
		osCmb.repaint();
	}
	
});

c.ipadx = 0;
c.ipady = 0;       //reset to default
c.weighty = 0.0;   //request any extra vertical space
c.weightx = 0.0;
c.insets = new Insets(0,150,0,0);  //top padding
c.gridx = 2;       //
c.gridwidth = 0;   //columns wide
c.gridy = 2;       //
myPanel.add(delOsBtn, c);

delOsBtn.addActionListener(new ActionListener() {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		int index = osCmb.getSelectedIndex();
			if(index != -1) {
				operatingSystems.remove(index);
				osCmbList.setListData(operatingSystems);

				osCmb.setSelectedIndex(-1);
				osCmb.revalidate();
				osCmb.repaint();
			}
			
			else {
				

        		JOptionPane.showMessageDialog(
                        myPanel, "Sinun on valittava poistettava vaihtoehto.");
			}
	
	}
	
});

c.ipady = 0;       //reset to default
c.weighty = 0.0;   //request any extra vertical space
c.weightx = 0.0;
c.insets = new Insets(60,0,0,0);  //top padding
c.gridx = 2;       //
c.gridwidth = 1;   //columns wide
c.gridy = 2;       //
myPanel.add(osCheckBox,c);
   myPanel.setVisible(true);
    }
    
    

}