package 编译时异常.自定义异常;
//一维数组模拟栈
/*
	编写程序，使用一维数组，模拟栈数据结构。
	要求：
		1、这个栈可以存储java中的任何引用类型的数据。
		2、在栈中提供push方法模拟压栈。（栈满了，要有提示信息。）
		3、在栈中提供pop方法模拟弹栈。（栈空了，也有有提示信息。）
		4、编写测试程序，new栈对象，调用push pop方法来模拟压栈弹栈的动作。
		5、假设栈的默认初始化容量是10.（请注意无参数构造方法的编写方式。）

 */
//压栈、弹栈失败，通过创建异常对象，然后抛出。
public class MyStack {
    private Object[] elements;
    int index = -1;
    /*public MyStack{

    }
    */
    public MyStack(Object[] elements) {
        this.elements = new Object[10];
    }

    public Object[] getElements() {
        return elements;
    }

    public void setElements(Object[] elements) {
        this.elements = elements;
    }

    public void push(Object obj) throws MyException{
        index++;
        if (index >= elements.length){
            throw new MyException("栈已满，压栈失败！");
        }
        System.out.println("压栈成功,栈帧指向"+index);
    }
    public void pop() throws MyException{
        if(index < 0){
            throw new MyException("栈已空，弹栈失败！");
        }
        while(true) {
            index--;
            while (index < elements.length) {
                System.out.print("弹栈" + elements[index] + "元素成功，");
                index--;
                System.out.println("栈帧指向" + index);
            }
        }
    }
}


