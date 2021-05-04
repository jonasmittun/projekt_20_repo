package dtu.company.app;

public class MainTesting {
    public static void main(String[] args){
        int place = 1;
        int holder = 1;
        for (int i = 4; i >= 1; i--) {

            for (int j = 4; j >= holder; j--) {

                for (int k = place; k < place + 5; k++){
                    //assignEmployee(i,k,"project " + j);
                    System.out.println("Empoyee:" + i + " Project:" + j + " Activity:" + k);
                }
            }
            holder++;
            place = place + 5;
        }
    }
}
