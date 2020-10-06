import BracketChecker.*;
import Exc.BracketException;
import Reflections.AnswerChecker;
import Reflections.Checker;
//import Reflections.RunnableChecker;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;
import java.util.concurrent.*;


public class Main {
    public static void main(String[] args) throws IOException, BracketException, IllegalAccessException, InvocationTargetException, ExecutionException, InterruptedException {
        Thread checkThread = new Thread(new AnswerChecker());
        checkThread.setDaemon(true);
        checkThread.start();
        CheckReflClass crc = new CheckReflClass(">It's a dynamic field<", 2);
        BracketCheckerInterface bracketChecker = new BracketChecker();
        File file = new File(".\\src\\main\\resources\\BracketsTest.txt");
        FileReader fr = new FileReader(file);
        int i = 1;
        int aCase = 0;
        crc.addBrackets("This is my string");
        crc.getDString();
        Scanner in = new Scanner(System.in);
        while(aCase != 7){
            System.out.println("Enter an option:" +
                    "\n\t1.Enter string to add brackets." +
                    "\n\t2.Get dynamic string from the test class." +
                    "\n\t3.Get static string from the test class." +
                    "\n\t4.Get dynamic integer from the test class." +
                    "\n\t5.Get static integer from the test class." +
                    "\n\t6.See result of multi thread strings checking" +
                    "\n\t7.Exit from the application." +
                    "\n\t\tPress enter after option execution to choose new option!");
            System.out.print("Enter number of option: ");
            aCase = in.nextInt();
            switch (aCase){
                case(1):
                    System.out.print("Enter string: ");
                    System.out.println(crc.addBrackets(in.next()));
                    System.in.read();
                    break;
                case(2):
                    System.out.println(crc.getDString());
                    System.in.read();
                    break;
                case(3):
                    System.out.println(crc.getStString());
                    System.in.read();
                    break;
                case(4):
                    System.out.println(crc.getDTwo());
                    System.in.read();
                    break;
                case(5):
                    System.out.println(crc.getStOne());
                    System.in.read();
                    break;
                case(6):
                    while(i <= 1001){
                            new Thread(new RunnableChecker(fr, i)).start();
                            i++;
                        }
                    System.in.read();
                    break;
                case(7):
                    break;
            }
        }
        fr.close();
    }

}
