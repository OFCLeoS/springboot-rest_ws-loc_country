package springboot;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@RestController
public class LatitudeLongitudeController {
    private final NominatimService nominatimService;

    public LatitudeLongitudeController(NominatimService nominatimService) {
        this.nominatimService = nominatimService;
    }

    @GetMapping
    public ResponseEntity<String> getContryFromLatLong(
            @RequestParam(required = true) @Min(value = -90) @Max(value = 90) float lat,
            @RequestParam(required = true) @Min(value = -180) @Max(value = 180) float lon) {
        Address addressObject = nominatimService.getCountryAndCodeFromLatLong(lat, lon);
        // If the address object is null, it is an external API issue, and
        // we will return 502 Bad Gateway, as per the specificiations
        if (addressObject == null) {
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Address could not be obtained");
        }
        String response = "Status Code:\n- 200 OK\nResponse:\n---------\n- Country\n   - " + addressObject.country + "\n- Country Code\n   - "
                + addressObject.country_code+"\n---------";
        return ResponseEntity.ok().contentType(MediaType.TEXT_MARKDOWN).body(response);
    }
}
