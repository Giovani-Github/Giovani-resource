import java.awt.*;
import java.awt.event.*;

class TeachWindow extends Frame{
	private Button b1;
	private Button b2;
	private Button b3;
	private Button b4;
	
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
	
	private void myEvent() {
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
}
