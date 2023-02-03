from FlightRadar24.api import FlightRadar24API
from AirportDistances import AirportDistances

fr_api = FlightRadar24API()
airline_icao = "UPS"
airline_flights = fr_api.get_flights(airline=airline_icao)

print(airline_flights)

list = []
file = open("test.txt", "r")

# file = open("file.txt", "x")

# for i in range(len(airline_flights)):
#     file = open("file.txt", "a")
#     file.write(airline_flights[i] + "")

# class FlightOperations:

#     def write_to_file():
#         file = open("fr_api.txt", "x")
#         file = open("fr_api.txt", "a")
#         file.write("as;ldfjl;asdkfj")
