import java.awt.EventQueue;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.awt.CardLayout;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
 
public class main extends JFrame {

	public static ArrayList<Contact> book = new ArrayList<Contact>();
	private JPanel paneIntro;

	/**
	 * Launch the application.
	 */
    public static void readData(){
        try{
            FileInputStream f= new FileInputStream("data.bin");
            ObjectInputStream is = new ObjectInputStream(f);
            Contact.numberOfContacts = (Integer) is.readObject();
            for(int i=0; i<Contact.numberOfContacts;i++){
              Contact.book.add((Contact) is.readObject());
            }
            is.close();
          }catch(Exception e){
            System.out.println(e);
          }
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
	public static void main(String[] args) {
		readData();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main frame = new main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
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
	public main() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 422, 274);
		paneIntro = new JPanel();
		paneIntro.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(paneIntro);
		paneIntro.setLayout(null);
		
		JButton btnAdd = new JButton("Add New");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
				add a = new add();
				a.setVisible(true);

			}
		});
		btnAdd.setBounds(146, 46, 117, 29);
		paneIntro.add(btnAdd);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
				search s = new search();
				s.setVisible(true);
			}
		});
		btnSearch.setBounds(146, 87, 117, 29);
		paneIntro.add(btnSearch);
		
		JButton btnBrowse = new JButton("Browse");
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
				browse b = new browse();
				b.setVisible(true);
			}
		});
		btnBrowse.setBounds(146, 128, 117, 29);
		paneIntro.add(btnBrowse);
	}
}
