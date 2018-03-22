package test;

public class HelloWorld {
	
	
    public void print()                           //define a new method to output
    {
	System.out.println("Hello World!"); 
    }
	
	public static void main(String[] args) {
		
		HelloWorld helloworld=new HelloWorld(); //new a object
		helloworld.print();                       //call the method
	}

}
