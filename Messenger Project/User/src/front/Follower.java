package front;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

import java.util.ArrayList;
import java.util.*;
import java.io.*;
import java.net.*;
import javax.imageio.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import domain.UserProfile;
import domain.UserProfile.*;
//import view.Board;      
import domain.FollowInfo;


public class Follower extends JFrame implements ActionListener {
	Frame f;
	private JPanel contentPane;
	private JScrollPane scrollPane;
	
	public static String self_user_id;
	public static String target_user_id;
	

	// private Image profile_pic = null;
	private String pic_path;
	
	Button main; 			// 나의 프로필로 돌아가기.  
	Button board; 	// 보드
	Button setting;
	
	public ArrayList<JFrame> info_frame;
	public ArrayList<Image> profile_pic;
	public ArrayList<String> follower_propic; /* 팔로워들의 프로필 사진 */ 
	public ArrayList<Button> follower_id; 		/* 팔로워들의 아이디 */ 
	public ArrayList<JTextField> follower_desc; /* 팔로워들의 한 줄 소개 */
	
	public JTable table = new JTable();
	private ArrayList<FollowInfo> info_list;
	
	public static void main(String[] args) throws IOException, ClassNotFoundException
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try {
					target_user_id = "admin_000";
					Follower pf = new Follower();
					pf.setVisible(true);
				}
				
