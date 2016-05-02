---
comments: true
date: 2016-05-01 
layout: post
title: "Troubleshooting memory issues: OOM-Killer"
img: http://i.dailymail.co.uk/i/pix/2010/08/03/article-1299819-059C6912000005DC-248_468x286.jpg
---

This is the first post about investigation memory issues. The OOM-Killer can be hard to detect if we don't look where we should and don't interpret the data properly.

### Where does Out of Memory killer comes from?

The linux kernel allows program to reserve more memory than it actually can offer. This behaviour can be disabled, however this is the default. It comes from the fact programs usually reserve more memory than they will actually use. One scenario can be a JVM reserving 1G of memory right away (`Xmx=1g Xms=1g`) running a program never actually using more than 512M. If the kernel didn't allow memory allocation over the physical constraints, you would be able to run less program than you currently are.

### What happen when every process request their memory?

The kernel has to sacrifice a process :( When this happen, you will find something like the following log in `/var/log/messages` or `/var/log/kern.log`.

```
May  1 21:13:05 dev-VirtualBox kernel: [ 4563.079551] memory-hog-2048 invoked oom-killer: gfp_mask=0x2084d0, order=0, oom_score_adj=0
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.079563] memory-hog-2048 cpuset=/ mems_allowed=0
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.079821] Mem-Info:
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.079827] Node 0 DMA per-cpu:
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.079834] CPU    0: hi:    0, btch:   1 usd:   0
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.079839] CPU    1: hi:    0, btch:   1 usd:   0
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.079845] CPU    2: hi:    0, btch:   1 usd:   0
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.079850] CPU    3: hi:    0, btch:   1 usd:   0
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.079855] Node 0 DMA32 per-cpu:
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.079861] CPU    0: hi:  186, btch:  31 usd:  26
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.079866] CPU    1: hi:  186, btch:  31 usd:  30
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.079872] CPU    2: hi:  186, btch:  31 usd:  34
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.079877] CPU    3: hi:  186, btch:  31 usd:  30
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.079882] Node 0 Normal per-cpu:
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.079888] CPU    0: hi:  186, btch:  31 usd: 172
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.079893] CPU    1: hi:  186, btch:  31 usd:  59
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.079898] CPU    2: hi:  186, btch:  31 usd: 119
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.079903] CPU    3: hi:  186, btch:  31 usd:  64
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.079913] active_anon:1832420 inactive_anon:146186 isolated_anon:0
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.079913]  active_file:44 inactive_file:120 isolated_file:0
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.079913]  unevictable:4 dirty:0 writeback:0 unstable:0
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.079913]  free:25271 slab_reclaimable:9320 slab_unreclaimable:5765
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.079913]  mapped:1454 shmem:2124 pagetables:9331 bounce:0
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.079913]  free_cma:0
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.079922] Node 0 DMA free:15900kB min:128kB low:160kB high:192kB active_anon:0kB inactive_anon:0kB active_file:0kB inactive_file:0kB unevictable:0kB isolated(anon):0kB isolated(file):0kB present:15992kB managed:15908kB mlocked:0kB dirty:0kB writeback:0kB mapped:0kB shmem:0kB slab_reclaimable:0kB slab_unreclaimable:8kB kernel_stack:0kB pagetables:0kB unstable:0kB bounce:0kB free_cma:0kB writeback_tmp:0kB pages_scanned:0 all_unreclaimable? yes
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.079934] lowmem_reserve[]: 0 3488 7966 7966
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.079942] Node 0 DMA32 free:47276kB min:29532kB low:36912kB high:44296kB active_anon:3225472kB inactive_anon:256744kB active_file:20kB inactive_file:172kB unevictable:0kB isolated(anon):0kB isolated(file):0kB present:3653568kB managed:3574580kB mlocked:0kB dirty:0kB writeback:0kB mapped:184kB shmem:2220kB slab_reclaimable:14660kB slab_unreclaimable:8120kB kernel_stack:816kB pagetables:15740kB unstable:0kB bounce:0kB free_cma:0kB writeback_tmp:0kB pages_scanned:336 all_unreclaimable? yes
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.079955] lowmem_reserve[]: 0 0 4478 4478
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.079962] Node 0 Normal free:37908kB min:37920kB low:47400kB high:56880kB active_anon:4104208kB inactive_anon:328000kB active_file:156kB inactive_file:308kB unevictable:16kB isolated(anon):0kB isolated(file):0kB present:4718592kB managed:4586280kB mlocked:16kB dirty:0kB writeback:0kB mapped:5632kB shmem:6276kB slab_reclaimable:22620kB slab_unreclaimable:14932kB kernel_stack:1832kB pagetables:21584kB unstable:0kB bounce:0kB free_cma:0kB writeback_tmp:0kB pages_scanned:1827 all_unreclaimable? yes
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.079973] lowmem_reserve[]: 0 0 0 0
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.079981] Node 0 DMA: 1*4kB (U) 1*8kB (U) 1*16kB (U) 0*32kB 2*64kB (U) 1*128kB (U) 1*256kB (U) 0*512kB 1*1024kB (U) 1*2048kB (R) 3*4096kB (M) = 15900kB
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080005] Node 0 DMA32: 98*4kB (E) 78*8kB (E) 264*16kB (UEM) 144*32kB (UEM) 97*64kB (UE) 41*128kB (UE) 16*256kB (UEM) 14*512kB (EM) 14*1024kB (UEM) 0*2048kB 0*4096kB = 46904kB
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080028] Node 0 Normal: 127*4kB (EM) 97*8kB (EM) 299*16kB (E) 149*32kB (UE) 103*64kB (UEM) 48*128kB (E) 8*256kB (EM) 6*512kB (UEM) 9*1024kB (UEM) 0*2048kB 0*4096kB = 37908kB
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080052] Node 0 hugepages_total=0 hugepages_free=0 hugepages_surp=0 hugepages_size=2048kB
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080057] 2321 total pagecache pages
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080063] 0 pages in swap cache
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080069] Swap cache stats: add 1498255, delete 1498255, find 623850/695336
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080073] Free swap  = 0kB
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080078] Total swap = 0kB
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080083] 2097038 pages RAM
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080088] 0 pages HighMem/MovableOnly
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080093] 33078 pages reserved
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080098] [ pid ]   uid  tgid total_vm      rss nr_ptes swapents oom_score_adj name
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080112] [  283]     0   283     4869       78      14        0             0 upstart-udev-br
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080120] [  289]     0   289    12954      234      27        0         -1000 systemd-udevd
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080128] [  467]     0   467     3815       68      12        0             0 upstart-socket-
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080135] [  588]   102   588    10010      297      23        0             0 dbus-daemon
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080142] [  610]     0   610    82559      278      65        0             0 ModemManager
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080149] [  656]     0   656     3850       72      13        0             0 upstart-file-br
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080156] [  670]   101   670    63961      224      28        0             0 rsyslogd
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080163] [  691]     0   691     4823       65      14        0             0 bluetoothd
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080170] [  700]     0   700    10863       93      26        0             0 systemd-logind
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080178] [  725]   111   725     8089       76      21        0             0 avahi-daemon
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080185] [  735]   111   735     8056       62      20        0             0 avahi-daemon
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080192] [  760]     0   760     5005       41      13        0             0 getty
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080198] [  764]     0   764     5005       41      13        0             0 getty
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080226] [  771]     0   771     5005       42      13        0             0 getty
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080233] [  772]     0   772     5005       39      12        0             0 getty
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080240] [  776]     0   776     5005       41      13        0             0 getty
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080247] [  850]     0   850    18838      230      41        0             0 cups-browsed
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080254] [  859]     0   859     4797       63      14        0             0 irqbalance
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080261] [  867]     0   867     1092       45       9        0             0 acpid
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080268] [  873]     0   873     5914       62      17        0             0 cron
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080275] [  932]     0   932    89147      411      72        0             0 NetworkManager
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080282] [  977]   106   977     9286       83      22        0             0 kerneloops
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080289] [  988]     0   988    53793      686      42        0             0 gdm
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080296] [  999]     0   999    73977      564      45        0             0 polkitd
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080303] [ 1018]     0  1018    74655      225      48        0             0 gdm-simple-slav
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080310] [ 1051]     0  1051     2558      573       8        0             0 dhclient
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080318] [ 1059]   109  1059    91360      372      78        0             0 whoopsie
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080325] [ 1072] 65534  1072     8807       64      22        0             0 dnsmasq
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080332] [ 1133]     0  1133    96904    11205     174        0             0 Xorg
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080339] [ 1182]     0  1182    75554      778      51        0             0 accounts-daemon
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080346] [ 1213]     0  1213    56259      102      20        0             0 VBoxService
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080353] [ 1405]     0  1405    73022     2875     109        0             0 apache2
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080360] [ 1419]     0  1419   101577      819      68        0             0 gdm-session-wor
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080367] [ 1453]  1000  1453     9984      179      24        0             0 init
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080374] [ 1554]     0  1554     5005       42      13        0             0 getty
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080381] [ 1592]  1000  1592     6110       65      17        0             0 dbus-launch
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080387] [ 1600]  1000  1600     9779       81      23        0             0 dbus-daemon
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080394] [ 1617]  1000  1617    28411      121      27       15             0 VBoxClient
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080401] [ 1624]  1000  1624    28938      139      26        0             0 VBoxClient
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080408] [ 1628]  1000  1628    11509      110      23        0             0 VBoxClient
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080415] [ 1633]  1000  1633    28088      137      24        0             0 VBoxClient
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080422] [ 1649]  1000  1649     2654       79       8        0             0 ssh-agent
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080429] [ 1655]  1000  1655    10004      307      23        0             0 dbus-daemon
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080436] [ 1662]  1000  1662     5576       52      15        0             0 upstart-event-b
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080443] [ 1680]  1000  1680     5630       90      12        0             0 upstart-dbus-br
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080450] [ 1681]  1000  1681     5578       52      13        0             0 upstart-dbus-br
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080457] [ 1685]  1000  1685     7697       75      16        0             0 upstart-file-br
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080464] [ 1692]  1000  1692    94282      552      52        0             0 ibus-daemon
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080471] [ 1706]  1000  1706   212350     2254     177        0             0 gnome-settings-
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080478] [ 1709]  1000  1709    91899      707      47        0             0 at-spi-bus-laun
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080485] [ 1710]  1000  1710   198563     1068     122        0             0 gnome-session
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080492] [ 1715]  1000  1715     9812      116      24        0             0 dbus-daemon
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080499] [ 1728]  1000  1728    49161      162      32        0             0 gvfsd
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080506] [ 1733]  1000  1733    73723      183      43        0             0 gvfsd-fuse
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080513] [ 1735]  1000  1735    73943      205      46        0             0 ibus-dconf
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080520] [ 1736]  1000  1736   133401     1187     107        0             0 ibus-ui-gtk3
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080527] [ 1738]  1000  1738    79529      471      88        0             0 ibus-x11
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080534] [ 1755]  1000  1755    31228      154      30        0             0 at-spi2-registr
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080541] [ 1776]     0  1776    59837      230      46        0             0 upowerd
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080548] [ 1786]  1000  1786    99095      267      52        0             0 gnome-keyring-d
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080555] [ 1797]  1000  1797    54980      268      43        0             0 ibus-engine-sim
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080562] [ 1813]  1000  1813    93447      576      90        0             0 pulseaudio
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080569] [ 1815]   107  1815    42229       58      19        0             0 rtkit-daemon
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080576] [ 1838]  1000  1838   535003   100313     522        0             0 gnome-shell
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080583] [ 1843]   113  1843    77760      386      55        0             0 colord
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080590] [ 1845]  1000  1845    44575      147      22        0             0 dconf-service
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080596] [ 1850]  1000  1850    97788      340      86        0             0 gsd-printer
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080603] [ 1967]  1000  1967   129444      635      82        0             0 gnome-shell-cal
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080610] [ 1973]  1000  1973   276760     1285     154        0             0 evolution-sourc
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080617] [ 1975]  1000  1975   101098      375      65        0             0 mission-control
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080624] [ 1978]  1000  1978    77893      958      54        0             0 gvfs-udisks2-vo
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080631] [ 1983]  1000  1983   160981     1618     143        0             0 goa-daemon
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080638] [ 1987]     0  1987   112920      960      56        0             0 udisksd
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080645] [ 1998]  1000  1998    53112      190      38        0             0 gvfs-gphoto2-vo
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080652] [ 2003]  1000  2003    46914      146      28        0             0 gvfs-goa-volume
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080659] [ 2023]  1000  2023    50071      667      35        0             0 gvfs-mtp-volume
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080666] [ 2027]  1000  2027    71490      181      40        0             0 gvfs-afc-volume
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080673] [ 2044]  1000  2044   120574    17714      93        0             0 tracker-store
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080679] [ 2046]  1000  2046   266727    17213     107        0             0 tracker-miner-f
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080686] [ 2095]  1000  2095   206485     9093     162        0             0 evolution-calen
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080693] [ 2208]  1000  2208    67595      170      34        0             0 gvfsd-burn
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080700] [ 2249]  1000  2249   140235     1498      97        0             0 zeitgeist-datah
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080726] [ 2254]  1000  2254    90908      805      47        0             0 zeitgeist-daemo
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080733] [ 2262]  1000  2262    64096      593      56        0             0 zeitgeist-fts
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080740] [ 2280]  1000  2280     2854       24      11        0             0 cat
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080747] [ 2282]  1000  2282   191359     2475     204        0             0 evolution-alarm
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080754] [ 2320]  1000  2320   156245     1641     121        0             0 update-notifier
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080761] [ 2385]  1000  2385   181535     3033     147        0             0 gnome-terminal
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080768] [ 2391]  1000  2391    14278      133      32        0             0 gconfd-2
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080775] [ 2396]  1000  2396     3706       40      13        0             0 gnome-pty-helpe
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080782] [ 2397]  1000  2397     7761     1573      20        0             0 bash
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080788] [ 2494]  1000  2494    96298      763      56        0             0 deja-dup-monito
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080796] [ 3166]  1000  3166     7726     1534      20        0             0 bash
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080837] [ 3517]     0  3517    19213      270      41        0             0 cupsd
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080844] [ 3693]  1000  3693   179795    13040     185        0             0 update-manager
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080851] [ 3912]  1000  3912     7706      374      20        0             0 htop
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080858] [ 4490]  1000  4490    31121      139      31        0             0 gvfsd-metadata
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080865] [ 6399]  1000  6399     7772     1585      20        0             0 bash
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080872] [ 6777]  1000  6777   525339   424087     836        0             0 memory-hog-2048
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080879] [ 6778]  1000  6778   525339   467427     921        0             0 memory-hog-2048
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080886] [ 6779]  1000  6779   525339   392670     775        0             0 memory-hog-2048
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080893] [ 6780]  1000  6780   525339   479845     945        0             0 memory-hog-2048
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080899] Out of memory: Kill process 6780 (memory-hog-2048) score 235 or sacrifice child
May  1 21:13:06 dev-VirtualBox kernel: [ 4563.080908] Killed process 6780 (memory-hog-2048) total-vm:2101356kB, anon-rss:1919364kB, file-rss:16kB
```        

The logs informs us the state of the box when the kernel sacrificed a child process. We also have the killed process pid and name at the end.

### How to read this log

There a lot of information in this log and I will focus on the part that are the most interesting. There is a table with the list of running pid when the memory starvation occured. For each PID we have the amount of memory being physically used (rss column) and the oom score adjustment `oom_score_adj`. The rss column is in number of page and each page is 4kb. Hence if you multiply the figures in this column by 4 you will have the memory used by the process in kb. The PID 6780 was using 479845*4/1024 = 1874 Mb of physical memory. The kernel uses an algorithm to determine which process it will kill. However, you can influence the algorithm.

### How to make my proces unkillable by oom killer

The kernel will compute a score for each process taking into account several low level information. The score will be between 0 and 1000 and the highest score is the process that will be killed. However this score is adjusted adding the `oom_score_adj`. Hence, if you set `oom_score_adj` to `-1000` your process will never be chosen by the kernel to be killed. In order to do that, you need to update the file `/proc/$PID/oom_score_adj`.

<script src="https://gist.github.com/toff63/4685c7bf24c2359b4923fddad746f268.js"></script>
