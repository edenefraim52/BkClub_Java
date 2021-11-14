import java.awt.*;
import javax.swing.*;

public class Soldier extends Person
{
	private String personalNum;
	private JTextField soldierField;
	private JLabel labelSoldier;
	private  JLabel soldierError;
	public Soldier()
    {
        super();
        labelSoldier = new JLabel("personal No.");
        labelSoldier.setBounds(0, 120,120, 40);
        labelSoldier.setHorizontalAlignment(4);
        labelPanel.add(labelSoldier);
        soldierField = new JTextField();
        soldierField.setBounds(0,120,250, 25);
        panelText.add(soldierField);
        soldierError = new JLabel("*");
        soldierError.setForeground(Color.red);
        soldierError.setVisible(false);
        soldierError.setBounds(0,120,120,35);
        errorPanel.add(soldierError);
        setSize(450,250);
        setTitle("Solider Clubber's Data");

    }
@Override
public boolean match(String key)
{
if(super.match(key))return true;
return ( personalNum.equals(key));
}
@Override
protected boolean validateData()
{
	if(!super.validateData())return false;
    if(!soldierField.getText().matches("[ROC]+\\/[1-9]\\d{6}"))
    {
    soldierError.setVisible(true);
    return false;
    }
    soldierError.setVisible(false);
    return true;
}
@Override
protected void commit()
{
	super.commit();
    personalNum=soldierField.getText();
}
@Override
protected void rollBack()
{
	super.rollBack();
   soldierField.setText(personalNum);
}
}
	