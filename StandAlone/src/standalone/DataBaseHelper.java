/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package standalone;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;


/**
 *
 * @author Hannu Raappana
 */
public class DataBaseHelper {
    String mPass = "testi";
    String mName = "root";
        String addr = "jdbc:mysql://localhost/ui-database";
    Connection con;
    
    
    //Constructor.
    public DataBaseHelper()  {
        
       
        
    	try {
            
			con = DriverManager.getConnection(addr, mName, mPass);
                        System.out.print("Success!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.print(e.getMessage());
			e.printStackTrace();
                        
		}
        
        
    }
    
    
    //Add category,
    void addCategory(String category) {
    	String sql;
    	sql = "INSERT INTO category (name) values ('"+category+"')";
    	 try {
        	 System.out.print("DEBUG" + sql);
            con = DriverManager.getConnection(addr, mName, mPass);
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
            
        } catch(SQLException e) {
            // Suppress
        }
    }
    
    
    //Add new product with different options.
    void defineProduct(String product, Vector <String> memory, Vector <String> hdd, Vector <String> os, AdditionalScreen screen) {
    	String mem_question;
    	int mandatory;
    	if(screen.memCheckBox.isSelected() == true)
    		mandatory = 1;
    	else
    		mandatory = 0;
    	mem_question = "INSERT INTO question (question, mandatory, product) values ('Lisämuisti', "+mandatory+", (SELECT id FROM product WHERE name = '"+product+"'))";
    	String os_question;
    	
    	if(screen.hardCheckBox.isSelected() == true)
    		mandatory = 1;
    	else
    		mandatory = 0;
    	os_question = "INSERT INTO question (question, mandatory, product) values ('Tilataanko ulkoinen kiintolevy', "+mandatory+", (SELECT id FROM product WHERE name = '"+product+"'))";
    	String hdd_question;
    	
    	if(screen.osCheckBox.isSelected() == true)
    		mandatory = 1;
    	else
    		mandatory = 0;
    	hdd_question = "INSERT INTO question (question, mandatory, product) values ('Käyttöjärjestelmä', "+mandatory+", (SELECT id FROM product WHERE name = '"+product+"'))";
    	

    	try {
           con = DriverManager.getConnection(addr, mName, mPass);
           Statement stmt = con.createStatement();
           stmt.executeUpdate(mem_question);
           stmt.executeUpdate(os_question);
           stmt.executeUpdate(hdd_question);
           
           for(int i = 0; i<memory.size(); i++) {
        	   
        	   String mem_option=null;
        	   mem_option = "INSERT INTO options (question, option_text, is_default) values ((SELECT id FROM question WHERE question = 'Lisämuisti'), '"+memory.get(i).toString()+"', 0)";
        	   System.out.print("BEBUG:" + mem_option);
        	   stmt.executeUpdate(mem_option);
        	  
        	   }
           
           for(int j = 0; j<os.size(); j++) {
        	   String os_option =null;
        	   os_option = "INSERT INTO options (question, option_text, is_default) values ((SELECT id FROM question WHERE question = 'Käyttöjärjestelmä'), '"+os.get(j).toString()+"', 0)";
        	   System.out.print("BEBUG:" + os_option);
        	   stmt.executeUpdate(os_option);
        	  
        	   }
           
           for(int k = 0; k<hdd.size(); k++) {
        	   String hdd_option =null;
        	   hdd_option = "INSERT INTO options (question, option_text, is_default) values ((SELECT id FROM question WHERE question = 'Tilataanko ulkoinen kiintolevy'), '"+hdd.get(k).toString()+"', 0)";
        	   System.out.print("BEBUG:" + hdd_option);
        	   stmt.executeUpdate(hdd_option);
        	   }
       } catch(SQLException e) {
           // Suppress
       }
    	
    	
    }
    
    //Add product.
    void addProduct(String product, String category) {
    	String sql;
    	sql = "INSERT INTO product (name, category) values ('"+product+"', (SELECT id FROM category WHERE name = '"+category+"'));";
    	
         try {
        	 System.out.print("DEBUG" + sql);
            con = DriverManager.getConnection(addr, mName, mPass);
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
            
        } catch(SQLException e) {
            // Suppress
        }
    }
    
    //Delete product.
    void deleteProduct(String product) {
    	String sql;
    	sql = "DELETE FROM product WHERE name = '"+product+"'";
    	
         try {
        	 System.out.print("DEBUG" + sql);
            con = DriverManager.getConnection(addr, mName, mPass);
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
            
        } catch(SQLException e) {
            // Suppress
        }
    }
    
    //Delete category
void deleteCategory(String category) {
    	
	String sql;
	sql = "DELETE FROM category WHERE name = '"+category+"'";
	
     try {
    	 System.out.print("DEBUG" + sql);
        con = DriverManager.getConnection(addr, mName, mPass);
        Statement stmt = con.createStatement();
        stmt.executeUpdate(sql);
        
    } catch(SQLException e) {
        // Suppress
    }
}



//Get all categories.
Vector<String> getCategories() {
    String sql;
    sql = "SELECT name from category";
    Vector <String>categories = new Vector <String>();
    
     try {
            con = DriverManager.getConnection(addr, mName, mPass);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                
                System.out.println("DEBUG: "+rs.getString(1));
                categories.add(rs.getString(1));
            }
        } catch(SQLException e) {
            // Suppress
        }
    return categories;
}

    //Get all products under the category.
    Vector <String> getCategory(String category) {
    	
        Vector<String> productList= new  Vector <String>();
        String sql = "SELECT p.name FROM product p LEFT JOIN category c ON c.id = p.category WHERE c.name LIKE '"+category.replace("ö",  "%")+"'";
        
        System.out.println(sql);
            
        try {
            con = DriverManager.getConnection(addr, mName, mPass);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                
                System.out.println("DEBUG: "+rs.getString(1));
                productList.add(rs.getString(1));
            }
        } catch(SQLException e) {
            // Suppress
        }
        
        return productList;
    }
    }

