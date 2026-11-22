package springboot;

import org.springframework.boot.webmvc.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

// Controller error for handling errors defined in the project specifications (400 and 502)
@RestController
public class NominatimErrorController implements ErrorController {

    @RequestMapping("/error")
    public String error(HttpServletRequest request) {
        int statusCode = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        String errorMessage = (String) request.getAttribute(RequestDispatcher.ERROR_MESSAGE);
        String response = statusCode + " Unknown Error: " + errorMessage;

        switch (statusCode) {
            case 400:
                response = "400 Bad Request: " + errorMessage;
                break;
            case 502:
                response = "502 Bad Gateway: " + errorMessage
                        + ". (Are you sure you provided the coordinates of a country, and not an ocean?)";
                break;
            default:
                break;
        }
        return response;
    }
}
