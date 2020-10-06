package Reflections;

public class Request {

    private static Class aClass = null;
    private static String methodName = null;
    private static String newAnswer = new String("");
    private static String oldAnswer = new String("");

    public static Class getAClass() {
        return aClass;
    }

    public static void setAClass(Class aClass) {
        Request.aClass = aClass;
    }

    public static String getMethodName() {
        return methodName;
    }

    public static void setMethodName(String methodName) {
        Request.methodName = methodName;
    }

    public static String getNewAnswer() {
        return newAnswer;
    }

    public static void setNewAnswer(String newAnswer) {
        Request.newAnswer = newAnswer;
    }

    public static String getOldAnswer() {
        return oldAnswer;
    }

    public static void setOldAnswer(String oldAnswer) {
        Request.oldAnswer = oldAnswer.toLowerCase();
    }
    public static void sendRequest(Class aClass, String methodName, String newAnswer){
        Request.aClass = aClass;
        Request.methodName = methodName;
        Request.newAnswer = newAnswer;
    }
}
