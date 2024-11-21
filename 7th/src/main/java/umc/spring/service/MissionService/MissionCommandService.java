package umc.spring.service.MissionService;

import umc.spring.domain.Mission;
import umc.spring.web.dto.MissionRequestDTO;

public interface MissionCommandService {
    Mission registerMission(MissionRequestDTO.MissionRegisterDto request);
    Mission challengeMission(MissionRequestDTO.ChallengeMissionDto request, Long missionId);
}
