package com.fr3sh.config;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import utils.FxmlUtils;

import com.fr3sh.JiraConf;
import com.fr3sh.JiraParam;



public class LoaderConfig {

	List<JiraParam> allParam;
	public List<JiraParam> getAllParam() {
		return allParam;
	}


	public static String nazwa_conf = "src/main/resources/config/conf.txt";
	public static String param = "src/main/resources/config/param.txt";
	
	private JiraConf conf;
	private JiraParam params;
	 
	public JiraConf getConf() {
		return conf;
	}

	public void setConf(JiraConf conf) {
		this.conf = conf;
	}

	public JiraParam getParams() {
		return params;
	}

	public void setParams(JiraParam params) {
		this.params = params;
	}



public void  read_conf (String nazwa){
      
      File a = new File(nazwa);
     
      BufferedReader reader2;
      reader2 = null;
      String  thisLine = null;
      try {
          reader2 = new BufferedReader(new FileReader(a));
          conf =new JiraConf();
          while ((thisLine = reader2.readLine()) != null){
          	 if ( thisLine.contains("login:")){
                   String [] arr = thisLine.split(":", 2);
                   conf.setLogin(arr[1].trim());
                    //  System.out.println(pdf_dir_path);
               }
          	 if ( thisLine.contains("pass:")){
                 String [] arr = thisLine.split(":", 2);
                 conf.setPass(arr[1].trim());
                  //  System.out.println(pdf_dir_path);
             }
          	 if ( thisLine.contains("dni:")){
               
                // pass = arr[1].trim();
                 
                String [] arr = thisLine.split(":", 2);   
           		String [] temp2 = arr[1].split(";");
           	
           				//arr[1].trim();
           		for (int i = 0; i< temp2.length; i++){
           		//	weekHodj.add(e)
           			System.out.println("Wpisane DNI H: " + temp2[i]);
           			conf.getWeekHodj().add(Integer.parseInt(temp2[i]));
           		}
           		conf.setPROPforLIST();
                  //  System.out.println(pdf_dir_path);
             }
          	 
          	if ( thisLine.contains("submit:")){
                String [] arr = thisLine.split(":", 2);
                String tt = arr[1].trim();
                if (tt.contains("y")){
                	conf.setSubmitProp(true);
                }else
                {
                	conf.setSubmitProp(false);
                }
                
                 //  System.out.println(pdf_dir_path);
            }
          	 
          }
      } 
      catch (IOException ex) {
        
      }
      
 finally{
   
   if(reader2!=null)
		try {
			reader2.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}


 
}


public void save_conf (String nazwa) throws IOException {
	
	
	  try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(nazwa))) {
		  
		  
          writer.write("login: "+ conf.getLogin() +"\n");
          writer.write("pass: "+ conf.getPass() +"\n");
          writer.write("dni:"+ conf.getPn() +";"+ conf.getWt()  +";" + conf.getSr()  +";" + conf.getCzw()  +";" + conf.getPt()  +";"          +"\n");
         
          if (conf.getSubmit()) {
        	  writer.write("submit:y \n");  
          }else {
        	  writer.write("submit:n \n"); 
          }
          
          
          
      } // the file will be automatically closed
}

  	
public void  read_param (String nazwa){
        
        File a = new File(nazwa);
       
        BufferedReader reader2;
        reader2 = null;
        String  thisLine = null;
        allParam = new  ArrayList<>();
        try {
            reader2 = new BufferedReader(new FileReader(a));
            params = new JiraParam();
            while ((thisLine = reader2.readLine()) != null){
            	
          	  
          	  if ( thisLine.contains("param:")){
                     String [] arr = thisLine.split(":", 2);
                     params = new JiraParam();
                     params.setNazwa(arr[1].trim());
     
                 }
            	 
            	if ( thisLine.contains(params.getNazwa()+".txt")){    		
            		String [] arr = thisLine.split(":", 2);
            		params.setLink(arr[1].trim());
            	}
            	
           if ( thisLine.contains(params.getNazwa()+".options")){      		
            		String [] arr = thisLine.split(":", 2);   
            		String [] temp2 = arr[1].split(";");
            	
            				//arr[1].trim();
            		for (int i = 0; i< temp2.length; i++){
            			//params.getOptions().add(temp2[i]);
            			params.addOptions(temp2[i]);
            			
            		}
                 
  		
            	}
            	
            	if ( thisLine.contains(params.getNazwa()+".important")){ 		
            		String [] arr = thisLine.split(":", 2); 
            		params.setImportant(arr[1].trim());	
            	}
            	
           	if ( thisLine.contains(params.getNazwa()+".h")){  		
            		String [] arr = thisLine.split(":", 2); 
            		String [] temp2 = arr[1].split("-");
            		
            		for (int i = 0; i< temp2.length; i++){
            			//params.getH().add(Integer.parseInt(temp2[i]));
            			params.addH(Integer.parseInt(temp2[i]));
           		}
            		
            		
                 // temp1.h = arr[1].trim();	
                  
                  allParam.add(params);
            	}
       	
            }
            
            for ( int i = 0; i<allParam.size(); i++){
          	  System.out.println( allParam.get(i).toString());
            }
            
            
        } 
        catch (IOException ex) {
          
        }
        
   finally{
     
     if(reader2!=null)
  		try {
  			reader2.close();
  		} catch (IOException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
  }


   
  } 
	
	
}
