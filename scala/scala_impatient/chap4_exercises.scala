1.
val gizmos=Map("SmartPhone" -> 500, "netbook" -> 600, "workstation" -> 1000)
for((gizmo, price) <- gizmos) yield (gizmo, price * 0.9)
