public class CostReport {

    private static double[] hourlyCostWithoutBattery = new double[24];

    public static double calculateWithoutBattery() {

        double totalCost = 0;

        double[] demand = DataInput.getDemand();
        double[] solar = DataInput.getSolar();
        double[] price = DataInput.getPrice();

        for (int i = 0; i < 24; i++) {

            double netEnergy = demand[i] - solar[i];

            if (netEnergy > 0) {
                totalCost += netEnergy * price[i];
            }

            hourlyCostWithoutBattery[i] = totalCost;
        }

        return totalCost;
    }

    public static double[] getHourlyCostWithoutBattery() {
        return hourlyCostWithoutBattery;
    }
}