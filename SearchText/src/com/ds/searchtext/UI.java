package com.ds.searchtext;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
  private JLabel tabName;
  private JLabel copyright;
  private JTextField logFile_textfield;
  private JLabel percentile_lable;
  private JTextField percentile_textfield;
  private JLabel sla_lable;
  private JTextField sla_textfield;
  private JButton browse_button;
  private JButton generate_button;
  private JButton export_button;
  Border border;
  JFrame frame;
  private JFileChooser fileChooser;
  
  public void create_UI()
  {
    Font font = new Font("Georgia", 0, 14);
    
    this.border = BorderFactory.createLineBorder(Color.BLACK, 1);
    try
    {
      UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
      UIMode = "Nimbus";
    }
    catch (Exception localException) {}
    this.header_lable = new JLabel("Search Text");
 //   this.header_lable.setForeground(Color.decode("#6495ed"));
/*    InputStream imageInputStream = getClass().getClassLoader().getResourceAsStream("img/Mastek.png");
    Image imagelogo = null;
    try
    {
      imagelogo = ImageIO.read(imageInputStream);
    }
    catch (IOException e2)
    {
      e2.printStackTrace();
    }
    ImageIcon imageIcon = new ImageIcon(imagelogo);
    */
 //   this.header_logo = new JLabel(imageIcon);
    this.tabName = new JLabel("      STR       ");
    this.tabName.setFont(font);
    this.tabName.setOpaque(true);
    this.tabName.setBackground(Color.WHITE);
    this.tabName.setBorder(this.border);
    
    this.logFile_lable = new JLabel("Log File Path :");
    this.logFile_textfield = new JTextField(4);
    
    
    this.percentile_lable = new JLabel("Percentile:");
    this.percentile_textfield = new JTextField();
    
    this.sla_lable = new JLabel("SLA(in secs):");
    this.sla_textfield = new JTextField();
    
    new JLabel("Output:");
    new JRadioButton("CSV");
    new JRadioButton("HTML");
    
    this.browse_button = new JButton("Browse");
    this.generate_button = new JButton("Generate");
    this.export_button = new JButton("Export");
    
    this.copyright = new JLabel("� 2017 Developed by Mastek Ltd.");
    this.copyright.setFont(new Font("Arial", 0, 10));
    setLayout(null);
  
    
    add(this.logFile_lable).setBounds(10, 70, 120, 30);
    add(this.logFile_textfield).setBounds(120, 70, 550, 30);
    add(this.browse_button).setBounds(670, 70, 105, 30);
    
    add(this.percentile_lable).setBounds(10, 120, 120, 30);
    add(this.percentile_textfield).setBounds(120, 120, 130, 30);
    
    add(this.sla_lable).setBounds(255, 120, 100, 30);
    add(this.sla_textfield).setBounds(330, 120, 130, 30);
    
    add(this.generate_button).setBounds(460, 120, 155, 30);
    add(this.export_button).setBounds(620, 120, 155, 30);
    
    this.browse_button.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        UI.this.fileChooser = new JFileChooser();
        int r = UI.this.fileChooser.showOpenDialog(UI.this.frame);
        if (r == 0) {
          UI.this.logFile_textfield.setText(UI.this.fileChooser.getSelectedFile().getPath());
        }
      }
    });

   
  }
  
  /*public boolean validation()
  {
    if (StartExecution.inputFile.equals(""))
    {
      selectValidFile();
      return false;
    }
    if ((StartExecution.percentile <= 0) || (StartExecution.percentile > 100))
    {
      JOptionPane.showMessageDialog(this.frame, 
        "Please Enter Percentile Between 1-100.", 
        "Warning!", 
        2);
      return false;
    }
    if ((Report.isSLA) && 
      (StartExecution.sla <= 0.0D))
    {
      JOptionPane.showMessageDialog(this.frame, 
        "Please Enter SLA Greater than 0.", 
        "Warning!", 
        2);
      return false;
    }
    return true;
  }*/
 /* 
  public void selectValidFile()
  {
    JOptionPane.showMessageDialog(this.frame, 
      "Please Select Valid Log File.", 
      "Warning!", 
      2);
    StartExecution.generated = false;
  }
  */
  public void view_UI()
  {
    this.frame = new JFrame("Single User Transaction Report");
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
  //  top.add(this.header_logo, "East");
    top.setBackground(Color.decode("#F0F8FF"));
    
    middle.setLayout(new BorderLayout());
    middle.add(this.tabName, "West");
    middle.setBackground(Color.decode("#F0F8FF"));
    middle.setOpaque(false);
    
    setBorder(this.border);
    setBackground(Color.decode("#191970"));
    
    top.setOpaque(false);
    container.add(top).setBounds(5, 0, 785, 70);
    container.add(middle).setBounds(5, 70, 785, 20);
    container.add(this).setBounds(5, 89, 785, 170);
    container.add(this.copyright).setBounds(598, 256, 200, 20);
    
    container.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
    container.setBackground(Color.decode("#B8F6FD"));
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
  /*  try
    {
      InputStream iconInputStream = getClass().getClassLoader().getResourceAsStream("img/icon.png");
      icon = ImageIO.read(iconInputStream);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }*/
    this.frame.setIconImage(icon);
    this.frame.setVisible(true);
    this.frame.setResizable(false);
  }
  
  public void actionPerformed(ActionEvent arg0) {}
}
