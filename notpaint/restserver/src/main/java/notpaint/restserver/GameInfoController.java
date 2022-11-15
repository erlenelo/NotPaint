package notpaint.restserver;

import notpaint.persistence.GameInfo;
import notpaint.persistence.LocalGameInfoPersistence;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * Handles requests for GameInfos.
 */
@RestController
public class GameInfoController {
    
    LocalGameInfoPersistence gameInfoPersistence = new LocalGameInfoPersistence();
    GameInfoLocker gameInfoLock = new GameInfoLocker();

    @GetMapping("/allGameInfos")
    public ResponseEntity<List<GameInfo>> getAllGameInfos() {
        try {
            var gameInfos = gameInfoPersistence.getAllGameInfos();
            return ResponseEntity.ok(gameInfos);
        } catch (IOException ioe) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/saveGameInfo")
    public ResponseEntity<Void> putSaveGameInfo(@RequestBody GameInfo gameInfo) {
        try {
            gameInfoPersistence.saveGameInfo(gameInfo);
            return ResponseEntity.ok().build();
        } catch (IOException ioe) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/lock")
    public ResponseEntity<Boolean> postRequestLock(@RequestParam(value = "uuid") UUID uuid) {
        try {
            GameInfo gameInfo = gameInfoPersistence.getGameInfoFromUUID(uuid);
            if (gameInfoLock.tryLockGameInfo(gameInfo)) {
                return ResponseEntity.ok(true);
            } else {
                return ResponseEntity.ok(false);
            }
        } catch (IOException ioe) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/lock")
    public ResponseEntity<Boolean> getLockStatus(@RequestParam(value = "uuid") UUID uuid) {
        try {
            GameInfo gameInfo = gameInfoPersistence.getGameInfoFromUUID(uuid);
            if (gameInfoLock.isLocked(gameInfo)) {
                return ResponseEntity.ok(true);
            } else {
                return ResponseEntity.ok(false);
            }
        } catch (IOException ioe) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/lock")
    public ResponseEntity deleteReleaseLock(@RequestParam(value = "uuid") UUID uuid) {
        try {
            GameInfo gameInfo = gameInfoPersistence.getGameInfoFromUUID(uuid);
            gameInfoLock.unlockGameInfo(gameInfo);
            return ResponseEntity.ok().build();
        } catch (IOException ioe) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    

    


}
