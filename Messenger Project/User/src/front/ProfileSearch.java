package front;

import java.awt.Button;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import domain.*;
import front.*;
import front.Follower.TableCell;
//import control.BoardVO;
//import view.Boardlist2;

public class ProfileSearch extends JFrame implements ActionListener{
	public static void main(String[] args) throws IOException, ClassNotFoundException
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try {
					search_String = "admin";
					ProfileSearch pf = new ProfileSearch();
					pf.setVisible(true);
				}
				
				catch(Exception e){
		            System.out.println("에러: " + e);
		        }
			}
		});
	}
	
	Connection conn;
    PreparedStatement pstmt;
    ResultSet rs;
    public static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost/messenger_db";
    public static final String USERID = "root";
    public static final String USERPWD = "aj021026";
 
    Frame f;
	private JPanel contentPane;
	private JScrollPane scrollPane;
	
	
	public static String self_user_id;
	public static String target_user_id;
	

	public static String search_String;
	public JTextField field_search; /* 서치 스트링 */ 
	Button search; /* Search submit */ 
	
	Button main; 			// 나의 프로필로 돌아가기.  
	Button notification; 	// 알림 
	Button setting; 		// 설정 
	
	Button Follower; 		// 팔로워 
	
	public JTable table = new JTable();
	private ArrayList<UserProfile> info_list;
	
	public ProfileSearch(String search_String)
	{
		this.search_String = search_String;
	}
	
    public ProfileSearch() {
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
		
        try {
            Class.forName(DRIVER_NAME);
           
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("drive connect failed...");
        }
        
        List<UserProfile> info_list = new ArrayList<UserProfile>();
        
        try {
            conn = DriverManager.getConnection(URL, USERID, USERPWD);
            String sql = "select * from users where user_name like %" + search_String + "%";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
 
            while (rs.next()) {
            	UserProfile target_user = new UserProfile(
        				rs.getString(1), // user_name
        				rs.getString(2), // id 
        				rs.getString(4), // image
        				rs.getString(5), // description 
        				rs.getInt(6), // birthday
        				rs.getString(7), // mail 
        				rs.getInt(8), // phone_number
        				rs.getString(9) // status
        				);
            	info_list.add(target_user);
            }
           
            String[] colNames = new String[] {"프로필 사진", "User Name", "User id", "한 줄 소개", "Follow"};
			Object[][] rowDatas = new Object[info_list.size()][colNames.length];
        	
			for(int i=0; i<info_list.size(); i++)
			{
				rowDatas[i] = new Object[] {
						info_list.get(i).getImage(),
						info_list.get(i).getUser_name(),
						info_list.get(i).getId(),
						info_list.get(i).getDescription()
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
	                UserProfile info = new UserProfile();
	                info = info_list.get(rowNum);
	               
	                System.out.println("옵션을 선택하세요.");
	                
	                String[] option = {"프로필 보기", "언팔로우", "취소"};
	                int answer = JOptionPane.showOptionDialog(null, "옵션을 선택하세요.", "알림", 
	                		JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, option, null);
	                
	                if(answer == 0) // 프로필 보기 
	                {
	                	System.out.println(info.getId());
	                	ProfileFrame pf_name = new ProfileFrame(info.getId());
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
	                        

		                	System.out.println(info.getId());
		                	String unfollow_query = "delete from follow where src_id = ? and tgt_id = ?";
		                	
							PreparedStatement unfollow_stmt = conn.prepareStatement(unfollow_query);
							
							unfollow_stmt.setString(1, target_user_id);
							unfollow_stmt.setString(2, info.getId());
							
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
            // new Boardlist2(list);
 
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
            	if(rs!=null)
            		rs.close();
            	
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
 
            try {
                pstmt.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
 
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
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
