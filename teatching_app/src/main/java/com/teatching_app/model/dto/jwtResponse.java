package com.teatching_app.model.dto;

import lombok.Getter;

@Getter
public class jwtResponse {
    private  String jwt;

    public jwtResponse(String jwt) {
        this.jwt = jwt;
    }
}
