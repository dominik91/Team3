import javax.swing.*;
import java.io.*;
import java.util.*;

/**
*Class handles which type of an image is being loaded at the moment
*Also validates that file names are good
*/
public class IMGFactory
{
   ArrayList<IMG> al = new ArrayList<IMG>();
   public IMGFactory()
   {            
   }
   
   public void createImage(File path)
   {
      String fName = path.getName();
      String type = fName.substring(fName.length()-3, fName.length());
      if(type.equals("png"))
      {
         al.add(new IMG(path));
      }
      else if(type.equals("gif"))
      {
         al.add(new IMG(path));
      }
      else if(type.equals("jpg"))
      {
         al.add(new IMG(path));
      }
   }
   
   public ArrayList getImages()
   {
      return al;
   }
}