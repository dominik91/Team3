import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.*;

/**
*Adapter class for the projects 1 and 2
*based on user input either the new version of the project is loaded or the old one
*/
public class AlbumAdapter {
   
   
   public static void main (String[] args){
   
      int windowWidth = 200;
      int windowHeight = 800;
   
      JFrame frame = new JFrame("Image Album GUI");
      frame.setVisible(true);
      frame.setSize(400,250);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   
      JPanel panel = new JPanel();
      frame.add(panel);
      JButton button = new JButton("Project 1");
      panel.add(button);
   
      button.addActionListener(
            new ActionListener()
            {
               public void actionPerformed(ActionEvent c)
               {  
                  frame.setVisible(false);
                  IMGAlbum album = new IMGAlbum();
                  album.setVisible(true);           
               }
            }
                     );
      JButton button2 = new JButton("Project 2");
      panel.add(button2); 
      
      ////////BUTTON ACTION FOR LOADING THE NEW VERSION
      button2.addActionListener(
            new ActionListener()
            {
               public void actionPerformed(ActionEvent c)
               {  
                  frame.setVisible(false);
                  
                  try {
                     //ALBUM LIST FORM CREATION
                     JFrame albumsFrame = new JFrame();
                     
                     JPanel contentPane = (JPanel)albumsFrame.getContentPane();
                     contentPane.setLayout(new BorderLayout());
                     
                     JPanel iconsPanel = new JPanel();
                     iconsPanel.setLayout(new GridLayout(0, 1));
                     
                     iconsPanel.setMinimumSize(new Dimension(windowWidth, windowHeight));
                     iconsPanel.setPreferredSize(new Dimension(windowWidth, windowHeight));
                     iconsPanel.setMaximumSize(new Dimension(windowWidth, windowHeight));
                     
                     //reading the xml file with the data about albums
                     File fXmlFile = new File("data.xml");
                     DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                     DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                     Document doc = dBuilder.parse(fXmlFile);
                     
                     doc.getDocumentElement().normalize();
                  
                     NodeList nList = doc.getElementsByTagName("album");
                  
                     for (int temp = 0; temp < nList.getLength(); temp++) {
                     
                        Node nNode = nList.item(temp);
                     
                        //for every node (album) create a button
                        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        
                           Element eElement = (Element) nNode;
                           ArrayList<String> imageNames = new ArrayList<String>();
                        
                           for(int i = 0; i< eElement.getElementsByTagName("img").getLength(); i++)
                           {
                              imageNames.add("images/" + eElement.getElementsByTagName("img").item(i).getTextContent());   //get names of files to be displayed by this album
                           }       
                           JButton albumButton = new JButton();
                           albumButton.setText( eElement.getElementsByTagName("name").item(0).getTextContent());
                           
                           iconsPanel.add(albumButton);
                           albumButton.addActionListener(
                                 new ActionListener()
                                 {
                                    public void actionPerformed(ActionEvent e)
                                    {
                                       new IMGAlbum(imageNames);            
                                    }
                                 });
                        }
                        
                        JScrollPane scrollPane = new JScrollPane(iconsPanel);
                     
                        contentPane.add(scrollPane, BorderLayout.CENTER);
                        //albumsFrame.pack();
                        albumsFrame.setVisible(true);
                     }
                  } 
                  catch (Exception e) {
                     e.printStackTrace();
                  }           
               }
            }
                     );     
   }   
}