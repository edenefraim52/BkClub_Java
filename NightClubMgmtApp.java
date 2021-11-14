import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

/**
 * This class initialize the main window of the BK night club management App with three buttons:search,add and exit.
 * This class also initialize the popup menu with the same options and initialize the mnemonics of these options.
 * This class defines the actions of every GUI component.
 * this class extends {@link javax.swing.JFrame}
 */
public class NightClubMgmtApp extends JFrame implements ActionListener {
 //Night-Club Regular Customers Repository
 private ArrayList <ClubAbstractEntity> clubbers;
 private final  JButton searchButton;
 private final  JButton addButton;
 private final  JButton exitButton;
 private final JMenuItem searchItem;
 private final JMenuItem addItem;
 private final JMenuItem exitItem;
 private final JPopupMenu popupMenu;
 private  File f;

 /**
  * Default constructor of the class,creates the window with all the GUI components.
  * load all the cilents from file and puts them in the array.
  * @throws IOException In the case the file could not be read or it was no possible to write to it
  * @throws ClassNotFoundException In the case the path of the file is not found
  */
 public NightClubMgmtApp() throws IOException, ClassNotFoundException {
  super();
  f=new File("BKCustomers.dat");
  clubbers = new ArrayList<>();
  loadClubbersDBFromFile();
  setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
  setSize(900, 600);
  setLayout(null);
  Font f1 = new Font("serif", Font.BOLD, 40);
  Font f2 = new Font("serif", Font.BOLD, 24);
  Font f3 = new Font("serif", Font.BOLD, 16);
  JLabel titleLabel = new JLabel("B.K Club Nahariya");
  titleLabel.setForeground(Color.BLACK);
  titleLabel.setBounds(430, 40, 400, 50);
  titleLabel.setFont(f1);
  ImageIcon background = new ImageIcon("clubdisco.jpg");
  JLabel discoImage = new JLabel(background);
  discoImage.add(titleLabel);
  discoImage.setBounds(0, 0, 900, 600);
  ImageIcon magnifyingGlassImage = new ImageIcon("magnifyingGlass.png");
  searchButton = new JButton("Search", magnifyingGlassImage);
  searchButton.setHorizontalTextPosition(SwingConstants.CENTER);
  searchButton.setVerticalTextPosition(SwingConstants.BOTTOM);
  searchButton.setFont(f2);
  searchButton.setBackground(Color.WHITE);
  searchButton.setToolTipText("Search a client");
  searchButton.setMnemonic('S');
  searchButton.setBounds(325, 250, 250, 200);
  discoImage.add(searchButton);
  ImageIcon plusImage = new ImageIcon("plus.png");
  addButton = new JButton("Add", plusImage);
  addButton.setHorizontalTextPosition(SwingConstants.CENTER);
  addButton.setVerticalTextPosition(SwingConstants.BOTTOM);
  addButton.setFont(f2);
  addButton.setBackground(Color.WHITE);
  addButton.setToolTipText("Add a client");
  addButton.setMnemonic('A');
  addButton.setBounds(600, 250, 250, 200);
  discoImage.add(addButton);
  exitButton = new JButton("Exit");
  exitButton.setHorizontalTextPosition(SwingConstants.CENTER);
  exitButton.setVerticalTextPosition(SwingConstants.BOTTOM);
  exitButton.setFont(f3);
  exitButton.setBackground(Color.WHITE);
  exitButton.setToolTipText("close the program");
  exitButton.setMnemonic('E');
  exitButton.setBounds(800, 500, 70, 30);
  discoImage.add(exitButton);
  add(discoImage);
  ItemHandler handler=new ItemHandler();
  String []options={"Search","Add","Exit"};
  ButtonGroup optionGroup=new ButtonGroup();
  popupMenu=new JPopupMenu();
  searchItem=new JMenuItem("Search");
  popupMenu.add(searchItem);
  optionGroup.add(searchItem);
  searchItem.addActionListener(handler);
  addItem=new JMenuItem("Add");
  popupMenu.add(addItem);
  optionGroup.add(addItem);
  addItem.addActionListener(handler);
  exitItem=new JMenuItem("Exit");
  popupMenu.add(exitItem);
  optionGroup.add(exitItem);
  exitItem.addActionListener(handler);
  setLocationRelativeTo(null);
  setVisible(true);
  setResizable(false);
  searchButton.addActionListener(this);
  addButton.addActionListener(this);
  exitButton.addActionListener(this);
  /**
   * Anonymous inner class that responsible to respond to mouse events
   */
  addMouseListener(
          new MouseAdapter()
          {
           @Override
           /**
            * activated when mouse is pressed activate checkForTriggerEvent
            * @param event the event was occurred
            */
           public void mousePressed(MouseEvent event) {
            checkForTriggerEvent(event);
           }
           @Override
           /**
            * activated when mouse is released and activate checkForTriggerEvent
            * @param event the event was occurred
            */
           public void mouseReleased(MouseEvent event) {
            checkForTriggerEvent(event);
           }

           /**
            * when mouse event happened the popup menu is represented
            * @param event the event was occurred pressed or released
            */
           private void checkForTriggerEvent(MouseEvent event)
           {
            if(event.isPopupTrigger())
             popupMenu.show(event.getComponent(),event.getX(),event.getY());
           }
          }

  );
 }

