import Annotations.Check;
import Reflections.Request;

public class CheckReflClass {
    public static String stString = "(It's a static string)";
    private static int stOne = 1;
    private String dString;
    private int dTwo;

    public CheckReflClass(String dString, int dTwo) {
        this.dString = dString;
        this.dTwo = dTwo;
    }
    @Check
    public static String getStString() {
        Request.sendRequest(CheckReflClass.class,"getDString", stString);
        return stString;
    }
    @Check
    public static int getStOne() {
        return stOne;
    }
    @Check
    public String getDString() {
        Request.sendRequest(this.getClass(),"getDString", dString);
        return dString;
    }

    public int getDTwo() {
        return dTwo;
    }

    @Check
    public String addBrackets(String s){
        String answer = "(" + s + ")";
        Request.sendRequest(this.getClass(),"addBrackets", answer);
        return answer;
    }
}
