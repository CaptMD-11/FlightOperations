import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class DistanceCalculator {

    private ArrayList<String> airportICAOCodes;
    private ArrayList<Double> latitudeData;
    private ArrayList<Double> longitudeData;

    private String airport1ICAO;
    private double airport1Lat;
    private double airport1Long;

    private String airport2ICAO;
    private double airport2Lat;
    private double airport2Long;

    private File file;

    public DistanceCalculator(String inputAirport1ICAO, String inputAirport2ICAO) {
        airportICAOCodes = new ArrayList<String>();
        latitudeData = new ArrayList<Double>();
        longitudeData = new ArrayList<Double>();
        airport1ICAO = inputAirport1ICAO;
        airport2ICAO = inputAirport2ICAO;
        file = new File("AirportData.csv");
        try {
            Scanner scanner = new Scanner(file);
            String line = scanner.nextLine();
            line = scanner.nextLine();
            while (scanner.hasNextLine()) {
                airportICAOCodes.add(line.substring(0, 4));
                latitudeData.add(Double.parseDouble(line.substring(5, line.lastIndexOf(","))));
                longitudeData.add(Double.parseDouble(line.substring(line.lastIndexOf(",") + 1)));
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        int airport1Index = 0;
        int airport2Index = 0;

        for (int i = 0; i < airportICAOCodes.size(); i++) {
            if (airportICAOCodes.get(i).equals(airport1ICAO))
                airport1Index = i;
            if (airportICAOCodes.get(i).equals(airport2ICAO))
                airport2Index = i;
        }

        airport1Lat = latitudeData.get(airport1Index);
        airport1Long = longitudeData.get(airport1Index);

        airport2Lat = latitudeData.get(airport2Index);
        airport2Long = longitudeData.get(airport2Index);

    }

    // IN UNITS OF KILOMETERS
    public double getDistance() {
        double earthRadius = 6378.1;
        double term1 = Math.pow(Math.sin((airport2Lat - airport1Lat) / 2), 2);
        double term2 = Math.cos(airport1Lat) * Math.cos(airport2Lat)
                * Math.pow(Math.sin((airport2Long - airport1Long) / 2), 2);
        return 2 * earthRadius * Math.sqrt(term1 + term2);
    }

}
