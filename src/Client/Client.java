package Client;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.net.URISyntaxException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.json.JSONArray;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import io.socket.emitter.Emitter.Listener;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;


public class Client extends JFrame {

	private JPanel contentPane;
	JComboBox cmbRegion;
	JComboBox cmbNameCountry;
	private Socket socket;
//	{
//        try {
//            socket = IO.socket("http://localhost:3000/");
//        }catch (URISyntaxException ex){
//            ex.printStackTrace();
//        }
//    }
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client frame = new Client();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws Exception 
	 * @throws Exception 
	 */
	public Client() throws Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 763, 498);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		cmbRegion = new JComboBox();
		cmbRegion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				String region = cmbRegion.getSelectedItem().toString();
//	        	socket.emit("country", region);
			}
		});
		cmbRegion.setModel(new DefaultComboBoxModel(new String[] {"Africa", "Americas", "Asia", "Europe", "Oceania"}));
		cmbRegion.setBounds(177, 90, 96, 20);
		contentPane.add(cmbRegion);
		
		cmbNameCountry = new JComboBox();
		cmbNameCountry.setBounds(345, 90, 244, 20);
		contentPane.add(cmbNameCountry);
		
		lblNewLabel = new JLabel("Dự Báo Thời Tiết");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(186, 11, 345, 55);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Nhiệt độ : ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(25, 151, 76, 24);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Nhiệt độ : ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setBounds(135, 151, 76, 24);
		contentPane.add(lblNewLabel_2);
//		socket.on("nameCountry", onNameCountry);
//		socket.on("weather", new Listener() {
//			
//			@Override
//			public void call(Object... arg0) {
//				new Thread(new Runnable() {
//					
//					@Override
//					public void run() {
//						System.out.println(arg0[0]);	
//					}
//				}).start();
//			}
//		});
//		socket.on(Socket.EVENT_CONNECT,onConnect);
//		socket.connect();
	}
	
	private Emitter.Listener onNameCountry = new Listener() {
		
		@Override
		public void call(Object... arg0) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						cmbNameCountry.removeAllItems();
						JSONArray arrCountry = (JSONArray) arg0[0];
						for (int i = 0; i < arrCountry.length(); i++) {
							cmbNameCountry.addItem(arrCountry.getString(i));
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
	};
	
	private Emitter.Listener onConnect = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
        	String region = cmbRegion.getSelectedItem().toString();
        	socket.emit("country", region);
        }
    };
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
}
