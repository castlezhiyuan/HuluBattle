#游戏简介
#游戏简介
##背景
游戏中有两大阵营，正义的葫芦娃一方和邪恶的妖精一方。在战争中，老爷爷和分别蛇精负责指挥葫芦娃和小妖怪作战，直到有一方全部死亡。
##技能
葫芦娃阵营中的葫芦兄弟拥有的武力值整体上高于妖怪阵营，而且七个葫芦娃的武力值各不相同。但是妖怪很狡猾，它会隐藏自己的技能，直到一对一作战时葫芦娃才直到面前敌人的强弱。所以尽管葫芦娃的武力比较高，但是到底是哪一方胜利仍是未知的。
##规则
游戏中有两次战斗。第一次是一对一战斗，胜者继续参加战斗，败者死亡。一对一战斗后，进行以阵营为单位的厮杀，活着的成员一同合作杀死对方阵营。胜负的判断不依据存活的人数而依据成员的总武力值。最后武力值高的阵营胜利，失败的一方全员死亡。
##界面
![](https://github.com/castlezhiyuan/HuluBattle/blob/master/pictures/pic1.PNG)
![](https://github.com/castlezhiyuan/HuluBattle/blob/master/pictures/pic2.PNG)
![](https://github.com/castlezhiyuan/HuluBattle/blob/master/pictures/pic3.PNG)
![](https://github.com/castlezhiyuan/HuluBattle/blob/master/pictures/pic4.PNG)
![](https://github.com/castlezhiyuan/HuluBattle/blob/master/pictures/pic5.PNG)
![](https://github.com/castlezhiyuan/HuluBattle/blob/master/pictures/pic6.PNG)
![](https://github.com/castlezhiyuan/HuluBattle/blob/master/pictures/pic7.PNG)


#游戏设计
##面向对象编程
###抽象
分析整个游戏，抽象出Creature,Camp。两个个基类分别描述场景中的生物体，阵营再从基类中派生出子类，实现一些更具体的功能。使得程序模块清晰。
###封装
封装包括过程封装和数据封装，如Position中的x，y都是private属性，但是可以通过getx(),gety()成员函数获得信息。使得对象的信息被保护。
###继承
1. Huluwa, Grandpa, Scorpion, Snake, Monstor继承Creature基类。BadCamp,GoodCamp继承Camp类，实现两大阵营对峙。图形化界面中窗口和面板继承JFrame和JPanel.
2.  因为类都是继承自终极基类Object，通过super()调用基类版本构造器(比如Position类构造器)
###多态
通过继承和接口，子类可以重写父类中的方法来实现具体行为，多态是超类通过方法签名，向子类提供了一个共同接口，由子类来完善或者覆盖它而实现的。在界面化设计中最经常运用到的就是继承JPanel的子类重写paint函数进行绘图。

##重要知识点运用
###类型框架
1. 使用Vector类来存储动态数组。使用Vector而不是ArrayList的原因是Vector是同步访问的，适用于多线程编程。通过addElement(),removeElement(),iterator迭代器等方法来实现对数组的增减遍历。
2. 使用Hashmap来实现一一映射。比如说Key的属性为姓名，Value的值为武力值，以便于斗争情况判断。

###Lambda表达式
λ表达式主要用于各种回调，比如事件响应器、传入Thread类的Runnable等，目的是让能够对程序行为进行抽象，使得行为可以传递而不仅仅是值，体现了函数式编程的特点。比如说游戏中查找回放记录要用FileFilter(file)-> {}实现过滤。
###多线程并发
每个生物体都是一个线程，图像的刷新也是一个进程。通过线程池来管理线程，并且用synchronized保证线程同步。
###图形化界面
游戏中设计了三个JFrame框架分别表示开始，游戏进行，回放界面。并且通过容器中加入不同的组件来优化界面的视觉。深入理解了java界面化编程中的事件委托机制，在事件监听者中编写事件处理方法来实现事件响应，同时还要在事件源上注册监听。
##设计原则
1. 遵守单一职责原则，将不同的职责封装到不同的类或模块中。
2. 遵循LOD原则，当其中某一个模块发生修改时，尽量少地影响其他模块，扩展会相对容易通信的限制，通过限制实体之间通信的宽度和深度，降低系统的耦合度，使类与类之间保持松散的耦合关系。

#创新和拓展
##1. 对于游戏场景的记录
整个场景是由一个二维数组构成。所以在记录场景时，每一个场面用一个int类型的数组记录，通过读取数组值来确定该位置上的生物。由于位置上还可能存放尸体，所以通过如下方法：
1.每一个位置值初始化为0；
2.设i表示生物的武力值（0到10之间，并且每一个生物武力值不同）。如果位置上有生者，则位置值加上i。如果有死者，则位置值减去 i\*100；
3.用t表示位置值的绝对值，t除以100的商加一可确定死者身份，100减去余数表示生者身份。

##2. 游戏胜负的判定
游戏分两场战争，并且随机性较大。虽然葫芦娃一方的武力值比较强，但是他们比较天真，队形总是统一的。但是妖怪一方会随机打散顺序，让迎战的葫芦娃不知道对手的真实水平。

##3.图片的多样化运用

#感悟
在学习Java的语法时，Java的语法是类似c语言的，所以学习的比较轻松。唯一需要注意的是关键字的用法，public，protected，private，static，abstract什么时候用，为什么要用，怎么用.
重点是领悟学习Java的面向对象的编程语言的特性。比如继承，构造器，抽象类，接口，方法的多态，重载，覆盖，Java的异常处理机制。
熟悉了解Java的类库。基础类库里面的类非常多，但是我真正使用的只有几个，比如说 java.io.\*; java.util.\*,等。
在编程中，许多听的似懂非懂的知识得到了切实运用，掌握一门语言一定要动手做、试着写代码，而不是抱一本书看看就行。很多东西和体会必须自己动手才能真正属于自己。写游戏的过程中，对于异常处理机制，多线程并发，图形化界面都有了深刻的认识，并且在遇到困难时要学会运用网络资源去自己解决。
#存在的问题和不足
由于加载的图片较多，所以登陆界面的背景会偶尔会出现空白，并且测试用例编写的不全面。
