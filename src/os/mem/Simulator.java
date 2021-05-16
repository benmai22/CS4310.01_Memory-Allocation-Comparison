package os.mem;

import java.util.ArrayList;
import java.util.Random;

public class Simulator<T extends Memory> {

    final private T memory;
    final private Random rsRandom;
    final private Random biRandom;

    public Simulator(T memory, long seed) {
        this.memory = memory;
        this.rsRandom = new Random();
        this.biRandom = new Random();
        this.rsRandom.setSeed(seed);
        this.biRandom.setSeed(seed);
    }

    public ArrayList<Metric> simulateRange(
            int epochs, int meanMin, int meanMax, int meanStep,
            int stdMin, int stdMax, int stdStep,boolean fresh)
    {
        ArrayList<Metric> metrics = new ArrayList<>();
        for (int i = meanMin; i < meanMax; i += meanStep)
        {
            for (int j = stdMin; j < stdMax; j += stdStep)
                metrics.add(simulate(epochs, i, j, fresh));
        }
        return metrics;
    }

    public Metric simulate(int epochs, int mean, int std, boolean fresh)
    {
        boolean requestFailed = false;
        ArrayList<Double> utilizations = new ArrayList<>();
        ArrayList<Integer> holesExaminedList = new ArrayList<>();
        double curUtilization;
        int ranBlock = -1;
        Metric metric = new Metric(epochs, mean, std);
        if (fresh)
            memory.clear(memory.getStore().size());
        // repeat x times
        for (int e = 0; e < epochs; e++)
        {
            // repeat until request fails, by generating random request size
//            memory.setHolesExamined(0);
            while (!requestFailed)
                requestFailed = !memory.request(generateRequestSize(mean, std));
            // save utilization and search times
            if (utilizations.size() > 0)
                curUtilization = memory.getUtilization() - utilizations.get(utilizations.size() - 1);
            else
                curUtilization = memory.getUtilization();
            if (curUtilization > 0)
                utilizations.add(curUtilization);
            holesExaminedList.add(memory.getHolesExamined());
            // rlease random occupied block
            ranBlock = pickRandomBlock();
            memory.release(ranBlock);
//            holesExaminedList.add(memory.getHolesExamined());
        }
        // compute average utilization and holes examined
        metric.setAvgUtilization(
                utilizations.stream().reduce(0.0, Double::sum)
                        / utilizations.size());
        metric.setAvgHolesExamined(
                holesExaminedList.stream().reduce(0, Integer::sum)
                        / (double)holesExaminedList.size());
        return metric;
    }

    private int generateRequestSize(int mean, int std)
    {
        int rSize;
        do {
            rSize = (int)(mean + rsRandom.nextGaussian() *  std);
        }
        while (rSize < 1 || rSize >= memory.getStore().size());
        return rSize;
    }

    private int pickRandomBlock()
    {
        int ranIndex;
        ArrayList<Integer> blocksIndices = new ArrayList<>();
        for (int i = 0; i < memory.getStore().size(); i++)
        {
            if (memory.getStore().get(i) > 0)
                blocksIndices.add(i);
        }
        if (blocksIndices.size() == 0)
            return -1;
        ranIndex = biRandom.nextInt(blocksIndices.size());
        return blocksIndices.get(ranIndex);
    }

    public T getMemory() {
        return memory;
    }
}
