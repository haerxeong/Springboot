package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MissionConverter;
import umc.spring.domain.Mission;
import umc.spring.service.MissionService.MissionCommandService;
import umc.spring.validation.annotation.NotChallengingMission;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionRestController {
    private final MissionCommandService missionCommandService;

    @PostMapping("/")
    public ApiResponse<MissionResponseDTO.MissionRegisterResultDto> registerMission(@RequestBody @Valid MissionRequestDTO.MissionRegisterDto request) {
        Mission mission = missionCommandService.registerMission(request);

        return ApiResponse.onSuccess(MissionConverter.toMissionRegisterResultDto(mission));
    }

    @PostMapping("{missionId}/challenge")
    public ApiResponse<MissionResponseDTO.ChallengeResultDto> challengeMission(
            @PathVariable @NotChallengingMission Long missionId,
            @RequestBody @Valid MissionRequestDTO.ChallengeMissionDto request) {
        Mission mission = missionCommandService.challengeMission(request, missionId);

        return ApiResponse.onSuccess(MissionConverter.toChallengeResultDto(mission));
    }
}
