package showCase;

public class TestJNI {

	private native void print();
	
	static {
		System.loadLibrary("Native");
	}

	public static void Main(String[] args) {
		// TODO Auto-generated method stub
		new TestJNI().print();
	}

}
