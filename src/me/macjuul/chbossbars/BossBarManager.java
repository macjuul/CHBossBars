package me.macjuul.chbossbars;

import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;

import com.laytonsmith.core.constructs.CArray;
import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.exceptions.CRE.CRECastException;
import com.laytonsmith.core.exceptions.CRE.CREIllegalArgumentException;
import com.laytonsmith.core.exceptions.CRE.CRENotFoundException;

public class BossBarManager {
	
	/*
	 * Get a bossbar by ID.
	 */
	public static BossBar getBar(String id) {
		if(barExists(id)) {
			return LifeCycle.bars.get(id);
		}
		throw new CRENotFoundException("Bossbar '" + id + "' does not exist", Target.UNKNOWN);	
	}
	
	/*
	 * Create a new bossbar
	 */
	public static void addBar(String id, BossBar bar) {
		if(barExists(id)) {
			throw new CRECastException("Bossbar '" + id + "' already exists", Target.UNKNOWN);
		}
		LifeCycle.bars.put(id, bar);
	}
	
	/*
	 * Check if a bossbar exists
	 */
	public static boolean barExists(String id) {
		return LifeCycle.bars.containsKey(id);
	}
	
	/*
	 * Delete an existing bossbar.
	 */
	public static void deleteBar(String id) {
		if(barExists(id)) {
			getBar(id).removeAll();
			LifeCycle.bars.remove(id);
			return;
		}
		throw new CRENotFoundException("Bossbar '" + id + "' does not exist", Target.UNKNOWN);
	}
	
	/*
	 * Create a new bossbar from an options CArray
	 * 
	 * The array can contain the following keys:
	 * - String Color
	 * - String Style
	 * - String title
	 * - Double progress
	 * - Array flags
	 */
	public static void setBarOptions(BossBar bar, CArray options) {
		try {
			if(options.containsKey("color")) {
				try {
					bar.setColor(BarColor.valueOf(options.get("color", Target.UNKNOWN).val().toUpperCase()));
				} catch(IllegalArgumentException e) {
					throw new CRENotFoundException("Invalid color value. Possible colors are BLUE, GREEN, YELLOW, PINK, PURPLE, RED and WHITE", Target.UNKNOWN);
				}
			}
			
			if(options.containsKey("style")) {
				try {
					bar.setStyle(BarStyle.valueOf(options.get("style", Target.UNKNOWN).val().toUpperCase()));
				} catch(IllegalArgumentException e) {
					throw new CRENotFoundException("Invalid style value. Possible styles are SEGMENTED_6, SEGMENTED_10, SEGMENTED_12, SEGMENTED_20 and SOLID", Target.UNKNOWN);
				}
			}
			
			if(options.containsKey("title")) {
				bar.setTitle(options.get("title", Target.UNKNOWN).val());
			}
			
			if(options.containsKey("progress")) {
				Double progress = Double.valueOf(options.get("progress", Target.UNKNOWN).val());
				if(progress < 0 || progress > 1) {
					throw new CRENotFoundException("Progress must be between 0.0 and 1.0", Target.UNKNOWN);
				}
				bar.setProgress(progress);
			}
		} catch(IllegalArgumentException e) {
			throw new CREIllegalArgumentException(e.getMessage(), Target.UNKNOWN);
		}
	}
}
