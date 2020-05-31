package graph;

public class Point{
	/*
	 * @param a 点的x坐标
	 * @param a 点的y坐标
	 */
	private int x,y;
	public Point(){}
	public Point(int xx, int yy){
		x = xx;
		y = yy;
	}
	public int getx() {
		return x;
	}
	public int gety() {
		return y;
	}
	public void setx(int xx) {
		this.x = xx;
	}
	public void sety(int yy) { 
		this.y = yy;
	}
	
	public Point minus(Point y) {
		Point n = new Point();
		n.setx(y.getx() - this.getx());
		n.sety(y.gety() - this.gety());
		return n;
	}
	Boolean equals(Point p) {
		/** 判断两个点是否相同
		 * */
		if(this.getx() == p.getx() && this.gety() == p.gety())
			return true;
		else return false;
	}
	void printPoint(){
		System.out.println(""+x+y);
	}
	
}