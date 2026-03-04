public class DataInput {

    public static double[] demand = new double[24];
    public static double[] solar = new double[24];
    public static double[] price = new double[24];

    // Automatically generate sample data
    static {
        for (int i = 0; i < 24; i++) {

            // Example demand pattern
            if (i >= 18 && i <= 22) {
                demand[i] = 15;  // Peak hours
            } else {
                demand[i] = 8;
            }

            // Example solar production (daytime only)
            if (i >= 6 && i <= 17) {
                solar[i] = 10;
            } else {
                solar[i] = 0;
            }

            // Example price pattern
            if (i >= 18 && i <= 22) {
                price[i] = 8;  // Expensive peak
            } else {
                price[i] = 5;
            }
        }
    }

    public static double[] getDemand() {
        return demand;
    }

    public static double[] getSolar() {
        return solar;
    }

    public static double[] getPrice() {
        return price;
    }
}