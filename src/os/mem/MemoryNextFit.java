package os.mem;

public class MemoryNextFit extends Memory {

    public MemoryNextFit(int n) {
        super(n);
    }

    @Override
    protected boolean satisfy() {
//        System.out.printf("Satisfy %d ...\n", processes.size());
        int bsize;
        boolean satisfied = false;
        for (int i = 0; i < store.size(); )
        {
//            boolean satisfiedAny = false;
            bsize = store.get(i);
//            System.out.printf("i: %d : %d\n", i, bsize);
            if (bsize < 0) {
                this.holesExamined++;
                for (Process p : processes)
                {
                    if (p.isSatisfied())
                        continue;
                    if (i >= store.size())
                        break;
                    bsize = store.get(i);
                    if (bsize < 0 && (-bsize) >= p.getLength())
                    {
//                        consider subsequent holes examined
//                        this.holesExamined++;
                        satisfied = satisfyAt(i, p.getLength());
                        p.setSatisfied(satisfied);
                        i += p.getLength();
//                        satisfiedAny = true;
                    }
                }
//                if (!satisfiedAny)
                if (i >= store.size())
                    break;
                i += Math.abs(store.get(i));
            }
            else
                i += bsize;
        }
        return satisfied;
    }
}
