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
import com.laytonsmith.core.constructs.CDouble;
import com.laytonsmith.core.constructs.CString;
import com.laytonsmith.core.constructs.CVoid;
import com.laytonsmith.core.constructs.Construct;
import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.environments.CommandHelperEnvironment;
import com.laytonsmith.core.environments.Environment;
import com.laytonsmith.core.exceptions.CRE.CRECastException;
import com.laytonsmith.core.exceptions.CRE.CREException;
import com.laytonsmith.core.exceptions.CRE.CREIllegalArgumentException;
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
				BossBarManager.setBarOptions(bar, Static.getArray(args[2], t));
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
			CArray options;
			BossBar bar = BossBarManager.getBar(id);
			
			if(args.length == 2) {
				options = Static.getArray(args[1], t);
			} else {
				options = new CArray(t);
				options.set(args[1].val(), args[2].val());
			}
			
			BossBarManager.setBarOptions(bar, options);
			
			return CVoid.VOID;
		}
		
		public String getName() {
			return "set_bossbar_options";
	 	}

		public Integer[] numArgs() {
			return new Integer[] {2, 3};
		}
		
		public String docs() {
            return "void {String ID, Array options | String ID, String option, String, value} Update a bossbars options";
        }

        public Version since() {
            return CHVersion.V3_3_2;
        }
        
	}
	
	@api
	public static class set_pbossbar extends AbstractFunction {

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
			return "set_pbossbar";
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
			
			bar.setVisible(visible);
			
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
    public static class get_bossbar_visibility extends AbstractFunction {

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
            BossBar bar = BossBarManager.getBar(id);
            
            return CBoolean.GenerateCBoolean(bar.isVisible(), t);
        }
        
        public String getName() {
            return "get_bossbar_visibility";
        }

        public Integer[] numArgs() {
            return new Integer[] {1};
        }
        
        public String docs() {
            return "void {String ID} Gets a bosbars visibility";
        }

        public Version since() {
            return CHVersion.V3_3_2;
        }
        
    }
	
	@api
	public static class set_bossbar_flag extends AbstractFunction {

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
			Boolean state = Static.getBoolean(args[2]);
			
			try {
				BarFlag f = BarFlag.valueOf(flag.toUpperCase());
				if(state) {
					bar.addFlag(f);
				} else {
					bar.removeFlag(f);
				}
			} catch(IllegalArgumentException e) {
				throw new CRENotFoundException("Unknown bar flag. Possible values PLAY_BOSS_MUSIC, DARKEN_SKY and CREATE_FOG", Target.UNKNOWN);
			}
				
			return CVoid.VOID;
		}
		
		public String getName() {
			return "set_bossbar_flag";
	 	}

		public Integer[] numArgs() {
			return new Integer[] {3};
		}
		
		public String docs() {
            return "void {String ID, String flag, Boolean state} Update a bossbars flag";
        }

        public Version since() {
            return CHVersion.V3_3_2;
        }
        
	}
	
	@api
	public static class get_bossbar_flag extends AbstractFunction {

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
			BarFlag f;
			
			try {
				f = BarFlag.valueOf(flag.toUpperCase());
			} catch(IllegalArgumentException e) {
				throw new CRENotFoundException("Unknown bar flag. Possible values PLAY_BOSS_MUSIC, DARKEN_SKY and CREATE_FOG", Target.UNKNOWN);
			}
				
			return CBoolean.GenerateCBoolean(bar.hasFlag(f), t);
		}
		
		public String getName() {
			return "get_bossbar_flag";
	 	}

		public Integer[] numArgs() {
			return new Integer[] {2};
		}
		
		public String docs() {
            return "Boolean {String ID, String flag} Returns true if a bossbars flag is set";
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
	
	@api
	public static class get_bossbar_options extends AbstractFunction {

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
			BossBar bar = BossBarManager.getBar(id);
			
			if(args.length == 1) {
				CArray options = new CArray(t);
				
				options.set("color", bar.getColor().toString(), t);
				options.set("style", bar.getStyle().toString(), t);
				options.set("progress", new Double(bar.getProgress()).toString(), t);
				options.set("title", bar.getTitle(), t);
				
				return options;
			}
			
			String option = args[1].val().toUpperCase();
			
			switch(option) {
			case "COLOR":
				return new CString(bar.getColor().toString(), t);
			case "STYLE":
				return new CString(bar.getStyle().toString(), t);
			case "PROGRESS":
				return new CDouble(bar.getProgress(), t);
			case "TITLE":
				return new CString(bar.getTitle(), t);
			default:
				throw new CREIllegalArgumentException("Invalid option. Possible options are COLOR, STYLE, PROGRESS and TITLE", Target.UNKNOWN);
			}
		}
		
		public String getName() {
			return "get_bossbar_options";
	 	}

		public Integer[] numArgs() {
			return new Integer[] {1, 2};
		}
		
		public String docs() {
            return "Array options {String ID | String ID, String option} Get a bossbars options. Can return a single option value if specified";
        }

        public Version since() {
            return CHVersion.V3_3_2;
        }
        
	}
}
