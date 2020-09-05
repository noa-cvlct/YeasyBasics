package com.gmail.perhapsitisyeazz.yeasybasics.command;

import com.gmail.perhapsitisyeazz.yeasybasics.manager.Message;
import com.gmail.perhapsitisyeazz.yeasybasics.util.Utils;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TimeCmd implements CommandExecutor {

	private final Message message = new Message();
	private final Utils utils = new Utils();

	private final String logo = message.logo;
	/*private final List<String> timeNames = Arrays.asList("sunrise", "day", "noon", "sunset", "night", "midnight");
	private final List<Long> ticksByNames = Arrays.asList(23000L, 1000L, 6000L, 12000L, 13000L, 18000L);*/

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
		if(args.length > 0) {
			if(args.length == 2) {
				if (sender instanceof Player) {
					Player player = (Player) sender;
					long time;
					try {
						time = Long.parseLong(args[1]);
						updateTime(player.getWorld(), time, args[0], player);
						return true;
					} catch (NumberFormatException e) {
						switch (args[1]) {
							case "sunrise":
								time = 23000L;
								break;
							case "day":
								time = 1000L;
								break;
							case "noon":
								time = 6000L;
								break;
							case "sunset":
								time = 12000L;
								break;
							case "night":
								time = 13000L;
								break;
							case "midnight":
								time = 18000L;
								break;
							default:
								player.sendActionBar(utils.getColMsg(logo + "&cError : Invalid number/string : '&5" + args[0] + "&c'."));
								return true;
						}
						updateTime(player.getWorld(), time, args[0], player);
					}
				} else {
					if(world(args[1]) != null) {
						sender.sendMessage(utils.getColMsg(logo + "&cError : You must put a time at argument 2."));
					} else {
						sender.sendMessage(utils.getColMsg(logo + "&cError : Invalid world : '&5" + args[0] + "&c'."));
					}
				}
			}
			if(args.length >= 3) {
				World world = world(args[1]);
				if(world != null) {
					long time;
					try {
						time = Long.parseLong(args[2]);
						updateTime(world, time, args[0], sender);
						return true;
					} catch (NumberFormatException e) {
						switch (args[1]) {
							case "sunrise":
								time = 23000L;
								break;
							case "day":
								time = 1000L;
								break;
							case "noon":
								time = 6000L;
								break;
							case "sunset":
								time = 12000L;
								break;
							case "night":
								time = 13000L;
								break;
							case "midnight":
								time = 18000L;
								break;
							default:
								message.sendMessage(sender, utils.getColMsg(logo + "&cError : Invalid number/string : '&5" + args[2] + "&c'."));
								return true;
						}
						updateTime(world, time, args[0], sender);
					}
				} else {
					message.sendMessage(sender, utils.getColMsg(logo + "&cError : Invalid world : '&5" + args[1] + "&c'."));
				}
			}
		} else {
			if(sender instanceof Player) {
				Player player = (Player) sender;
				long playerWorldTime = player.getWorld().getTime();
				String playerWorldName = player.getWorld().getName();
				player.sendMessage(utils.getColMsg(logo + "&aYour world:\n&3» &a" + playerWorldName + " &7- &f" + playerWorldTime));
			} else {
				ComponentBuilder builder = new ComponentBuilder();
				builder.append(utils.getColMsg(logo + "&aWorlds list:"));
				for(World world : Bukkit.getWorlds()) {
					builder
							.append("\n » ").color(ChatColor.DARK_AQUA)
							.append(world.getName()).color(ChatColor.GREEN)
							.append(" - ").color(ChatColor.GRAY)
							.append(String.valueOf(world.getTime())).color(ChatColor.WHITE);
				}
				sender.sendMessage(builder.create());
			}
		}
		return true;
	}

	private void updateTime(World world, long time, String arg, CommandSender sender) {
		String worldName = world.getName();
		if(arg.equalsIgnoreCase("set")) {
			if (time >= 0L && time <= 60000L) {
				world.setTime(time);
				message.sendMessage(sender, utils.getColMsg(logo + "&b" + worldName + "&a's time has been set to &b" + time + "&a."));
			} else {
				message.sendMessage(sender, utils.getColMsg(logo + "&cError : Invalid time : '&5" + time + "&c', it must be between 0 and 60,000."));
			}
		} else if(arg.equalsIgnoreCase("add")) {
			world.setTime(time + world.getTime());
			message.sendMessage(sender, utils.getColMsg(logo + "&b" + worldName + "&a's time has been set to &b" + time + "&a."));
		}
	}

	private World world(String world) {
		return Bukkit.getWorld(world);
	}
}
