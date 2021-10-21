package de.otto.minecrafttutorium;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class MinecraftTutorium extends JavaPlugin {
    @Override
    public void onEnable() {
        System.out.println("Hello World!");

        this.getCommand("heal").setExecutor(new Commands());
        this.getCommand("boom").setExecutor(new SchedulerCommandExample(this));
        this.getServer().getPluginManager().registerEvents(new Events(), this);
    }

    @Override
    public void onDisable() {
        System.out.println("Bye!");
    }
}
