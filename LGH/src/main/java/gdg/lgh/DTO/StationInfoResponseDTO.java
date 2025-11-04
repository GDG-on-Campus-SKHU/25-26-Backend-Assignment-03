package gdg.lgh.DTO;

import gdg.lgh.Domain.KorailStation;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class StationInfoResponseDTO {
    private Long id;
    private String stationName;
    private Long lineId;
    private String lineName;

    public static StationInfoResponseDTO from(KorailStation station) {
        return StationInfoResponseDTO.builder()
                .id(station.getId())
                .stationName(station.getStationName())
                .lineId(station.getLine().getId())
                .lineName(station.getLine().getLineName())
                .build();
    }
}
