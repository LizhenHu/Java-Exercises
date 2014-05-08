package CH17;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class GUIEventDemo extends JFrame{
	private JLabel jlbMessage = new JLabel("Hello!",JLabel.CENTER);
	private JTextField jtfMessage = new JTextField(10);
	
	//create check boxs to set the font for the message
	private JCheckBox jchkBold = new JCheckBox("Bold");
	private JCheckBox jchkItalic = new JCheckBox("Italic");
	
	private JRadioButton jrdRed = new JRadioButton("Red");
	private JRadioButton jrdGreen = new JRadioButton("Green");
	private JRadioButton jrdBlue = new JRadioButton("Blue");
	
	public static void main(String[] args){
		GUIEventDemo frame = new GUIEventDemo();
		frame.pack();
		frame.setTitle("GUIEventDemo"); 
		frame.setLocationRelativeTo(null); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		frame.setVisible(true);
	}
	
	public GUIEventDemo(){
		jlbMessage.setBorder(BorderFactory.createRaisedBevelBorder());
		add(jlbMessage, BorderLayout.CENTER);
		
		//create a panel for checkboxes
		JPanel pCheckBox = new JPanel(new GridLayout(2,1));
		pCheckBox.add(jchkBold);
		pCheckBox.add(jchkItalic);
		add(pCheckBox, BorderLayout.EAST);
		
		//create a panel for radio buttons
		JPanel pRadioButton = new JPanel();
		pRadioButton.setLayout(new GridLayout(3,1));
		pRadioButton.add(jrdRed);
		pRadioButton.add(jrdGreen);
		pRadioButton.add(jrdBlue);
		add(pRadioButton, BorderLayout.WEST);
		
		//create a radio button group then if one button is clicked, 
		//others can not be selected at the same time
		ButtonGroup group = new ButtonGroup();
		group.add(jrdRed);
		group.add(jrdGreen);
		group.add(jrdBlue);
		
		//set initial message color to blue
		jrdBlue.setSelected(true);
		jlbMessage.setForeground(Color.blue);
		
		//create a panel to hold label and text field
		JPanel jpTextField = new JPanel();
		jpTextField.setLayout(new BorderLayout(5,0));
		jpTextField.add(new JLabel("Enter a new message"), BorderLayout.WEST);
		jpTextField.add(jtfMessage,BorderLayout.CENTER);
		jtfMessage.setHorizontalAlignment(JTextField.RIGHT);
		add(jpTextField, BorderLayout.NORTH);
		
		//register listeners with check boxes
		jchkBold.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				setNewFont();
			}
		});
		
		jchkItalic.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				setNewFont();
			}
		});
		
		jrdRed.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				jrdRed.setSelected(true);
				jlbMessage.setForeground(Color.red);
			}
		});
		
		jrdGreen.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				jrdGreen.setSelected(true);
				jlbMessage.setForeground(Color.green);
			}
		});
		
		jrdBlue.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				jrdBlue.setSelected(true);
				jlbMessage.setForeground(Color.blue);
			}
		});

		jtfMessage.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				
				jlbMessage.setText(jtfMessage.getText());
				jtfMessage.requestFocusInWindow();
			}
		});
	
	}
	
	
	private void setNewFont(){
		int fontStyle = Font.PLAIN;
		fontStyle += (jchkBold.isSelected() ? Font.BOLD : Font.PLAIN);
		fontStyle += (jchkItalic.isSelected() ? Font.ITALIC : Font.PLAIN);
		
		//set font for the message
		Font font = jlbMessage.getFont();
		jlbMessage.setFont(
				new Font(font.getName(), fontStyle, font.getSize()));
	}
}
