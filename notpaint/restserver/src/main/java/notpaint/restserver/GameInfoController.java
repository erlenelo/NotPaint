package notpaint.restserver;

import notpaint.core.GameInfo;
import notpaint.core.persistence.LocalGameInfoPersistence;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * Handles requests for GameInfos.
 */
@RestController
public class GameInfoController {
    
    LocalGameInfoPersistence gameInfoPersistence = new LocalGameInfoPersistence();

    @GetMapping("/allGameInfos")
    public ResponseEntity<List<GameInfo>> getAllGameInfos() {
        try {
            var gameInfos = gameInfoPersistence.getAllGameInfos();
            return ResponseEntity.ok(gameInfos);
        } catch (IOException ioe) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/newGameInfo")
    public ResponseEntity<Void> postNewGameInfo(@RequestBody GameInfo gameInfo) {
        try {
            gameInfoPersistence.saveGameInfo(gameInfo);
            return ResponseEntity.ok().build();
        } catch (IOException ioe) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    


}
