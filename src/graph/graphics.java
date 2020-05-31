package graph;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class graphics {
	public final ArrayList<Shape> shapeSet = new ArrayList<Shape>();
	public graphics(){}
	public void add(Shape s) { //新的图被加入
		shapeSet.add(s);
	}
	public void drawGraph(Graphics g) {
		for(Shape i:shapeSet) {
			i.draw(g);
		} 
	}

	public void change(Point source, Point offset) {
		/** @param source 被选中的点
		 *  @param offset 偏移量
		 *  只有一个图层，但是是有加入顺序的。
		 * */
		if(shapeSet.isEmpty()) {
			System.out.println("nothing");
			return;
		}
		for(Shape i: shapeSet) {
			int idx = i.getIndex(source);
			System.out.println(idx);
			if(idx == -1){
				System.out.println("Move the graph.");
				 //点击的点不是顶点，操作为拖动图形
				i.move(offset);
				break;
			}
			else if(idx != 0){
				System.out.println("Change the size of graph");
				i.reshape(idx, offset);
				break;
			}
			else continue;
		}
	}
	public void print() {
	for(Shape i: shapeSet) {
		i.show();
	}
}

	public void d() {
		for(Shape i:shapeSet) {
			i.p();
		} 
	}
	public static void main(String [] args) {
		graphics g = new graphics();
		Point p1 = new Point(1, 1);
		Point p2 = new Point(21, 21);
		
		Rectangle r = new Rectangle(p1, p2, Color.white, Color.black);
		
		Point pa = new Point(30, 30);
		Point pb = new Point(40, 50);
		
		Triangle t = new Triangle(pa, pb, Color.white, Color.black);
		
		g.add(r);
		g.add(t);
		g.d();
		
		Point s = new Point(30, 40);
		Point offset = new Point(20, 0);
		
		g.change(s, offset);
		
		g.d();
		
		
	}
}
