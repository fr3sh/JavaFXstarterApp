package com.fr3sh.config;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;
import javax.crypto.spec.SecretKeySpec;

import utils.FxmlUtils;

import com.fr3sh.JiraConf;
import com.fr3sh.JiraParam;



public class LoaderConfig {

	private static LoaderConfig instance;
    
    private LoaderConfig(){}
	
	List<JiraParam> allParam;
	public List<JiraParam> getAllParam() {
		return allParam;
	}

	public void addToAllParams(JiraParam a) {
		allParam.add(a);
		
	}
	
	public static LoaderConfig getInstanceUsingDoubleLocking(){
	    if(instance == null){
	        synchronized (LoaderConfig.class) {
	            if(instance == null){
	                instance = new LoaderConfig();
	            }
	        }
	    }
	    return instance;
	}
// Ładowanie z resources
/*	public static String nazwa_conf = "src/main/resources/config/conf.txt";
	public static String param = "src/main/resources/config/param.txt";
	public static String geckoDriver = "src/main/resources/gecoDriver/geckodriver18.exe";
	
	//for serialization
	public static String serialization_param = "src/main/resources/config/conf.txt";*/

//Ładowanie z plików obok jara	
	public static String nazwa_conf = "./conf.txt";
	public static String param = "./param.txt";
	public static String geckoDriver = "./geckodriver18.exe";
	public static String serialization_param = "./conf.txt";
	
	private JiraConf conf;
	private JiraParam params;
	 
	public JiraConf getConf() {
		
		 if(conf == null){
		        synchronized (JiraConf.class) {
		            if(conf == null){
		            	this.read_conf(nazwa_conf);
		            }
		        }
		    }
		
			 
		 
		return conf;
	}

	public void setConf(JiraConf conf) {
		this.conf = conf;
	}

	public JiraParam getParams() {
		
		 if(params == null){
			 this.read_param(param);
		 }
		return params;
	}

	public void setParams(JiraParam params) {
		this.params = params;
	}



public void  read_conf (String nazwa){
      
	
	//// Bez synchronizacji odczytywanie prosto z pliku 
  /*    File a = new File(nazwa);
     
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
}*/


      try {
    	  	FileInputStream fi = new FileInputStream(new File(serialization_param));
			ObjectInputStream oi = new ObjectInputStream(fi);
			 
			
			//WHIOUT ENCRYPTION with serialization
		/*	 conf =new JiraConf();
			 conf = (JiraConf) oi.readObject();
			 oi.close();*/
			
			 
			 //WITH ENCRYPTION
			 conf = (JiraConf) decrypt(fi);

			
			
			fi.close();

		} catch (FileNotFoundException e) {
			System.out.println("File" +serialization_param+" not found");
			conf =new JiraConf();
		} catch (IOException e) {
			System.out.println("Error initializing stream SERIALIZATION");
			conf =new JiraConf();
			//e.printStackTrace();
		} 
			
	/*	 catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} */catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      
      
      
 
}


public void save_conf (String nazwa) throws IOException {
	
	/// bez serializacji do czystego pliku 
/*	  try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(nazwa))) {
		  
		  
          writer.write("login: "+ conf.getLogin() +"\n");
          writer.write("pass: "+ conf.getPass() +"\n");
          writer.write("dni:"+ conf.getPn() +";"+ conf.getWt()  +";" + conf.getSr()  +";" + conf.getCzw()  +";" + conf.getPt()  +";"          +"\n");
         
          if (conf.getSubmit()) {
        	  writer.write("submit:y \n");  
          }else {
        	  writer.write("submit:n \n"); 
          }
          
          
          
      } // the file will be automatically closed
	  */
	  
	  //Serializacja do pliku 
	  try {
			FileOutputStream f = new FileOutputStream(new File(nazwa));
			ObjectOutputStream o = new ObjectOutputStream(f);
		
			// WHITOUT ENCRYPTION
			// Write objects to file
		//	o.writeObject(conf);
			
			///ENCRYPTION
			encrypt(conf, f);

		//	o.close();
			f.close();

			
	  }
		 catch (FileNotFoundException e) {
			System.out.println("File" +nazwa + "not found");
		} catch (IOException e) {
			System.out.println("Error initializing stream");
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	  
	  
}

private static final byte[] key = "NoNeedToSeeSteal".getBytes();
private static final String transformation = "AES";

public static void encrypt(Serializable object, OutputStream ostream) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
    try {
        // Length is 16 byte
        SecretKeySpec sks = new SecretKeySpec(key, transformation);

        // Create cipher
        Cipher cipher = Cipher.getInstance(transformation);
        cipher.init(Cipher.ENCRYPT_MODE, sks);
        SealedObject sealedObject = new SealedObject(object, cipher);

        // Wrap the output stream
        CipherOutputStream cos = new CipherOutputStream(ostream, cipher);
        ObjectOutputStream outputStream = new ObjectOutputStream(cos);
        outputStream.writeObject(sealedObject);
        outputStream.close();
    } catch (IllegalBlockSizeException e) {
        e.printStackTrace();
    }
}

public static Object decrypt(InputStream istream) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
    SecretKeySpec sks = new SecretKeySpec(key, transformation);
    Cipher cipher = Cipher.getInstance(transformation);
    cipher.init(Cipher.DECRYPT_MODE, sks);

