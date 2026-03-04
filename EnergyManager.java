public class EnergyManager {

    private Battery battery;
    private double totalCost = 0;
    private double[] hourlyCostWithBattery = new double[24];

    public EnergyManager(Battery battery) {
        this.battery = battery;
    }

    public double runSimulation() {

        totalCost = 0;

        for (int hour = 0; hour < 24; hour++) {

            double demand = DataInput.demand[hour];
            double solar = DataInput.solar[hour];
            double price = DataInput.price[hour];

            double netDemand = demand - solar;

            // If solar produces extra → charge battery
            if (netDemand < 0) {
                battery.charge(Math.abs(netDemand));
                netDemand = 0;
            }

            // 🔥 Smart Peak Pricing Logic
            if (price > 8) {
                // If electricity price is high → discharge max possible
                netDemand -= battery.discharge(battery.getMaxDischarge());
            } else {
                // Otherwise discharge only needed amount
                netDemand -= battery.discharge(netDemand);
            }

            if (netDemand < 0) {
                netDemand = 0;
            }

            double cost = netDemand * price;
            hourlyCostWithBattery[hour] = cost;
            totalCost += cost;
        }

        return totalCost;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public double[] getHourlyCostWithBattery() {
        return hourlyCostWithBattery;
    }
}