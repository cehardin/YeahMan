package cehardin.yeahman.impl;

import cehardin.yeahman.Address;
import java.util.UUID;

/**
 *
 * @author Chad
 */
public final class UUIDAddress implements Address {
    UUID uuid;

    public UUIDAddress() {
        uuid = UUID.randomUUID();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (this.uuid != null ? this.uuid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UUIDAddress other = (UUIDAddress) obj;
        if (this.uuid != other.uuid && (this.uuid == null || !this.uuid.equals(other.uuid))) {
            return false;
        }
        return true;
    }

    
    
    
    
}
