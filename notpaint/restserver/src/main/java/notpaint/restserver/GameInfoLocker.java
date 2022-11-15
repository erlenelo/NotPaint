package notpaint.restserver;

import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

import notpaint.persistence.GameInfo;

public class GameInfoLocker {

    // Priority queue of lock infos, sorted by unlock time, to easily unlock the expired locks.
    PriorityQueue <LockInfo> lockInfosQueue; 

    // Set of all currently locked game infos for fast lookup.
    Set <UUID> lockedGameInfos;

    public GameInfoLocker() {
        // Sort by lockUntil, so we can easily find the first lock that has expired
        lockInfosQueue = new PriorityQueue<LockInfo>(new Comparator<LockInfo>() {
            @Override
            public int compare(LockInfo o1, LockInfo o2) {
                return o1.lockUntil.compareTo(o2.lockUntil);
            }
        });

        lockedGameInfos = new HashSet<UUID>();
    }

    public boolean tryLockGameInfo(GameInfo info) { 
        if(isLocked(info)) {
            return false;
        } else {
            lockGameInfo(info);
            return true;
        }
    }

    public boolean isLocked(GameInfo info) {
        removeExpiredLocks();

        return lockedGameInfos.contains(info.getUuid());
    }

    public void unlockGameInfo(GameInfo info) {
        LockInfo lockToRemove = null;
        for (LockInfo lockInfo : lockInfosQueue) {
            if (lockInfo.uuid.equals(info.getUuid())) {
                lockToRemove = lockInfo;
                break;
            }
        }
        if (lockToRemove != null) {
            lockInfosQueue.remove(lockToRemove);
        }
        lockedGameInfos.remove(info.getUuid());
    }

    private void lockGameInfo(GameInfo info) {
        // Lock until the duration of a round, plus 2 seconds to account for delays on client side
        Date lockUntil = new Date(
            System.currentTimeMillis() + info.getSecondsPerRound() * 1000 + 2000);
        var lockInfo = new LockInfo(info.getUuid(), lockUntil);
        lockInfosQueue.add(lockInfo);
        lockedGameInfos.add(info.getUuid());
    }

    private void removeExpiredLocks() {
        // Remove all expired locks
        while (lockInfosQueue.peek() != null && lockInfosQueue.peek().lockUntil.before(new Date())) {
            var lockInfo = lockInfosQueue.poll();
            lockedGameInfos.remove(lockInfo.uuid);
        }
    }
}
