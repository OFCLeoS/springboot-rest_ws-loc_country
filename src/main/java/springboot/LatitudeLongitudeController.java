package springboot;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@RestController
public class LatitudeLongitudeController {

    private final String latErrorMsg = "lat must be between -90 and 90.";
    private final String lonErrorMsg = "lon must be between -180 and 180.";
    private final NominatimService nominatimService;

    public LatitudeLongitudeController(NominatimService nominatimService) {
        this.nominatimService = nominatimService;
    }

    @GetMapping
    public ResponseEntity<String> getContryFromLatLong(
            @RequestParam(required = true) @Min(value = -90, message = latErrorMsg) @Max(value = 90, message = latErrorMsg) float lat,
            @RequestParam(required = true) @Min(value = -180, message = lonErrorMsg) @Max(value = 180, message = lonErrorMsg) float lon) {
        Address addressObject = nominatimService.getCountryAndCodeFromLatLong(lat, lon);
        // If the address object is null, it is an external API issue, and
        // we will return 502 Bad Gateway, as per the specificiations
        if (addressObject == null) {
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Address could not be obtained");
        }
        String response = "200 OK: Country: " + addressObject.country + ".\nCountry Code: "
                + addressObject.country_code;
        return ResponseEntity.ok(response);
    }
}
