/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package standalone;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
/**
 *
 * @author Hannu Raappana
 */
public class AddScreen extends JPanel implements ListSelectionListener{
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		//Basic variables and objects.
         JPanel myPanel;
         JPanel uiPanel;
     
         
        JButton addCategoryBtn;
        JButton delCategoryBtn;
        JButton addItemBtn;
        JButton delItemBtn;
        
        JScrollPane scrollcat;
        JScrollPane scrollit;
        JList <String >catList;
        JList <String >itList;
       
        Vector <String> categories;
        Vector <String >products;
        
        DataBaseHelper dbHelper = new DataBaseHelper();
        
        public AddScreen()  {
            showUI();
        }
        
       
        //Get product.
        public String getCategory() {
            String category = null;
                if(catList.getSelectedValue() != null && itList.getSelectedValue() !=  null) {
                	category = catList.getSelectedValue().toString();
                }
                else {
                    return null;
                }
                
               
                
            return category;
                
        }
        
        public String getProduct() {
        	String product = null;
            if(catList.getSelectedValue() != null && itList.getSelectedValue() !=  null) {
            	product = itList.getSelectedValue().toString();
            }
            else {
                return null;
            }
            
           
            
        return product;
        }
        
        //Update categories method
        public void update_category(String s, String method) {
        
            if(method == "Lisaa") {
            	
            	dbHelper.addCategory(s);
            }
            
            else {
                
            	dbHelper.deleteCategory(s);
                
            }
            
        }
        
        //update products method.
        public void update_item(String s, String c, String method) {
        	     if(method == "Lisaa") {
                	 
                	 	dbHelper.addProduct(s, c);
               	  
            }
            
            else {
                
                	dbHelper.deleteProduct(s);
            }
                 
        }
            
        //Show the addscreen.
		public void showUI() {
       
			  categories=new Vector<String>();
			  categories = (Vector<String>) dbHelper.getCategories();
			 
			   //products
			  products=new Vector<String>();
			  
			  
			  //Dimension for the scrollbars.
			  Dimension d = new Dimension(150,100);
			 
			  //Lists and scrollbars for the categories and products.
			  catList=new JList<String>(categories);
			  scrollcat=new JScrollPane(catList);
			  catList.addListSelectionListener(this);
			  
			  scrollcat.setPreferredSize(d);
			  itList=new JList<String>(products);
			  scrollit=new JScrollPane(itList);
			  scrollit.setPreferredSize(d);
			  
			  //panel for the categories and products.
			  uiPanel = new JPanel();
			  JPanel catListpane=new JPanel();
			  JPanel itlistpane=new JPanel();
			  itlistpane.add(scrollit);
			  catListpane.add(scrollcat);
			  
			  JPanel catListop = new JPanel();
			  JPanel itlistop = new JPanel();
			  catListop.add(new JLabel("Kategoria: "));
			  itlistop.add(new JLabel("Tuote: "));
			  
			           
			            //Gridbaglayout.
			           
			  GridBagLayout layout = new GridBagLayout();
			  GridBagConstraints c = new GridBagConstraints();
			  uiPanel.setLayout(layout);
			            
        
			  
			  //Placing the objects on the gridbag
            
c.ipady = 0;       //reset to default
c.ipadx = 0;
c.weightx = 0;
c.fill = 0;
c.weighty = 0.0;   //request any extra vertical space
c.anchor = GridBagConstraints.PAGE_END; //bottom of space
c.insets = new Insets(0,0,100,0);  //top padding
c.gridx = 3;       
c.gridwidth = 1;   //columns wide
c.gridy = 1;       //
uiPanel.add(catListop,c);

c.ipady = 0;       //reset to default
c.weighty = 1.0;   //request any extra vertical space
c.insets = new Insets(10,0,0,0);  //top padding
c.gridx = 4;       //
c.gridwidth = 1;   //columns wide
c.gridy = 1;       //
uiPanel.add(catListpane,c);



//Add category to the database.
addCategoryBtn = new JButton("Lisää");
    addCategoryBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                //Execute when button is pressed
                String inputValue = JOptionPane.showInputDialog(uiPanel, "Lisää uusi kategoria");
               if(inputValue != null) {
	                categories.add(inputValue.toString());
	                catList.setListData(categories);
	                scrollcat.revalidate();
	                scrollcat.repaint();
	                update_category(inputValue, "Lisaa");
               }
            }
        });      
    
