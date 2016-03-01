package me.macjuul.chbossbars;

import java.util.Iterator;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.annotations.api;
import com.laytonsmith.core.CHVersion;
import com.laytonsmith.core.Static;
import com.laytonsmith.core.constructs.CArray;
import com.laytonsmith.core.constructs.CBoolean;
import com.laytonsmith.core.constructs.CString;
import com.laytonsmith.core.constructs.CVoid;
import com.laytonsmith.core.constructs.Construct;
import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.environments.CommandHelperEnvironment;
import com.laytonsmith.core.environments.Environment;
import com.laytonsmith.core.exceptions.CRE.CRECastException;
import com.laytonsmith.core.exceptions.CRE.CREException;
import com.laytonsmith.core.exceptions.CRE.CRENotFoundException;
import com.laytonsmith.core.exceptions.CRE.CREThrowable;
import com.laytonsmith.core.functions.AbstractFunction;

public class BossBarFunctions {
	public static String docs() {
        return "BossBar management in CommandHelper";
    }
	
	@api
	public static class create_bossbar extends AbstractFunction {

		@SuppressWarnings("unchecked")
		public Class<? extends CREThrowable>[] thrown() {
			return new Class[] {
                 CRECastException.class
			};
		}
		
		public boolean isRestricted() {
            return false;
        }
		
		public Boolean runAsync() {
            return false;
        }
		
		public Construct exec(Target t, Environment environment, Construct... args) throws CREException {
			String id = args[0].val();
			String title = args[1].val();
			
			BossBar bar = Bukkit.createBossBar(title, BarColor.PINK, BarStyle.SOLID);
			
			if(args.length == 3) {
				BossBarManager.setBarOptions(bar, Static.getArray(args[1], t));
			}
			
			BossBarManager.addBar(id, bar);
			
			return CVoid.VOID;
		}
		
		public String getName() {
			return "create_bossbar";
	 	}

		public Integer[] numArgs() {
			return new Integer[] {2, 3};
		}
		
		public String docs() {
            return "void {String ID, String title, [Array options]} Creates a new bossbar with the given ID and optional options";
        }

        public Version since() {
            return CHVersion.V3_3_2;
        }
        
	}
	
	@api
	public static class delete_bossbar extends AbstractFunction {

		@SuppressWarnings("unchecked")
		public Class<? extends CREThrowable>[] thrown() {
			return new Class[] {
                 CRECastException.class
			};
		}
		
		public boolean isRestricted() {
            return false;
        }
		
		public Boolean runAsync() {
            return false;
        }
		
		public Construct exec(Target t, Environment environment, Construct... args) throws CREException {
			String id = args[0].val();
			
			BossBarManager.deleteBar(id);
			
			return CVoid.VOID;
		}
		
		public String getName() {
			return "delete_bossbar";
	 	}

		public Integer[] numArgs() {
			return new Integer[] {1};
		}
		
		public String docs() {
            return "void {String ID} Deletes a bossbar";
        }

        public Version since() {
            return CHVersion.V3_3_2;
        }
        
	}
	
	@api
	public static class set_bossbar_options extends AbstractFunction {

		@SuppressWarnings("unchecked")
		public Class<? extends CREThrowable>[] thrown() {
			return new Class[] {
                 CRECastException.class
			};
		}
		
		public boolean isRestricted() {
            return false;
        }
		
		public Boolean runAsync() {
            return false;
        }
		
		public Construct exec(Target t, Environment environment, Construct... args) throws CREException {
			String id = args[0].val();
			CArray options = Static.getArray(args[1], t);
			BossBar bar = BossBarManager.getBar(id);
			
			BossBarManager.setBarOptions(bar, options);
			
			return CVoid.VOID;
		}
		
		public String getName() {
			return "set_bossbar_options";
	 	}

		public Integer[] numArgs() {
			return new Integer[] {2};
		}
		
		public String docs() {
            return "void {String ID, Array options} Update a bossbars options";
        }

        public Version since() {
            return CHVersion.V3_3_2;
        }
        
	}
	
	@api
	public static class add_pbossbar extends AbstractFunction {

		@SuppressWarnings("unchecked")
		public Class<? extends CREThrowable>[] thrown() {
			return new Class[] {
                 CRECastException.class
			};
		}
		
		public boolean isRestricted() {
            return false;
        }
		
		public Boolean runAsync() {
            return false;
        }
		
		public Construct exec(Target t, Environment environment, Construct... args) throws CREException {
			Player player;
			String barID;
			
			if(args.length == 1) {
				player = (Player) environment.getEnv(CommandHelperEnvironment.class).GetPlayer().getHandle();
				barID = args[0].val();
			} else {
				player = Bukkit.getPlayer(args[0].val());
				barID = args[1].val();
			}
			
			BossBar bar = BossBarManager.getBar(barID);
			bar.addPlayer(player);
				
			return CVoid.VOID;
		}
		
		public String getName() {
			return "add_pbossbar";
	 	}

		public Integer[] numArgs() {
			return new Integer[] {1, 2};
		}
		
		public String docs() {
            return "void {[String player], String ID} Adds the bossbar to the given player. Defaults to the player running the script";
        }

        public Version since() {
            return CHVersion.V3_3_2;
        }
        
	}
	
	@api
	public static class remove_pbossbar extends AbstractFunction {

		@SuppressWarnings("unchecked")
		public Class<? extends CREThrowable>[] thrown() {
			return new Class[] {
                 CRECastException.class
			};
		}
		
