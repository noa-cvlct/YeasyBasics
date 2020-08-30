package com.gmail.perhapsitisyeazz.yeasybasics.command;

import com.gmail.perhapsitisyeazz.yeasybasics.manager.Message;
import com.gmail.perhapsitisyeazz.yeasybasics.util.Utils;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.jetbrains.annotations.NotNull;

public class HealCmd implements CommandExecutor {

	private final Message message = new Message();
	private final Utils utils = new Utils();

	private final String logo = TextComponent.toLegacyText(message.logo);

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
		if(args.length > 0) {
			Player target = Bukkit.getPlayer(args[0]);
			if(target != null && target != sender) {
				heal(sender, target);
				utils.sendMessage(sender, logo + ChatColor.GREEN + target.getName() + "has been successfully heal.");
				target.sendActionBar(logo + ChatColor.GREEN + "You have been successfully heal by " + sender.getName());
			} else if(sender instanceof Player) {
				Player player = (Player) sender;
				heal(sender, player);
				player.sendActionBar(logo + ChatColor.GREEN + "You have been successfully heal.");
			}
		} else {
			if(sender instanceof Player) {
				Player player = (Player) sender;
				heal(sender, player);
				player.sendActionBar(logo + ChatColor.GREEN + "You have been successfully heal.");
			} else {
				sender.sendMessage(logo + ChatColor.RED + "Error : Missing argument.");
			}
		}
		return true;
	}

	private void heal(CommandSender sender, Player player) {
		if(player.getHealth() != 0) {
			AttributeInstance targetAttribute = player.getAttribute(Attribute.GENERIC_MAX_HEALTH);
			if (targetAttribute != null) player.setHealth(targetAttribute.getValue());
			player.setFoodLevel(20);
			player.setFireTicks(0);
			for (PotionEffect effect : player.getActivePotionEffects()) {
				player.removePotionEffect(effect.getType());
			}
		} else {
			utils.sendMessage(sender, logo + ChatColor.RED + "Error : " + player.getName() + " is dead.");
		}
	}
}
