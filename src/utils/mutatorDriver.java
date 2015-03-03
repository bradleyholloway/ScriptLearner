package utils;

import MutationEngine.Engine4;

/**
 * Created by Roberto on 3/1/2015.
 */
public class mutatorDriver {

    public static void main(String args[])
    {
        Engine4 test = new Engine4(50);
        for(int x = 0; x < 100000; x++)
        {
            test.runMatchedGeneration(7,3,10);
            System.out.println(" generation: " + x);
        }
    }
}