 /**
  * inner class of NightClubMgmtApp that responsible to respond to the events that occoured in popup menu
  * and activate the mathood that belongs to what is chosen in the popup menu
  */
 private class ItemHandler implements ActionListener
 {
  @Override
  /**
   * check which item from the popup menu was selected and activate the right methood for him:
   * manipulateDB for searh item,activeAddButton for add item and activeExitButton for exit item
   */
  public void actionPerformed(ActionEvent event)
  {
     if(event.getSource()==searchItem)
     {
       manipulateDB();
     }
   if(event.getSource()==addItem)
   {
    activeAddButton();
   }
   if(event.getSource()==exitItem)
   {
    activeExitButton();
   }
     }
  }

 /**
  * ask from the user key(id or(personal num for soliders or student ID for students) of any client and
  * search of a client with the same key in the customer base. if yes the details of this client represents in the screen.
  * if not a message is displayed on the screen that there is no client with same key in the customer base
  */

 private void manipulateDB()  {

  String input = JOptionPane.showInputDialog(this, "Please Enter The Clubber's Key");
  boolean found = false;
  while (true) {
   if (input==null)return;
   for (ClubAbstractEntity clubber : clubbers)
    if (clubber.match(input)) {
     clubber.initHandlers();
     found=true;
     clubber.setVisible(true);
     return;
    }
   if (!found) {
    JOptionPane.showMessageDialog(this, "Clubber with key " + input + " does not exist", "error", JOptionPane.INFORMATION_MESSAGE);
    return;
   } else found = !found;
  }
 }// End of method manipulateDB

 /**
  * load the details of any client from BKCustomers file and puts them in the array.
  * @throws IOException In the case the file could not be read
  * @throws ClassNotFoundException In the case the path of the file is not found
  */
 private void loadClubbersDBFromFile() throws IOException, ClassNotFoundException {
//Read data from file, create the corresponding objects and put them
//into clubbers ArrayList. For example:

  if (!(f.length() == 0)) {
   FileInputStream fis = new FileInputStream("BKCustomers.dat");
   ObjectInputStream ois = new ObjectInputStream(fis);
   clubbers = (ArrayList<ClubAbstractEntity>) ois.readObject();
  }
 }

 @Override
 /**
  * check which button is pressed and activates the methods depending on what is written on the button
  * manipulateDB for search button activeAddButton for add button and activeExitButton for exit button
  * @param e the event was occurred
  */
 public void actionPerformed(ActionEvent e) {
  if (e.getSource() == searchButton) {
  manipulateDB();
  }
  if (e.getSource() == addButton)
  {
    activeAddButton();

  }
  if(e.getSource()==exitButton)
  {
   activeExitButton();
  }
 }

 /**
  * When thew user press exit all what is written in the array and write it to BKCustomers file
  * @throws IOException In the case ity was no possible to write to the file
  */
 private void writeClubbersDBtoFile() throws IOException {
  FileOutputStream fos = new FileOutputStream("BKCustomers.dat");
  ObjectOutputStream oos = new ObjectOutputStream(fos);
  oos.writeObject(clubbers);
  oos.close();
 }
 /*private void activeSearchButton()
 {
  manipulateDB();
 }*/

 /**
  * Ask from the user to enter what kind of client he want to add
  * open a window that the user can enter the details of this client and finnaly put it in the array
  */
 private void activeAddButton()
 {
  String[] selections = { "Person", "Solider", "Student" };
  Object val = JOptionPane.showInputDialog(this,"choose the type of client","add client",JOptionPane.QUESTION_MESSAGE
          ,null,selections,selections[0]);
  if (val == "Person")
   clubbers.add(new Person());
  if (val == "Solider")
   clubbers.add(new Soldier());
  if (val == "Student")
   clubbers.add(new Student());
 }

 /**
  * write all the array to BKCustomers file and ends the program
  */
 private void activeExitButton()
 {
  try {
   writeClubbersDBtoFile();
   System.exit(0);
  } catch (IOException ioException) {
   ioException.printStackTrace();
  }
 }

 public static void main(String[] args) throws IOException, ClassNotFoundException {
  NightClubMgmtApp appliction = new NightClubMgmtApp();

 }
}
//End of class NightClubMgmtApp