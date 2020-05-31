package app;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Enumeration;
import java.util.Stack;

import graph.Point;
import graph.Rectangle;
import graph.Shape;
import graph.Triangle;
import graph.graphics;

public class sketchPanel_1st_edition extends Canvas implements MouseListener, MouseMotionListener {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//	private Point begin = new Point(0, 0);
//	private Point end = new Point(0, 0);
	private int bx = 0, by = 0, ex = 0, ey = 0;
	private boolean isPressing = false;
	Graphics g = this.getGraphics();
	private static graphics Graph = new graphics();
	private final Stack<Shape> currshape =  new Stack<Shape>();
	private int type = 0;
	/** @param type
	 * 0 ~ 不绘制
	 * 1 ~ rectangle
	 * 2 ~ triangle
	 * */
	private Color l, f;
	/**
	 * @param bx by ex ey 分别是拖拽动作起点和终点的横纵坐标
	 * @param isPressing 判断是否在拖拽，如果是，在绘制图像的时候做绘制过程动画
	 * @param g 画笔
	 * @param currshape Graph 用来存放要绘画的图
	 * @param l f 分别是绘制图像的时候 线条颜色和填充颜色
	 * */
	
	public sketchPanel_1st_edition() {
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	public void set_type(int t) {
		this.type = t;
	}
	
	public void set_lcolor(Color ll) {
		l = ll;
	}
	
	public void set_fcolor(Color ff) {
		f = ff;
	}
	public void set_graphics(Graphics gg) { //画笔
		this.g = gg;
	}
	
	public Graphics get_graphics() {
		return this.g;
	}
	public void set_graph(graphics ga) {
		//this.Graph =  ga;
	}
	@Override
	public void paint(Graphics g) {
		//先绘制出画板
		int w = 800;
		int h = 600;
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, w, h);
		
		Shape s = null; //准备绘制所有图,已经保存在列表里面了
		Enumeration<Shape> current = currshape.elements();
		//Graph.drawGraph(g);
		while(current.hasMoreElements()) {
			s = current.nextElement();
			s.draw(g);
		}
		
		if(isPressing) { //增加动态变化，在拖动鼠标的过程中有迹可循
			switch(type) {
			case 1: //画矩形
				g.setColor(l);
				int w1 = bx - ex;
				int h1 = by - ey;
				if(ex < bx) {
					if(ey < by) { //end 点在左上方
						g.drawRect(ex, ey, w1, h1);
					} else {
						g.drawRect(ex, by, -w1, -h1);
					}
				} else {
					if(ey < by) {
						g.drawRect(bx, ey, -w1, h1);
					} else {
						g.drawRect(bx, by , -w1, -h1);
					}
				}
				
				break;
			case 2: //画三角形
				int cx =ex - 2 *(ex - bx);
				int cy = ey;
				g.setColor(l);
				g.drawLine(bx, by, ex, ey);
				g.drawLine(bx, by, cx, cy);
				g.drawLine(ex, ey, cx, cy);
				break;
				
			case 0: //只是拖动
				g.setColor(Color.BLUE);
				g.drawLine(bx, by, ex, ey);
			}
		}
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		ex = (e.getX());
		ey = (e.getY());
		this.repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		bx = (e.getX());
		by = (e.getY());
		isPressing = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		ex = (e.getX());
		ey = (e.getY());
		isPressing = false;
		Point begin = new Point(bx, by);
		Point end = new Point(ex, ey);
		switch(type) {
		case 1: //画矩形
			Rectangle r = new Rectangle(begin, end, l, f);
			currshape.push(r);
			Graph.add(r);
			
			break;
		case 2: //画三角形
			
			Triangle t = new Triangle(begin, end, l , f);
			currshape.push(t);
			Graph.add(t);
			break;
		case 0: //只做移动或者更改大小
			Graph.change(begin, begin.minus(end));
			break;
		}
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
