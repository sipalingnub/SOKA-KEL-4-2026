package soka.simulasi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cloudbus.cloudsim.Host;
import org.cloudbus.cloudsim.Vm;
import org.cloudbus.cloudsim.VmAllocationPolicy;

/**
 * Penempatan VM deterministik sesuai Gambar 1 draft desain:
 * VM 0-8 (Tipe 1) -> Host A (0-2), 3 VM per host;
 * VM 9-14 (Tipe 2) -> Host B (3-5), 2 VM per host.
 * (VmAllocationPolicySimple bawaan menaruh SEMUA VM di Host A karena memilih host dengan PE bebas terbanyak.)
 */
public class VmAllocationPolicyByType extends VmAllocationPolicy {
    private final Map<String, Host> vmTable = new HashMap<>();
    /** Jumlah VM tipe-1 (cepat). VM id < vmTipe1 -> pool host cepat, sisanya -> pool host lambat. */
    private final int vmTipe1;
    /** Index host pertama pool lambat = jumlah host cepat. */
    private final int hostSplit;

    public VmAllocationPolicyByType(List<? extends Host> list) { this(list, 9, 3); }

    /** Versi fleksibel untuk jumlah host/VM berapa pun. */
    public VmAllocationPolicyByType(List<? extends Host> list, int vmTipe1, int hostSplit) {
        super(list);
        this.vmTipe1 = vmTipe1;
        this.hostSplit = hostSplit;
    }

    private int targetHost(int vmId) {
        List<Host> hosts = getHostList();
        int n = hosts.size();
        if (n == 0) return 0;
        int split = Math.max(0, Math.min(hostSplit, n));
        boolean fastVm = vmId < vmTipe1;
        int poolStart = fastVm ? 0 : split;
        int poolEnd = fastVm ? split : n;
        if (poolEnd <= poolStart) { poolStart = 0; poolEnd = n; } // pool kosong -> pakai semua host
        int poolSize = poolEnd - poolStart;
        // round-robin di dalam pool berdasarkan urutan VM tipe tsb
        int order = fastVm ? vmId : (vmId - vmTipe1);
        return poolStart + (Math.floorMod(order, poolSize));
    }

    @Override public boolean allocateHostForVm(Vm vm) {
        List<Host> hosts = getHostList();
        int idx = targetHost(vm.getId());
        if (idx < hosts.size() && allocateHostForVm(vm, hosts.get(idx))) return true;
        // fallback: coba host mana pun yang muat (tahan terhadap perubahan kapasitas/jumlah)
        for (int i = 0; i < hosts.size(); i++) {
            if (i == idx) continue;
            if (allocateHostForVm(vm, hosts.get(i))) return true;
        }
        return false;
    }

    @Override public boolean allocateHostForVm(Vm vm, Host host) {
        if (host.vmCreate(vm)) { vmTable.put(vm.getUid(), host); return true; }
        return false;
    }

    @Override public void deallocateHostForVm(Vm vm) {
        Host h = vmTable.remove(vm.getUid());
        if (h != null) h.vmDestroy(vm);
    }

    @Override public Host getHost(Vm vm) { return vmTable.get(vm.getUid()); }
    @Override public Host getHost(int vmId, int userId) { return vmTable.get(Vm.getUid(userId, vmId)); }
    @Override public List<Map<String, Object>> optimizeAllocation(List<? extends Vm> vmList) { return null; }
}