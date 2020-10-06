package BracketChecker;

import Annotations.Check;
import BracketPair.*;
import Exc.BracketException;

import java.util.*;
import java.util.stream.Collectors;

public class BracketChecker implements BracketCheckerInterface {
    private static List<BracketPair> brackets = new ArrayList<>();
    static {
        BracketPairFactory<BracketPair> bpFactory = BracketPair::new;
        brackets.add(bpFactory.create('(',')'));
        brackets.add(bpFactory.create('[',']'));
        brackets.add(bpFactory.create('{','}'));
        brackets.add(bpFactory.create('<','>'));
    }

    public void setBrackets(BracketPair... brackets) {
        this.brackets = Arrays.asList(brackets);
    }

    public List<BracketPair> getBrackets() {
        return brackets;
    }

    private Set<String> createSubstrings(String s) throws BracketException {
        Set<String> substrings = new HashSet<>();
        int sum;
        int aStart;
        boolean charIsOpenerFlag;
        boolean charIsCloserFlag;
        boolean stringContainsBracket = false;
        for(BracketPair bP: brackets){
            aStart = 0;
            sum = 0;
            for(int c = 0; c < s.length(); c++){
                if(s.charAt(c) == bP.getOpener()){
                    sum++;
                    charIsOpenerFlag = true;
                    charIsCloserFlag = false;
                    stringContainsBracket = true;
                }
                else if(s.charAt(c) == bP.getCloser()){
                    sum--;
                    charIsOpenerFlag = false;
                    charIsCloserFlag = true;
                    stringContainsBracket = true;
                }
                else {
                    charIsOpenerFlag = false;
                    charIsCloserFlag = false;
                }
                if(sum == 1 && charIsOpenerFlag){
                    aStart = c;
                }
                if(charIsCloserFlag && sum == 0){
                        substrings.add(s.substring(aStart ,c + 1));
                    aStart = c;
                }
            }
        }
        if(substrings.size()== 0){
            if(stringContainsBracket) {
                substrings.add(s);
            }
            else {
                throw new BracketException("There is no any bracket");
            }
        }
        return substrings;
    }
    private boolean checkSubstring(String str) throws BracketException {
        int sum = 0;
        for(BracketPair bp: brackets){
            for(int c = 0; c < str.length(); c++){
                if(bp.getOpener() == str.charAt(c)){
                    sum++;
                }
                else if (bp.getCloser() == str.charAt(c)){
                    sum--;
                }
                if(sum < 0){
                    throw new BracketException(bp.getCloser() + " was found before " + bp.getOpener());
                }
            }
            if (sum != 0){
                throw new BracketException(bp.getOpener()
                        + " was found more than "
                        + bp.getCloser()
                        + " or some brackets close incorrect!");
            }
        }
        return true;
    }
    @Check
    public String checkString(String s) {
        List<Object> answers = new ArrayList<>();
        try {
            answers = createSubstrings(s).stream().map( x -> {
                try {
                    return checkSubstring(x);
                } catch (BracketException e) {
                    return e.getMessage();
                }
            }).collect(Collectors.toList());
        } catch (BracketException e) {
            return e.getMessage();
        }
        for(Object answer: answers){
            if(!answer.equals("true")){
                return answer.toString();
            }
        }
        return "true";
    }
}
