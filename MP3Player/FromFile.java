import java.util.*;
import java.io.*;

/*
*Group members: D.Cvjetkovic, M.Mijalic, M.Mustapic, A.Sutalo
*
*Helper class used to read a file and get song names out of it and store them in an Iterable array
*/

public class FromFile implements Iterable<String>{

   private List<String> fileNames = new ArrayList<String>();
   
   //class takes a file name and reads that file line by line and stores it into an Iterable array
   public FromFile(String fileName){
      try{ 
         BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
         String line = br.readLine();
         while( line != null ) 
         {
            fileNames.add(line) ;
            line = br.readLine();
         }
      }catch(Exception e){}
   } 
   public Iterator<String> iterator(){
      return fileNames.iterator();
   }
}