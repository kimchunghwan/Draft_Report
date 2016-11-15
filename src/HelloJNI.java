class HelloJNI{
   private native void print();
   public static void main( String[] args ){
      new HelloJNI().print();
   }


   static{
      System.loadLibrary( "HelloJNI" );
   }
}