c.ipady = 0;       //reset to default
c.weighty = 0.0;   //request any e vertical space
c.weightx = 1;
c.insets = new Insets(0,0,0,0);  //top padding
c.gridx = 3;       //align
c.gridy = 1;       //row
uiPanel.add(addCategoryBtn,c);

    

delCategoryBtn = new JButton("Poista");

c.ipady = 0;       //reset to default
c.weighty = 0.0;   //request any extra vertical space
c.weightx = 1;
c.insets = new Insets(0,150,0,0);  //top padding
c.gridx = 3;       //align
c.gridy = 1;       //row
uiPanel.add(delCategoryBtn,c);

//Add actionlistener to the delcategory button.
   delCategoryBtn.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e)
            {
                 int m = catList.getSelectedIndex();
                
              if(m != -1) {
                //Execute when button is pressed
	               int n = JOptionPane.showConfirmDialog(
	               uiPanel, "Haluatko poistaa valitun kategorian?",
	                        "", JOptionPane.YES_NO_OPTION);

                if(n == 0) 
                	categories.remove(m);
	                catList.setListData(categories);
	                scrollcat.revalidate();
	                scrollcat.repaint();
                update_category("inputValue", "Poista");
                
            }   else {
                        JOptionPane.showMessageDialog(
                            uiPanel, "Sinun on valittava kategoria.");
   }}
          
            
           
        });      




c.ipady = 0;       //reset to default
c.weighty = 1.0;   //request any extra vertical space
c.insets = new Insets(0,0,100,20);  //top padding
c.gridx = 3;       //
c.gridwidth = 1;   //columns wide
c.gridy = 2;       //row
uiPanel.add(itlistop,c);

addItemBtn = new JButton("Lisää");
c.ipady = 0;       //reset to default
c.weighty = 0.0;   //request any extra vertical space
c.weightx  = 1;
c.insets = new Insets(0,0,0,0);  //padding
c.gridx = 3;       //
c.gridwidth = 1;   //columns wide
c.gridy = 2;       //row
uiPanel.add(addItemBtn,c);

//Add actionlistener to the additem button.
 addItemBtn.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e)
            {
                //Execute when button is pressed
            	
            	if(catList.getSelectedValue() != null) {
            		String inputValue = (String) JOptionPane.showInputDialog(uiPanel, "Lisää uusi tuote");
               
                if(inputValue != null) {
	                products.add(inputValue.toString());
	                itList.setListData(products);
	                scrollit.revalidate();
	                scrollit.repaint();
	                String category = catList.getSelectedValue().toString();
	                update_item(inputValue, category, "Lisaa" );
                }
            	}
            	
            	else  {
            		
            		JOptionPane.showMessageDialog(
                            uiPanel, "Sinun on valittava kategoria.");
            	}
            }
        });      

delItemBtn = new JButton("Poista");
c.ipady = 0;       //reset to default
c.weighty = 0;   //request any extra vertical space
c.insets = new Insets(0,150,0,0);  //top padding
c.gridx = 3;       //aligned with button 2
c.gridwidth = 1;   //columns wide
c.gridy = 2;       //row
uiPanel.add(delItemBtn,c);


//add actionlistener to the delitem button.
delItemBtn.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e)
            {
                 int m = itList.getSelectedIndex();
                
              if(m != -1) {
	                //Execute when button is pressed
	               int n = JOptionPane.showConfirmDialog(
                            uiPanel, "Haluatko poistaa valitun tuotteen?",
                            "",
                            JOptionPane.YES_NO_OPTION);

                if(n == 0)  {
                	
                	
                	 String item = itList.getSelectedValue().toString();
                     update_item(item, "", "Poista");
                     products.remove(m);
                     itList.setListData(products);
                     
                     scrollit.revalidate();
                     scrollit.repaint();
                }
                
            }   else {
                        JOptionPane.showMessageDialog(
                            uiPanel, "Sinun on valittava tuote.");
   }}
          
        });      


c.ipady = 0;       //reset to default
c.weighty = 0.0;   //request any extra vertical space
c.insets = new Insets(0,0,0,0);  //top padding
c.gridx = 4;       //aligned with button 2
c.gridwidth = 1;   //columns wide
c.gridy = 2;       //second row  
uiPanel.add(itlistpane,c);


        }


		


			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
                                
				updateProductList();
			}
			
                        //Update product list according to selected category.
			void updateProductList() {
				products = dbHelper.getCategory(catList.getSelectedValue().toString());
				itList.setListData(products);
				itList.revalidate();
				itList.repaint();
			}
         
}
