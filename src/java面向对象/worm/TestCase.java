package worm;

import org.junit.Test;


/**
 * 测试案例
 * @author Administrator
 *
 */
public class TestCase {
//	@Test  //来自JUnit的注释标记，用于执行测试方法
//	//测试蛇的初始化
//	public void testWormInit(){
//		System.out.println("测试Worm构造器");
//		Worm worm  = new Worm();
//		System.out.println(worm);
//	}
//	@Test
//	public void testWormContains(){
//		System.out.println("测试Worm 包含算法");
//		Worm worm = new Worm();
//		System.out.println(worm.contains(2, 0));
//		System.out.println(worm.contains(2,2));
//	}
//	@Test
//	public void testWormStage(){
//		System.out.println("创建舞台实例");
//		WormStage stage = new WormStage();
//		System.out.println(stage.toString());
//	}
//	
//	@Test
//	public void testCreep(){
//		System.out.println("爬行测试");
//		Worm worm = new Worm();
//		System.out.println(worm);
//		worm.creep();
//		System.out.println(worm);
//	}	
//	@Test
//	public void testCreepForFood(){
//		System.out.println("检查食物的爬行");
//		Worm worm = new Worm();
//		Cell food = new Cell(1,2);
//		System.out.println(worm);
//		System.out.println(worm.creep(Worm.DOWN, food));
//		System.out.println(worm);
//		System.out.println(worm.creep(Worm.RIGHT,food));
//		System.out.println(worm);
//		System.out.println(worm.creep(Worm.RIGHT,food));
//		System.out.println(worm);
//		System.out.println(worm.creep(Worm.DOWN,food));
//		System.out.println(worm);
//		System.out.println(worm.creep(Worm.LEFT,food));
//		System.out.println(worm);
//	
//	}
	@Test
	public void testHit(){
		System.out.println("检查蛇撞击检查");
		Worm worm = new Worm();
		Cell food = new Cell(3,0);
		worm.creep();
//		for(int i=1;i<34;i++){
//			worm.creep(Worm.DOWN, food);
//		}
//		System.out.println(worm.toString());
//		System.out.println(worm.hit(Worm.DOWN));
		for(int i=1;i<36;i++){
			worm.creep(Worm.RIGHT, food);
		}
		System.out.println(worm.toString());
		System.out.println(worm.hit(Worm.DOWN));
		
//		worm.creep(Worm.DOWN, food);
//		System.out.println(worm);
//		System.out.println(worm.creep(Worm.DOWN, food));
//		System.out.println(worm);
//		System.out.println(worm.hit(Worm.LEFT));
//		System.out.println(worm.hit(Worm.RIGHT));
		worm.creep(Worm.RIGHT, food);
		worm.creep(Worm.RIGHT, food);
		//worm.creep(Worm.RIGHT, food);
//		System.out.println(worm);
//		System.out.println(worm.creep(Worm.UP, food));
//		System.out.println(worm);
//		System.out.println(worm.hit(Worm.UP));
		worm.creep(Worm.UP, food);
		System.out.println(worm.toString());
		System.out.println(worm.hit(Worm.LEFT));
	}

}
