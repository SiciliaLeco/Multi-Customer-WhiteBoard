运行Connect包里的CanvasServer启动服务器，运行OpenGLApp就是一个用户登陆。


#### 1. 实验要求

1. 借鉴 实验一的类的架构构建绘图类集合，shape、shape类的派生类和graphics类；

2. 将实验一 的OpenGLApp改为Jframe类的派生类，包含Graphics类实例作为数据成员，利用特定的布局管理器（layout）构建主窗口，在窗口中创建相应的控件允许用户选择当前绘制图形的形状、线条颜色、填充颜色等；

3. 添加相应的事件监听和相应方法（处理用户的输入），例如绘制图形的形状、线条颜色、填充颜色的选择变化；

4. 重载主窗口的paintComponent（Graphics g）方法，用于在主窗口中绘制Graphics类实例的图形数据，绘制过程中调用这些图形数据自己的draw()方法，可以把主窗口的g 传给每个图形元素类的 draw方法 ；

5. 在主窗口中注册的MouseListener接口中相关处理方法，根据当前的用户选择实现类似window附件中painter工具的功能，例如：绘制指定图形和移动图形。 

6. 利用sock、多线程机制，修改程序结构实现一个基于java sock的网络白板程序(多个用户协同白板绘图程序)的设计与实现。

#### 2. Shape 类及其派生类（graph包）

##### 2.1 Shape类抽象类

draw()方法，绘制图像；
reshape()重塑图像；
getIndex()获取某个点在图中的标号，正方形有1，2两点，三角形有1，2，3三点，如果返回-1，说明在图像内，返回0在图像外。
move()方法，按照一定偏移量移动图像；
idxInShape()方法寻找某一点是否在图像内部。

##### 2.2其他类

Rectangle类、Triangle类是继承Shape 的派生类；Point类是二维点。



#### 3. graphics 类（graph包）

里面有一个Arraylist类型的`shapeSet`，用来存放所有的图形数据。

##### 3.1 方法介绍

add()方法，加入新增的图案；
drawGraph()方法，遍历`shapeSet`列表，把里面的图案都画出来。
change()方法，做出改变。传入两个二维向量，source和offset，分别是要进行操作的点和偏移量。如果source点是顶点，则对图形做拉伸；如果是图像内中心附近的一点，则对图像做拖动，偏移量为offset。

由于本实验只考虑一个图层，因此只会对一个图形做出改变，在进行change方法时，也是遍历`shapeset`，但是一旦source点符合某一个图形的操作点，对他做操作。即，如果两个图像重叠了，那么change操作对先加入画布的图像作出更改。

#### 4. SkecthPanel初代

说初代是因为这个初代是单机的画板，还没有加上联网的功能。整体而言，是一个继承了Canvas类并且实现了MouseListener, MouseMotionListener两个接口的类。所做的工作就是接受用户在画板上的鼠标事件，然后把颜色、坐标点信息都传给一个static的shape集合，之后我写套接字编程，打算就是把这个集合的内容打包传输给其他用户或者客户端。

isPressing参数是为了实现画图时后的动画而加的，我的思路是在拖拽鼠标的过程中不断绘图。

g是画笔对象，用它在绘板上作画。

#### 5. OpenGLApp初代



#### 6. Connect 包

Connect 包里面包括了一个Server，一个继承了Thread的ServerThread（它的作用是将每一个用户的行为封装成了一个线程，就可以多线程调度）
