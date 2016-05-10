import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;



public class IMGAlbum extends JFrame
{
   ArrayList<IMG> al = new ArrayList<IMG>();
   //IMGLoader loader = new IMGLoader();
   
   int windowWidth = 200;
   int windowHeight = 800;
   
   
   /**
   *Constructor adapted from project 1
   *Accepts a string arraylist which has the list of images that need to be withing an album
   */
   public IMGAlbum(ArrayList<String> files)
   {
      this.setSize(windowWidth,windowHeight);
      JPanel contentPane = (JPanel)this.getContentPane();
      contentPane.setLayout(new BorderLayout());
            
      //thumbnails(icons) panel 
      JPanel iconsPanel = new JPanel();
      iconsPanel.setLayout(new GridLayout(0, 1));
      iconsPanel.setMinimumSize(new Dimension(windowWidth, windowHeight));
      iconsPanel.setPreferredSize(new Dimension(windowWidth, windowHeight));
      iconsPanel.setMaximumSize(new Dimension(windowWidth, windowHeight));
      
      //start the loader
      IMGLoader loader = new IMGLoader(files);
      
      //load images
      al = loader.getImages();
      
      //create thumbnail buttons 
      for(IMG i:al)
      {
         JButton button = new JButton();
         button.setIcon(i.getIcon());
         //add buttons
         iconsPanel.add(button);
         
         //action listener to display images
         button.addActionListener(
               new ActionListener()
               {
                  public void actionPerformed(ActionEvent e)
                  {
                     displayFrame(i);            
                  }
               });
      }
      
      //add thumnails panel to scrollpane
      JScrollPane scrollPane = new JScrollPane(iconsPanel);
      //add thumbnails to frame
      contentPane.add(scrollPane, BorderLayout.CENTER);
      setVisible(true);         
   }
   
   
/**********************************************PROJECT 1 CODE*********************************************************/   
   /**
   *MAIN CLASS OF THE PROJECT 1 IMAGE ALBUM 
   *Loads all images located in a selected folder
   *Images are all shown as thumbnails
   *Images can be edited and saved (bugged)
   */
   public IMGAlbum()
   {
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      this.setSize(windowWidth,windowHeight);
      JPanel contentPane = (JPanel)this.getContentPane();
      contentPane.setLayout(new BorderLayout());
            
      //thumbnails(icons) panel 
      JPanel iconsPanel = new JPanel();
      iconsPanel.setLayout(new GridLayout(0, 1));
      iconsPanel.setMinimumSize(new Dimension(windowWidth, windowHeight));
      iconsPanel.setPreferredSize(new Dimension(windowWidth, windowHeight));
      iconsPanel.setMaximumSize(new Dimension(windowWidth, windowHeight));
      
      //start the loader
      IMGLoader loader = new IMGLoader();
      
      //load images
      al = loader.getImages();
      
      //create thumbnail buttons 
      for(IMG i:al)
      {
         JButton button = new JButton();
         button.setIcon(i.getIcon());
         //add buttons
         iconsPanel.add(button);
         
         //action listener to display images
         button.addActionListener(
               new ActionListener()
               {
                  public void actionPerformed(ActionEvent e)
                  {
                     displayFrame(i);            
                  }
               });
      }
      
      //add thumnails panel to scrollpane
      JScrollPane scrollPane = new JScrollPane(iconsPanel);
      //add thumbnails to frame
      contentPane.add(scrollPane, BorderLayout.CENTER);
      setVisible(true);         
   }
   
   //method used to create the display frame and to imitate the repaint() method
   public void displayFrame(IMG i)
   {
      //new frame to display images
      JFrame frame = new JFrame(); 
      JLabel label = new JLabel(i.getIcon());
      JScrollPane displayPane = new JScrollPane(label);
                     
      frame.setSize(600,600);
      frame.setLayout(new BorderLayout());
      frame.add(displayPane, BorderLayout.CENTER);
                     
      JPanel buttons = new JPanel();
      buttons.setLayout(new FlowLayout());
      JButton resize = new JButton("Resize");
      JButton size = new JButton("Current Size");
      JButton rotate = new JButton("Rotate");
      JButton flip = new JButton("Flip");                     
      JButton save = new JButton("Save");
   
      if(i.getType().equals("gif"))
      {
         resize.setEnabled(false);
         rotate.setEnabled(false);
         flip.setEnabled(false);
         save.setEnabled(false);
      }
                     
      resize.addActionListener(
            new ActionListener()
            {
               public void actionPerformed(ActionEvent c)
               {
                  JOptionPane.showMessageDialog(frame, "Current size is(WxH) > " + i.getWidth() + " x " + i.getHeight());
                  String w = JOptionPane.showInputDialog(frame,"Which width do you want?", null);
                  String h = JOptionPane.showInputDialog(frame,"Which height do you want?", null);
                              
                  try
                  {
                     IMGEditor editor = new IMGEditor(i);
                     editor.resize(Integer.parseInt(w),Integer.parseInt(h));
                     frame.setVisible(false);
                     displayFrame(i);
                  }
                  catch(Exception e)
                  {JOptionPane.showMessageDialog(frame, e.getMessage());
                     
                     JOptionPane.showMessageDialog(frame, "Only numbers please!");
                  }           
               }
            }
                     );
                     
      size.addActionListener(
            new ActionListener()
            {
               public void actionPerformed(ActionEvent c)
               {
                  JOptionPane.showMessageDialog(frame, "Current size is(WxH) > " + i.getWidth() + " x " + i.getHeight());
               }
            }
                     );
   
      flip.addActionListener(
            new ActionListener()
            {
               public void actionPerformed(ActionEvent c)
               {
                  IMGEditor editor = new IMGEditor(i);
                  editor.flip();
                  frame.setVisible(false);
                  displayFrame(i);           
               }
            }
                     );
                     
      rotate.addActionListener(
            new ActionListener()
            {
               public void actionPerformed(ActionEvent c)
               {
                  try
                  {
                     String deg = JOptionPane.showInputDialog(frame,"How many degrees? (90, 180, 270, 360)", null);
                     
                     IMGEditor editor = new IMGEditor(i);
                     editor.rotate(Double.parseDouble(deg));
                     frame.setVisible(false);
                     displayFrame(i);           
                  }
                  catch(Exception e)
                  {
                     JOptionPane.showMessageDialog(frame, "Only numbers please!");
                  }
               }
            }
                     );
                     
      save.addActionListener(
            new ActionListener()
            {
               public void actionPerformed(ActionEvent c)
               {
                  IMGEditor editor = new IMGEditor(i);
                  editor.saveIMG();          
               }
            }
                     );
                     
                     
                     
      buttons.add(resize);
      buttons.add(size);
      buttons.add(rotate);
      buttons.add(flip);
      buttons.add(save);
                     
      frame.add(buttons, BorderLayout.SOUTH);
                     
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
   }
   /*public static void main(String[]args)
   {
      IMGAlbum album = new IMGAlbum();
      album.setVisible(true);
   }*/
}