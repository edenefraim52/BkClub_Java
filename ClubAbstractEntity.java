import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public abstract class ClubAbstractEntity extends JFrame
{
private  JButton okButton;
private  JButton cancelButton;
private JPanel buttonPanel;
private JPanel centerPanel;
private ButtonsHandler handler;



public ClubAbstractEntity()
{
super();
setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
setResizable(false);
buttonPanel=new JPanel();
centerPanel=new JPanel();
centerPanel.setLayout(null);
okButton = new JButton("Ok");
cancelButton =new JButton("Cancel");
cancelButton.setEnabled(false);
buttonPanel.add(okButton);
buttonPanel.add(cancelButton);
add(buttonPanel, BorderLayout.SOUTH);
add(centerPanel);
initHandlers();

}
protected void addToCenter(Component guiComponent)
{
	centerPanel.add(guiComponent);
}
public abstract boolean match(String key);
protected abstract boolean validateData();
protected abstract void commit();
protected abstract void rollBack();

private class ButtonsHandler implements ActionListener 
{
	
      @Override
      public void actionPerformed(ActionEvent event)
      {
         if (event.getSource() == okButton)
         {
            if(validateData())
            {
            	commit();
                cancelButton.setEnabled(true);
            	setVisible(false);
            }

         }
      if (event.getSource() == cancelButton)
      {
          rollBack();
          setVisible(false);
      }
      }
}
public void initHandlers()
{
    ButtonsHandler handler = new ButtonsHandler();
    okButton.addActionListener(handler);
    cancelButton.addActionListener(handler);
}
}

