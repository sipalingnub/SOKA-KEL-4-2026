package soka.simulasi;

/** Konfigurasi simulasi yang tahan perubahan jumlah dataset/host/VM/datacenter. */
public final class SimConfig {
    public int hostA = 3;
    public int hostB = 3;
    public int vmTipe1 = 9;
    public int vmTipe2 = 6;
    public int datacenters = 1;

    public static SimConfig defaults() { return new SimConfig(); }

    /** Parse argumen key=value: hostA=3 hostB=3 vm1=9 vm2=6 dc=1 */
    public static SimConfig fromArgs(String[] args, int offset) {
        SimConfig c = new SimConfig();
        for (int i = offset; i < args.length; i++) {
            String[] kv = args[i].split("=", 2);
            if (kv.length != 2) continue;
            String k = kv[0].trim().toLowerCase();
            int v;
            try { v = Integer.parseInt(kv[1].trim()); } catch (NumberFormatException e) { continue; }
            if (v < 0) continue;
            switch (k) {
                case "hosta": case "host_a": c.hostA = v; break;
                case "hostb": case "host_b": c.hostB = v; break;
                case "vm1": case "vmt1": case "vmtipe1": c.vmTipe1 = v; break;
                case "vm2": case "vmt2": case "vmtipe2": c.vmTipe2 = v; break;
                case "dc": case "datacenters": case "datacenter": c.datacenters = Math.max(1, v); break;
                default: break;
            }
        }
        c.validate();
        return c;
    }

    public void validate() {
        if (hostA + hostB <= 0) throw new IllegalArgumentException("Total host harus > 0");
        if (vmTipe1 + vmTipe2 <= 0) throw new IllegalArgumentException("Total VM harus > 0");
        if (datacenters < 1) datacenters = 1;
    }

    public int totalHosts() { return hostA + hostB; }
    public int totalVms() { return vmTipe1 + vmTipe2; }

    @Override public String toString() {
        return String.format("hostA=%d hostB=%d vm1=%d vm2=%d dc=%d",
                hostA, hostB, vmTipe1, vmTipe2, datacenters);
    }
}
