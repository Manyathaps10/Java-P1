import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class TicTacToe {
	private JFrame frame;
	private JPanel[] panels;
	private JLabel label;
	private JButton[] buttons;
	private JButton exit,reset;
	private ImageIcon icon1,icon2;
	private int player=1;
	private int count=0;
	private boolean winnerFound=false;
	
	
	public TicTacToe(){
		frame=new JFrame("Tic Tac Toe");
		frame.setSize(500,600);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);//to close the JVM
		frame.getContentPane().setBackground(Color.black);
		frame.setLayout(null);
		addPanels();
		loadImages();
		frame.setVisible(true);
	}
	
	private void loadImages() {
		icon1=new ImageIcon(getClass().getResource("images/cross.png"));//if you are calling a method and not giving reference then it will be considered your class method
		icon2=new ImageIcon(getClass().getResource("images/circle.png"));//hr class ke pass 11 default method hote hai kyunki hr class ki supermost class object hoti hai aur object ke pass ye default method hote	
	}
	
	private void addPanels() {
		panels=new JPanel[3];
		for(int i=0;i<panels.length;i++) {
			panels[i]=new JPanel();
			frame.add(panels[i]);
		}
		panels[0].setBounds(50,30,400,50);
		panels[1].setBounds(50,100,400,350);
		panels[2].setBounds(50,480,400,40);
		panels[2].setOpaque(false);
		addLabel();		
	}

	private void addLabel() {
		label=new JLabel("First Player turn...");
		label.setFont(new Font("elephant",Font.PLAIN,25));
		label.setForeground(Color.blue);
		panels[0].setBackground(Color.cyan);
		panels[0].add(label);
		addButtons();		
	}

	private void addButtons() {
		buttons=new JButton[9];
		panels[1].setLayout(new GridLayout(3,3));
		TicListener listener=new TicListener();
		for(int i=0;i<buttons.length;i++) {
			buttons[i]=new JButton();
			buttons[i].addActionListener(listener);
			buttons[i].setBackground(Color.yellow);
			panels[1].add(buttons[i]);
		}
		addExitAndResetButton();	
	}

	private void addExitAndResetButton() {
		reset= new JButton("RESET");
		panels[2].add(reset);
		exit=new JButton("EXIT");
		panels[2].add(exit);
		Font font=new Font("arial",Font.PLAIN,18);//making object because have to add color in two buttons
		reset.setFont(font);
		exit.setFont(font);
		reset.setEnabled(false);
		exit.setForeground(Color.red);
		reset.addActionListener(new ResetListener());
		exit.addActionListener(new ExitListener());
	}
	
	class TicListener implements ActionListener
	{
		public void actionPerformed(ActionEvent evt) {
			JButton bb=(JButton)evt.getSource();//JButton mai typecast nhi kiya to type mismatch hoga kyunki getsource ka return type object hai i.e. supermost class
			//JOptionPane.showMessageDialog(frame, "Button clicked");
			if(player==1)
			{
				bb.setIcon(icon1);
				label.setText("Second Player turn...");
				panels[0].setBackground(Color.white);
				label.setForeground(Color.black);
				player=2;
			}
			else if(player==2)
			{
				bb.setIcon(icon2);
				label.setText("First Player turn...");
				panels[0].setBackground(Color.cyan);
				label.setForeground(Color.blue);
				player=1;
			}
			bb.setEnabled(false);
			findWinner();	
			count++;
			if(count==9 && !winnerFound)
			{
				label.setText("GAME IS OVER...");
				panels[0].setBackground(Color.red);
				label.setForeground(Color.white);
				reset.setEnabled(true);
				JOptionPane.showMessageDialog(frame, "Game ended in a tie");
			}
		}
		
		private void findWinner()
		{
			if(buttons[0].getIcon()==icon1 && buttons[1].getIcon()==icon1 && buttons[2].getIcon()==icon1)
				announceWinner(0,1,2);
			if(buttons[0].getIcon()==icon2 && buttons[1].getIcon()==icon2 && buttons[2].getIcon()==icon2)
				announceWinner(0,1,2);
			if(buttons[3].getIcon()==icon1 && buttons[4].getIcon()==icon1 && buttons[5].getIcon()==icon1)
				announceWinner(3,4,5);
			if(buttons[3].getIcon()==icon2 && buttons[4].getIcon()==icon2 && buttons[5].getIcon()==icon2)
				announceWinner(3,4,5);
			if(buttons[6].getIcon()==icon1 && buttons[7].getIcon()==icon1 && buttons[8].getIcon()==icon1)
				announceWinner(6,7,8);
			if(buttons[6].getIcon()==icon2 && buttons[7].getIcon()==icon2 && buttons[8].getIcon()==icon2)
				announceWinner(6,7,8);
			if(buttons[0].getIcon()==icon1 && buttons[3].getIcon()==icon1 && buttons[6].getIcon()==icon1)
				announceWinner(0,3,6);
			if(buttons[0].getIcon()==icon2 && buttons[3].getIcon()==icon2 && buttons[6].getIcon()==icon2)
				announceWinner(0,3,6);
			if(buttons[1].getIcon()==icon1 && buttons[4].getIcon()==icon1 && buttons[7].getIcon()==icon1)
				announceWinner(1,4,7);
			if(buttons[1].getIcon()==icon2 && buttons[4].getIcon()==icon2 && buttons[7].getIcon()==icon2)
				announceWinner(1,4,7);
			if(buttons[2].getIcon()==icon1 && buttons[5].getIcon()==icon1 && buttons[8].getIcon()==icon1)
				announceWinner(2,5,8);
			if(buttons[2].getIcon()==icon2 && buttons[5].getIcon()==icon2 && buttons[8].getIcon()==icon2)
				announceWinner(2,5,8);
			if(buttons[0].getIcon()==icon1 && buttons[4].getIcon()==icon1 && buttons[8].getIcon()==icon1)
				announceWinner(0,4,8);
			if(buttons[0].getIcon()==icon2 && buttons[4].getIcon()==icon2 && buttons[8].getIcon()==icon2)
				announceWinner(0,4,8);
			if(buttons[2].getIcon()==icon1 && buttons[4].getIcon()==icon1 && buttons[6].getIcon()==icon1)
				announceWinner(2,4,6);
			if(buttons[2].getIcon()==icon2 && buttons[4].getIcon()==icon2 && buttons[6].getIcon()==icon2)
				announceWinner(2,4,6);
		}
		
		private void disableButtons()
		{
			for(JButton b:buttons)
				b.setEnabled(false);
		}
		
		private void announceWinner(int i,int j,int k)
		{
			winnerFound=true;
			buttons[i].setBackground(Color.green);
			buttons[j].setBackground(Color.green);
			buttons[k].setBackground(Color.green);
			label.setText("GAME IS OVER...");
			panels[0].setBackground(Color.red);
			label.setForeground(Color.white);
			disableButtons();
			reset.setEnabled(true);
			if(player==2)
				JOptionPane.showMessageDialog(frame, "First player has won");
			else
				JOptionPane.showMessageDialog(frame, "Second player has won");		
		}		
	}
	
	class ResetListener implements ActionListener
	{
		public void actionPerformed(ActionEvent evt) {
			player=1;
			count=0;
			winnerFound=false;
			label.setText("First Player turn...");
			panels[0].setBackground(Color.cyan);
			label.setForeground(Color.blue);
			for(JButton b:buttons)
			{
				b.setEnabled(true);
				b.setIcon(null);
				b.setBackground(Color.yellow);
			}
			reset.setEnabled(false);
		}
		
	}
	
	class ExitListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			int ch=JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit");
			if(ch==JOptionPane.YES_OPTION)
				System.exit(0);//terminates JVM
		}	
	}

	public static void main(String[] args) {
		new TicTacToe();
	}
}
