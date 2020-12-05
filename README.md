# HostTransferLocalProxy

Attempt at a local Minecraft proxy to handle host transfer messages.

Run the program with the destination host as the local argument. This starts a local proxy at port 25565.

Example output:

```
[main] INFO project.AppMain - Starting proxy server on port 25565 for remote game.sutdmc.opensutd.org:25565
[main] INFO project.TcpIpProxy - listening...
[Thread-0] INFO project.Connection - new connection localhost:55069
[Thread-0] INFO project.Connection - project.Proxy localhost:55069 <-> game.sutdmc.opensutd.org:25565
[Thread-1] INFO project.Proxy - project.Proxy localhost:55069 --> game.sutdmc.opensutd.org:25565
[Thread-1] INFO project.C2SProxy - Client Handshake
[Thread-2] INFO project.Proxy - project.Proxy game.sutdmc.opensutd.org:25565 --> localhost:55069
[Thread-2] INFO project.S2CProxy - Server Encryption Request
[Thread-1] INFO project.C2SProxy - Client Encryption Response
[Thread-1] INFO project.C2SProxy - Stole shared secret of length 128
[Thread-2] INFO project.S2CProxy - Server sent packet ID 4 to client
[Thread-2] INFO project.S2CProxy - Server sent packet ID 2083466 to client
[Thread-2] INFO project.S2CProxy - Server sent packet ID 2026141 to client
[Thread-2] INFO project.S2CProxy - Server sent packet ID 275 to client
[Thread-2] INFO project.S2CProxy - Server sent packet ID 10721 to client
[Thread-2] INFO project.S2CProxy - Server sent packet ID 14067 to client
[Thread-2] INFO project.S2CProxy - Server sent packet ID 102 to client
[Thread-2] INFO project.S2CProxy - Server sent packet ID 74 to client
```