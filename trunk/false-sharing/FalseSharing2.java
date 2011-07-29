public final class FalseSharing2
    implements Runnable
{
    private static final int ITEMS = 1 << 24;
    private static final int MASK = ITEMS - 1;

    public final static long ITERATIONS = 500L * 1000L * 1000L;
    public static final int LINE_SIZE = 8;

    private final int arrayIndex;
    private final int arrayOffset;

    private static long[][] wcArrays = new long[4][];
    private static long[][] fsArrays = new long[4][];
    static
    {
        for (int i = 0; i < fsArrays.length; i++)
        {
            wcArrays[i] = new long[ITEMS];
            fsArrays[i] = new long[LINE_SIZE];
        }
    }

    public FalseSharing2(final int arrayIndex, final int arrayOffset)
    {
        this.arrayIndex = arrayIndex;
        this.arrayOffset = arrayOffset;
    }

    public static void main(final String[] args) throws Exception
    {
        final long start = System.nanoTime();
        runTest();
        System.out.println("duration = " + (System.nanoTime() - start));
    }

    private static void runTest() throws InterruptedException
    {
        Thread[] threads = new Thread[4];

        for (int i = 0; i < threads.length; i++)
        {
            threads[i] = new Thread(new FalseSharing2(i, i));
        }

        for (Thread t : threads)
        {
            t.start();
        }

        for (Thread t : threads)
        {
            t.join();
        }
    }

    public void run()
    {
        long i = ITERATIONS + 1;
        while (0 != --i)
        {
            wcArrays[0][(int)i & MASK] = i;
            wcArrays[1][(int)i & MASK] = i;
            wcArrays[2][(int)i & MASK] = i;
            wcArrays[3][(int)i & MASK] = i;

            //fsArrays[arrayIndex][arrayOffset] = i; // without false sharing
            //fsArrays[0][arrayOffset] = i;            // with false sharing
        }
    }
}
