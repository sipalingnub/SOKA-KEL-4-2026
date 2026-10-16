package soka.simulasi;

import java.util.ArrayList;
import java.util.List;
import org.cloudbus.cloudsim.Host;
import org.cloudbus.cloudsim.Pe;
import org.cloudbus.cloudsim.VmSchedulerTimeShared;
import org.cloudbus.cloudsim.provisioners.BwProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.PeProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.RamProvisionerSimple;

/**
 * Factory Host siap pakai sesuai draft desain.
 * Tipe A: 3 unit (ID 0-2), Tipe B: 3 unit (ID 3-5).
 */
public final class HostFactory {

    private HostFactory() {}

    /** @return List&lt;Host&gt; 6 unit siap pakai. */
    public static List<Host> createHosts() {
        return createHosts(3, 3);
    }

    /** Versi fleksibel: jumlah host tiap tipe bisa diubah tanpa ubah kode lain. */
    public static List<Host> createHosts(int numTypeA, int numTypeB) {
        List<Host> hosts = new ArrayList<>();
        int id = 0;
        for (int i = 0; i < numTypeA; i++) {
            hosts.add(createHost(id++, 4000, 8, 16384, 10000, 1000000));
        }
        for (int i = 0; i < numTypeB; i++) {
            hosts.add(createHost(id++, 2000, 4, 8192, 5000, 1000000));
        }
        return hosts;
    }

    public static Host createHost(int id, int mipsPerPe, int peCount,
                                  int ramMb, long bwMbps, long storageMb) {
        List<Pe> peList = new ArrayList<>();
        for (int i = 0; i < peCount; i++) {
            peList.add(new Pe(i, new PeProvisionerSimple(mipsPerPe)));
        }
        return new Host(
                id,
                new RamProvisionerSimple(ramMb),
                new BwProvisionerSimple(bwMbps),
                storageMb,
                peList,
                new VmSchedulerTimeShared(peList));
    }
}