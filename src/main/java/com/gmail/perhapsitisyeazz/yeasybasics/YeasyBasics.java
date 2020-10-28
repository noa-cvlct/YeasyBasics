package com.gmail.perhapsitisyeazz.yeasybasics;

import com.gmail.perhapsitisyeazz.yeasybasics.commands.*;
import com.gmail.perhapsitisyeazz.yeasybasics.listeners.UtilsEvt;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class YeasyBasics extends JavaPlugin {

    public final File spellFile = new File("plugins/YeasyBasics/spells/");

    @Override
    public void onEnable() {
        if(!spellFile.exists()) {
            boolean wasCreated = spellFile.mkdirs();
            getLogger().warning("Directory creation " + (wasCreated ? "successful" : "failed") + ".");
        }
        this.getServer().getPluginManager().registerEvents(new UtilsEvt(this), this);
        this.getCommand("gamemode").setExecutor(new GamemodeCmd());
        this.getCommand("heal").setExecutor(new HealCmd());
        this.getCommand("speed").setExecutor(new SpeedCmd());
        this.getCommand("time").setExecutor(new TimeCmd());
        this.getCommand("invsee").setExecutor(new InvSeeCmd());
    }

    @Override
    public void onDisable() {
    }
}
