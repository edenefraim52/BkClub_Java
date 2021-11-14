import java.awt.*;
import javax.swing.*;

public class Student extends Person
{
	private String studentID;
	private JTextField studentField;
	  private JLabel labelStudent;
	  private JLabel studentError;

    public Student()
    {
        super();
        labelStudent = new JLabel("student ID");
        labelStudent.setBounds(0, 120,120, 40);
        labelStudent.setHorizontalAlignment(4);
        labelPanel.add(labelStudent);
        studentField= new JTextField();
        studentField.setBounds(0,120,250, 25);
        panelText.add(studentField);
        studentError = new JLabel("*");
        studentError.setForeground(Color.red);
        studentError.setVisible(false);
        studentError.setBounds(0,120,120,35);
        errorPanel.add(studentError);
        setSize(450,250);
        setTitle("Student Clubber's Data");

    }
@Override
public boolean match(String key)
{
if(super.match(key))return true;
return (studentID.substring(3).equals(key));
}
@Override
protected boolean validateData()
{
	if(!super.validateData())return false;
    if(!studentField.getText().matches("[A-Z]{3}[1-9]+\\d{4}"))
   {
    studentError.setVisible(true);
    return false;
   }
    studentError.setVisible(false);
   return true;
}
@Override
protected void commit()
{
	super.commit();
    studentID=studentField.getText();
}
@Override
protected void rollBack()
{
	super.rollBack();
   studentField.setText(studentID);
}

}
	