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
        int lockUntilCompare = lockUntil.compareTo(o.lockUntil);
        if (lockUntilCompare != 0) {
            return lockUntilCompare;
        } else {
            return uuid.compareTo(o.uuid);
        }
    }    

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof LockInfo)) {
            return false;
        }

        LockInfo other = (LockInfo) obj;
        return uuid.equals(other.uuid) && lockUntil.equals(other.lockUntil);
    }
    
}
