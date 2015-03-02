package utils;

import MutationEngine.Engine3;

/**
 * Created by Roberto on 3/1/2015.
 */
public class mutatorDriver {

    public static void main(String args[])
    {
        Engine3 test = new Engine3();
        for(int x = 0; x < 10000; x++)
        {
            test.runGeneration();
            System.out.println(" generation: " + x);
        }
    }
}
