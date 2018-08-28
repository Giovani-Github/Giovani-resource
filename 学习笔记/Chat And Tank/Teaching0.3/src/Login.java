import java.awt.*;
import java.awt.event.*;

public class Login extends Frame{
	private TextField accountText;//账号编辑框
	private TextField passwordText;//密码编辑框
	private Button account;//登陆按钮
	private Button exit;//退出按钮
	private String accountStr = "admin";//初始账户
	private String passwordStr = "123456";//初始密码
	private boolean check = false;//账户密码是否正确
	private TeachWindow tw;
	
	private void showTeach() {
		tw = new TeachWindow();
		tw.luachFream();
	}
	
	public void paint(Graphics g) {
		g.drawString("登陆账号：", 10, 50);
		g.drawString("登陆密码：", 10, 80);
	}
	
	public void luancFrame() {
		setBounds(300, 200, 250, 150);
		setLayout(null);//不要布局管理器
		
		//添加账号编辑框
		accountText = new TextField(10);
		accountText.setBounds(70, 35, 150, 20);//手动设置显示的位置
		add(accountText);
		
		//添加密码编辑框
		passwordText  = new TextField(10);
		passwordText.setBounds(70, 65, 150, 20);
		add(passwordText);
		
		//添加登陆按钮
		account = new Button("登陆");
		account.setBounds(50, 100, 30, 25);
		add(account);
		
		//添加退出安妮
		exit = new Button("退出");
		exit.setBounds(150, 100, 30, 25);
		add(exit);
		
		this.setResizable(false);
		myEvent();
		setVisible(true);
	}
	
	//事件监听
	private void myEvent() {
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		account.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validation();
			}
		});
		
		passwordText.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
					validation();
			}
		
		});
	}
	
	//验证密码的操作
	public void validation() {
		String sa = accountText.getText();
		String sp = passwordText.getText();
		
		if(sa.equals(accountStr) && sp.equals(passwordStr)) {
			check = true;
			showTeach();
			check = false;
			setVisible(false);
		} else {
			check = false;
		}
	}
	
	public static void main(String[] args) {
		new Login().luancFrame();
	}
}
