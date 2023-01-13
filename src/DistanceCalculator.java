import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class DistanceCalculator {

    private String[] airportICAOCodes;
    private double[] latitudeData;
    private double[] longitudeData;

    private String airport1ICAO;
    private double airport1Lat;
    private double airport1Long;

    private String airport2ICAO;
    private double airport2Lat;
    private double airport2Long;

    private File file;

    public DistanceCalculator(String inputAirport1ICAO, String inputAirport2ICAO) {
        airportICAOCodes = new String[7698];
        latitudeData = new double[7698];
        longitudeData = new double[7698];
        airport1ICAO = inputAirport1ICAO;
        airport2ICAO = inputAirport2ICAO;
        file = new File("AirportData.csv");
        try {
            airportICAOCodes = getColumnOfStrings(5);
            latitudeData = getColumnOfNumbers(6);
            longitudeData = getColumnOfNumbers(7);
            System.out.println(latitudeData.length);
            System.out.println(longitudeData.length);
            int airport1Index = 0;
            int airport2Index = 0;

            for (int i = 0; i < airportICAOCodes.length; i++) {
                if (airportICAOCodes[i].equals(airport1ICAO))
                    airport1Index = i;
                if (airportICAOCodes[i].equals(airport2ICAO))
                    airport2Index = i;
            }

            airport1Lat = latitudeData[airport1Index];
            airport1Long = longitudeData[airport1Index];

            airport2Lat = latitudeData[airport2Index];
            airport2Long = longitudeData[airport2Index];

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private String[] getColumnOfStrings(int col) {
        ArrayList<String> res = new ArrayList<String>();
        try {
            Scanner scanner = new Scanner(file);
            String line = scanner.nextLine();
            line = scanner.nextLine();
            while (scanner.hasNextLine()) {
                if (col == 0)
                    res.add(line.substring(0, line.indexOf(",")));
                else if (col == getNumCols() - 1)
                    res.add(line.substring(line.lastIndexOf(",") + 1));
                else
                    res.add(
                            line.substring(getNthIndexOf(line, col, ",") + 1, getNthIndexOf(line, col +
                                    1, ",")));
                line = scanner.nextLine();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        String[] output = new String[res.size()];
        for (int i = 0; i < res.size(); i++) {
            output[i] = res.get(i);
        }
        return output;
    }

    public double[] getColumnOfNumbers(int col) {
        ArrayList<Double> res = new ArrayList<Double>();
        try {
            Scanner scanner = new Scanner(file);
            String line = scanner.nextLine();
            line = scanner.nextLine();
            while (scanner.hasNextLine()) {
                if (col == 0)
                    res.add(Double.parseDouble(line.substring(0, line.indexOf(","))));
                else if (col == getNumCols() - 1)
                    res.add(Double.parseDouble(line.substring(line.lastIndexOf(",") + 1)));
                else
                    res.add(Double.parseDouble(
                            line.substring(getNthIndexOf(line, col, ",") + 1, getNthIndexOf(line, col +
                                    1, ","))));
                line = scanner.nextLine();
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        double[] output = new double[res.size()];
        for (int i = 0; i < res.size(); i++) {
            output[i] = res.get(i);
        }
        return output;
    }

    private int getNumCols() {
        try {
            int count = 0;
            Scanner scanner = new Scanner(file);
            String line = scanner.nextLine();
            for (int i = 0; i < line.length(); i++) {
                if (line.substring(i, i + 1).equals(","))
                    count++;
            }
            return count + 1;
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    private int getNthIndexOf(String line, int n, String str) {
        int count = 0;
        ArrayList<Integer[]> tracker = new ArrayList<Integer[]>();
        for (int i = 0; i < line.length(); i++) {
            if (line.substring(i, i + 1).equals(str)) {
                count++;
                Integer[] arr = { count, i };
                tracker.add(arr);
            }
        }
        for (int i = 0; i < tracker.size(); i++) {
            if (n == tracker.get(i)[0])
                return tracker.get(i)[1];
        }
        return 0;
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
