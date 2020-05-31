package graph;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Shape{
	public abstract void show();
	public abstract void draw(Graphics g);
	abstract void p();
	abstract void reshape(int ind, Point offset);
	/**
	 * @param index 点的编号
	 * @param offset 点的偏移量
	 * */
	abstract int getIndex(Point mouseclicked);
	abstract void move(Point offset);
	abstract Boolean idxInShape(Point mouseclicked);
	public abstract int type(); // 1 ~ Tri , 2 ~rect
	/** 对于graphics类，两个点就可以确定一个图形，因此只需要写两个get点的函数方法
 	 * */
	public abstract Point get_a();
	public abstract Point get_b();
	public abstract Color get_l(); 
	public abstract Color get_f();
}

