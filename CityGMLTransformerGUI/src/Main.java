import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main{
 
    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Prepare 3D Citymodel for Energy Simulation");
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton button = new JButton("Select GML FIle Directory");
        
        button.addActionListener(new ActionListener() {
        	
          public void actionPerformed(ActionEvent ae) {
        	  
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
            	File selectedDirectory = fileChooser.getCurrentDirectory();
            	System.out.println(fileChooser.getCurrentDirectory());
            	File selectedFile = fileChooser.getSelectedFile();
            	System.out.println(selectedFile.getName());
            }
          }
        });
        
        JButton button2 = new JButton("Set Output Directory");
        button2.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent ae) {
        	JFileChooser fileChooser2 = new JFileChooser();
        	fileChooser2.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        	int returnValue = fileChooser2.showSaveDialog(null);
        	if (returnValue == JFileChooser.APPROVE_OPTION) {
        		System.out.println(fileChooser2.getSelectedFile());
        		}
        	}
    	});
        
        
        
        JButton button3 = new JButton("Start");
        button3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
//				ToDo Insert ConnectBuildingparts directory
			}
		});
        
        JLabel ConnectBP = new JLabel("(Only LOD2) Connect Buildingparts:");
        frame.add(ConnectBP);
        frame.add(button);
        frame.add(button2);
        frame.add(button3);
        frame.pack();
        frame.setVisible(true);
      }
    }