import javax.swing.*;
import java.io.*;
import java.util.*;

/**
*LOADER method handles choosing of the directory for images
*/
public class IMGLoader
{
   ArrayList<IMG> al = new ArrayList<IMG>();
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
         for (int i = 0; i < listOfFiles.length; i++) 
         {
            fName = listOfFiles[i].getName();
            if (listOfFiles[i].isFile()&&((fName.substring(fName.length()-3, fName.length())).equals("jpg"))) 
            {
               al.add(new IMG(listOfFiles[i]));
            } 
         }
      } 
      else {
         System.out.println("No Selection ");
      }
   }
   
   public ArrayList getImages()
   {
      return al;
   }
}