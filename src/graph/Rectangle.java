package graph;

import java.awt.Color;
import java.awt.Graphics;

public class Rectangle extends Shape {
	private Point a,b;
	private int width, height;
	private Color line, fill;
	
	public Rectangle(Point aa, Point bb, Color l, Color f) {
		super();
		a=aa;b=bb;
		int wid = bb.getx() - aa.getx();
		int hei = aa.gety() - bb.gety();
		width = wid>0?wid:-wid;
		height = hei>0?hei:-hei;
		line = l;
		fill = f;
		
	}
	@Override
	public int type() {
		return 0;
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
		System.out.println(a.getx() + " " + a.gety() + " " + b.getx() + " " + b.gety());
	}

	public void p() {
		System.out.println("paint Rectangle test!");
	}
	@Override
	public void draw(Graphics g) {
		g.setColor(this.line);
		g.drawRect(a.getx()-1, a.gety()-1, width+2, height+2);
		g.setColor(this.fill);
		g.fillRect(a.getx(), a.gety(), width, height);
		//g.fillRect(x, y, width, height);
	}
	
	@Override
	void reshape(int ind, Point offset) {
		// TODO Auto-generated method stub
		if(ind == 1) { //第一个点做修改
			a.setx(a.getx() + offset.getx());
			a.sety(a.gety() + offset.gety());
			System.out.println("Rectangle reshaped!");
		}
		else if(ind == 2) {
			b.setx(b.getx() + offset.getx());
			b.sety(b.gety() + offset.gety());
			System.out.println("Rectangle reshaped!");
		}
		////width 和 height也要对应改变
		int wid = b.getx() - a.getx();
		int hei = a.gety() - b.gety();
		width = wid>0?wid:-wid;
		height = hei>0?hei:-hei;
	}
	
	@Override
	int getIndex(Point mc) {
		// TODO Auto-generated method stub
		if(Math.abs(mc.getx() - a.getx()) < 5 && Math.abs(mc.gety() - a.gety()) < 5)
		//if(mc.getx() == a.getx() && mc.gety() == a.gety())
			return 1;
		else if(Math.abs(mc.getx() - b.getx()) < 5 && Math.abs(mc.gety() - b.gety()) < 5)
		//else if(mc.getx() == b.getx() && mc.gety() == b.gety())
			return 2;
		else if(idxInShape(mc))
			return -1; //-1表示移动
		else return 0;
	}
	@Override
	void move(Point offset) {
		// TODO Auto-generated method stub
		a.setx(a.getx() + offset.getx());
		a.sety(a.gety() + offset.gety());
		b.setx(b.getx() + offset.getx());
		b.sety(b.gety() + offset.gety());
		System.out.println("Rectangle moved!");
	}
	@Override
	Boolean idxInShape(Point mc) {
		/** 如果在图像内，返回真
		 * */
		int midx = (a.getx() + b.getx()) / 2;
		int midy = (a.gety() + b.gety()) / 2;
		if(Math.abs(midx - mc.getx()) < width / 2 && Math.abs(midy - mc.gety()) < height / 2)
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
	
	public int getWidth() {
		return width;
	}
	
	public void setWidth(int w) {
		this.width = w;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int h) {
		this.height = h;
	}
}
