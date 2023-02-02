from FlightRadar24.api import FlightRadar24API
from AirportDistances import AirportDistances

fr_api = FlightRadar24API()

str_addon = "test"

for i in range(5):
    file = open(str_addon + ".txt", "x")
    file = open(str_addon + ".txt", "a")
    file.write("hello")
    file.close()
    str_addon += "1"

# airline_icao = "UPS"
# thy_flights = fr_api.get_flights(airline=airline_icao)
# print(thy_flights)


# class FlightOperations:

#     def write_to_file():
#         file = open("fr_api.txt", "x")
#         file = open("fr_api.txt", "a")
#         file.write("as;ldfjl;asdkfj")
