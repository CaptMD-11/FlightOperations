public class Runner {
    public static void main(String[] args) {

        DistanceCalculator obj = new DistanceCalculator("OMDB", "EGLL");

        System.out.println(obj.getDistanceMi());
    }
}