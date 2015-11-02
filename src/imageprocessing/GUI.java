package imageprocessing;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sun.xml.internal.ws.api.Component;

import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;


public class GUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblObjectNo, lblOriginal, lblBinary, lblColoured, lblHighlighted, lblDisplay;
	private JSlider sliderLuminance;
	private static ConnectedComponentImage CCIPictures;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private String currentImageFile;
	private Boolean blnWhiteBackground;
		
	public static void main(String[] args) {
			
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		
		
	}
 

	public void updateImages(String imageFile, Boolean background, int luminance){
		
		CCIPictures = new ConnectedComponentImage(imageFile, background, luminance);
		
		CCIPictures.getPicture().save("images\\currentPicture.jpg");
		lblOriginal.setIcon(new ImageIcon(new ImageIcon("images\\currentPicture.jpg")
				.getImage().getScaledInstance(150, 75, Image.SCALE_DEFAULT)));
		lblDisplay.setIcon(new ImageIcon(new ImageIcon("images\\currentPicture.jpg")
				.getImage().getScaledInstance(782, 317, Image.SCALE_DEFAULT)));
		
		CCIPictures.binaryComponentImage().save("images\\currentBinaryPicture.jpg");
		lblBinary.setIcon(new ImageIcon(new ImageIcon("images\\currentBinaryPicture.jpg")
				.getImage().getScaledInstance(150, 75, Image.SCALE_DEFAULT)));
		
		lblObjectNo.setText("No of Objects: " + CCIPictures.countComponents());
		
		CCIPictures.colourComponentImage().save("images\\currentColouredPicture.jpg");
		lblColoured.setIcon(new ImageIcon(new ImageIcon("images\\currentColouredPicture.jpg")
				.getImage().getScaledInstance(150, 75, Image.SCALE_DEFAULT)));
		
		CCIPictures.identifyComponentImage().save("images\\currentHighlightedPicture.jpg");
		lblHighlighted.setIcon(new ImageIcon(new ImageIcon("images\\currentHighlightedPicture.jpg")
				.getImage().getScaledInstance(150, 75, Image.SCALE_DEFAULT)));
		
		
	}
	
	public GUI() {		
				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 782, 26);
		contentPane.add(menuBar);
		
		JMenuItem mnuOpen = new JMenuItem("Open Image");
		menuBar.add(mnuOpen);
		mnuOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		        JFileChooser chooser = new JFileChooser();
		        Component parent = null;
		        chooser.setCurrentDirectory(new File("images") );
		        int returnVal = chooser.showOpenDialog((java.awt.Component) parent);
		        if(returnVal == JFileChooser.APPROVE_OPTION) {
		               currentImageFile = chooser.getSelectedFile().getAbsolutePath();
		        }
		        
		        sliderLuminance.setValue(128);
		        
		        updateImages(currentImageFile, blnWhiteBackground, sliderLuminance.getValue());
		        
		            
			}
			
			
		});
		
		lblOriginal = new JLabel("Original Picture");
		lblOriginal.setHorizontalAlignment(SwingConstants.CENTER);
		lblOriginal.setBounds(40, 40, 150, 75);
		contentPane.add(lblOriginal);
		
		lblBinary = new JLabel("Binary Picture");
		lblBinary.setHorizontalAlignment(SwingConstants.CENTER);
		lblBinary.setBounds(224, 40, 150, 75);
		contentPane.add(lblBinary);
		
		lblColoured = new JLabel("Coloured Picture");
		lblColoured.setHorizontalAlignment(SwingConstants.CENTER);
		lblColoured.setBounds(408, 40, 150, 75);
		contentPane.add(lblColoured);
		
		lblHighlighted = new JLabel("Highlighted Picture");
		lblHighlighted.setHorizontalAlignment(SwingConstants.CENTER);
		lblHighlighted.setBounds(592, 40, 150, 75);
		contentPane.add(lblHighlighted);
		
		lblDisplay = new JLabel("Display Picture");
		lblDisplay.setHorizontalAlignment(SwingConstants.CENTER);
		lblDisplay.setBounds(0, 236, 782, 317);
		contentPane.add(lblDisplay);
		
		JButton btnOriginal = new JButton("Original");
		btnOriginal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblDisplay.setIcon(new ImageIcon(new ImageIcon("images\\currentPicture.jpg")
						.getImage().getScaledInstance(782, 317, Image.SCALE_DEFAULT)));
			}
		});
		btnOriginal.setBounds(60, 135, 110, 25);
		contentPane.add(btnOriginal);
		
		JButton btnBinary = new JButton("Binary");
		btnBinary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblDisplay.setIcon(new ImageIcon(new ImageIcon("images\\currentBinaryPicture.jpg")
						.getImage().getScaledInstance(782, 317, Image.SCALE_DEFAULT)));
			}
		});
		btnBinary.setBounds(244, 135, 110, 25);
		contentPane.add(btnBinary);
		
		JButton btnColoured = new JButton("Coloured");
		btnColoured.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblDisplay.setIcon(new ImageIcon(new ImageIcon("images\\currentColouredPicture.jpg")
						.getImage().getScaledInstance(782, 317, Image.SCALE_DEFAULT)));
			}
		});
		btnColoured.setBounds(428, 135, 110, 25);
		contentPane.add(btnColoured);
		
		JButton btnHighlighted = new JButton("Highlighted");
		btnHighlighted.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblDisplay.setIcon(new ImageIcon(new ImageIcon("images\\currentHighlightedPicture.jpg")
						.getImage().getScaledInstance(782, 317, Image.SCALE_DEFAULT)));
			}
		});
		btnHighlighted.setBounds(612, 135, 110, 25);
		contentPane.add(btnHighlighted);
		
		JRadioButton rbtnBlack = new JRadioButton("Black");
		rbtnBlack.setSelected(true);
		blnWhiteBackground = false;
		rbtnBlack.setHorizontalAlignment(SwingConstants.CENTER);
		rbtnBlack.setBackground(Color.WHITE);
		buttonGroup.add(rbtnBlack);
		rbtnBlack.setBounds(60, 200, 110, 25);
		contentPane.add(rbtnBlack);
		rbtnBlack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				blnWhiteBackground = false;
			}
		});
		
		JRadioButton rbtnWhite = new JRadioButton("White");
		rbtnWhite.setHorizontalAlignment(SwingConstants.CENTER);
		rbtnWhite.setBackground(Color.WHITE);
		buttonGroup.add(rbtnWhite);
		rbtnWhite.setBounds(244, 200, 110, 25);
		contentPane.add(rbtnWhite);
		rbtnWhite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				blnWhiteBackground = true;
			}
		});
		
		sliderLuminance = new JSlider();
		sliderLuminance.setMajorTickSpacing(32);
		sliderLuminance.setMinimum(64);
		sliderLuminance.setMaximum(192);
		sliderLuminance.setValue(128);
		sliderLuminance.setBounds(383, 200, 200, 25);
		contentPane.add(sliderLuminance);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateImages(currentImageFile, blnWhiteBackground, sliderLuminance.getValue());
			}
		});
		btnUpdate.setBounds(612, 200, 110, 25);
		contentPane.add(btnUpdate);
		
		JLabel lblBackgroundColour = new JLabel("Background Colour");
		lblBackgroundColour.setHorizontalAlignment(SwingConstants.CENTER);
		lblBackgroundColour.setBounds(60, 174, 294, 16);
		contentPane.add(lblBackgroundColour);
		
		JLabel lblObjectLuminance = new JLabel("Object Luminance(64 - 192)");
		lblObjectLuminance.setHorizontalAlignment(SwingConstants.CENTER);
		lblObjectLuminance.setBounds(383, 171, 200, 16);
		contentPane.add(lblObjectLuminance);
		
		lblObjectNo = new JLabel("Objects");
		lblObjectNo.setHorizontalAlignment(SwingConstants.CENTER);
		lblObjectNo.setBounds(612, 171, 110, 16);
		contentPane.add(lblObjectNo);
				
	}
	
}
