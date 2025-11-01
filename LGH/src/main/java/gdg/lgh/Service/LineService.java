package gdg.lgh.Service;

import gdg.lgh.DTO.LineInfoResponseDTO;
import gdg.lgh.DTO.LineSaveRequestDTO;
import gdg.lgh.Domain.KorailLine;
import gdg.lgh.Repository.LineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LineService {

    private final LineRepository lineRepository;

    @Transactional
    public LineInfoResponseDTO saveLine(LineSaveRequestDTO lineSaveRequestDTO) {
        KorailLine line = KorailLine.builder()
                .lineName(lineSaveRequestDTO.getLineName())
                .openYear(lineSaveRequestDTO.getOpenYear())
                .build();

        lineRepository.save(line);

        return LineInfoResponseDTO.from(line);
    }

    @Transactional
    public void deleteLine(Long lineId) {
        lineRepository.deleteById(lineId);
    }

    @Transactional(readOnly = true)
    public List<LineInfoResponseDTO> getAllLine() {
        return lineRepository.findAll()
                .stream()
                .map(LineInfoResponseDTO::from)
                .toList();
    }
}
