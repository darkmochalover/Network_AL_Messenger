package front;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import java.io.*;
import java.net.*;
import javax.imageio.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import domain.UserProfile;

public class ProfileFrameTest extends JFrame implements ActionListener
{
	Frame f;
	private JPanel contentPane;
	
	private String user_id;
	

	Image profile_pic = null;
	
	JTextField field_user_name; 		/* User Name */
	JTextField field_user_id; 			/* User id */
	JTextField field_follower; 			/* Follower */
	JTextField field_following; 		/* Following */
	JTextField field_birthday; 			/* 생일 */
	JTextField field_phone; 			/* 연락처 - 휴대폰 */
	JTextField field_mail; 				/* 연락처 - 메일 */
	JTextField field_desc; 				/* 한 줄 소개 */
	
	
	Button main; 			// 메인 
	Button notification; 	// 알림 
	Button setting; 		// 설정 
	Button edit; 	// 프로필 수정 
	
	
	public ProfileFrameTest() throws IOException
	{
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setSize(500, 700);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		/* Buttons */
		// 메인 버튼 
		main = new Button("Main");
		main.addActionListener(this);
		main.setFont(new Font("Tahoma", Font.PLAIN, 12));
		main.setBounds(25, 10, 150, 20);
		contentPane.add(main);
		
		// 알림 버튼 
		notification = new Button("notification");
		notification.addActionListener(this);
		notification.setFont(new Font("Tahoma", Font.PLAIN, 12));
		notification.setBounds(175, 10, 150, 20);
		contentPane.add(notification);
		
		// 설정 버튼 
		setting = new Button("setting");
		setting.addActionListener(this);
		setting.setFont(new Font("Tahoma", Font.PLAIN, 12));
		setting.setBounds(325, 10, 150, 20);
		contentPane.add(setting);
		
		/* if user == self, */ 
		edit = new Button("edit");
		edit.addActionListener(this);
		edit.setFont(new Font("Tahoma", Font.PLAIN, 12));
		edit.setBounds(420, 70, 50, 20);
		contentPane.add(edit);
		
		
		/* Profile picture */
		String pic_path = "/Users/ajin/Desktop/2022/2022 2학기/데이터베이스 및 실습/Workshop/UserMainPage/profilepic/pic1.JPG";
		File sourceimage = new File(pic_path);
		profile_pic = ImageIO.read(sourceimage);
		profile_pic = profile_pic.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
		JLabel label_profile_pic = new JLabel(new ImageIcon(profile_pic));
		label_profile_pic.setBounds(20, 70, 150, 150);
		contentPane.add(label_profile_pic);
		
		/* User Name */
		field_user_name = new JTextField("ex..");
		field_user_name.setFont(new Font("Tahoma", Font.PLAIN, 16));
		field_user_name.setEditable(false);
		field_user_name.setBounds(200, 70, 200, 31);
		contentPane.add(field_user_name);
		
		/* User id */
		field_user_id = new JTextField("@ex..");
		field_user_id.setBounds(200, 100, 200, 20);
		field_user_id.setEditable(false);
		contentPane.add(field_user_id);
		field_user_id.setColumns(10);
		
		/* Follower */
		JLabel label_follower = new JLabel("Followers");
		label_follower.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_follower.setBounds(230, 140, 70, 20);
		contentPane.add(label_follower);
		
		field_follower = new JTextField("0");
		field_follower.setFont(new Font("Tahoma", Font.PLAIN, 12));
		field_follower.setEditable(false);
		field_follower.setBounds(230, 170, 70, 20);
		field_follower.setColumns(10);
		contentPane.add(field_follower);
		
		/* Following */ 
		JLabel label_following = new JLabel("Following");
		label_following.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_following.setBounds(320, 140, 70, 20);
		contentPane.add(label_following);
		
		field_following = new JTextField("0");
		field_following.setFont(new Font("Tahoma", Font.PLAIN, 12));
		field_following.setEditable(false);
		field_following.setBounds(320, 170, 70, 20);
		field_following.setColumns(10);
		contentPane.add(field_following);
		
		
		/* 생일 */
		JLabel label_birthday = new JLabel("생일");
		label_birthday.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_birthday.setBounds(20, 240, 100, 20);
		contentPane.add(label_birthday);
		
		field_birthday = new JTextField();
		field_birthday.setFont(new Font("Tahoma", Font.PLAIN, 12));
		field_birthday.setEditable(false);
		field_birthday.setBounds(80, 240, 400, 20);
		field_birthday.setColumns(10);
		contentPane.add(field_birthday);
		
		/* 연락처 - 휴대폰 */
		JLabel label_phone = new JLabel("휴대폰");
		label_phone.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_phone.setBounds(20, 260, 100, 20);
		contentPane.add(label_phone);
		
		field_phone = new JTextField();
		field_phone.setFont(new Font("Tahoma", Font.PLAIN, 12));
		field_phone.setEditable(false);
		field_phone.setBounds(80, 260, 400, 20);
		field_phone.setColumns(10);
		contentPane.add(field_phone);
		
		/* 연락처 - 메일 */
		JLabel label_mail = new JLabel("메일");
		label_mail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_mail.setBounds(20, 280, 100, 20);
		contentPane.add(label_mail);
		
		field_mail = new JTextField();
		field_mail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		field_mail.setEditable(false);
		field_mail.setBounds(80, 280, 400, 20);
		field_mail.setColumns(10);
		contentPane.add(field_mail);
		
		/* 한 줄 소개 */
		JLabel label_desc = new JLabel("한 줄 소개");
		label_desc.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_desc.setBounds(20, 320, 100, 20);
		contentPane.add(label_desc);
		
		field_desc = new JTextField("ex..");
		field_desc.setFont(new Font("Tahoma", Font.PLAIN, 12));
		field_desc.setEditable(false);
		field_desc.setBounds(20, 340, 450, 100);
		field_desc.setColumns(10);
		contentPane.add(field_desc);
		
		
		setTitle("Profile");
		setVisible(true);
		
	}
	
	public ProfileFrameTest(String user_id)
	{
		this.user_id = user_id;
	}
	
	public ProfileFrameTest(UserProfile user)
	{
		this.user_id = user_id;
	}
	
	public static void main(String[] args) throws IOException
	{
		ProfileFrameTest pf = new ProfileFrameTest();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Button bb = (Button) e.getSource();
		
		
		if(bb == main)
		{
			dispose();
			System.out.println("Main");
		}
		if(bb == notification)
		{
			dispose();
			System.out.println("notification");
		}
		if(bb == setting)
		{
			dispose();
			System.out.println("setting");
		}
		if(bb == edit)
		{
			dispose();
			System.out.println("edit");
		}
		
	}
	
}
