package Tetris;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.*;
import javax.swing.*;

import java.awt.SystemColor;


public class menu implements ActionListener, WindowListener {
	
	JFrame f = new JFrame();
	game game;
	JTextField tfName;
	JButton btnStart;
	
	public menu(){

		Image logo = new ImageIcon(getClass().getResource("logo.png")).getImage();
		
		f = new JFrame();
		f.setTitle("Tetris");
		f.setResizable(false);
		f.getContentPane().setLayout(null);
		f.setLocationRelativeTo(null);
		f.setSize(220, 200);
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBackground(Color.gray);
		f.setIconImage(logo);
		f.addWindowListener(this);
				
		btnStart = new JButton("Start");
		btnStart.setBounds(32, 59, 150, 30);
		btnStart.addActionListener(this);
		btnStart.setBackground(Color.WHITE);
		f.getContentPane().add(btnStart);
		
		JLabel Logo = new JLabel("");
		Logo.setLocation(0, 5);
		Logo.setSize(214, 50);
		Logo.setHorizontalAlignment(SwingConstants.CENTER);
		Logo.setIcon(new ImageIcon(menu.class.getResource("/Tetris/tetris.png")));
		f.getContentPane().add(Logo);
		
		tfName = new JTextField("");
		tfName.setToolTipText("Seed");
		tfName.setBounds(32, 116, 150, 30);
		f.getContentPane().add(tfName);
		
		JLabel lblSeed = new JLabel("Seed:");
		lblSeed.setBounds(32, 100, 150, 14);
		f.getContentPane().add(lblSeed);
		
		JLabel Copyright =new JLabel("\u00A9Philipp Kitzm\u00FCller");
		Copyright.setVerticalAlignment(SwingConstants.BOTTOM);
		Copyright.setForeground(SystemColor.controlShadow);
		Copyright.setBounds(78, 157, 120, 15);
		f.getContentPane().add(Copyright);
		
		
		f.setVisible(true);
		game = new game(f);
	}
	
	
    public void actionPerformed (ActionEvent ae){
	  	
    	if(ae.getSource() == btnStart){
    		f.setVisible(false);
    		String seedString = tfName.getText();System.out.println("Eingabe");
    		int seed = seedGenerator(seedString);
    		game.startGame(seed);
    	}
    	
    }
	
	public void setVisible(){
		f.setVisible(true);		
	}
	
	public int seedGenerator(String seedString){
		int seed = 0;		
    	char[] c = seedString.toCharArray();
    	for(int i = 0; i < c.length; i++){
    		seed = seed *10 + (int) c[i];
    	} 	
    	
		return seed;
	}


	/////////////////////////////////////////////// Windows Listender
	
	public void windowActivated(WindowEvent e) {
		System.out.println("[WindowsListener] Fenster ist Aktiv");
	}

	
	public void windowClosed(WindowEvent e) {		
		
	}

	
	public void windowClosing(WindowEvent e) {
		System.out.println("[WindowsListener] Menu Fenster würde geschlossen");
		System.exit(0);
	}

	
	public void windowDeactivated(WindowEvent e) {}

	
	public void windowDeiconified(WindowEvent e) {}

	
	public void windowIconified(WindowEvent e) {}

	
	public void windowOpened(WindowEvent e) {}
}
