import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

public class add extends JFrame {

	private JPanel contentPane;
	private JTextField txtFirst;
	private JTextField txtLast;
	private JTextField txtPhone;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					add frame = new add();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
    public static void writeData(){
        try{
            FileOutputStream f = new FileOutputStream("data.bin");
            ObjectOutputStream os =new ObjectOutputStream(f);
            int n=Contact.numberOfContacts;
            os.writeObject(n);
            for(int i=0;i<Contact.numberOfContacts;i++){
                os.writeObject(Contact.book.get(i));
            }
            os.close();
          }catch(Exception e){
            System.out.println(e);
          }
    }

	//close window
	public void close() {
		 WindowEvent closeWindow = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
		 Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closeWindow);
		 writeData();
	}
	/**
	 * Create the frame.
	 */
	public add() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFirst = new JLabel("First Name");
		lblFirst.setBounds(42, 58, 74, 16);
		contentPane.add(lblFirst);
		
		JLabel lblLast = new JLabel("Last Name");
		lblLast.setBounds(42, 98, 74, 16);
		contentPane.add(lblLast);
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setBounds(42, 141, 61, 16);
		contentPane.add(lblPhone);
		
		txtFirst = new JTextField();
		txtFirst.setBounds(195, 53, 130, 26);
		contentPane.add(txtFirst);
		txtFirst.setColumns(10);
		
		txtLast = new JTextField();
		txtLast.setBounds(195, 93, 130, 26);
		contentPane.add(txtLast);
		txtLast.setColumns(10);
		
		txtPhone = new JTextField();
		txtPhone.setBounds(195, 136, 130, 26);
		contentPane.add(txtPhone);
		txtPhone.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((txtPhone.getText().isEmpty()) || (txtLast.getText().isEmpty()) || (txtFirst.getText().isEmpty())) {
					JOptionPane.showMessageDialog(null, "Please enter the required fields.",
						      "Error", JOptionPane.ERROR_MESSAGE);
				}
				else {
					Contact c = new Contact();
					c.setFname((txtFirst.getText()));
					c.setLname(txtLast.getText());
					c.setPhone(txtPhone.getText());
					Contact.book.add(c);
					Contact.numberOfContacts++;
				}
			}
		});
		btnSave.setBounds(32, 213, 117, 29);
		contentPane.add(btnSave);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtLast.setText("");
				txtPhone.setText("");
				txtFirst.setText("");
			}
		});
		btnReset.setBounds(161, 213, 117, 29);
		contentPane.add(btnReset);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
				main m = new main();
				m.setVisible(true);
			}
		});
		btnCancel.setBounds(290, 213, 117, 29);
		contentPane.add(btnCancel);
	}
}
