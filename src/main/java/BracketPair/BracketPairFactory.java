package BracketPair;

import BracketPair.BracketPair;

public interface BracketPairFactory<BP extends BracketPair>{
    BP create(char opener, char closer);
}
