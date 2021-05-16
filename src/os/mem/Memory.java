package os.mem;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Abstract class for memory structure
 */
abstract public class Memory {

    protected ArrayList<Integer> store;
    protected ArrayList<Process> processes;
    protected int holesExamined;
//    protected ArrayList<Integer> holesPos;

    /**
     * Constructor of Memory class by n cell
     * @param n memory size
     */
    public Memory(int n) {
//        this.store = new ArrayList<>(Collections.nCopies(n, 0));
        clear(n);
    }

    public ArrayList<Integer> getStore() {
        return this.store;
    }

    public void setStore(ArrayList<Integer> store) {
        this.store = store;
    }

    public int getHolesExamined() {
        return holesExamined;
    }

    public void setHolesExamined(int holesExamined) {
        this.holesExamined = holesExamined;
    }

    /**
     * Add a process to request list
     * @param length length of process to be allocated
     * @return true if satisfied, false otherwise
     */
    public boolean request(int length) {
        this.processes.add(new Process(length));
        return satisfy();
    }

    /**
     * Release allocated block at a given valid index
     * @param i index of block to be released
     */
    public void release(int i) {
        // release block;
        if (i < 0 || i >= store.size() || store.get(i) < 1)
            return;
        store.set(i, -store.get(i));
        // merge holes
        mergeHoles();
        satisfy();
    }

    /**
     * Merge neighbor holes
     */
    public void mergeHoles()
    {
        int s;
        for (int i = 0; i < this.getStore().size();)
        {
            s = store.get(i);
            if (s > 0)
                i += s;
            else if (i - s >= this.getStore().size())
                break;
            else if (store.get(i - s) > 0)
                i -= s;
            else {
                store.set(i, s + store.get(i - s));
                store.set(i - s, 0);
            }
            if (i >= this.getStore().size())
                break;
            s = store.get(i);
            for (int j = 1; j < Math.abs(s); j++)
                store.set(i + j, 0);
        }
    }

    /**
     * compute space utilization
     * @return memory utilization
     */
    public double getUtilization() {
        // collect postive numbers then sum them
        double allocated = (double) store.stream()
                .filter(s -> s > 0)
                .reduce(0, Integer::sum);
        // caclulate ratio
        double ratio = allocated / this.getStore().size();
        return ratio;
    }

    /**
     * Satisfy a single process at a given valid position
     * @param i Starting index of the block to be allocated.
     * @param length The length of the block to be allocated
     * @return true if satisfied, false otherwise.
     */
    public boolean satisfyAt(int i, int length)
    {
        if (i < 0 || i > store.size()
                || store.get(i) > -1
                || (-store.get(i)) < length)
            return false;
        int s = Math.abs(store.get(i));
        store.set(i, length);
        if (s > length)
            store.set(i + length, -(s - length));
        return true;
    }

    /**
     * Clear memory and initialize internal state.
     * @param n size of memory.
     */
    public void clear(int n)
    {
        this.store= new ArrayList<>(Collections.nCopies(n, 0));
        this.store.set(0, -this.store.size());
        this.processes = new ArrayList<>();
        this.holesExamined = 0;
    }

    /**
     * Satisfy 0 or more process from the list of requests
     * Abstract method, to be implemented for each strategy:
     * first-fit, next-fit, best-fit, worst-fit
     * @return true if at least one process has been satisfied, false otherwise.
     */
    abstract protected boolean satisfy();
}
