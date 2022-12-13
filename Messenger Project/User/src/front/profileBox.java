package front;

import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class profileBox extends JFrame implements ActionListener{
	/* Self id & target id */
    private String target_user_id;
    private String self_id;
    
    /* Pop-up Menu */
    public static String option[] = {"프로필 수정", "상태 변경", "프로필 보기", "1:1 채팅하기", "1:1 게임하기"};
    PopupMenu profile_menu = new PopupMenu();
    MenuItem profile_item1 = new MenuItem(option[0]);
    MenuItem profile_item2 = new MenuItem(option[1]);
    MenuItem profile_item3 = new MenuItem(option[2]);
    MenuItem profile_item4 = new MenuItem(option[3]);
    MenuItem profile_item5 = new MenuItem(option[4]);
    /* --- */
    
    public static void main() throws IOException
    {
    	profileBox pb = new profileBox("admin", "admin");
    	pb.setVisible(true);
    	
    }
    
    public void setTarget(String target_id)
    {
    	this.target_user_id = target_id;
    }
    
    public void setSelf(String self_id)
    {
    	this.self_id = self_id;
    }
    
    public profileBox(String self_id, String target_id) throws IOException
    {	
        /* Set ids */
        this.self_id = self_id;
    	this.target_user_id = target_id;
    	
    }
   

    public JPanel init() throws IOException
    {
    	JPanel contentPane;
    	
    	/* Set Main Pane */
    	setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setSize(500, 200);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		/* Button & TextField */
		Image profile_pic = null;
		JTextField field_user_name; 		/* User Name */
		JTextField field_user_id; 			/* User id */
		JTextField field_desc; 				/* 한 줄 소개 */
		
		/* Set main block */

		/* Profile picture */
		String pic_path = "/Users/ajin/Desktop/2022/2022 2학기/컴퓨터 네트워크 및 실습 /Active_Learning/Messenger Project/User/src/image/kitty.jpeg";
		File sourceimage = new File(pic_path);
		profile_pic = ImageIO.read(sourceimage);
		profile_pic = profile_pic.getScaledInstance(130, 130, Image.SCALE_SMOOTH);
		JLabel label_profile_pic = new JLabel(new ImageIcon(profile_pic));
		label_profile_pic.setBounds(10, 70, 100, 100);
		contentPane.add(label_profile_pic);
		
		return contentPane;
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
    

}
