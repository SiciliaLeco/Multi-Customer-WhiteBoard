package Connect;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Connect.ClientControl;
import graph.Shape;
import graph.graphics;

public class OpenGLApp extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4776844100092072198L;
	JPanel toolkit = new JPanel(); //存放命令
	JButton triangle = new JButton("△");
	JButton rectangle = new JButton("□");
	JButton select = new JButton("选择");
	JButton line_color = new JButton("线条颜色");
	JButton fill_color = new JButton("填充颜色");
	JPanel curr_line = new JPanel();
	JPanel curr_fill = new JPanel();
	static graphics ga = new graphics();
	
	
	Color lined_color, filled_color;
	
	public Socket socket;
	public ClientControl controller;
	public Graphics g;
	public sketchPanel canvas;
	//sketchPanel canvas = new sketchPanel(controller);
	
	
	private void initSocket() {
		try {
			socket = new Socket("localhost", 8000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void addShapetoSet(Shape s) {
		ga.add(s);
		//将获取的Shape 全部加入到当前集合中
	}
	
	private void initFrame() {
		g = this.getGraphics();
		controller = new ClientControl(socket, g);
		canvas = new sketchPanel(controller);
		canvas.setGraphics(g);
		setSize(800, 600);
		canvas.setSize(800, 600);
		canvas.setBackground(Color.WHITE);
		curr_line.setBackground(Color.WHITE);
		curr_fill.setBackground(Color.WHITE);
		
		
		setTitle("几何画板");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//默认按键事件
		add(canvas, BorderLayout.CENTER);
		toolkit.add(select);
		toolkit.add(triangle);
		toolkit.add(rectangle);
		toolkit.add(line_color);
		toolkit.add(curr_line);
		toolkit.add(fill_color);
		toolkit.add(curr_fill);
		add(toolkit,  BorderLayout.SOUTH);
		
		//初始化：
		canvas.set_fcolor(Color.WHITE);
		canvas.set_lcolor(Color.BLACK);
		canvas.set_type(0);
		
		controller.receivefromServer();
	}
	private void initListener() {
		//为按钮添加动作事件
				line_color.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						/** 从chooser中选择颜色，默认为空
						 * */
						lined_color = JColorChooser.showDialog(new JFrame(), "选择线条颜色", null);
						if(lined_color == null)
							return;
						curr_line.setBackground(lined_color);
						canvas.set_lcolor(lined_color);
					}
				});
				
				
				fill_color.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						/** 从chooser中选择颜色，默认为空
						 * */
						filled_color = JColorChooser.showDialog(new JFrame(), "选择填充颜色", null);
						if(filled_color == null)
							return;
						curr_fill.setBackground(filled_color);
						canvas.set_fcolor(filled_color);
					}
				});
				
				select.addActionListener(new ActionListener() {
					/** 选中图像做平移或者缩放
					 * */
					@Override
					public void actionPerformed(ActionEvent e) {
//						rec_tri = 0;
						canvas.set_type(0);
						System.out.println("select.");
					}
				});
				
				rectangle.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
//						rec_tri = 1;
						canvas.set_type(1);
						System.out.println("select Rectangle.");
					}
				});
				
				triangle.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
//						rec_tri = 2;
						canvas.set_type(2);
						System.out.println("select Triangle.");
					}
				});	
	}
	public OpenGLApp() {
		initSocket();
		initFrame();
		initListener();
		setLocationByPlatform(true);
        setResizable(true);
	}
	public static void main(String []args) {
		OpenGLApp frame = new OpenGLApp();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		EventQueue.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                JFrame frame = new sketchFrame();
//                frame.setVisible(true);
//                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            }
//        });
	}
}
