package Connect;

import java.awt.Color;
import java.awt.Graphics;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Stack;

import graph.Point;
import graph.Rectangle;
import graph.Shape;
import graph.Triangle;

public class ClientControl {
	private Socket socket;
	@SuppressWarnings("unused")
	private Graphics g2; 
	public ClientControl(Socket s, Graphics g) 	{
		this.socket = s;
		this.g2 = g;
	}

	//接受服务器发送的信息
	public void receivefromServer() {
		new Thread() {
			@Override
			public void run() {
				try {
				while(true) {
					
					InputStream is  = socket.getInputStream();
					OutputStream os = socket.getOutputStream();
					
					DataInputStream dis = new DataInputStream(is);
					DataOutputStream dos = new DataOutputStream(os);
					
					
					int type;
					int r_bx, r_by, r_ex, r_ey; 
					type = dis.readInt();
					r_bx = dis.readInt();
					r_by = dis.readInt();
					r_ex = dis.readInt();
					r_ey = dis.readInt();
					int l, c;
					l = dis.readInt();
					c = dis.readInt();
					Color ll = new Color(l);
					Color cc = new Color(c);
					System.out.println("type:"+ type);
					System.out.println("get:" + r_bx + " " + r_by +" " + r_ex +" " + r_ey);
					System.out.println("Color:" + ll.getRGB() + " " + cc.getRGB());
					switch(type) {
					case 1: //rectangle 
						int wid = r_bx - r_ex;
						int hit = r_by - r_ey;
						Point a = new Point(r_bx, r_by);
						Point b = new Point(r_ex, r_ey);
						Rectangle r = new Rectangle(a, b, ll, cc);
						sketchPanel.Graph.add(r); 
						break; //一定要写break，不然会重叠绘制！
					case 2: 
						Point at = new Point(r_bx, r_by);
						Point bt = new Point(r_ex, r_ey);
						Triangle t = new Triangle(at, bt, ll, cc);
						sketchPanel.Graph.add(t);
						break;
					case 0: //move
						Point begin = new Point(r_bx, r_by);
						Point end = new Point(r_ex, r_ey);
						sketchPanel.Graph.change(begin, begin.minus(end));
						break;
					}
				}
				
				} catch(Exception e) {
					e.printStackTrace();
				} 
			}
		}.start();
	}
	
	public void sendtoServer(Point begin, Point end, int type, Color l, Color f) {
		try {
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			
			DataInputStream dis = new DataInputStream(is);
			DataOutputStream dos = new DataOutputStream(os);
			/** 写入信息的格式比较整齐，如下：
			 *  type Xa Ya Xb Yb LR LG LB FR FG FB
			 *  
			 *  @param type 形状的类型
			 *  @param Xa Xb Ya Yb 形状对应的a b 点的坐标
			 *  @param LR LG LB 线条颜色的rgb对应值
			 *  @param FR FG FB 填充颜色的rgb对应值
			 * */
			
			int bx, by, ex, ey;
			bx = begin.getx(); by = begin.gety();
			ex = end.getx(); ey = end.gety(); 
			
			System.out.println("传输鼠标事件坐标： "+ bx + " " + by +"; " + ex + " " + ey);
			dos.writeInt(type);
			dos.writeInt(bx);
			dos.writeInt(by);
			dos.writeInt(ex);
			dos.writeInt(ey);
			dos.writeInt(l.getRGB());
			dos.writeInt(f.getRGB());
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
