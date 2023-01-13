import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

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

    // CSV FILE IS IN ICAO, LAT, LONG ORDER
    public DistanceCalculator(String inputAirport1ICAO, String inputAirport2ICAO) {
        airportICAOCodes = new ArrayList<String>();
        latitudeData = new ArrayList<Double>();
        longitudeData = new ArrayList<Double>();
        airport1ICAO = inputAirport1ICAO;
        airport2ICAO = inputAirport2ICAO;
        try {
            URL url = new URL("https://raw.githubusercontent.com/CaptMD-11/KaggleCSVFiles/main/AirportICAOLatLong.csv");
            URLConnection urlConnection = url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    urlConnection.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                airportICAOCodes.add(line.substring(0, 4));
                latitudeData.add(Double.parseDouble(line.substring(5, line.lastIndexOf(","))));
                longitudeData.add(Double.parseDouble(line.substring(line.lastIndexOf(",") + 1)));
            }
            br.close();
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

        airport1Lat = Math.toRadians(latitudeData.get(airport1Index));
        airport1Long = Math.toRadians(longitudeData.get(airport1Index));

        airport2Lat = Math.toRadians(latitudeData.get(airport2Index));
        airport2Long = Math.toRadians(longitudeData.get(airport2Index));

    }

    // IN UNITS OF NAUTICAL MILES
    public double getDistance() {
        return 3440.1 * Math.acos((Math.sin(airport1Lat) * Math.sin(airport2Lat))
                + (Math.cos(airport1Lat) * Math.cos(airport2Lat) * Math.cos(airport1Long - airport2Long)));
    }

}
