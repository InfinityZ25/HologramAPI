package de.inventivegames.hologram;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class HologramPlugin extends JavaPlugin implements Listener {

	protected static HologramPlugin instance;

	boolean usePackets = false;

	@Override
	public void onEnable() {
		instance = this;
		Bukkit.getPluginManager().registerEvents(new HologramListeners(), this);

		if (Bukkit.getPluginManager().isPluginEnabled("PacketListenerApi")) {
			usePackets = true;
			System.out.println("[HologramAPI] Found PacketListenerAPI. Enabled touch-holograms.");
			new PacketListener(instance);
			HologramAPI.packetsEnabled = true;
		}

		if (Bukkit.getPluginManager().isPluginEnabled("ViaVersion")) {
			System.out.println("[HologramAPI] Found ViaVersion.");
			HologramAPI.enableProtocolSupport();
		}

	}

	@Override
	public void onDisable() {
		for (Hologram h : HologramAPI.getHolograms()) {
			HologramAPI.removeHologram(h);
		}
		if (usePackets) {
			PacketListener.disable();
		}
	}

}
