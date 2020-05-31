package Connect;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Date;

public class ServerThread extends Thread{
	public Socket socket;
	public DataInputStream dis;
	public DataOutputStream dos; 
	/** @param socket 一个客户端
	 *  @param is os 客户端输入和输出的数据流
	 *  交互的画板，需要用户传输自己画的，接受其他用户画的
	 * */
	
	public ServerThread(Socket s) {
		this.socket = s;
	}
	
	@Override
	public void run() {
		try {
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			dis = new DataInputStream(is);
			dos = new DataOutputStream(os);
			while(true) {
				//服务器中转接收数据
				int val = dis.read(); //读取当前用户数据
				for(int i = 0; i < CanvasServer.threadStack.size(); i++) {
					//将与服务器进行了连接的客户 写当前数据
					ServerThread tmp = CanvasServer.threadStack.get(i);
					if(this != tmp) {
						tmp.dos.write(val);
						tmp.dos.flush();
					}
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			CanvasServer.threadStack.remove(this); //出现异常，当前用户退出
			try {
				this.socket.close();
				CanvasServer.jtaLog.append(new Date() + " Player has quited!\n"); //打印在服务器
				CanvasServer.playerNum -= 1;
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//System.out.println("An account is quited!");
			
		}  
//		finally {
//			try {
//				dos.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			try {
//				dis.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
	}
}
