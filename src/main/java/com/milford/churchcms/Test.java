package com.milford.churchcms;

import com.milford.churchcms.service.TextMessageService;


public class Test{

//     public static void main(String []args) throws Exception{
//         
//         TextMessageService text = new TextMessageService();
//         
//         text.sendMessage(staff, messageText, requester)
//         
//     }
     
     public static void exceptionTry(){// throws Exception{
        int a = 0;
   //     try{
            a = 10/0;
    //    }catch(Exception ex){
          //  ex.printStackTrace(); // In Black
    //        throw new OurException("This is messy"); //In Red
    //    }
    }

}

class OurException extends RuntimeException{
    public OurException(String errorMessage) {
        super(errorMessage);
    }
}

//class SfFuture{
//    ExecutorService executor = Executors.newSingleThreadExecutor();
//    
//    public Future<Integer> calculate(Integer input) {        
//        return executor.submit(() -> {
//            Thread.sleep(1000);
//            return input * input;
//        });
//    }
//    
//    public void shutdown(){
//        executor.shutdown();
//    }
//}

//    class Beverage{
//        @Autowired
//        ChurchRepository churchRepository;
//        
//        public void doStuff(){
//            System.out.println("Password: " + returnInfo());
//        }
//        
//      private ChurchInfo returnInfo(){
//        List<ChurchInfo> infoList = churchRepository.findAll();
//        ChurchInfo myInfo = null;        
//        for(ChurchInfo info : infoList){
//            myInfo = info;
//        }
//        System.out.println("Church Info: " + myInfo.toString());
//        return myInfo;
//      }
//    }

//class Lambda{
//     List<Integer> numbers = Arrays.asList(1,2,3);
//    public void doStuff(){
//       // numbers.stream().filter( number -> (number % 2 != 0)).forEach(System.out::println);
//       numbers.stream().filter(Test123::isOdd).forEach(System.out::println);
//    }
//    
//}
//
//class Test123{
//    
//    public boolean isOdd(int number){
//        return (number % 2 != 0);
//    }
//    
//    public boolean hasOverHundredPoints() {
//        return true;
//    }
//}
//
//class Color implements Comparable<Color>{
//    private int R;
//    private int G;
//    private int B;
//
//    public Color(int R, int G, int B) {
//        this.R = R;
//        this.G = G;
//        this.B = B;
//    }
//
//    public int getR() {
//        return R;
//    }
//
//    public void setR(int R) {
//        this.R = R;
//    }
//
//    public int getG() {
//        return G;
//    }
//
//    public void setG(int G) {
//        this.G = G;
//    }
//
//    public int getB() {
//        return B;
//    }
//
//    public void setB(int B) {
//        this.B = B;
//    }
//
//    @Override
//    public int compareTo(Color t) {
//        if(this.R == t.R){
//            return 0;
//        }
//        return 1;
//    }
//}
//
//        
//        Thread loop = new Thread(
//          new Runnable() {
//            @Override
//            public void run() {
//              while (true) {
//                if (Thread.interrupted()) {
//                  break;
//                }
//                // Continue to do nothing
//              }
//            }
//          }
//        );
//        loop.start();
//        System.out.println("Enter Y/N.");
//        Scanner s = new Scanner(System.in);
//        String nextLine = s.nextLine();
//        
//        if("y".equalsIgnoreCase(nextLine))
//            loop.interrupt();
//        System.out.println("Loop: " + loop.isInterrupted());

