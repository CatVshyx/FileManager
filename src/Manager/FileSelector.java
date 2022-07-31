package Manager;

import java.io.File;


class Stack{
	final static int MAX_SIZE = 30;
	private int top;
	private String[] arr;
	Stack(){
		arr = new String[MAX_SIZE];
		top = -1;
	}
	public String peek() { 
		return arr[top];
	}
	public void push(String value) throws NullPointerException , ArrayIndexOutOfBoundsException {
		if (top + 1 >= MAX_SIZE) {
			arr = new String[MAX_SIZE];
			top = -1;
		}
		arr[++top] = value;
	}
	public String pop () throws ArrayIndexOutOfBoundsException {
		if ( top-1 < 0) {throw new ArrayIndexOutOfBoundsException("Array is out of range: " + (top - 1));}
		return arr[top--];
	}
	public String[] getArr() {
		return arr;
	}
}

public class FileSelector{
	private String startPath;
	private String currentPath = startPath;
	private Stack theStack = new Stack();
	public String[] getList() {
		return new File(currentPath).list();
	}
	public static File[] getListRoots() {
 		return File.listRoots();
	}
	public File[] getListFiles() { 
		if (currentPath == null) { return null;}
		return  new File(currentPath).listFiles(); 
	}
	public String getPath() { return currentPath; }
	public void setPath(String path) {
		File newFile = new File(path);
		if (!newFile.isDirectory()) { System.out.println("you cant get here its a file"); return;}	
		currentPath = path;

	}
	public void forward() throws ArrayIndexOutOfBoundsException {
		currentPath = theStack.peek();
		theStack.pop();
	}
	public void back() throws NullPointerException , ArrayIndexOutOfBoundsException {
		theStack.push(currentPath);
		currentPath = new File(currentPath).getParent();
	}
	public Stack getStack() { return theStack; }
	public void setStartPath(String ans) {
		this.currentPath = ans;
	}

}
