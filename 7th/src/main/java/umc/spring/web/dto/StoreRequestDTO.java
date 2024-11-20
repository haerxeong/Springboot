package umc.spring.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import umc.spring.validation.annotation.ExistRegion;

public class StoreRequestDTO {
    @Getter
    public static class RegisterDto {
        @NotBlank
        String name;
        @NotBlank
        String address;
        @NotNull
        Float score;
//        @ExistRegion
//        Long regionId;
    }
}
