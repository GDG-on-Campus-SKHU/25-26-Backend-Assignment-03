package gdg.lgh.Service;

import gdg.lgh.DTO.StationInfoResponseDTO;
import gdg.lgh.DTO.StationSaveRequestDTO;
import gdg.lgh.Domain.KorailLine;
import gdg.lgh.Domain.KorailStation;
import gdg.lgh.Repository.LineRepository;
import gdg.lgh.Repository.StationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StationService {

    private final StationRepository stationRepository;
    private final LineRepository lineRepository;

    @Transactional
    public StationInfoResponseDTO saveStation(StationSaveRequestDTO stationSaveRequestDTO) {
        KorailLine line = lineRepository.findById(stationSaveRequestDTO.getLineId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 노선입니다."));

        KorailStation station = KorailStation.builder()
                .line(line)
                .stationName(stationSaveRequestDTO.getStationName())
                .build();

        stationRepository.save(station);

        return StationInfoResponseDTO.from(station);
    }

    @Transactional(readOnly = true)
    public StationInfoResponseDTO getStation(Long stationId) {
        KorailStation station = stationRepository.findById(stationId)
                .orElseThrow(() -> new IllegalArgumentException("요청하신 정거장 정보를 찾을 수 없습니다."));

        return StationInfoResponseDTO.from(station);
    }

    @Transactional
    public StationInfoResponseDTO updateStation(Long stationId, StationSaveRequestDTO stationSaveRequestDTO) {
        KorailStation station = stationRepository.findById(stationId)
                .orElseThrow(() -> new IllegalArgumentException("요청하신 정거장 정보를 찾을 수 없습니다."));

        KorailLine line = lineRepository.findById(stationSaveRequestDTO.getLineId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 노선입니다."));

        station.update(stationSaveRequestDTO.getStationName(), line);

        return StationInfoResponseDTO.from(station);
    }

    @Transactional
    public void deleteStation(Long stationId) {
        stationRepository.deleteById(stationId);
    }

    @Transactional(readOnly = true)
    public List<StationInfoResponseDTO> getAllStation() {
        return stationRepository.findAll()
                .stream()
                .map(StationInfoResponseDTO::from)
                .toList();
    }
}
