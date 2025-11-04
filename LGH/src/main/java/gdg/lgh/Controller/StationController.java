package gdg.lgh.Controller;

import gdg.lgh.DTO.StationInfoResponseDTO;
import gdg.lgh.DTO.StationSaveRequestDTO;
import gdg.lgh.Service.StationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("metroStations")
public class StationController {

    private final StationService stationService;

    @PostMapping
    public ResponseEntity<StationInfoResponseDTO> saveStation(@RequestBody StationSaveRequestDTO stationSaveRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(stationService.saveStation(stationSaveRequestDTO));
    }

    @GetMapping("/{stationId}")
    public ResponseEntity<StationInfoResponseDTO> getStation(@PathVariable Long stationId) {
        return ResponseEntity.status(HttpStatus.OK).body(stationService.getStation(stationId));
    }

    @PatchMapping("/{stationId}")
<<<<<<< HEAD
    public ResponseEntity<StationInfoResponseDTO> updateStation(@PathVariable Long stationId,
=======
    public ResponseEntity<?> updateStation(@PathVariable Long stationId,
>>>>>>> 45740897e8db15ed5a4d411ff00b31a3c1a43da0
                                         @RequestBody StationSaveRequestDTO stationSaveRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(stationService.updateStation(stationId, stationSaveRequestDTO));
    }

    @DeleteMapping("/{stationId}")
    public ResponseEntity<StationInfoResponseDTO> deleteStation(@PathVariable Long stationId) {
        stationService.deleteStation(stationId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity<List<StationInfoResponseDTO>> getAllStation() {
        return ResponseEntity.status(HttpStatus.OK).body(stationService.getAllStation());
    }
}
