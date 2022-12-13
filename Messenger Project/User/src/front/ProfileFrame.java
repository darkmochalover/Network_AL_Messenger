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
import domain.UserProfile.*;
//import view.Board;
import front.*;

public class ProfileFrame extends JFrame implements ActionListener
{
	Frame f;
	private JPanel contentPane;
	
	public static String self_user_id;
	public static String target_user_id;
	

	private Image profile_pic = null;
	private String pic_path;
	
	public JTextField field_user_name; 		/* User Name */
	public JTextField field_user_id; 			/* User id */
	public JTextField field_follower; 			/* Follower */
	public JTextField field_following; 		/* Following */
	public JTextField field_birthday; 			/* 생일 */
	public JTextField field_phone; 			/* 연락처 - 휴대폰 */
	public JTextField field_mail; 				/* 연락처 - 메일 */
	public JTextField field_desc; 				/* 한 줄 소개 */
	
	
	Button main; 			// 메인 
	Button board; 	// 보드로 이동.  
	Button setting; 		// 설정 
	
	Button Follower; 		// 팔로워 
	Button Following; 		// 팔로잉 
	
	
	/* if self, */
	Button edit; 	// 프로필 수정 
	
	/* else, */
	Button follow; // 팔로우 버튼
	Button subscribe; // 구독 버튼 
	

	public static void main(String[] args) throws IOException, ClassNotFoundException
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try {
					target_user_id = "admin_000";
					ProfileFrame pf = new ProfileFrame();
					pf.setVisible(true);
				}
				catch(Exception e){
		            System.out.println("에러: " + e);
		        }
			}
		});
		
	}
	
	public ProfileFrame() throws IOException, ClassNotFoundException
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
		board = new Button("board");
		board.addActionListener(this);
		board.setFont(new Font("Tahoma", Font.PLAIN, 12));
		board.setBounds(175, 10, 150, 20);
		contentPane.add(board);
		
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
		
		/* else, */
		follow = new Button("follow"); // 팔로우 버튼
		follow.addActionListener(this);
		follow.setFont(new Font("Tahoma", Font.PLAIN, 12));
		follow.setBounds(420, 70, 50, 20);
		contentPane.add(follow);
		
		subscribe = new Button("subscribe"); // 구독 버튼 
		subscribe.addActionListener(this);
		subscribe.setFont(new Font("Tahoma", Font.PLAIN, 12));
		subscribe.setBounds(420, 70, 50, 20);
		contentPane.add(subscribe);
		
		
		/* Profile picture */
		String pic_path = "/Users/ajin/Desktop/2022/2022 2학기/데이터베이스 및 실습/Workshop/UserMainPage/profilepic/pic1.JPG";
		File sourceimage = new File(pic_path);
		profile_pic = ImageIO.read(sourceimage);
		profile_pic = profile_pic.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
		JLabel label_profile_pic = new JLabel(new ImageIcon(profile_pic));
		label_profile_pic.setBounds(40, 50, 150, 150);
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
//		JLabel label_follower = new JLabel("Followers");
//		label_follower.setFont(new Font("Tahoma", Font.PLAIN, 12));
//		label_follower.setBounds(230, 140, 70, 20);
//		contentPane.add(label_follower);

		Follower = new Button("Follower");
		Follower.addActionListener(this);
		Follower.setFont(new Font("Tahoma", Font.PLAIN, 12));
		Follower.setBounds(230, 140, 70, 20);
		contentPane.add(Follower);
		
		field_follower = new JTextField("0");
		field_follower.setFont(new Font("Tahoma", Font.PLAIN, 12));
		field_follower.setEditable(false);
		field_follower.setBounds(230, 170, 70, 20);
		field_follower.setColumns(10);
		contentPane.add(field_follower);
		
		/* Following */ 
