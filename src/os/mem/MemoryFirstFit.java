package os.mem;

public class MemoryFirstFit extends Memory {

    public MemoryFirstFit(int n) {
        super(n);
    }

    @Override
    protected boolean satisfy() {
//        System.out.printf("Satisfy %d ...\n", processes.size());
        int bsize;
        boolean satisfied = false;
        for (Process p : processes){
            if (p.isSatisfied())
                continue;
            for (int i = 0; i < store.size();)
            {
                bsize = store.get(i);
                if (bsize < 0)
                {
                    this.holesExamined++;
                    bsize = Math.abs(bsize);
                    if (bsize >= p.getLength())
                    {
                        satisfied = satisfyAt(i, p.getLength());
                        p.setSatisfied(satisfied);
                        break;
                    }
                }
                i += Math.abs(store.get(i));
            }
        }
        return satisfied;
    }
}
