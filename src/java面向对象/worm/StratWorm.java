package worm;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

public class StratWorm {
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
	public StratWorm(){}
	public void start(){
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
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		stage.action();// 启动蛇的运行
	}
}
