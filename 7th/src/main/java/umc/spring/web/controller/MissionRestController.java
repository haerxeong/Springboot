package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MissionConverter;
import umc.spring.domain.Mission;
import umc.spring.service.MissionService.MissionCommandService;
import umc.spring.service.MissionService.MissionQueryService;
import umc.spring.validation.annotation.CheckPage;
import umc.spring.validation.annotation.NotChallengingMission;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionRestController {
    private final MissionCommandService missionCommandService;
    private final MissionQueryService missionQueryService;

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

    @GetMapping("/my-missions")
    @Operation(summary = "내가 진행중인 미션 목록 조회 API", description = "내가 진행중인 미션들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함")
    })
    @Parameters({
            @Parameter(name = "page", description = "페이지 번호, query string 입니다!")
    })
    public ApiResponse<MissionResponseDTO.MissionPreViewListDTO> getMyMissions(@RequestParam(name = "memberId") Long memberId, @CheckPage @RequestParam(name = "page") Integer page) {
        Page<Mission> missionList = missionQueryService.getMyMissions(memberId, page);
        return ApiResponse.onSuccess(MissionConverter.missionPreViewListDTO(missionList));
    }
}