		public boolean isRestricted() {
            return false;
        }
		
		public Boolean runAsync() {
            return false;
        }
		
		public Construct exec(Target t, Environment environment, Construct... args) throws CREException {
			Player player;
			String barID;
			
			if(args.length == 1) {
				player = (Player) environment.getEnv(CommandHelperEnvironment.class).GetPlayer().getHandle();
				barID = args[0].val();
			} else {
				player = Bukkit.getPlayer(args[0].val());
				barID = args[1].val();
			}
			
			BossBar bar = BossBarManager.getBar(barID);
			bar.removePlayer(player);
			
			return CVoid.VOID;
		}
		
		public String getName() {
			return "remove_pbossbar";
	 	}

		public Integer[] numArgs() {
			return new Integer[] {1, 2};
		}
		
		public String docs() {
            return "void {[String player], String ID} Removed a bossbar from a player";
        }

        public Version since() {
            return CHVersion.V3_3_2;
        }
        
	}
	
	@api
	public static class set_bossbar_visibility extends AbstractFunction {

		@SuppressWarnings("unchecked")
		public Class<? extends CREThrowable>[] thrown() {
			return new Class[] {
                 CRECastException.class
			};
		}
		
		public boolean isRestricted() {
            return false;
        }
		
		public Boolean runAsync() {
            return false;
        }
		
		public Construct exec(Target t, Environment environment, Construct... args) throws CREException {
			String id = args[0].val();
			boolean visible = Static.getBoolean(args[1]);
			BossBar bar = BossBarManager.getBar(id);
			
			if(visible) {
				bar.show();
			} else {
				bar.hide();
			}
			
			return CVoid.VOID;
		}
		
		public String getName() {
			return "set_bossbar_visibility";
	 	}

		public Integer[] numArgs() {
			return new Integer[] {2};
		}
		
		public String docs() {
            return "void {String ID, Boolean visible} Sets a bosbars visibility";
        }

        public Version since() {
            return CHVersion.V3_3_2;
        }
        
	}
	
	@api
	public static class add_bossbar_flag extends AbstractFunction {

		@SuppressWarnings("unchecked")
		public Class<? extends CREThrowable>[] thrown() {
			return new Class[] {
                 CRECastException.class
			};
		}
		
		public boolean isRestricted() {
            return false;
        }
		
		public Boolean runAsync() {
            return false;
        }
		
		public Construct exec(Target t, Environment environment, Construct... args) throws CREException {
			BossBar bar = BossBarManager.getBar(args[0].val());
			String flag = args[1].val();
			
			try {
				bar.addFlag(BarFlag.valueOf(flag.toUpperCase()));
			} catch(IllegalArgumentException e) {
				throw new CRENotFoundException("Unknown bar flag. Possible values", Target.UNKNOWN);
			}
				
			return CVoid.VOID;
		}
		
		public String getName() {
			return "add_bossbar_flag";
	 	}

		public Integer[] numArgs() {
			return new Integer[] {1, 2};
		}
		
		public String docs() {
            return "void {String ID, String flag} Adds the given flag to the bossbar. Possible flags are CREATE_FOG, DARKEN_SKY and PLAY_BOSS_MUSIC";
        }

        public Version since() {
            return CHVersion.V3_3_2;
        }
        
	}
	
	@api
	public static class remove_bossbar_flag extends AbstractFunction {

		@SuppressWarnings("unchecked")
		public Class<? extends CREThrowable>[] thrown() {
			return new Class[] {
                 CRECastException.class
			};
		}
		
		public boolean isRestricted() {
            return false;
        }
		
		public Boolean runAsync() {
            return false;
        }
		
		public Construct exec(Target t, Environment environment, Construct... args) throws CREException {
			BossBar bar = BossBarManager.getBar(args[0].val());
			BarFlag flag = BarFlag.valueOf(args[1].val());
			
			try {
				if(bar.hasFlag(flag)) {
					bar.removeFlag(flag);
					return CBoolean.TRUE;
				}
			} catch(IllegalArgumentException e) {
				throw new CRENotFoundException("Unknown bar flag", Target.UNKNOWN);
			}
				
			return CBoolean.FALSE;
		}
		
		public String getName() {
			return "remove_bossbar_flag";
	 	}

		public Integer[] numArgs() {
			return new Integer[] {1, 2};
		}
		
		public String docs() {
            return "boolean {String ID, String flag} Removed the given flag from the bossbar. Returns true if the flag exited and was removed";
        }

        public Version since() {
            return CHVersion.V3_3_2;
        }
        
	}
	
	@api
	public static class get_bossbars extends AbstractFunction {

		@SuppressWarnings("unchecked")
		public Class<? extends CREThrowable>[] thrown() {
			return new Class[] {
                 CRECastException.class
			};
		}
		
		public boolean isRestricted() {
            return false;
        }
		
		public Boolean runAsync() {
            return false;
        }
		
		public Construct exec(Target t, Environment environment, Construct... args) throws CREException {
			Set<String> bars = LifeCycle.bars.keySet();
			CArray list = new CArray(t);
			
			Iterator<String> i = bars.iterator();
			
			while(i.hasNext()) {
				list.push(new CString(i.next(), t), t);
			}
			
			return list;
		}
		
		public String getName() {
			return "get_bossbars";
	 	}

		public Integer[] numArgs() {
			return new Integer[] {0};
		}
		
		public String docs() {
            return "Array {} Lists all bossbars";
        }

        public Version since() {
            return CHVersion.V3_3_2;
        }
        
	}
	
}