				catch(Exception e){
		            System.out.println("에러: " + e);
		        }
			}
		});
	}
	
	public Follower(String target_user_id)
	{
		this.target_user_id = target_user_id;
	}
	

	public Follower()
	{
		
		setResizable(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setSize(500, 700);
		setContentPane(contentPane);
		
		
		/* Buttons */
		// 메인 버튼 
		main = new Button("My Profile");
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
		
		try {
			// 1. 드라이버 로딩
            Class.forName("com.mysql.jdbc.Driver");

            // 2. 연결하기
            String url = "jdbc:mysql://localhost/TeamProjectTest2";
            String db_id = "root";		String db_pw = "aj021026";
            Connection conn = DriverManager.getConnection(url, db_id, db_pw);
            
            // 팔로우, 팔로잉 수 불러오기. 
            String crnt_follower_id;
            
            String follower_finder = "select src_id from follow where tgt_id = ?";
            PreparedStatement follower_query = conn.prepareStatement(follower_finder);
            
            
            follower_query.setString(1, target_user_id);
        	
        	ResultSet followerResultSet = follower_query.executeQuery();
        	
        	ResultSetMetaData rsmd = followerResultSet.getMetaData();
        	
        	info_list = new ArrayList<FollowInfo>();

					
        	
			while(followerResultSet.next())
        	{
        		crnt_follower_id = followerResultSet.getString(1);
        		System.out.println("follower: " + crnt_follower_id);
        		
        		String follower_info_query = "select * from users where id = ?";
        		PreparedStatement follower_info_stmt = conn.prepareStatement(follower_info_query);
        		follower_info_stmt.setString(1, crnt_follower_id);
        		
        		ResultSet follower_info_RS = follower_info_stmt.executeQuery();
        		
        		if(follower_info_RS.next())
        		{
        			follower_info_RS.getString(1); // name 
        			
        			FollowInfo crnt_info = new FollowInfo(
        					follower_info_RS.getString(4), 		/* 프로필 사진 */ 
        					follower_info_RS.getString(1), 		/* User Name */
        					follower_info_RS.getString(2), 		/* User id */
        					follower_info_RS.getString(5)		/* 한 줄 소개 */
        					);
        			
        			
        			info_list.add(crnt_info);
        		}
        	}

			String[] colNames = new String[] {"프로필 사진", "User Name", "User id", "한 줄 소개", "Follow"};
			Object[][] rowDatas = new Object[info_list.size()][colNames.length];
        	
			for(int i=0; i<info_list.size(); i++)
			{
				rowDatas[i] = new Object[] {
						info_list.get(i).getTarget_propic(),
						info_list.get(i).getTarget_name(),
						info_list.get(i).getTarget_id(),
						info_list.get(i).getTarget_desc()
				};
			}
        	

			table = new JTable();
			table.setModel(new DefaultTableModel(rowDatas, colNames) {
				boolean[] columnEditables = new boolean[] {
						false, false, false, false, false, true
				};
				public boolean isCellEditable(int row, int column)
				{
					return columnEditables[column];
				}
			});
			
			table.getColumnModel().getColumn(0).setResizable(true);
			table.getColumnModel().getColumn(0).setPreferredWidth(40);
			table.getColumnModel().getColumn(1).setResizable(false);
			table.getColumnModel().getColumn(1).setPreferredWidth(50);
			table.getColumnModel().getColumn(2).setResizable(false);
			table.getColumnModel().getColumn(2).setPreferredWidth(100);
			table.getColumnModel().getColumn(3).setResizable(false);
			table.getColumnModel().getColumn(3).setPreferredWidth(50);
			table.getColumnModel().getColumn(4).setPreferredWidth(30);
			table.getColumnModel().getColumn(4).setCellRenderer(new TableCell());;
			table.getColumnModel().getColumn(4).setCellEditor(new TableCell());;
			
			

			scrollPane = new JScrollPane(table);
			contentPane.add(scrollPane);
	        scrollPane.setBounds(100, 100, 450, 300);
	        scrollPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	        
	        // getContentPane().add(scrollPane);
	        
			table.addMouseListener(new MouseAdapter()
			{
	            public void mouseClicked(MouseEvent e) {
	                // TODO Auto-generated method stub
	                int rowNum = table.getSelectedRow();
	                FollowInfo info = new FollowInfo();
	                info = info_list.get(rowNum);
	               
	                System.out.println("옵션을 선택하세요.");
	                
	                String[] option = {"프로필 보기", "차단", "취소"};
	                int answer = JOptionPane.showOptionDialog(null, "옵션을 선택하세요.", "알림", 
	                		JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, option, null);
	                
	                if(answer == 0) // 프로필 보기 
	                {
	                	System.out.println(info.getTarget_id());
	                	ProfileFrame pf_name = new ProfileFrame(info.getTarget_id());
						ProfileFrame pf = null;
						
	                	try {
	                		pf = new ProfileFrame();
						} catch (ClassNotFoundException | IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	                	pf.setVisible(true);
	                	
	                }
	                if(answer == 1) // 언팔로우
	                {
	                	try {
	            			// 1. 드라이버 로딩
	                        Class.forName("com.mysql.jdbc.Driver");

	                        // 2. 연결하기
	                        String url = "jdbc:mysql://localhost/messenger_db";
	                        String db_id = "root";		String db_pw = "aj021026";
	                        Connection conn = DriverManager.getConnection(url, db_id, db_pw);
	                        

		                	System.out.println(info.getTarget_id());
		                	String unfollow_query = "delete from follow where src_id = ? and tgt_id = ?";
		                	
							PreparedStatement unfollow_stmt = conn.prepareStatement(unfollow_query);
							
							unfollow_stmt.setString(1, target_user_id);
							unfollow_stmt.setString(2, info.getTarget_id());
							
							int cnt = unfollow_stmt.executeUpdate();
							
							
	                	}
	                	catch(ClassNotFoundException error){
	                        System.out.println("드라이버 로딩 실패");
	                    }
	                    catch(SQLException error){
	                        System.out.println("에러: " + error);
	                    }
	                	
	                }
	                else
	                {
	                	// 취소
	                }
//	                new BoardUpdate(info);
	            }	
			});
			
		scrollPane.setViewportView(table);
        	
		}
		catch(ClassNotFoundException e){
            System.out.println("드라이버 로딩 실패");
        }
        catch(SQLException e){
            System.out.println("에러: " + e);
        }

		setTitle("Followers");
		contentPane.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Button bb = (Button) e.getSource();
		
		
		if(bb == main)
		{
			// dispose();
			System.out.println("My Profile");
			ProfileFrame pf_name = new ProfileFrame(target_user_id);
			ProfileFrame pf = null;
			
        	try {
        		pf = new ProfileFrame();
			} catch (ClassNotFoundException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	pf.setVisible(true);
		}
		if(bb == board)
		{
			// dispose();
//			new Board();
			System.out.println("board");
		}
	}
	
	class TableCell extends AbstractCellEditor implements TableCellEditor, TableCellRenderer{

		JButton jb;
		
		public TableCell() {
			// TODO Auto-generated constructor stub
			jb = new JButton("Follow");
//			jb.addActionListener((ActionListener) this);
			jb.setFont(new Font("Tahoma", Font.PLAIN, 12));
			jb.setVisible(true);
			
			jb.addActionListener(e -> {
				System.out.println(table.getValueAt(table.getSelectedRow(), 1));
			});

//			table.getValueAt(table.getSelectedRow(), 1);
		
		}
		
		@Override
		public Object getCellEditorValue() {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			// TODO Auto-generated method stub
			return jb;
		}
		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {
			// TODO Auto-generated method stub
			return jb;
		}
		
	}

}
