package com.cvbank.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class ResponseSuccessObject extends Response {

    private Object data;

    public ResponseSuccessObject(Object data) {
        super(SUCCESS,null,null);
        this.data = data;
    }
}



