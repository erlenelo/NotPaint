package notpaint.restserver;

import java.util.Date;
import java.util.UUID;

class LockInfo implements Comparable<LockInfo> {
    UUID uuid;
    Date lockUntil;

    LockInfo(UUID uuid, Date lockUntil) {
        this.uuid = uuid;
        this.lockUntil = lockUntil;
    }

    @Override
    public int compareTo(LockInfo o) {
        return lockUntil.compareTo(o.lockUntil);
    }

}
