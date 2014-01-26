package cehardin.yeahman;

import cehardin.yeahman.impl.Engine;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() throws InterruptedException
    {
        final Runtime runtime = Runtime.getRuntime();
        final int numProcessors = runtime.availableProcessors();
        final ExecutorService executorService = Executors.newFixedThreadPool(numProcessors);   
        final Engine engine = new Engine(executorService);
        final Address hello1, hello2;
        
        engine.addActorFactory("hello", new HelloActor.Factory());
        
        hello1 = engine.createActor("hello");
        hello2 = engine.createActor("hello");
        
        engine.sendMessage(hello2, new HelloActor.Hello(hello1));
        
        Thread.sleep(10000);
    }
}
