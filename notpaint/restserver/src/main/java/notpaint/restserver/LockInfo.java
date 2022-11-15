package notpaint.restserver;

import java.util.Date;
import java.util.UUID;

class LockInfo {
    UUID uuid;
    Date lockUntil;

    LockInfo(UUID uuid, Date lockUntil) {
        this.uuid = uuid;
        this.lockUntil = lockUntil;
    }    
}
