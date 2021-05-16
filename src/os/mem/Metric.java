package os.mem;

public class Metric {

    private int mean;
    private int std;
    private int epochs;
    private double avgUtilization;
    private double avgHolesExamined;

    public Metric(int epochs, int mean, int std) {
        this.mean = mean;
        this.std = std;
        this.epochs = epochs;
    }

    public double getAvgUtilization() {
        return avgUtilization;
    }

    public void setAvgUtilization(double avgUtilization) {
        this.avgUtilization = avgUtilization;
    }

    public double getAvgHolesExamined() {
        return avgHolesExamined;
    }

    public void setAvgHolesExamined(double avgHolesExamined) {
        this.avgHolesExamined = avgHolesExamined;
    }

    public int getMean() {
        return mean;
    }

    public void setMean(int mean) {
        this.mean = mean;
    }

    public int getStd() {
        return std;
    }

    public void setStd(int std) {
        this.std = std;
    }

    public int getEpochs() {
        return epochs;
    }

    public void setEpochs(int epochs) {
        this.epochs = epochs;
    }

    @Override
    public String toString() {
        return "Metric{" +
                "mean=" + mean +
                ", std=" + std +
                ", epochs=" + epochs +
                ", avgUtilization=" + avgUtilization +
                ", avgHolesExamined=" + avgHolesExamined +
                '}';
    }
}
