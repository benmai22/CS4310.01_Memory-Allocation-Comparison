package os.mem;

public class Process {

    private int length;
    private boolean satisfied;

    public Process(int length) {
        this.length = length;
        this.satisfied = false;
    }

    public int getLength() {
        return length;
    }

    public boolean getSatisfied() {
        return satisfied;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setSatisfied(boolean satisfied) {
        this.satisfied = satisfied;
    }

    public boolean isSatisfied() {
        return satisfied;
    }
}
