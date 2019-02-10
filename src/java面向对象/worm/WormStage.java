package worm;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

public class WormStage extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 行数 */
	public static final int ROWS = 35;
	// 列数
	public static final int COLS = 35;
	// 格子大小
	public static final int CELL_SIZE = 10;
	private Worm worm;
	private Cell food;


	public WormStage() {
		worm = new Worm();
		food = createFood();		
	}

	/**
	 * 生成一个食物 1.生成随机数X,Y 2.检查蛇是否包含x,y 2.1 如果包含返回 1 3.创建食物节点.
	 * 
	 * @return
	 */
	private Cell createFood() {
		int x;
		int y;
		Random r = new Random();
		do {
			x = r.nextInt(COLS);
			y = r.nextInt(ROWS);
		} while (worm.contains(x, y));
		return new Cell(x, y);
	}

	/** 重写绘图方法 */
	public void paint(Graphics g) {
		// 填充背景
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		// 绘制食物
		g.setColor(Color.RED);
		g.fill3DRect(CELL_SIZE * food.getX(), CELL_SIZE * food.getY(), CELL_SIZE, CELL_SIZE, true);
		// 绘制蛇

		for (int i = 0; i < worm.getCellsLength(); i++) {
			if (i == 0) {
				g.setColor(Color.GREEN);
			} else {
				g.setColor(Color.CYAN);
			}
			g.fill3DRect(CELL_SIZE * worm.getCells(i).getX(), CELL_SIZE * worm.getCells(i).getY(), CELL_SIZE, CELL_SIZE,
					true);
		}
	}

	public void action() {
		// worm.creep(food);
		// repaint();//swing JPanel 中声明的方法，会尽快的启动
		// //界面的重绘功能，尽快调用paint(g)方法绘制界面
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				// 爬行控制逻辑
				if (worm.hit()) {
					worm = new Worm();
					food = createFood();
				} else {
					if (worm.creep(food)) {
						food = createFood();
					}
				}
				repaint();
			}
		}, 0, 1000 / 5);
		// this 当前舞台面板
		this.requestFocus();	
		this.addKeyListener(new KeyAdapter() {
			int keyReleased = 0;
			public void keyPressed(KeyEvent e) {
				// key 代表哪个按键被按下！		
				int key = e.getKeyCode();
				System.out.println(key);
				System.out.println(keyReleased);
				//if (keyReleased == key) {
			//	} else {
					switch (key) {
					case KeyEvent.VK_UP:// 上箭头按下
						creepTo(Worm.UP);
						break;
					case KeyEvent.VK_DOWN:// 下箭头按下
						creepTo(Worm.DOWN);
						break;
					case KeyEvent.VK_RIGHT:// 左箭头按下
						creepTo(Worm.RIGHT);
						break;
					case KeyEvent.VK_LEFT:// 左箭头按下
						creepTo(Worm.LEFT);
						break;
				//	}
				//keyReleased = 0;
			}
			}
		});// addKeyListener
	}// action();
		// 爬行控制方法，在按键按下时候调用
	
	private void creepTo(int direction) {
		if (worm.hit(direction)) {
			worm = new Worm();
			food = createFood();
		} else {
			boolean eat = worm.creep(direction, food);
			if (eat) {
				food = createFood();
			}
		}
		repaint();
	}

	public static void main(String[] args) {
		// 启动软件
		JFrame frame = new JFrame("贪吃蛇");
		WormStage stage = new WormStage();
		frame.setLayout(null);// 取消窗口的默认布局，取消自动充满
		frame.add(stage); // 窗口里添加面板
		stage.setSize(CELL_SIZE * COLS, CELL_SIZE * ROWS);// 设置面板大小
		stage.setLocation(50, 45);// 设置面板位置
		frame.setSize(460, 480);// 设置窗口大小
		stage.setBorder(new LineBorder(Color.BLACK));// 添加边框,黑线框
		frame.setLocationRelativeTo(null);// frame居中
		frame.setVisible(true);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		stage.action();// 启动蛇的运行
	}

	public String toString() {
		return "worm:" + worm.toString() + "\nfood:" + food;
	}
}
