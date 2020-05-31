package graph;

import java.awt.Color;
import java.awt.Graphics;

public class Triangle extends Shape{
	/** @param a
	 *  @param b
	 *  @param c
	 * */
	Point a,b,c;
	private Color line, fill;
	@Override
	public int type() {
		return 1;
	}
	@Override
	public Point get_a() {
		return this.a;
	}
	@Override
	public Point get_b() {
		return this.b;
	}
	@Override
	public Color get_l() {
		return this.line;
	}
	@Override
	public Color get_f() {
		return this.fill;
	}
	@Override
	public void show() {
		System.out.println(a.getx() + " " + a.gety() + " " + b.getx() + " " + b.gety() + " " 
				+ c.getx() + " " + c.gety() );
	}
	private int eps = 50;
	public Triangle(){}
	public Triangle(Point aa, Point bb, Color l, Color f){
		super();
		/** 本程序绘制等边三角形
		 * */
		a=aa; b=bb; 
		c = new Point();
		c.sety(b.gety());
		c.setx(b.getx()-2*(b.getx()-a.getx()));
		line = l;
		fill = f;
	} 
	public void p() {
		System.out.println("paint Triangle test!");
	}
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(line);
		int px[] = {a.getx(), b.getx(), c.getx()};
		int py[] = {a.gety(), b.gety(), c.gety()};
		g.drawPolygon(px, py, 3);
		g.setColor(fill);
		g.fillPolygon(px, py, 3);
	}
	@Override
	void reshape(int ind, Point offset) {
		// TODO Auto-generated method stub
		/** 三角形的三个点收到改变都会改变整个图形
		 * */
		if(ind == 1) {
			a.setx(a.getx() + offset.getx());
			a.sety(a.gety() + offset.gety());
			System.out.println("Triangle reshaped, a point" + a.getx() + "  "+ a.gety());
		}
		else if(ind == 2) {
			b.setx(b.getx() + offset.getx());
			b.sety(b.gety() + offset.gety());
			System.out.println("Triangle reshaped, b point" + b.getx()+ "  " + b.getClass());
		}
		else if(ind == 3) {
			c.setx(c.getx() + offset.getx());
			c.sety(c.gety() + offset.gety());
			System.out.println("Triangle reshaped, c point" + c.getx() + "  " + c.gety());
		}
	}
	@Override
	int getIndex(Point mc) {
		// TODO Auto-generated method stub
		//if(mc.getx() == a.getx() && mc.gety() == a.gety())
		if(Math.abs(mc.getx() - a.getx()) < 5 && Math.abs(mc.gety() - a.gety()) < 5)
			return 1;
		else if(Math.abs(mc.getx() - b.getx()) < 5 && Math.abs(mc.gety() - b.gety()) < 5)
		//else if(mc.getx() == b.getx() && mc.gety() == b.gety())
			return 2;
		//else if(mc.getx() == c.getx() && mc.gety() == c.gety())
		if(Math.abs(mc.getx() - c.getx()) < 5 && Math.abs(mc.gety() - c.gety()) < 5)
			return 3;
		else if(idxInShape(mc))
			return -1; //移动
		else return 0; //不对该图形做任何操作
	}
	@Override
	void move(Point offset) {
		// TODO Auto-generated method stub
		a.setx(a.getx() + offset.getx());
		a.sety(a.gety() + offset.gety());
		b.setx(b.getx() + offset.getx());
		b.sety(b.gety() + offset.gety());
		c.setx(c.getx() + offset.getx());
		c.sety(c.gety() + offset.gety());
		System.out.println("Triangle moved!");
	}
	@Override
	Boolean idxInShape(Point mc) {
		/** 由于三角形内部判断比较复杂，改为判断点击的点是否在中心旁边
		 * @param center_x 三角形中心的x坐标
		 * */
		int center_x = (a.getx()+b.getx()+c.getx())/3;
		int center_y = (a.gety()+b.gety()+c.gety())/3;
		if(Math.abs(mc.getx()-center_x) < eps && 
				Math.abs(mc.gety()-center_y) < eps)
			return true;
		else return false;
	}
	
	public Point getA() {
		return a;
	}
	public void setA(Point aa) {
		this.a = aa;
	}
	public Point getB() {
		return b;
	}
	public void setB(Point bb) {
		this.b = bb;
	}
	public Point get() {
		return c;
	}
	public void setC(Point cc) {
		this.c = cc;
	}
}
