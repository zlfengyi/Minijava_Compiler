package util;

public class Environment {
    public static final int maxStack = 50;
    public static final int maxStackSize = 1000000; // 1 meg total stack
    public static final int maxHeap = 1000;         // 1K lim for each HALLOC
    public static final int maxHeapSize = 1000000;  // 10 meg for heap
    public static int maxRuntimeInstruction = 1000000;
    public static int maxOutputChar = 50000;

    public static boolean instructionOutput=false;
    public static boolean instructionCount=false;
    public static boolean verbose=false;
    public static boolean allowDumps=false;
}
