package com.cvbank.auth.payload;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class ResponseSuccessObject extends Response {

    private Object data;

    public ResponseSuccessObject(Object data) {
        super(Response.SUCCESS,null,null);
        this.data = data;
    }
}



