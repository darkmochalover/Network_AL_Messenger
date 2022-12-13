package front;

import java.awt.Button;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import domain.FollowInfo;
import domain.UserProfile;
import front.ProfileSearch;

public class ProfileMain extends JFrame implements ActionListener {
	
	Frame f;
	private JPanel contentPane;
	
	
	public static String self_user_id;
	public static String target_user_id;
	

	private Image profile_pic = null;
	private String pic_path;
	
	public JTextField field_search; /* 서치 스트링 */ 
	Button search; /* Search submit */ 
	
	
	
	Button main; 			// 나의 프로필로 돌아가기.  
	Button board; 	// 보드
	Button setting; 		// 설정 
	
	Button Follower; 		// 팔로워 
	
	public ArrayList<JFrame> info_frame;
	public ArrayList<String> follower_propic; /* 팔로워들의 프로필 사진 */ 
	public ArrayList<Button> follower_id; 		/* 팔로워들의 아이디 */ 
	public ArrayList<JTextField> follower_desc; /* 팔로워들의 한 줄 소개 */
	
	public JTable table = new JTable();
	private ArrayList<UserProfile> info_list;
	Button Following; 		// 팔로잉 
	
	
	/* if self, */
	Button edit; 	// 프로필 수정 
	
	/* else, */
	Button follow; // 팔로우 버튼
	Button subscribe; // 구독 버튼 
	
	String search_string;
	

	public static void main(String[] args) throws IOException, ClassNotFoundException
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try {
					target_user_id = "admin_000";
					ProfileMain pf = new ProfileMain();
					pf.setVisible(true);
				}
				catch(Exception e){
		            System.out.println("에러: " + e);
		        }
			}
		});
		
	}
	
	public ProfileMain()
	{
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setSize(500, 700);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		// Search 기능 
		/* Field */
		field_search = new JTextField("검색할 유저 이름을 입력하세요 . . . ");
		field_search.setBounds(30, 40, 360, 20);
		contentPane.add(field_search);
		field_search.setColumns(10);
		
		/* Buttons */
		search = new Button("Search!");
		search.addActionListener(this);
		search.setFont(new Font("Tahoma", Font.PLAIN, 12));
		search.setBounds(400, 40, 80, 20);
		contentPane.add(search);
		
		// 메인 버튼 
		main = new Button("My Page");
		main.addActionListener(this);
		main.setFont(new Font("Tahoma", Font.PLAIN, 12));
		main.setBounds(25, 10, 150, 20);
		contentPane.add(main);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub

		Button bb = (Button) ae.getSource();
		if(bb == search)
		{

			search_string = field_search.getText();
			System.out.println(search_string);
			ProfileSearch ps_n = new ProfileSearch(search_string);

			ProfileSearch ps = new ProfileSearch();
			ps.setVisible(true);
		}
		
		
	};
	
	

}
