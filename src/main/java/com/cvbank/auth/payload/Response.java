package com.cvbank.auth.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {

    protected static final String SUCCESS = "success";
    protected static final String ERROR = "error";

    protected String timestamp;
    protected String status;
    protected Integer code;
    protected String message;

    public Response(String status, Integer code, String message) {
        this.timestamp = timeStamp();
        this.status    = status;
        this.code      = code;
        this.message   = message;
    }


    protected String timeStamp() {
        Date d1 = new Date();
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/YYYY HH:mm:ss");
        String formattedDate = df.format(d1);
        return formattedDate;
    }
}



