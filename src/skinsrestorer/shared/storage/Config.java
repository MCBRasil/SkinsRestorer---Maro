package skinsrestorer.shared.storage;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import skinsrestorer.shared.utils.YamlConfig;

public class Config {

	private static YamlConfig config = new YamlConfig(
			"plugins" + File.separator + "SkinsRestorer" + File.separator + "", "config", true);

	public static boolean DISABLE_ONJOIN_SKINS = false;
	public static int SKIN_CHANGE_COOLDOWN = 30;
	public static String ALT_SKIN_PROPERTY_URL = "https://dev.mcapi.ca/profile/";
	public static boolean USE_MYSQL = false;
	public static String MYSQL_HOST = "localhost";
	public static String MYSQL_PORT = "3306";
	public static String MYSQL_DATABASE = "db";
	public static String MYSQL_SKINTABLE = "Skins";
	public static String MYSQL_PLAYERTABLE = "Skins";
	public static String MYSQL_USERNAME = "admin";
	public static String MYSQL_PASSWORD = "pass";
	public static boolean DEFAULT_SKINS_ENABLED = false;
	public static boolean DISABLED_SKINS_ENABLED = false;
	public static List<String> DEFAULT_SKINS = null;
	public static List<String> DISABLED_SKINS = null;
	public static boolean MULTIBUNGEE_ENABLED = false;
	public static String CUSTOMSKINS_USERNAME = "username";
	public static String CUSTOMSKINS_PASSWORD = "password";
	public static String CUSTOMSKINS_NAME = "";
	public static String CUSTOMSKINS_ID = "";
	public static String CUSTOMSKINS_AUTHTOKEN = "";
	public static String CUSTOMSKINS_CLIENTTOKEN = "";
	public static boolean UPDATER_ENABLED = true;
	public static boolean AUTOUPDATE = true;

	public static void load(InputStream is) {
		config.copyDefaults(is);
		config.reload();
		DISABLE_ONJOIN_SKINS = config.getBoolean("DisableOnJoinSkins", DISABLE_ONJOIN_SKINS);
		SKIN_CHANGE_COOLDOWN = config.getInt("SkinChangeCooldown", SKIN_CHANGE_COOLDOWN);
		DEFAULT_SKINS_ENABLED = config.getBoolean("DefaultSkins.Enabled", DEFAULT_SKINS_ENABLED);
		DISABLED_SKINS_ENABLED = config.getBoolean("DisabledSkins.Enabled", DISABLED_SKINS_ENABLED);
		MULTIBUNGEE_ENABLED = config.getBoolean("MultiBungee.Enabled", MULTIBUNGEE_ENABLED);
		USE_MYSQL = config.getBoolean("MySQL.Enabled", USE_MYSQL);
		MYSQL_HOST = config.getString("MySQL.Host", MYSQL_HOST);
		MYSQL_PORT = config.getString("MySQL.Port", MYSQL_PORT);
		MYSQL_DATABASE = config.getString("MySQL.Database", MYSQL_DATABASE);
		MYSQL_SKINTABLE = config.getString("MySQL.SkinTable", MYSQL_SKINTABLE);
		MYSQL_PLAYERTABLE = config.getString("MySQL.PlayerTable", MYSQL_PLAYERTABLE);
		MYSQL_USERNAME = config.getString("MySQL.Username", MYSQL_USERNAME);
		MYSQL_PASSWORD = config.getString("MySQL.Password", MYSQL_PASSWORD);

		if (config.get("Updater.Enabled") == null) {
			config.set("Updater.Enabled", true);
			config.save();
		}

		UPDATER_ENABLED = config.getBoolean("Updater.Enabled");

		/*
		 * if (config.get("Updater.AutoUpdate") == null) {
		 * config.set("Updater.AutoUpdate", false); config.save(); }
		 * 
		 * AUTOUPDATE = config.getBoolean("Updater.AutoUpdate");
		 */

		DEFAULT_SKINS = config.getStringList("DefaultSkins.Names");
		if (DEFAULT_SKINS == null || DEFAULT_SKINS.isEmpty()) {
			DEFAULT_SKINS = new ArrayList<>();
			DEFAULT_SKINS.add("Steve");
			config.set("DefaultSkins.Names", DEFAULT_SKINS.toArray());
		}

		DISABLED_SKINS = config.getStringList("DisabledSkins.Names");
		if (DISABLED_SKINS == null || DISABLED_SKINS.isEmpty()) {
			DISABLED_SKINS = new ArrayList<>();
			DISABLED_SKINS.add("Steve");
			config.set("DisabledSkins.Names", DISABLED_SKINS.toArray());
		}

		try {
			CUSTOMSKINS_USERNAME = config.getString("CustomSkins.Username");
			CUSTOMSKINS_PASSWORD = config.getString("CustomSkins.Password");
			CUSTOMSKINS_NAME = config.getString("CustomSkins.Name");
			CUSTOMSKINS_ID = config.getString("CustomSkins.ID");
			CUSTOMSKINS_AUTHTOKEN = config.getString("CustomSkins.Authtoken");
			CUSTOMSKINS_CLIENTTOKEN = config.getString("CustomSkins.Clienttoken");
		} catch (Exception e) {

		}

	}
}
