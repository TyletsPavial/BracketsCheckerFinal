package Reflections;

import Annotations.Check;
import BracketChecker.BracketChecker;
import BracketChecker.BracketCheckerInterface;
import Exc.BracketException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Checker {

    static BracketCheckerInterface bracketChecker = new BracketChecker();

    public static void checkAnswer(Object obj,String met, String arg) throws
            InvocationTargetException, IllegalAccessException, BracketException {

        Annotation[] annotations;
        Class aClass = obj.getClass();
        Method[] methods = aClass.getDeclaredMethods();

        for(Method method: methods){
            if(method.getName().equals(met)){
                annotations = method.getAnnotations();
                for(Annotation annotation: annotations) {
                    if((Check) annotation != null) {
                        method.setAccessible(true);
                        if (arg != null) {
                            System.out.println(bracketChecker.checkString((String) method.invoke(obj, arg)));
                        } else {
                            System.out.println(bracketChecker.checkString((String) method.invoke(obj)));
                        }
                    }
                }
            }
        }
    }

    public static void checkFields(Object obj) throws IllegalAccessException, BracketException {
        Class aClass = obj.getClass();
        Field[] fields = aClass.getDeclaredFields();
        for(Field field: fields){
            if(field.getType().toString().equals("class java.lang.String")){
                field.setAccessible(true);
                System.out.println(field.getName() + ": " + field.get(obj) + " " +
                        bracketChecker.checkString((String)field.get(obj)));
            }
        }
    }
    public static void callMethodByAnnotation(Object obj, String anno) throws InvocationTargetException, IllegalAccessException {
        Class aClass = obj.getClass();
        Method[] methods = aClass.getDeclaredMethods();
        for(Method method: methods){
            Annotation[] annotations = method.getAnnotations();
            for(Annotation annotation: annotations){
                if(annotation.toString().equals("@Annotations." + anno + "()")){
                    System.out.println("Method: " + method.getName());
                    System. out.println("Result: " + method.invoke(obj));
                }
            }
        }
    }
}
