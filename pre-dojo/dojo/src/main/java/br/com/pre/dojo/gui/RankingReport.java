package br.com.pre.dojo.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import br.com.pre.dojo.service.PrintService;

public class RankingReport extends JFrame implements ActionListener, WindowListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1651203080362512495L;
	private JPanel contentPane;
	private  JTextArea textArea;
	private  String fileName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RankingReport frame = new RankingReport();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public RankingReport(String fileName){
		this();
		this.fileName=fileName;
		
		
		
	}
	public RankingReport() {
		setTitle("Resultado do Ranking");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		this.addWindowListener(this);
		textArea = new JTextArea();
		textArea.setBounds(41, 13, 242, 14);
		 JScrollPane scrollingArea = new JScrollPane(textArea);
		 contentPane.add(scrollingArea, BorderLayout.CENTER);
		//contentPane.add(textArea);
		
	}

	
	public void actionPerformed(ActionEvent e) {

	
	}
		
	public void windowClosing(WindowEvent e) {
	   
	    this.dispose();
	    System.exit(0);
	  }
	
	  public void windowOpened(WindowEvent e) {
		  
		  PrintService printService= new PrintService(fileName,textArea);
		  printService.printResult();
	  }
	  
	  
	  public void windowClosed(WindowEvent e) {
		  
	  }
	  
	  public void windowIconified(WindowEvent e) {
		  
	  }
	  
	  public void windowDeiconified(WindowEvent e) {
	  
	  }
	  
	  public void windowActivated(WindowEvent e) {
	  
	  }
	  
	  public void windowDeactivated(WindowEvent e) {
	  
	  }
	
	
	
}
