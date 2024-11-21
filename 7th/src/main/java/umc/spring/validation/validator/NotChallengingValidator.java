package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.repository.MemberMissionRepository;
import umc.spring.validation.annotation.NotChallengingMission;
import umc.spring.web.dto.MissionRequestDTO;

@Component
@RequiredArgsConstructor
public class NotChallengingValidator implements ConstraintValidator<NotChallengingMission, Long> {

    private final MemberMissionRepository memberMissionRepository;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {   // value는 missionId
        long memberId = 1;  // 더미데이터니까 걍 일케할게요... 실제로는 토큰쓰지않을까...

        boolean isValid = !memberMissionRepository.existsByMemberIdAndMissionIdAndStatus(
                memberId, value, MissionStatus.CHALLENGING);

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.MISSION_CHALLENGING.toString()).addConstraintViolation();
        }

        return isValid;
        }

}