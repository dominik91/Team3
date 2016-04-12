import java.io.*;
import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.BufferedImage;


/**
*IMAGE class
*stores information about the image file as well as the buffered image which is used for manipulation
*and the incons for display purposes
*/
public class IMG
{
   ImageIcon icon;
   BufferedImage img = null;
   File f;
   public IMG(File f)
   {
      try
      {
         icon =new ImageIcon(f.toURL());
         img = ImageIO.read(f);
         this.f = f;
      }
      catch(Exception e)
      {
      }
   }
   
   public BufferedImage getBuffered()
   {
      return img;
   }
   
   public Icon getIcon()
   {
      return icon;
   }
   
   public int getHeight()
   {
      return icon.getIconHeight();
   }
   
   public int getWidth()
   {
      return icon.getIconWidth();
   }
   
   //method updates the icon
   public void setIcon(ImageIcon icon)
   {
      this.icon = icon;
   }
   
   //method recreates a buffered image from an image icon
   //essencial for updating an image more than once
   public void setBufferedFromImageIcon()
   {
      img = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
      Graphics g = img.createGraphics();
      icon.paintIcon(null, g, 0,0);
      g.dispose();
   }
   
   //method updates the buffered image
   //essencial for updating an image more than once
   public void setBuffered(BufferedImage img)
   {
      this.img = img;
   }
   
   //saves the image that was edited
   public void saveMe()
   {
      try
      {
         ImageIO.write(img, "jpg", new File("edited" + f.getName()));
      }
      catch(Exception e)
      {
         System.out.println(e.getMessage());            
      }
   }
}