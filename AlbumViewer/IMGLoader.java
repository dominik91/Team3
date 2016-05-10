import javax.swing.*;
import java.io.*;
import java.util.*;

/**
*LOADER method handles choosing of the directory for images
*/
public class IMGLoader
{
   ArrayList<IMG> al; // = new ArrayList<IMG>();
   IMGFactory factory = new IMGFactory();
            
   public IMGLoader()
   {
      JFileChooser chooser = new JFileChooser();
      chooser.setCurrentDirectory(new java.io.File("."));
      chooser.setDialogTitle("Select folder");
      chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
      chooser.setAcceptAllFileFilterUsed(false);
   
      if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
         File folder = chooser.getSelectedFile();
           
         File[] listOfFiles = folder.listFiles();
         String fName;
         String fType;
      
         
         for (int i = 0; i < listOfFiles.length; i++) 
         {
            factory.createImage(listOfFiles[i]);
         }
      } 
      else {
         System.out.println("No Selection ");
      }
   }
   
   /**
   *Constructor for project 2
   *Uses an arraylist of filenames provided by the adapter class to this image album
   */
   public IMGLoader(ArrayList<String> files)
   {          
      for(String path : files)
      {
         factory.createImage(new File(path));
      }
   }
   
   public ArrayList getImages()
   {
      return factory.getImages();
   }
}