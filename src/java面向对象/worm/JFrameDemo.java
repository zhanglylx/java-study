package worm;
import javax.swing.JFrame; //Frame 框架，相框
import javax.swing.JPanel; //panel 面板
import javax.swing.border.LineBorder;

import com.sun.javafx.geom.Edge;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * 显示窗口与绘图
 * @author Administrator
 *
 */
public class JFrameDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("窗口");
		Stage pane = new Stage();
		frame.setLayout(null);//取消窗口的默认布局，取消自动充满
		frame.add(pane); //窗口里添加面板
		pane.setSize(10*35,10*35);//设置面板大小
		pane.setLocation(50, 45);//设置面板位置
		frame.setSize(460, 480);//设置窗口大小
		pane.setBorder(new LineBorder(Color.BLACK));//添加边框,黑线框
		frame.setLocationRelativeTo(null);//frame居中
		frame.setVisible(true);
		pane.requestFocus();//获取输入“焦点”
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//窗口关闭，程序关闭
		//继承KeyAdapter比实现KeyListener更加简洁
		//在pane上添加键盘事件的监听，获得到底哪个按键被按下
		pane.addKeyListener(new KeyListener(){
			//在按键按下的时执行的方法
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				System.out.println("keyPressed"+e.getKeyCode()+e.getKeyChar());
			}
			//键入某个键是调用的方法
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				System.out.println("keyTyped"+e.getKeyChar());
				
			}
			//在按键释放时候执行的方法
			public void keyReleased(KeyEvent e) {
				System.out.println("keyReleased"+e.getKeyCode());
				// TODO Auto-generated method stub
				
			}
		});//
	}

}
class Stage extends JPanel{
	/**重写了默认的绘图方法*/
	public void paint(Graphics g){
		g.setColor(Color.DARK_GRAY); //面板颜色
		g.fillRect(0, 0, this.getWidth(), this.getHeight());//面板大小
		g.setColor(Color.RED); //绘图颜色
		g.fill3DRect(50, 50, 30, 20, true);
	}
}
