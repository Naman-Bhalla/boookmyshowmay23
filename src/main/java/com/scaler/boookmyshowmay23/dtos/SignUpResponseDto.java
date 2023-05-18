package com.scaler.boookmyshowmay23.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpResponseDto {
    private ResponseStatus responseStatus;
    private Long userId;
}
