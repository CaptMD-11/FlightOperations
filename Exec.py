from FlightRadar24.api import FlightRadar24API
from AirportDistances import AirportDistances

fr_api = FlightRadar24API()


airline_icao = "UPS"
thy_flights = fr_api.get_flights(airline=airline_icao)
print(thy_flights)
