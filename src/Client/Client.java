package Client;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.net.URISyntaxException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.json.JSONArray;
import org.json.JSONObject;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import io.socket.emitter.Emitter.Listener;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;


public class Client extends JFrame {

	private JPanel contentPane;
	JComboBox cmbRegion;
	JComboBox cmbNameCountry;
	private JLabel lblNewLabel;
	private JLabel lblNhietDo;
	private JLabel lblDoAm,txtNhietDo,txtApSuat,txtHuongGio,txtTamNhin,txtTocDoGio,txtDoAm;
	private Socket socket;
	{
        try {
            socket = IO.socket("http://localhost:3000/");
        }catch (URISyntaxException ex){
            ex.printStackTrace();
        }
    }
	
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
		setBounds(100, 100, 652, 466);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		cmbRegion = new JComboBox();
		cmbRegion.setFont(new Font("Tahoma", Font.BOLD, 12));
		cmbRegion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String region = cmbRegion.getSelectedItem().toString();
	        	socket.emit("country", region);
			}
		});
		cmbRegion.setModel(new DefaultComboBoxModel(new String[] {"Africa", "Americas", "Asia", "Europe", "Oceania"}));
		cmbRegion.setBounds(92, 89, 128, 33);
		contentPane.add(cmbRegion);
		
		cmbNameCountry = new JComboBox();
		cmbNameCountry.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (arg0.getStateChange() == ItemEvent.SELECTED) {
					String country = cmbNameCountry.getSelectedItem().toString();
					if (country.equals("Viet Nam")) country = country.replace(" ", "");
		        	socket.emit("sendCountry", country);
				}
			}
		});
		cmbNameCountry.setBounds(285, 89, 247, 33);
		contentPane.add(cmbNameCountry);
		
		lblNewLabel = new JLabel("DỰ BÁO THỜI TIẾT");
		lblNewLabel.setForeground(Color.DARK_GRAY);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(135, 11, 345, 55);
		contentPane.add(lblNewLabel);
		
		lblNhietDo = new JLabel("");
		lblNhietDo.setBounds(25, 160, 30, 30);
		lblNhietDo.setForeground(Color.DARK_GRAY);
		lblNhietDo.setIcon(reIcon("icons8-thermometer-automation-30.png", lblNhietDo));
		lblNhietDo.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lblNhietDo);
		
		lblDoAm = new JLabel("");
		lblDoAm.setBounds(358, 347, 30, 30);
		lblDoAm.setForeground(Color.DARK_GRAY);
		lblDoAm.setIcon(reIcon("icons8-dew-point-30.png", lblDoAm));
		lblDoAm.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lblDoAm);
		
		JLabel lblApSuat = new JLabel("");
		lblApSuat.setBounds(25, 254, 30, 30);
		lblApSuat.setForeground(Color.DARK_GRAY);
		lblApSuat.setIcon(reIcon("icons8-barometer-30.png",lblApSuat));
		lblApSuat.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lblApSuat);
		
		JLabel lblHuongGio = new JLabel("");
		lblHuongGio.setBounds(25, 347, 30, 30);
		lblHuongGio.setForeground(Color.DARK_GRAY);
		lblHuongGio.setIcon(reIcon("icons8-tornado-30.png",lblHuongGio));
		lblHuongGio.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lblHuongGio);
		
		JLabel lblTocDoGio = new JLabel("");
		lblTocDoGio.setBounds(358, 160, 30, 30);
		lblTocDoGio.setForeground(Color.DARK_GRAY);
		lblTocDoGio.setIcon(reIcon("icons8-wind-speed-43-47-30.png",lblTocDoGio));
		lblTocDoGio.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lblTocDoGio);
		
		JLabel lblTamNhin = new JLabel("");
		lblTamNhin.setBounds(358, 254, 30, 30);
		lblTamNhin.setForeground(Color.DARK_GRAY);
		lblTamNhin.setIcon(reIcon("icons8-binoculars-30.png",lblTamNhin));
		lblTamNhin.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lblTamNhin);
		
		txtNhietDo = new JLabel("nhiet do");
		txtNhietDo.setBounds(77, 160, 196, 32);
		contentPane.add(txtNhietDo);
		
		txtApSuat = new JLabel("ap suat");
		txtApSuat.setBounds(77, 254, 196, 32);
		contentPane.add(txtApSuat);
		
		txtHuongGio = new JLabel("huong gio");
		txtHuongGio.setBounds(77, 347, 196, 30);
		contentPane.add(txtHuongGio);
		
		txtTocDoGio = new JLabel("toc do gio");
		txtTocDoGio.setBounds(410, 160, 196, 30);
		contentPane.add(txtTocDoGio);
		
		txtTamNhin = new JLabel("tam nhin");
		txtTamNhin.setBounds(410, 254, 196, 32);
		contentPane.add(txtTamNhin);
		
		txtDoAm = new JLabel("do am");
		txtDoAm.setBounds(410, 347, 196, 32);
		contentPane.add(txtDoAm);
		socket.on("nameCountry", onNameCountry);
		socket.on("weather", new Listener() {
			
			@Override
			public void call(Object... arg0) {
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						try {
							JSONObject object = (JSONObject) arg0[0];
							if (!object.has("main")) { 
								JOptionPane.showMessageDialog(null, "Không có dữ liệu"); 
								return;
							}
							JSONObject maim = object.getJSONObject("main");
							txtNhietDo.setText(Do_C(maim.getString("temp")));
							txtApSuat.setText(maim.getString("pressure")+" atm");
							txtDoAm.setText(maim.getString("humidity")+"%");
							JSONObject wind = object.getJSONObject("wind");
							txtTocDoGio.setText(wind.get("speed")+" m/s");
							txtHuongGio.setText(wind.get("deg")+"");
							txtTamNhin.setText(ConvertV(object.get("visibility")+""));
						} catch (Exception e) {
							// TODO: handle exception
						}
					}
				}).start();
			}
		});
		socket.on(Socket.EVENT_CONNECT,onConnect);
		socket.connect();
	}
	
	public String Do_C(String c) {
		 Float C = Float.parseFloat(c);
		 Float doC;
		 doC = (float) ((C-275.15));
		 return  (float) Math.round(doC) +" ºC";
	}
	
	public static String ConvertV(String c){
		float vis = Float.parseFloat(c);
		
		return vis/1000 +" km";
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
	
	public ImageIcon reIcon(String path, JLabel lblimg) {
		ImageIcon img = new ImageIcon("img\\"+path);
		Image im = img.getImage().getScaledInstance(lblimg.getWidth(), lblimg.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon anh = new ImageIcon(im);
		return anh;
	}
}