    CipherInputStream cipherInputStream = new CipherInputStream(istream, cipher);
    ObjectInputStream inputStream = new ObjectInputStream(cipherInputStream);
    SealedObject sealedObject;
    try {
        sealedObject = (SealedObject) inputStream.readObject();
        return sealedObject.getObject(cipher);
    } catch (ClassNotFoundException | IllegalBlockSizeException | BadPaddingException e) {
        e.printStackTrace();
        return null;
    }
}


public void save_param (String nazwa) throws IOException {
	
	// problem z nowymi liniami i utf 8w nowej newBufferedwritej 
	 // try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(nazwa),StandardCharsets.UTF_8)) {
	BufferedWriter writer = null;
	try {
		 writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(nazwa), "UTF8")); 
		
		  
		//  confFile.getParams()
		  
		  for (int i = 0; i < allParam.size(); i++) {
			  writer.write("param:"+ allParam.get(i).getNazwa() );
			  writer.newLine();
			  writer.write(allParam.get(i).getNazwa() + ".txt:"+ allParam.get(i).getLink() );
			  writer.newLine();
			  writer.write(allParam.get(i).getNazwa() + ".options:");
			  for (int j = 0; j < allParam.get(i).getOptions().size(); j++) {
				System.out.println(allParam.get(i).getOptions().get(j).toString() +"\n");
				
				//String a = allParam.get(i).getOptions().get(j).toString();
				//Object f = allParam.get(i).getOptions();
				  writer.write(allParam.get(i).getOptions().get(j).toString() +";");
			  }
			 // writer.write("\n"); 
			  writer.newLine();
			//  writer.write(allParam.get(i).getNazwa() + ".importatnt: "+ allParam.get(i).getImportant() +"\n");
			  		if (allParam.get(i).getImp1()) {
		        	  writer.write(allParam.get(i).getNazwa() + ".important:y"); 
		        	  writer.newLine();
		          }else {
		        	  writer.write(allParam.get(i).getNazwa() + ".important:n"); 
		        	  writer.newLine();
		          }
			  
			  
			  writer.write(allParam.get(i).getNazwa() + ".h:");

			  writer.write(allParam.get(i).getH().get(0).intValue() +"-" );
			  writer.write(allParam.get(i).getH().get(1).intValue() +"" );
			 // writer.write("\n"); 
			  writer.newLine();
			  writer.newLine();
			  
		}
       
 
		  // releases all bytes to the underlying stream
		  writer.flush();
        
    }
	 catch (IOException e)
    {
       System.out.println(e.getMessage());
    }// the file will be automatically closed tylko przy newbufferedwriter
	finally {
    // releases system resources from the streams
    if(writer!=null)
    	writer.close();
 
	}
}


public void  read_param (String nazwa){
        
        File a = new File(nazwa);
       
        BufferedReader reader2;
        reader2 = null;
        String  thisLine = null;
        allParam = new  ArrayList<>();
        try {
        	//Problemy z UTF i character encoding
            //reader2 = new BufferedReader(new FileReader(a));
        	reader2 = new BufferedReader(new InputStreamReader(new FileInputStream(a), "UTF-8"));
        	
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
            		 String tt = arr[1].trim();
            		if (tt.contains("y")){
                    	params.Imp1Property(true);	
            		}
            		else {
            			params.Imp1Property(false);	
            		}
            		
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
