import java.awt.*;
import javax.swing.*;

public class Person extends ClubAbstractEntity {
    private String[] personData = {"ID", "Name", "Surname", "Tel"};
    private JTextField[] textFields;
    private String[] RE = {"\\d+-\\d{7}\\|[1-9]",
            "[A-Z][a-z]+",
            "([A-Z][a-z]*['-]?)+",
            "\\+\\([1-9]\\d{0,2}\\)[1-9]\\d{0,2}-[1-9]\\d{6}"};

    private JLabel[] personDetails;
    private String ID;
    private String Name;
    private String Surname;
    private String Tel;
    private JLabel[] error;
    protected JPanel labelPanel;
    protected JPanel panelText;
    protected JPanel errorPanel;



    public Person() {
        super();
        personDetails = new JLabel[personData.length];
        textFields = new JTextField[personData.length];
        labelPanel = new JPanel();
        errorPanel = new JPanel();
        labelPanel.setLayout(null);
        labelPanel.setBounds(10, 0,120, 320);
        addToCenter(labelPanel);
        panelText = new JPanel();
        panelText.setLayout(null);
        panelText.setBounds(140, 10,250, 220);
        addToCenter(panelText);
        error = new JLabel[personData.length];
        errorPanel.setLayout(null);
        errorPanel.setBounds(400, 0,15, 220);
        addToCenter(errorPanel);
        setLocationRelativeTo(null);
        setSize(450, 220);
        setTitle("Person Clubber's Data");
        for (int i = 0; i < personData.length; i++) {
            personDetails[i] = new JLabel(personData[i]);
            personDetails[i].setBounds(0, 30 * i,120, 40);
            personDetails[i].setHorizontalAlignment(4);
            labelPanel.add(personDetails[i]);
            textFields[i] = new JTextField();
            textFields[i].setBounds(0, 30 * i,250, 25);
            panelText.add(textFields[i]);
            error[i] = new JLabel("*");
            error[i].setForeground(Color.red);
            error[i].setVisible(false);
            error[i].setBounds(0, 30 * i,80, 40);
            errorPanel.add(error[i]);

        }
        setVisible(true);
    }

    public boolean match(String key) {
        return (ID.equals(key));
    }

    protected boolean validateData() {
        for (int i = 0; i < RE.length; i++) {
            if (!textFields[i].getText().matches(RE[i])) {
                error[i].setVisible(true);
                return false;
            } else error[i].setVisible(false);
        }
        return true;
    }

    protected void commit() {
        ID = textFields[0].getText();
        Name = textFields[1].getText();
        Surname = textFields[2].getText();
        Tel = textFields[3].getText();

    }

    protected void rollBack() {

        textFields[0].setText(ID);
        textFields[1].setText(Name);
        textFields[2].setText(Surname);
        textFields[3].setText(Tel);
    }
}