package gdg.lgh.Controller;

import gdg.lgh.DTO.LineInfoResponseDTO;
import gdg.lgh.DTO.LineSaveRequestDTO;
import gdg.lgh.Service.LineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/metroLines")
public class LineController {

    private final LineService lineService;

    @PostMapping
    public ResponseEntity<LineInfoResponseDTO> saveLine(@RequestBody LineSaveRequestDTO lineSaveRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(lineService.saveLine(lineSaveRequestDTO));
    }

    @DeleteMapping("/{lineId}")
    public ResponseEntity<LineInfoResponseDTO> deleteLineById(@PathVariable Long lineId) {
        lineService.deleteLine(lineId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity<List<LineInfoResponseDTO>> getAllLine() {
        return ResponseEntity.status(HttpStatus.OK).body(lineService.getAllLine());
    }
}
