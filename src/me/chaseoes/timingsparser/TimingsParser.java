package me.chaseoes.timingsparser;

import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class TimingsParser extends JavaPlugin {

    @Override
    public boolean onCommand(CommandSender cs, Command cmnd, String string, String[] strings) {
        if (cmnd.getName().equalsIgnoreCase("timingsparser")) {
            if (strings.length == 0) {
                cs.sendMessage(ChatColor.RED + "Usage: /timingsparser <timings file name>");
                return true;
            }

            File f = new File("timings/" + strings[0] + ".txt");
            if (f.exists()) {
                TimingsFile tf = new TimingsFile(f);
                try {
                    GoogleShortener gs = new GoogleShortener(tf.parse());
                    cs.sendMessage(gs.shorten());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                cs.sendMessage(ChatColor.RED + "The file " + strings[0] + " could not be found.");
            }

        }
        return true;
    }

}
