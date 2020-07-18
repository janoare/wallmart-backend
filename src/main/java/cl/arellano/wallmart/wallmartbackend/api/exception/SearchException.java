package cl.arellano.wallmart.wallmartbackend.api.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class SearchException extends RuntimeException {
    String errorCode;
}
