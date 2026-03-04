public class Battery {

    private double capacity;
    private double currentCharge;
    private double maxCharge;
    private double maxDischarge;

    public Battery(double capacity, double maxCharge, double maxDischarge) {
        this.capacity = capacity;
        this.maxCharge = maxCharge;
        this.maxDischarge = maxDischarge;
        this.currentCharge = 0;
    }

    public void charge(double amount) {
        double chargeAmount = Math.min(amount, maxCharge);
        currentCharge += chargeAmount;

        if (currentCharge > capacity) {
            currentCharge = capacity;
        }
    }

    public double discharge(double amount) {
        double dischargeAmount = Math.min(amount, maxDischarge);
        dischargeAmount = Math.min(dischargeAmount, currentCharge);

        currentCharge -= dischargeAmount;
        return dischargeAmount;
    }

    public double getMaxDischarge() {
        return maxDischarge;
    }

    public double getCurrentCharge() {
        return currentCharge;
    }
}