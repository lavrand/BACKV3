package com.cvbank.auth.payload;

import com.cvbank.auth.model.Profile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {

    private Optional<Profile> profile;
    private String token;

}
