package soka.simulasi;

import java.util.ArrayList;
import java.util.List;
import org.cloudbus.cloudsim.CloudletSchedulerSpaceShared;
import org.cloudbus.cloudsim.Vm;

/**
 * Factory VM siap pakai sesuai draft desain.
 * Tipe 1: 9 unit (ID 0-8), Tipe 2: 6 unit (ID 9-14).
 */
public final class VmFactory {

    private VmFactory() {}

    /**
     * @param brokerId user/broker ID pemilik VM
     * @return List&lt;Vm&gt; 15 unit siap pakai.
     */
    public static List<Vm> createVms(int brokerId) {
        return createVms(brokerId, 9, 6);
    }

    /** Versi fleksibel: jumlah VM tiap tipe bisa diubah. ID selalu 0..N-1 berurutan. */
    public static List<Vm> createVms(int brokerId, int numTipe1, int numTipe2) {
        List<Vm> vms = new ArrayList<>();
        int id = 0;
        for (int i = 0; i < numTipe1; i++) {
            vms.add(new Vm(id++, brokerId, 1000, 1, 2048, 1000, 10000,
                    "Xen", new CloudletSchedulerSpaceShared()));
        }
        for (int i = 0; i < numTipe2; i++) {
            vms.add(new Vm(id++, brokerId, 500, 1, 1024, 500, 5000,
                    "Xen", new CloudletSchedulerSpaceShared()));
        }
        return vms;
    }
}