package vendingmachine.util;

import vendingmachine.domain.Coin;

import java.util.TreeMap;

@FunctionalInterface
public interface RandomNumbersGenerator {

    TreeMap<Coin, Integer> generate(int amount);
}
