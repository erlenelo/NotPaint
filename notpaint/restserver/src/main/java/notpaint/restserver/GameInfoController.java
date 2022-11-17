package notpaint.restserver;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
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


/**
 * Handles requests for GameInfos.
 */
@RestController
public class GameInfoController {
    
    LocalGameInfoPersistence gameInfoPersistence = new LocalGameInfoPersistence();
    GameInfoLocker gameInfoLocker = new GameInfoLocker();

    /**
     * HTTP GET method for getting a list of all GameInfos.
     *
     * @return a list of all GameInfos
     */
    @GetMapping("/allGameInfos")
    public ResponseEntity<List<GameInfo>> getAllGameInfos() {
        try {
            var gameInfos = gameInfoPersistence.getAllGameInfos();
            return ResponseEntity.ok(gameInfos);
        } catch (IOException ioe) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * HTTP PUT method for creating a new GameInfo, or updating an existing one.
     *
     * @param gameInfo the GameInfo to create or update
     */
    @PutMapping("/saveGameInfo")
    public ResponseEntity<Void> putSaveGameInfo(@RequestBody GameInfo gameInfo) {
        try {
            gameInfoPersistence.saveGameInfo(gameInfo);
            return ResponseEntity.ok().build();
        } catch (IOException ioe) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * HTTP POST method for locking a GameInfo.
     *
     * @param uuid UUID of the GameInfo to lock
     * @return true if the GameInfo was locked, false if it was already locked.
     */
    @PostMapping("/lock")
    public ResponseEntity<Boolean> postRequestLock(@RequestParam(value = "uuid") UUID uuid) {
        try {
            GameInfo gameInfo = gameInfoPersistence.getGameInfoFromUuid(uuid);
            if (gameInfoLocker.tryLockGameInfo(gameInfo)) {
                return ResponseEntity.ok(true);
            } else {
                return ResponseEntity.ok(false);
            }
        } catch (IOException ioe) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * HTTP GET method for checking if a GameInfo is locked.
     *
     * @param uuid UUID of the GameInfo to check
     * @return true if the GameInfo is locked, false if it is not locked.
     */
    @GetMapping("/lock")
    public ResponseEntity<Boolean> getLockStatus(@RequestParam(value = "uuid") UUID uuid) {
        try {
            GameInfo gameInfo = gameInfoPersistence.getGameInfoFromUuid(uuid);
            if (gameInfoLocker.isLocked(gameInfo)) {
                return ResponseEntity.ok(true);
            } else {
                return ResponseEntity.ok(false);
            }
        } catch (IOException ioe) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * HTTP DELETE method for unlocking a GameInfo.
     *
     * @param uuid UUID of the GameInfo to unlock
     */
    @DeleteMapping("/lock")
    public ResponseEntity<Void> deleteReleaseLock(@RequestParam(value = "uuid") UUID uuid) {
        try {
            GameInfo gameInfo = gameInfoPersistence.getGameInfoFromUuid(uuid);
            gameInfoLocker.unlockGameInfo(gameInfo);
            return ResponseEntity.ok().build();
        } catch (IOException ioe) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
