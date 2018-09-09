package com.cvbank.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class ResponseError extends Response {
    private Object data = null;

    public ResponseError(Integer code, String message) {

        super(Response.ERROR, code, message);
    }
}



