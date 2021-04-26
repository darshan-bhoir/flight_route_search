import axios from 'axios';

const SEARCH_FLIGHTS_URL = 'http://localhost:8082/api/search/flights';

class ApiService {

    searchFlights(request) {
        return axios.post(""+SEARCH_FLIGHTS_URL, request);
    }
}

export default new ApiService();