public class Runner {
    public static void main(String[] args) {
        // System.out.println(VCSVLocal.getNumCols("AirportData.csv"));

        DistanceCalculator obj = new DistanceCalculator("EGNX", "YSSY");

        System.out.println(obj.getDistance());
    }
}