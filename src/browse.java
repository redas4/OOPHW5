import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JRadioButtonMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;

public class browse extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					browse frame = new browse();
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
	public browse() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
				main m = new main();
				m.setVisible(true);
			}
		});
		btnNewButton.setBounds(247, 226, 117, 29);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(39, 18, 365, 195);
		contentPane.add(scrollPane);
		
		
	       
		String col[] = {"fname","lname","phone"};

		DefaultTableModel tableModel = new DefaultTableModel(col, 0);
		                                            // The 0 argument is number rows.
		JTable table = new JTable(tableModel) {
	         public boolean editCellAt(int row, int column, java.util.EventObject e) {
	             return false;
	          }
	       };
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
	      
		for(int i = 0; i< Contact.numberOfContacts; i++) {
			String first = Contact.book.get(i).getFname();
			String last = Contact.book.get(i).getLname();
			String phone = Contact.book.get(i).getPhone();
			
			Object[] data = {first,last,phone};
			tableModel.addRow(data);
		}
		
		scrollPane.setViewportView(table);
		
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 // check for selected row first
	            if(table.getSelectedRow() != -1) {
	               // remove selected row from the model
	            	int x = table.getSelectedRow();
	            	Contact.book.remove(x);
	            	Contact.numberOfContacts--;
	               tableModel.removeRow(table.getSelectedRow());
	             
	            }
			}
		});
		btnDelete.setBounds(94, 226, 117, 29);
		contentPane.add(btnDelete);
		
		
	}
}
