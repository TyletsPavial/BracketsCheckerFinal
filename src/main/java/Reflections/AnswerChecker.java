package Reflections;
import Annotations.Check;
import BracketChecker.BracketChecker;
import BracketChecker.BracketCheckerInterface;
import Exc.BracketException;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.*;

public class AnswerChecker implements Runnable {

    @Override
    public void run() {
        BracketCheckerInterface bracketChecker = new BracketChecker();
        System.out.println("Checker was started!!!");
        Class aClass;
        Method method = null;
        List<Annotation> annotations;
        while (true){
            if(!Request.getNewAnswer().toLowerCase().equals(Request.getOldAnswer())){
                aClass = Request.getAClass();
                try {
                    method = aClass.getDeclaredMethod("addBrackets",String.class);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
                annotations = Arrays.asList(method.getAnnotations());
                for (Annotation annotation : annotations) {
                    if ((Check) annotation != null) {
                        Request.setOldAnswer(Request.getNewAnswer());
                        try {
                            System.out.println(bracketChecker.checkString(Request.getNewAnswer()));
                        } catch (BracketException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
            }
        }
    }
}
