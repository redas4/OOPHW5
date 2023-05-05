import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class search extends JFrame {

	private JPanel contentPane;
	private JTextField txtSearch;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					search frame = new search();
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
	public search() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		

		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
				main m = new main();
				m.setVisible(true);			}
		});
		btnCancel.setBounds(20, 118, 117, 29);
		contentPane.add(btnCancel);
		
		txtSearch = new JTextField();
		txtSearch.setBounds(149, 36, 282, 26);
		contentPane.add(txtSearch);
		txtSearch.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(149, 77, 282, 189);
		contentPane.add(scrollPane);
		
		
		table = new JTable();
		
		String col[] = {"fname","lname","phone"};

		DefaultTableModel tableModel = new DefaultTableModel(col, 0);		                                            // The 0 argument is number rows.
		JTable table = new JTable(tableModel) {
	         public boolean editCellAt(int row, int column, java.util.EventObject e) {
	             return false;
	          }
	       };
		
	      	
		scrollPane.setViewportView(table);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = txtSearch.getText();
				tableModel.setRowCount(0);
				for(int i = 0; i< Contact.numberOfContacts; i++) {
						String first = Contact.book.get(i).getFname();
						String last = Contact.book.get(i).getLname();
						String phone = Contact.book.get(i).getPhone();
						if(first.contains(s) || last.contains(s) || phone.contains(s)) {							
							Object[] data = {first,last,phone};
							tableModel.addRow(data);
						}
				}				
				
			}
		});
		
		btnSearch.setBounds(20, 36, 117, 29);
		contentPane.add(btnSearch);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtSearch.setText("");
				//Need to clear the jtable as well!!!
		/*		for(int i = 0;i<Contact.numberOfContacts;i++) {
					tableModel.removeRow(i);
				}
				*/
				tableModel.setRowCount(0);
			}
		});
		btnReset.setBounds(20, 77, 117, 29);
		contentPane.add(btnReset);
	}
}
