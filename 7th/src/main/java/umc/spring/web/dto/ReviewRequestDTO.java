package umc.spring.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import umc.spring.validation.annotation.ExistMember;
import umc.spring.validation.annotation.ExistStore;

public class ReviewRequestDTO {
    @Getter
    public static class ReviewRegisterDto {
        @ExistMember
        Long memberId;
        @ExistStore
        Long storeId;
        @NotBlank
        String body;
        @NotNull
        float score;
    }
}
