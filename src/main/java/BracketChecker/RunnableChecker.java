package BracketChecker;

import java.io.FileReader;
import java.io.IOException;

public class RunnableChecker implements Runnable {
    private FileReader fr;
    private int threadNo;
    private static int i = 0;
    public RunnableChecker(FileReader fr, int tn){
        this.threadNo = tn;
        this.fr = fr;
    }

    public FileReader getFr() {
        return fr;
    }

    public void setFr(FileReader fr) {
        this.fr = fr;
    }

    public static int getI() {
        return i;
    }

    public static void setI(int i) {
        RunnableChecker.i = i;
    }

    @Override
    public void run(){
        System.out.println("Thread " + this.threadNo + " was started!");
        String string;
        BracketChecker bracketChecker = new BracketChecker();
        int stringNumb = 0;
        do {
            string = getString();
            if(string.length() > 1){
                stringNumb = ++i;
            }
            synchronized (string) {
                if (string.length() > 1) {
                    System.out.println("Thread " + this.threadNo + ". String number "
                            + stringNumb + ": " + bracketChecker.checkString(string));
                }
                //else{
                //    System.out.println("Thread " + this.threadNo + ". String number "
                //            + stringNumb + ": There is no any string!");
                //}
            }

        }while(string.length() > 1);
        System.out.println("Thread " + this.threadNo + " was stopped!");
    }

    private synchronized String getString(){
        int c =0;
        String string = "";
        do {
            try {
                c = fr.read();
                string = string + (char)c;

            } catch (IOException e) {
                e.printStackTrace();
            }
        }while (c != 10 && c != -1);

        return string;
    }

}
