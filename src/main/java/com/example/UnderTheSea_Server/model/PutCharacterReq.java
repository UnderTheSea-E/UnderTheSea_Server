package com.example.UnderTheSea_Server.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class PutCharacterReq {
    public Long character_id;
    public String character_name;
}
