package com.ds.searchtext;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class UI
  extends JPanel
  implements ActionListener
{
  public static String UIMode = "Windows";
  public static Image icon = null;
  private JLabel header_lable;
  private JLabel logFile_lable;
  private JTextField logFile_textfield;
  private JLabel percentile_lable;
  private JTextField percentile_textfield;
  private JButton browse_button;
  private JButton generate_button;
  Border border;
  JFrame frame;
   String filePath;
   String data;
  JFileChooser chooser = new JFileChooser();
  
 
Search sc = new Search();
  
  public void create_UI()
  {
    this.border = BorderFactory.createLineBorder(Color.BLACK, 1);
    try
    {
      UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
      UIMode = "Nimbus";
    }
    catch (Exception localException) {}
    this.header_lable = new JLabel("Search Text");
    
    this.logFile_lable = new JLabel("File Path :");
    this.logFile_textfield = new JTextField(4);
    
    
    this.percentile_lable = new JLabel("Text to Find:");
    this.percentile_textfield = new JTextField();
    this.browse_button = new JButton("Browse");
    this.generate_button = new JButton("Find");

 
    setLayout(null);
  
    
    add(this.logFile_lable).setBounds(10, 70, 120, 30);
    add(this.logFile_textfield).setBounds(120, 70, 550, 30);
    add(this.browse_button).setBounds(670, 70, 105, 30);
    
    add(this.percentile_lable).setBounds(10, 120, 120, 30);
    add(this.percentile_textfield).setBounds(120, 120, 400, 30);
    
    add(this.generate_button).setBounds(600, 120, 155, 30);
    
    this.browse_button.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
    
    	    chooser.setCurrentDirectory(new java.io.File("."));
    	    chooser.setDialogTitle("choosertitle");
    	    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    	    chooser.setAcceptAllFileFilterUsed(false);

    	    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
    	      System.out.println("getCurrentDirectory(): " + chooser.getCurrentDirectory());
    	      UI.this.logFile_textfield.setText(chooser.getSelectedFile().getAbsolutePath());
    	      System.out.println("getSelectedFile() : " + chooser.getSelectedFile());
    	    } else {
    	      System.out.println("No Selection ");
    	    }
   
      }
    });

    this.generate_button.addActionListener(new ActionListener()
    {

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			Search s1 = new Search();
			filePath = (chooser.getSelectedFile().getAbsolutePath());
			System.out.println(filePath);
			data = percentile_textfield.getText();
			System.out.println(data);

			getData();
		}
    	
    });
    }
   

  
  public void view_UI()
  {
    this.frame = new JFrame("Search Text");
    this.frame.setDefaultCloseOperation(3);
    JPanel container = new JPanel();
    container.setLayout(new BorderLayout(5, 5));
    JPanel top = new JPanel();
    JPanel middle = new JPanel();
    
    container.setLayout(null);
    Font font = new Font("Georgia", 0, 26);
    this.header_lable.setFont(font);
    
    top.setLayout(new BorderLayout());
    top.add(this.header_lable, "West");

    middle.setLayout(new BorderLayout());

    middle.setOpaque(false);
    
    setBorder(this.border);
    
    top.setOpaque(false);
    container.add(top).setBounds(5, 0, 785, 70);
    container.add(middle).setBounds(5, 70, 785, 20);
    container.add(this).setBounds(5, 89, 785, 170);
    
    container.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
 
    this.frame.add(container);
    
    this.frame.getContentPane().setBackground(Color.BLACK);
    this.frame.pack();
    this.frame.setSize(800, 300);
    int width = 800;
    int height = 302;
    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (screen.width - width) / 2;
    int y = (screen.height - height) / 2;
    this.frame.setLocation(x, y);

    this.frame.setIconImage(icon);
    this.frame.setVisible(true);
    this.frame.setResizable(false);
  }
  
  
  static ArrayList<String> fileList = new ArrayList<String>();
	public void listFiles(File directory)
	{
		for(final File fileEntry : directory.listFiles())
		{
			if(fileEntry.isDirectory())
			{
				listFiles(fileEntry);
			}
			else
			{		
					if(fileEntry.getName().contains(".txt"))
					{
						fileList.add(fileEntry.getAbsolutePath());
					//	System.out.println(fileEntry.getAbsolutePath());
					}
				
			}
		}
	}
	
	public void getData()
	{
		Search s1 = new Search();
		File f1 = new File(filePath);
		listFiles(f1);
		System.out.println(fileList);
		Iterator<String> itr = fileList.iterator();
		int flag = 0;
		while(itr.hasNext())
		{
			String fileName = itr.next();
			File file = new File(fileName);
			try {
				
				BufferedReader br = new BufferedReader(new FileReader(file));
				String readInfo;
				String value = data;
				int count = 0;
			//	System.out.println("--------------------"+fileName+"---------------------------");
				while((readInfo= br.readLine())!=null)
				{
					count++;
					if(readInfo.contains(value))
					{
						flag++;
						System.out.println("Data found in file "+fileName+ " Line Number "+count);
					}
				}
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		System.out.println(flag);
		if(flag ==0 )
		{
		System.out.println("Data not found in any file");
		}
		
	}
  
  public void actionPerformed(ActionEvent arg0) {}
}
