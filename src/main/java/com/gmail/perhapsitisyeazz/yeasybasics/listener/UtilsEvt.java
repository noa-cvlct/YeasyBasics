package com.gmail.perhapsitisyeazz.yeasybasics.listener;

import com.gmail.perhapsitisyeazz.yeasybasics.YeasyBasics;
import com.gmail.perhapsitisyeazz.yeasybasics.manager.Message;
import com.gmail.perhapsitisyeazz.yeasybasics.util.Utils;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class UtilsEvt implements Listener {

	public YeasyBasics main;
	public UtilsEvt(YeasyBasics main) {
		this.main = main;
	}

    private final Utils utils = new Utils();
    private final Message message = new Message();

    @EventHandler
    private void onJoin(PlayerJoinEvent event) {
        String player = event.getPlayer().getDisplayName();
        event.setJoinMessage(utils.getColMsg("&7[&a&l+&7] " + player));
    }

    @EventHandler
    private void onQuit(PlayerQuitEvent event) {
        String player = event.getPlayer().getDisplayName();
        event.setQuitMessage(utils.getColMsg("&7[&ac&l-&7] " + player));
    }


    @EventHandler
    private void onChat(AsyncPlayerChatEvent event) {
        String newMessage = event.getMessage();
        for(Player player : Bukkit.getOnlinePlayers()) {
            if(player == event.getPlayer()) continue;
            if(event.getMessage().contains(player.getName())) {
                ChatColor color = ChatColor.of(ChatColor.of(event.getMessage()).getColor());
                newMessage = newMessage.replaceAll(player.getName(), ChatColor.AQUA + player.getName() + color);
                player.sendActionBar(message.logo + utils.getColMsg("&b" + event.getPlayer().getName() + " &7has pinged you in the chat."));
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1F, 1F);
            }
        }
        event.setMessage(ChatColor.translateAlternateColorCodes('&', newMessage));
    }
}
