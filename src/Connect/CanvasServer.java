package Connect;

import java.awt.BorderLayout;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class CanvasServer extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static JTextArea jtaLog = new JTextArea(); //服务器信息显示
	
	// Create a scroll pane to hold text area
    JScrollPane scrollPane = new JScrollPane(jtaLog);
	public static int playerNum = 0;
	public ServerSocket serverSocket;
	public static Stack<ServerThread> threadStack = new Stack<ServerThread>();
	//用一个栈区存放当前的线程
	public void start() throws IOException {
		serverSocket = new ServerSocket(8000); //分配端口
		jtaLog.append(new Date() +
		        ": Server started at socket 8000\n");
		while(true) {
			//等待用户加入
			Socket socket = serverSocket.accept();
			playerNum += 1;
			jtaLog.append(new Date() + "：Player "+ playerNum + "has entered!\n");
			ServerThread st = new ServerThread(socket); //开启新的用户线程
			st.start();
			threadStack.add(st);
		}
	}
	
	public CanvasServer() {
		// Add the scroll pane to the frame
	    add(scrollPane, BorderLayout.CENTER);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setSize(300, 300);
	    setTitle("CanvasServer");
	    setVisible(true);
	}
	
	public static void main(String [] args) throws IOException {
		CanvasServer cs = new CanvasServer();
		cs.start();//启动服务器端
	}
}
