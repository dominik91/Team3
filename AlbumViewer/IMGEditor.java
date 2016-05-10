import java.io.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.BufferedImage;

/**
*EDITOR holds all editing methods and controlls them
*/
public class IMGEditor
{
   IMG i;
   public IMGEditor(IMG i)
   {
      this.i = i;
   }
   
   public void resize(int w, int h)
   {
      Image im = i.getBuffered().getScaledInstance(w, h, i.getBuffered().SCALE_DEFAULT);   
      i.setIcon(new ImageIcon(im));
      i.setBufferedFromImageIcon();
   }
   
   public void flip()
   {
      BufferedImage flipped  = createFlipped(i.getBuffered());
      i.setIcon(new ImageIcon(flipped));
      i.setBuffered(flipped);
   }
   
   public void rotate(double deg)
   {
      BufferedImage rotated = createRotated(i.getBuffered(), deg);
      i.setIcon(new ImageIcon(rotated));
      i.setBuffered(rotated);
   }
   
   public void saveIMG()
   {
      i.saveMe();
   }
    //rotates the image using the affine transform
   private static BufferedImage createRotated(BufferedImage image, double deg)
    {
        AffineTransform at = AffineTransform.getRotateInstance(Math.PI, image.getWidth()/2, image.getHeight()/2.0);
        return createTransformed(image, at);
    }
   
   //flipps the image using the affine transform
   private static BufferedImage createFlipped(BufferedImage image)
   {
      AffineTransform at = new AffineTransform();
      at.concatenate(AffineTransform.getScaleInstance(1, -1));
      at.concatenate(AffineTransform.getTranslateInstance(0, -image.getHeight()));
      return createTransformed(image, at);
   }
   
   //method recreates a buffered image after transformation was done on it using the affine transform 
   private static BufferedImage createTransformed(BufferedImage image, AffineTransform at)
   {
      BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(),BufferedImage.TYPE_INT_ARGB);
      Graphics2D g = newImage.createGraphics();
      g.transform(at);
      g.drawImage(image, 0, 0, null);
      g.dispose();
      return newImage;
   }
}