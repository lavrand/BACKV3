package com.cvbank.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class ResponseSuccessEmpty extends Response {

    public Object data = null;

    public ResponseSuccessEmpty() {

        super(Response.SUCCESS,null,null);
    }
}



