package springboot;

import org.springframework.boot.webmvc.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

// Controller error for handling errors defined in the project specifications (400 and 502)
@RestController
public class NominatimErrorController implements ErrorController {

    @RequestMapping("/error")
    public ResponseEntity<String> handleError(HttpServletRequest request) {
        // We get the status code of the error and the error message
        HttpStatus status = HttpStatus.valueOf((int)request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE));
        String errorMessage = (String) request.getAttribute(RequestDispatcher.ERROR_MESSAGE);
        // By default, the error is unknown
        String response = status + " Unknown Error: " + errorMessage;

        switch (status) {
            case BAD_REQUEST:
                response = "400 Bad Request: " + errorMessage;
                break;
            case BAD_GATEWAY:
                response = "502 Bad Gateway: " + errorMessage
                        + ". (Are you sure you provided the coordinates of a country, and not an ocean?)";
                break;
            default:
                break;
        }
        return ResponseEntity.status(status).contentType(MediaType.TEXT_MARKDOWN).body(response);
    }
}
