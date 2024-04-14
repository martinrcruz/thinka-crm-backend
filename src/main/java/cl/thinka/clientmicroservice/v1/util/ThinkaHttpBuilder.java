package cl.thinka.clientmicroservice.v1.util;

import cl.thinka.clientmicroservice.v1.enums.ResponseCode;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
public class ThinkaHttpBuilder {

    private ThinkaHttpBuilder() {}

    public static ResponseEntity<ThinkaResponse> buildHttpResponse(ThinkaResponse response) {
        if(response.getCode() == ResponseCode.SUCCESS.getCode()) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else if(response.getCode() == ResponseCode.BAD_REQUEST.getCode()) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else if(response.getCode() == ResponseCode.UNAUTHORIZED.getCode()) {
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        } else if(response.getCode() == ResponseCode.FORBIDDEN.getCode()) {
            return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
        } else if(response.getCode() == ResponseCode.NOT_FOUND.getCode()) {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } else if(response.getCode() == ResponseCode.INTERNAL_SERVER_ERROR.getCode()) {
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
