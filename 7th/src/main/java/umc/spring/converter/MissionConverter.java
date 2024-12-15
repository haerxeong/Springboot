package umc.spring.converter;

import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class MissionConverter {
    public static MissionResponseDTO.MissionRegisterResultDto toMissionRegisterResultDto(Mission mission) {
        return MissionResponseDTO.MissionRegisterResultDto.builder()
                .missionId(mission.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Mission toMission(MissionRequestDTO.MissionRegisterDto request, Store store) {
        return Mission.builder()
                .store(store)
                .missionSpec(request.getMissionSpec())
                .reward(request.getReward())
                .deadline(request.getDeadline())
                .build();
    }

    // 도전중인 미션
    public static MissionResponseDTO.ChallengeResultDto toChallengeResultDto(Mission mission) {
        return MissionResponseDTO.ChallengeResultDto.builder()
                .missionId(mission.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static MissionResponseDTO.MissionPreViewDTO missionPreViewDTO(Mission mission) {
        return MissionResponseDTO.MissionPreViewDTO.builder()
                .missionName(mission.getMissionSpec())
                .reward(mission.getReward())
                .deadline(mission.getDeadline())
                .createdAt(mission.getCreatedAt().toLocalDate())
                .build();
    }

    public static MissionResponseDTO.MissionPreViewListDTO missionPreViewListDTO(Page<Mission> missionList) {
        return MissionResponseDTO.MissionPreViewListDTO.builder()
                .isLast(missionList.isLast())
                .isFirst(missionList.isFirst())
                .totalPage(missionList.getTotalPages())
                .totalElements(missionList.getTotalElements())
                .listSize(missionList.getNumberOfElements())
                .missionList(missionList.stream().map(MissionConverter::missionPreViewDTO).collect(Collectors.toList()))
                .build();
    }
}
