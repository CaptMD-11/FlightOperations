from FlightRadar24.api import FlightRadar24API
from FlightOperations import FlightOperations

fr_api = FlightRadar24API()

# airlines = fr_api.get_airlines()
# flights = fr_api.get_flights()

airline_icao = "FDX"
thy_flights = fr_api.get_flights(airline=airline_icao)
print(thy_flights)
