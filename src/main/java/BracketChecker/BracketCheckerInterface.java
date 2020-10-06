package BracketChecker;

import Annotations.Check;
import Exc.BracketException;

public interface BracketCheckerInterface {
    @Check
    String checkString(String s) throws BracketException;

}
