package utils;

import MutationEngine.Engine2;

/**
 * Created by Roberto on 3/1/2015.
 */
public class mutatorDriver {

    public static void main(String args[])
    {
        Engine2 test = new Engine2();

        for(int x = 0; x< 100; x++)
        {
            test.testMutations();
            System.out.println(x);
        }
    }
}
