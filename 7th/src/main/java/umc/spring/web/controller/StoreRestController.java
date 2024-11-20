package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Store;
import umc.spring.service.StoreService.StoreCommandService;
import umc.spring.validation.annotation.ExistRegion;
import umc.spring.web.dto.StoreRequestDTO;
import umc.spring.web.dto.StoreResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreRestController {
    private final StoreCommandService storeCommandService;

    @PostMapping("/regions/{regionId}/stores")
    public ApiResponse<StoreResponseDTO.RegistResultDTO> addStoreToRegion(
            @PathVariable @ExistRegion Long regionId,
            @RequestBody @Valid StoreRequestDTO.RegisterDto request) {
        Store store = storeCommandService.addStoreToRegion(regionId, request);
        return ApiResponse.onSuccess(StoreConverter.toRegistResultDTO(store));
    }
}