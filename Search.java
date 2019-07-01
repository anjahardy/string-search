public class Search {

    public Search(String pattern, String text){
    }

    public int searchAlgo(String pattern, String text) {

        int m = pattern.length();
        int n = text.length();

        double start = 0;
        start = System.currentTimeMillis();                     // start algorithm timer

        for(int i = 0; i <= (n-m); i++){
            int j;
            for(j = 0; j <= (m-1); j++){
                if(text.charAt(i+j) != pattern.charAt(j)){
                    break;
                }
            }
            if(j == m){

                double end1 = System.currentTimeMillis();
                double totalTime1 = end1- start;
                System.out.println("brute force time " + totalTime1);

                return i;
            }
        }

        double end2 = System.currentTimeMillis();               // stop timer
        double totalTime2 = end2 - start;                       // find total time
        System.out.println("brute force time " + totalTime2);

        return -1;
    }
}
