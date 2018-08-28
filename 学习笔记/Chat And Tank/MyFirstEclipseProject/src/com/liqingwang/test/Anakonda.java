package com.liqingwang.test;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
 
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
 
public class Anakonda extends JFrame {
    public static final int ANSKONDA_PART_WIDTH = 8; // 蛇宽
    public static final int ANSKONDA_PART_HEIGHT = 8; // 蛇高
    public static final int GAME_FACE_WIDTH = ANSKONDA_PART_WIDTH * 30; // 游戏屏宽
    public static final int GAME_FACE_HEIGHT = ANSKONDA_PART_WIDTH * 30; // 游戏屏高
 
    private Control control;
 
    public Anakonda(String name, Control control) {
        super(name);
        this.setSize(GAME_FACE_WIDTH, GAME_FACE_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.control = control;
        this.add(control);
        this.setLocationRelativeTo(null); // 中间显示
        this.addKeyListener(new KeyMonitor(control));
        this.setVisible(true);
    }
 
    public void launch() {
        try {
            while (true) {
                Thread.sleep(500);
                this.repaint(); // 重画
                control.gameOver(); // 检查挂的方法
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
 
    public static void main(String[] args) {
        Control control = new Control();
        Anakonda anakonda = new Anakonda("Anakonda", control);
        anakonda.launch();
    }
}
 
class Control extends JPanel {
 
    // 长度
    List<Part> parts;
    Part eatPart; // 预知目标
    private Random random;
 
    public Control() {
        initialization();
    }
 
    // 初始化一个点
    public void initialization() {
        random = new Random();
        eatPart = newPart();
        parts = new ArrayList<Part>();
        parts.add(new Part(Anakonda.ANSKONDA_PART_WIDTH,
                Anakonda.ANSKONDA_PART_HEIGHT, Guide.down, new Point(112, 0)));
    }
 
    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.RED);
        eatPart.draw(g);
        take();
        g.setColor(Color.BLUE);
        // 画蛇
        for (int i = 0; i < parts.size(); i++) {
            Part part = parts.get(i);
            part.draw(g);
        }
        // 前面一个点告诉后面一个点下一次的方向
        for (int i = parts.size() - 1; i >= 0; i--) {
            if ((i - 1) >= 0) {
                parts.get(i).guide = parts.get(i - 1).guide;
            }
        }
        g.setColor(Color.GRAY);
        g.drawString("长度 : " + parts.size(), 0, 10);
        g.setColor(c);
    }
 
    // 随机产生一个被吃的点 规则: 小于宽,必须和蛇的路线一样
    public Part newPart() {
        int x = random.nextInt(Anakonda.GAME_FACE_WIDTH - Anakonda.ANSKONDA_PART_WIDTH);
        int y = random.nextInt(Anakonda.GAME_FACE_HEIGHT - Anakonda.ANSKONDA_PART_HEIGHT);
        int xTemp = x % Anakonda.ANSKONDA_PART_WIDTH;
        int yTemp = y % Anakonda.ANSKONDA_PART_HEIGHT;
        x = xTemp == 0 ? xTemp : x - xTemp <= 0 ? 0 : x - xTemp;
        y = yTemp == 0 ? yTemp : y - yTemp <= 0 ? 0 : y - yTemp;
        System.out.println(" x : " + x + " y : " + y);
        return new Part(Anakonda.ANSKONDA_PART_WIDTH,
                Anakonda.ANSKONDA_PART_HEIGHT, Guide.stop, new Point(x, y));
         
    }
 
    // 检查是否吃到 随机点
    public void take() {
        if (!parts.get(0).p.equals(eatPart.p))
            return;
        Part part = parts.get(parts.size() - 1);
        Part newPart = new Part(Anakonda.ANSKONDA_PART_WIDTH,
                Anakonda.ANSKONDA_PART_HEIGHT, part.guide, (Point) part.p
                        .clone());
        switch (newPart.guide) {
        case up:
            newPart.p.y += newPart.height;
            break;
        case down:
            newPart.p.y -= newPart.height;
            break;
        case left:
            newPart.p.x += newPart.width;
            break;
        case right:
            newPart.p.x -= newPart.width;
            break;
        }
        parts.add(newPart);
        eatPart = newPart();
    }
 
    // 挂的方法
    public boolean gameOver() {
        Part part = parts.get(0);
        if (part.p.x < 0 || part.p.x > Anakonda.GAME_FACE_WIDTH || part.p.y < 0
                || part.p.y > Anakonda.GAME_FACE_HEIGHT) {
            JOptionPane.showMessageDialog(this, "GAME OVER!", "warning",
                    JOptionPane.OK_OPTION);
            initialization();
            return true;
        }
        return false;
    }
 
}
 
// 小点蛇类
class Part {
    int width;
    int height;
    Guide guide = Guide.down;
    Point p;
 
    public Part() {
    }
 
    public Part(int width, int height, Guide guide, Point p) {
        this.width = width;
        this.height = height;
        this.guide = guide;
        this.p = p;
    }
 
    public void draw(Graphics g) {
        nextWay();
        g.draw3DRect(p.x, p.y, width, height, false);
    }
 
    public void nextWay() {
        switch (guide) {
        case up:
            p.y = p.y - height;
            break;
        case down:
            p.y = p.y + height;
            break;
        case left:
            p.x = p.x - width;
            break;
        case right:
            p.x = p.x + width;
            break;
        }
    }
}
 
class KeyMonitor extends KeyAdapter {
 
    private Control control;
 
    public KeyMonitor(Control control) {
        this.control = control;
    }
 
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
        case KeyEvent.VK_UP:
            if (control.parts.get(0).guide == Guide.down)
                return;
            control.parts.get(0).guide = Guide.up;
            break;
        case KeyEvent.VK_DOWN:
            if (control.parts.get(0).guide == Guide.up)
                return;
            control.parts.get(0).guide = Guide.down;
            break;
        case KeyEvent.VK_LEFT:
            if (control.parts.get(0).guide == Guide.right)
                return;
            control.parts.get(0).guide = Guide.left;
            break;
        case KeyEvent.VK_RIGHT:
            if (control.parts.get(0).guide == Guide.left)
                return;
            control.parts.get(0).guide = Guide.right;
            break;
        default:
            return;
        }
    }
}
 
enum Guide {
    up, down, left, right, stop
}