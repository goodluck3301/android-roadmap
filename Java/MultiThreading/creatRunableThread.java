class Some implements Runnable {
    @Override
    public void run() {
        for(int i = 0; i< 10; i++)
            System.out.println("Number - "+i);
    }
}


public class Inter {
    public static void main(String[] args) {
      
        Thread test = new Thread(new Some());
        Thread test1 = new Thread(new Some());
    
        test.start();
        test1.start();
    }
}
