package os.mem;

public class MemoryBestFit extends Memory
{
    public MemoryBestFit(int n) {
        super(n);
    }

    @Override
    public boolean satisfy() {
//        System.out.printf("Satisfy %d ...\n", processes.size());
        int min_i;
        int min_s;
        int bsize;
        boolean satisfied = false;
        for (Process p : processes){
            if (p.isSatisfied())
                continue;
            min_i = -1;
            min_s = Integer.MAX_VALUE;
            for (int i = 0; i < store.size();)
            {
                bsize = store.get(i);
                if (bsize < 0)
                {
                    this.holesExamined++;
                    bsize = Math.abs(bsize);
                    if (bsize < min_s && bsize >= p.getLength())
                    {
                        min_i = i;
                        min_s = bsize;
                    }
                }
                i += bsize;
            }
            if (min_i > -1)
            {
                satisfied = satisfyAt(min_i, p.getLength());
                p.setSatisfied(satisfied);
            }
        }
        return satisfied;
    }
}
