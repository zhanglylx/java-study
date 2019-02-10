package worm;

import java.util.Arrays;

/**
 * 贪吃蛇
 * 
 * @author Administrator
 *
 */
public class Worm {
	public static final int DEFAULT_LENGTH = 55;
	private Cell[] cells;
	public static final int UP = 1;
	public static final int DOWN = -1;
	public static final int LEFT = 2;
	public static final int RIGHT = -2;
	/** 蛇当前的运行方向 */
	private int currentDirection;

	public Worm() {
		cells = new Cell[DEFAULT_LENGTH];
		for (int i = 0; i < cells.length; i++) {
			cells[i] = new Cell(i, 0);
		}
		// 启动程序默认起始方向
		currentDirection = DOWN;
	}
	public Cell getCells(int number){
		return cells[number];
	} 
	public int getCellsLength(){
		return cells.length;
	}
	public boolean contains(int x, int y) {
		for (int i = 0; i < cells.length; i++) {
			Cell node = cells[i];
			if (node.getX() == x && node.getY() == y) {
				return true;
			}
		}
		return false;
	}

	public void creep() {
		for (int i = cells.length - 1; i > 0; i--) {
			cells[i] = cells[i - 1];
		}
		cells[0] = createHead(currentDirection);
	}

	// 检查头方向并赋值
	private Cell createHead(int currentDirection) {
		int x = cells[0].getX();
		int y = cells[0].getY();
		switch (currentDirection) {
		case UP:
			y--;
			break;
		case DOWN:
			y++;
			break;
		case LEFT:
			x--;
			break;
		case RIGHT:
			x++;
			break;
		default:
			System.out.println("检查头方向出错direction：" + currentDirection);
			System.exit(0);
			break;
		}
		return new Cell(x, y);
	}

	/**
	 * 1 计算currentDirection与direction的和， 如果是0表示反向了，就结束方法返回，不进行任何动作 2
	 * currentDirection = direction 改变当前的方向， 作为下次运行的方向 3 判断当前头节点的坐标与食物对象的坐标一致
	 * 如果一致说明是吃到食物了 4 如果吃到食物，就将cells数组进行扩容 将cells数组内容的每个元素向后移动 5
	 * 将新头节点插入的头位置cells[0]= newHead 6 返回是否吃到食物
	 * 
	 * @param x
	 * @param y
	 */
	public boolean creep(Cell food){
		return creep(currentDirection,food);
	}
	public boolean creep(int direction, Cell food) {
		if (currentDirection + direction == 0) {
			return false;// 反向了，不进行任何操作
		}
		currentDirection = direction;
		Cell head = createHead(direction);
		boolean eat = head.getX() == food.getX() && head.getY() == food.getY();
		if (eat) {
			cells = Arrays.copyOf(cells, cells.length + 1);
		}
		for (int i = cells.length - 1; i > 0; i--) {
			cells[i] = cells[i - 1];
		}
		cells[0] = head;
		return eat;
	}

	public boolean hit() {
		return hit(currentDirection);
	}

	// 检查蛇是否撞到边界或撞到自己
	public boolean hit(int direction) {
		Cell head = createHead(direction);
		if (head.getX() < 0 || head.getX() >= WormStage.COLS || head.getY() < 0 || head.getY() >= WormStage.ROWS) {
			return true;
		}
		
		for (int i = 3; i <cells.length-1; i++) {
			if (cells[i].getX() == head.getX() && cells[i].getY() == head.getY()) {
				return true;
			}
		}
		return false;

	}

	public String toString() {
		return Arrays.toString(cells);
	}
}
