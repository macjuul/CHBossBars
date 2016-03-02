package me.macjuul.chbossbars;

import java.util.HashMap;

import org.bukkit.boss.BossBar;

import com.laytonsmith.PureUtilities.SimpleVersion;
import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.core.extensions.AbstractExtension;
import com.laytonsmith.core.extensions.MSExtension;

@MSExtension("CHBossBars")
public class LifeCycle extends AbstractExtension {
	public static HashMap<String, BossBar> bars;

	public Version getVersion() {
        return new SimpleVersion(1, 0, 1);
    }

    public void onShutdown() {
        System.out.println("CHBossBars " + getVersion() + " has sucessfully been disabled!");
        
        for(BossBar b : bars.values()) {
        	b.removeAll();
        }
    }

    public void onStartup() {
        System.out.println("CHBossBars " + getVersion() + " has sucessfully been enabled!");
        bars = new HashMap<String,BossBar>();
    }

}
