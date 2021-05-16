package os.mem;

public class MemoryWorstFit extends Memory{

    public MemoryWorstFit(int n) {
        super(n);
    }

    @Override
    protected boolean satisfy() {
//        System.out.printf("Satisfy %d ...\n", processes.size());
        int max_i;
        int max_s;
        int bsize;
        boolean satisfied = false;
        for (Process p : processes){
            if (p.isSatisfied())
                continue;
            max_i = -1;
            max_s = Integer.MIN_VALUE;
            for (int i = 0; i < store.size();)
            {
                bsize = store.get(i);
                if (bsize < 0)
                {
                    this.holesExamined++;
                    bsize = Math.abs(bsize);
                    if (bsize > max_s && bsize >= p.getLength())
                    {
                        max_i = i;
                        max_s = bsize;
                    }
                }
                i += bsize;
            }
            if (max_i > -1)
            {
                satisfied = satisfyAt(max_s, p.getLength());
                p.setSatisfied(satisfied);
            }
        }
        return satisfied;
    }
}
