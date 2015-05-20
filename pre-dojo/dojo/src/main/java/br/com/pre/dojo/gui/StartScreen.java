package br.com.pre.dojo.gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class StartScreen extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7095421327352795242L;

	private JPanel contentPane;
	private JTextPane textPane;
	private JButton generate;
	private JButton choose;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartScreen frame = new StartScreen();
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
	public StartScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 503, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setText("Entre com o arquivo de leitura");
		lblNewLabel.setBounds(41, 13, 242, 14);
		contentPane.add(lblNewLabel);

		textPane = new JTextPane();
		textPane.setBounds(10, 50, 364, 20);
		textPane.setEditable(false);
		contentPane.add(textPane);

		choose = new JButton("Escolher");
		choose.setFont(new Font("Dialog", Font.PLAIN, 10));
		choose.setBounds(384, 39, 79, 31);
		choose.addActionListener(this);
		contentPane.add(choose);

		generate = new JButton("Gerar Ranking");
		generate.setFont(new Font("Dialog", Font.PLAIN, 10));

		generate.setBounds(26, 147, 101, 23);
		generate.addActionListener(this);
		generate.setEnabled(false);
		contentPane.add(generate);

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == choose) {

			JFileChooser fc = new JFileChooser();

			// restringe a amostra a diretorios apenas
			fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

			int res = fc.showOpenDialog(null);

			if (res == JFileChooser.APPROVE_OPTION) {
				File diretorio = fc.getSelectedFile();
				textPane.setText(diretorio.getAbsolutePath());
				generate.setEnabled(true);

			} else {
				JOptionPane.showMessageDialog(null,
						"Voce nao selecionou nenhum diretorio.");
			}
		}else if(e.getSource() == generate) {
			
			RankingReport rr= new RankingReport(textPane.getText());
			rr.setVisible(true);
		}
	}
	
	

}
