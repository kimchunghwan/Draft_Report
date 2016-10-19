package showCase;

public class helloWorld {

	private native void print();

	static {
		System.loadLibrary("Native");
	}

	public static void main(String[] args) {
		new helloWorld().print();
	}

}