import requests
import math


class FlightOperations:

    def __init__(self, airport1_icao, airport2_icao):

        self.airport_icao_codes = []
        self.latitude_data = []
        self.longitude_data = []
        self.airport1_icao = airport1_icao
        self.airport2_icao = airport2_icao

        url = "https://raw.githubusercontent.com/CaptMD-11/KaggleCSVFiles/main/AirportICAOLatLong.csv"
        response = requests.get(url)
        for line in response.text.splitlines():
            self.airport_icao_codes.append(line[0:4])
            self.latitude_data.append(float(line[5:line.rfind(",")]))
            self.longitude_data.append(float(line[line.rfind(",") + 1:]))

        airport1_index = 0
        airport2_index = 0

        for i in range(len(self.airport_icao_codes)):
            if self.airport_icao_codes[i] == airport1_icao:
                airport1_index = i
            if self.airport_icao_codes[i] == airport2_icao:
                airport2_index = i

        self.airport1_lat = math.radians(self.latitude_data[airport1_index])
        self.airport1_long = math.radians(self.longitude_data[airport1_index])

        self.airport2_lat = math.radians(self.latitude_data[airport2_index])
        self.airport2_long = math.radians(self.longitude_data[airport2_index])

    def get_distance_NM(self):
        return 3440.1 * math.acos((math.sin(self.airport1_lat) * math.sin(self.airport2_lat)) + (math.cos(self.airport1_lat) * math.cos(self.airport2_lat) * math.cos(self.airport1_long - self.airport2_long)))

    def get_distance_KM(self):
        return self.get_distance_NM() * 1.852

    def get_distance_Mi(self):
        return self.get_distance_NM() * 1.15078


obj = FlightOperations("KLAX", "OMDB")
print(obj.get_distance_NM())
