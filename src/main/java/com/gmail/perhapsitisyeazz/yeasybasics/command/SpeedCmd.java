package com.gmail.perhapsitisyeazz.yeasybasics.command;

import com.gmail.perhapsitisyeazz.yeasybasics.manager.Message;
import com.gmail.perhapsitisyeazz.yeasybasics.util.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SpeedCmd implements CommandExecutor {

	private final Message message = new Message();
	private final Utils utils = new Utils();

	private final String logo = message.logo;

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(args.length > 0) {
				try {
					float f = Float.parseFloat(args[0].replace(',', '.'));
					if (f >= 0f && f <= 10f) {
						f = f / 10;
						String str;
						float speed;
						if (player.isFlying()) {
							player.setFlySpeed(f);
							str = " fly";
							speed = player.getFlySpeed();
						} else {
							player.setWalkSpeed(f);
							str = " walk";
							speed = player.getWalkSpeed();
						}
						if(f != speed) {
							player.sendActionBar(logo + utils.getColMsg("&aYour&b" + str + "&a speed has been set to &b" + args[0] + "&a."));
						} else {
							player.sendActionBar(logo + utils.getColMsg("&aYour&b" + str + "&a speed is already &b" + args[0] + "&a."));
						}
					} else {
						player.sendActionBar(logo + utils.getColMsg("&cError : Invalid number : '&5" + args[0] + "&c', it must be between 0 and 10."));
					}
				} catch (NumberFormatException e) {
					player.sendActionBar(logo + utils.getColMsg("&cError : Invalid number : '&5" + args[0] + "&c'."));
				}
			} else {
				player.sendActionBar(logo + utils.getColMsg("&cError : Missing number argument."));
			}
		} else {
			sender.sendMessage(logo + utils.getColMsg("&cError : Player-only command."));
		}
		return true;
	}
}
