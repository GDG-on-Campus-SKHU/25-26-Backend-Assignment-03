package gdg.lgh.DTO;

import gdg.lgh.Domain.KorailLine;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LineInfoResponseDTO {
    private Long id;
    private String lineName;
    private int openYear;

    public static LineInfoResponseDTO from(KorailLine line) {
        return LineInfoResponseDTO.builder()
                .id(line.getId())
                .lineName(line.getLineName())
                .openYear(line.getOpenYear())
                .build();
    }
}
