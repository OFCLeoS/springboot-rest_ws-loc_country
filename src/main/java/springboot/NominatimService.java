package springboot;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class NominatimService {
    // URI Explanation: We use the reverse endpoint of the nominatim API. It will
    // provide a bunch of details, and inside of them, the address
    // which contains the country AND country code. The format is in JSON, and zoom
    // = 3 is used because it provides us only the details we
    // need inside the address (which are the country and country code)
    private final String uriBase = "https://nominatim.openstreetmap.org/reverse?format=jsonv2&layer=address&zoom=3";
    private final RestClient restClient;

    public NominatimService(RestClient restClient) {
        this.restClient = restClient;
    }

    // Nominatim requires a User-Agent Header, or else the request is denied.
    public Address getCountryAndCodeFromLatLong(float latitude, float longitude) {
        NominatimObject nominatimObject = restClient.get()
                .uri(uriBase + "&lat=" + latitude + "&lon=" + longitude)
                .header("User-Agent", "SpringbootCountryCodeGrabber")
                .retrieve()
                .body(NominatimObject.class);
        return nominatimObject.address;
    }
}