//		JLabel label_following = new JLabel("Following");
//		label_following.setFont(new Font("Tahoma", Font.PLAIN, 12));
//		label_following.setBounds(320, 140, 70, 20);
//		contentPane.add(label_following);
		
		Following = new Button("Following");
		Following.addActionListener(this);
		Following.setFont(new Font("Tahoma", Font.PLAIN, 12));
		Following.setBounds(320, 140, 70, 20);
		contentPane.add(Following);
		
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
		
		try {
			// 1. 드라이버 로딩
            Class.forName("com.mysql.jdbc.Driver");

            // 2. 연결하기
            String url = "jdbc:mysql://localhost/messenger_db";
            String db_id = "root";		String db_pw = "aj021026";
            Connection conn = DriverManager.getConnection(url, db_id, db_pw);
            
            // 팔로우, 팔로잉 수 불러오기. 
            int follower_count = 0;
            int following_count = 0;
            String follower_counter = "select count(tgt_id) from follow where src_id = ?";
            String following_counter = "select count(tgt_id) from follow where tgt_id = ?";
            
            PreparedStatement follower_query = conn.prepareStatement(follower_counter);
            PreparedStatement following_query = conn.prepareStatement(following_counter);
            
            follower_query.setString(1, target_user_id);
        	following_query.setString(1, target_user_id);
        	
        	ResultSet followerResultSet = follower_query.executeQuery();
        	ResultSet followingResultSet = following_query.executeQuery();
        	
        	if(followerResultSet.next())
        	{
        		follower_count = followerResultSet.getInt(1);
        		System.out.println("follower: " + follower_count);
        	}
        	
        	if(followingResultSet.next())
        	{
        		following_count = followingResultSet.getInt(1);
        	}
        	
        	
            // 유저 기본적인 정보 다 불러옴. 
            String user_load = "select * from users where id = ?";
            PreparedStatement psmt = conn.prepareStatement(user_load);
        	psmt.setString(1, target_user_id);
        	
        	
        	ResultSet rs = psmt.executeQuery();
        	
        	
        	
        	if(rs.next())
        	{
        		field_user_name.setText(rs.getString(1)); 		/* User Name */
        		field_user_id.setText("@"+rs.getString(2)); 			/* User id */
        		field_follower.setText(Integer.toString(follower_count));; 			/* Follower */
        		field_following.setText(Integer.toString(following_count));; 		/* Following */
        		field_birthday.setText(Integer.toString(rs.getInt(6))); 			/* 생일 */
        		field_phone.setText(UserProfile.phone_format(rs.getInt(8))); 			/* 연락처 - 휴대폰 */
        		field_mail.setText(rs.getString(7)); 				/* 연락처 - 메일 */
        		field_desc.setText(rs.getString(5));				/* 한 줄 소개 */
        	}
            
		}
		catch(ClassNotFoundException e){
            System.out.println("드라이버 로딩 실패");
        }
        catch(SQLException e){
            System.out.println("에러: " + e);
        }
		
		
		setTitle("Profile");
		setVisible(true);
		
	}
	
	public ProfileFrame(String target_user_id)
	{
		// super();
		this.target_user_id = target_user_id;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Button bb = (Button) e.getSource();
		
		
		if(bb == main)
		{
			// dispose();
			dispose();
			
			ProfileFrame pf_name = new ProfileFrame(target_user_id);
			ProfileFrame pf = null;
			
        	try {
        		pf = new ProfileFrame();
			} catch (ClassNotFoundException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	pf.setVisible(true);
			System.out.println("Main");
		}
		if(bb == board)
		{
			// dispose();
//			new Board();
			System.out.println("board");
		}
		if(bb == setting)
		{
			// dispose();
			System.out.println("setting");
		}
		
		if(bb == Following)
		{
			System.out.println("following");
			Following f_n = new Following(target_user_id);
			Following f = new Following();
			f.setVisible(true);
		}
		
		if(bb == Follower)
		{
			System.out.println("follower");
			Follower f_n = new Follower(target_user_id);
			Follower f = null;
			f = new Follower();
			f.setVisible(true);
		}
		
		if(bb == edit)
		{
			// dispose();
			ProfileEdit edit_frame_name = new ProfileEdit(target_user_id);
			ProfileEdit edit_frame = null;
			try {
				edit_frame = new ProfileEdit();
			} catch (ClassNotFoundException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			edit_frame.setVisible(true);
			System.out.println("edit");
		}
	}
	
}
