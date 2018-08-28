import java.awt.*;
import java.awt.event.*;

/**
 * 教学主界面类
 * @author 李庆旺
 *
 */
class TeachWindow extends Frame{
	private Button b1;
	private Button b2;
	private Button b3;
	private Button b4;
	private Button enter = new Button("确定");
	private TeachWindow t;//自身引用，做dialog对话框用
	private Dialog f = new Dialog(t, "提示");
	private Label la = new Label();//标签，在对话框里显示
	
	
	public TeachWindow(TeachWindow t) {
		this.t = t;
	}
	
	public void luachFream() {
		setBounds(300, 300, 800, 600);
		setLayout(null);//布局管理器设置为空，以便任意添加组件
		b1 = new Button("同步课程");
		b2 = new Button("单元练习");
		b3 = new Button("练习思考");
		b4 = new Button("应试训练");
		
		b1.setBounds(100, 100, 60, 30);
		b2.setBounds(110, 150, 60, 30);
		b3.setBounds(120, 200, 60, 30);
		b4.setBounds(130, 250, 60, 30);
		
		add(b1);
		add(b2);
		add(b3);
		add(b4);
		
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
		
		//点击同步课堂时的监听
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f.setBounds(400, 300, 200, 150);
				f.setLayout(null);
				
				//添加确定按钮
				enter.setBounds(80, 100, 40, 30);
				f.add(enter);
				
				//添加标签
				la.setBounds(50, 30, 100, 100);
				la.setText("进入同步课堂教学");
				f.add(la);
				
				f.setVisible(true);
			}		
		});
		
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent w) {
				f.setVisible(false);
			}
		});
		
		enter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f.setVisible(false);
				
			}
			
		});
	}
}
