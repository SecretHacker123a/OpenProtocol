package dev.undefinedteam.gensh1n.protocol.heypixel;

import dev.undefinedteam.gensh1n.utils.RandomUtils;
import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.util.List;

@StringEncryption
@ControlFlowObfuscation
public class HardwareList {

    public static final List<String> CPUS = List.of(
        "AMD Ryzen(TM) 9 5980HX @ 3.3GHz",
        "AMD Ryzen(TM) 9 5980HS @ 3.3GHz",
        "AMD Ryzen(TM) 9 5900HX @ 3.3GHz",
        "AMD Ryzen(TM) 9 5900HS @ 3.3GHz",
        "AMD Ryzen(TM) 7 5800H @ 3.2GHz",
        "AMD Ryzen(TM) 7 5800HS @ 3.2GHz",
        "AMD Ryzen(TM) 7 5800U @ 2.7GHz",
        "AMD Ryzen(TM) 5 5600H @ 3.3GHz",
        "AMD Ryzen(TM) 5 5600HS @ 3.3GHz",
        "AMD Ryzen(TM) 5 5600U @ 2.9GHz",
        "AMD Ryzen(TM) 7 1700X @ 3.4GHz",
        "AMD Ryzen(TM) 7 1700 @ 3.0GHz",
        "AMD Ryzen(TM) 5 1600X @ 3.6GHz",
        "AMD Ryzen(TM) 5 1600 @ 3.2GHz",
        "AMD Ryzen(TM) 3 1300X @ 3.5GHz",
        "AMD Ryzen(TM) 9 7950X @ 4.5GHz",
        "AMD Ryzen(TM) 9 7900X @ 4.7GHz",
        "AMD Ryzen(TM) 9 7900 @ 3.6GHz",
        "AMD Ryzen(TM) 7 7700X @ 4.5GHz",
        "AMD Ryzen(TM) 7 7700 @ 3.8GHz",
        "AMD Ryzen(TM) 5 7600X @ 4.7GHz",
        "AMD Ryzen(TM) 5 7600 @ 3.8GHz",
        "AMD Ryzen(TM) 5 5500 @ 3.6GHz",
        "AMD Ryzen(TM) 7 5700G @ 3.8GHz",
        "AMD Ryzen(TM) 5 5600G @ 3.9GHz",
        "AMD Ryzen(TM) 7 5800X @ 3.8GHz",
        "AMD Ryzen(TM) 9 3900X @ 3.8GHz",
        "AMD Ryzen(TM) 9 3900 @ 3.1GHz",
        "AMD Ryzen(TM) 7 3700X @ 3.6GHz",
        "AMD Ryzen(TM) 5 3600X @ 3.8GHz",
        "AMD Ryzen(TM) 5 3600 @ 3.6GHz",
        "AMD Ryzen(TM) 3 3300X @ 3.8GHz",
        "AMD Ryzen(TM) 9 5950X @ 3.4GHz",
        "Intel(R) Core(TM) i9-10900K @ 3.7GHz",
        "Intel(R) Core(TM) i7-10700K @ 3.8GHz",
        "Intel(R) Core(TM) i5-10600K @ 4.1GHz",
        "Intel(R) Core(TM) i9-9900K @ 3.6GHz",
        "Intel(R) Core(TM) i7-9700K @ 3.6GHz",
        "Intel(R) Core(TM) i5-9600K @ 3.7GHz",
        "Intel(R) Core(TM) i7-8700K @ 3.7GHz",
        "Intel(R) Core(TM) i5-8600K @ 3.6GHz",
        "Intel(R) Core(TM) i9-7900X @ 3.3GHz",
        "Intel(R) Core(TM) i7-7800X @ 3.5GHz",
        "Intel(R) Core(TM) i5-7600K @ 3.8GHz",
        "Intel(R) Core(TM) i9-7960X @ 2.8GHz",
        "Intel(R) Core(TM) i9-7940X @ 3.1GHz",
        "Intel(R) Core(TM) i7-7820X @ 3.6GHz",
        "Intel(R) Core(TM) i7-7700K @ 4.2GHz",
        "Intel(R) Core(TM) i5-7500 @ 3.4GHz",
        "Intel(R) Core(TM) i7-6800K @ 3.4GHz"
    );


    public static final List<String> DISKS = List.of(
        "Samsung SSD 941 EVO 1TB",
        "Western Digital Blue SN550 NVMe 1TB",
        "Crucial P5 NVMe 1TB",
        "Seagate FireCuda 520 NVMe 1TB",
        "Kingston A2000 NVMe PCIe M.2 1TB",
        "Corsair MP600 PRO NVMe Gen4 1TB",
        "ADATA XPG Gammix D30 NVMe 1TB",
        "SanDisk Extreme PRO NVMe 1TB",
        "Plextor M9P Plus NVMe PCIe M.2 1TB",
        "OWC Aura Pro X2 NVMe 1TB",
        "Gigabyte AORUS NVMe Gen4 1TB",
        "TeamGroup Delta Max NVMe PCIe M.2 1TB",
        "KLEVV Cras C930 NVMe PCIe M.2 1TB"
    );

    public static final List<String> NETWORKS = List.of(
        "TAP-Windows Adapter V9-WFP 802.2 MAC Layer LightWeight Filter-2000",
        "Realtek PCIe GbE Family Controller-WFP Native MAC Layer LightWeight Filter-0000",
        "Broadcom 802.11ac WLAN Adapter-WFP Native MAC Layer LightWeight Filter-0001",
        "Intel Centrino Advanced-N 6230 Network Adapter-WFP Native MAC Layer LightWeight Filter-0002",
        "Qualcomm Atheros AR9485WB-EG Wireless Network Adapter-WFP Native MAC Layer LightWeight Filter-0003",
        "Marvell Yukon 88E8059 PCI-E Gigabit Ethernet Controller-WFP Native MAC Layer LightWeight Filter-0004",
        "TP-Link Archer T4U AC1300 Wireless Network Adapter-WFP Native MAC Layer LightWeight Filter-0005",
        "D-Link DWA-182 USB 3.0 N600 Wi-Fi Adapter-WFP Native MAC Layer LightWeight Filter-0006",
        "ASUS USB-N13 Nano Dual-Band 802.11n Network Wi-Fi",
        "Netgear A6210 AC1200 DB Wi-Fi WFP Native LightWeight Pro",
        "Edimax EW-7811UTC 802.11ac Wi-Fi",
        "Linksys WUSB6300 AC1200 Wi-Fi Adapter-WFP "
    );

    public static String randomCpu() {
        return CPUS.get(RandomUtils.nextInt(0, CPUS.size() - 1));
    }

    public static String randomDisk() {
        return DISKS.get(RandomUtils.nextInt(0, DISKS.size() - 1));
    }

    public static String randomNetwork() {
        return NETWORKS.get(RandomUtils.nextInt(0, NETWORKS.size() - 1));
    }
}
