/* Telegram_Backup
 * Copyright (C) 2016 Fabian Schlenz
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>. */

package de.fabianonline.telegram_backup;

class CommandLineOptions {
	public boolean cmd_console = false;
	public String account = null;
	public boolean cmd_help = false;
	public boolean cmd_login = false;
	public boolean cmd_debug = false;
	public boolean cmd_list_accounts = false;
	public Integer limit_messages = null;
	public String target = null;
	public boolean cmd_version = false;
	public String export = null;
	public boolean cmd_license = false;
	public boolean cmd_daemon = false;

	public CommandLineOptions(String[] args) {
		String last_cmd = null;

		for (String arg : args) {
			if (last_cmd != null) {
				switch (last_cmd) {
					case "--account":
						this.account = arg;
						break;
					case "--limit-messages":
						this.limit_messages = Integer.parseInt(arg);
						break;
					case "--target":
						this.target = arg;
						break;
					case "--export":
						this.export = arg;
						break;
				}
				last_cmd = null;
				continue;
			}

			switch (arg) {
				case "-a": case "--account":
					last_cmd = "--account";              continue;
					
				case "-h": case "--help":
					this.cmd_help = true;                break;
					
				case "-l": case "--login":
					this.cmd_login = true;               break;
					
				case "--debug":
					this.cmd_debug = true;               break;
					
				case "-A": case "--list-accounts":
					this.cmd_list_accounts = true;       break;
					
				case "--limit-messages":
					last_cmd = arg;                      continue;
					
				case "--console":
					this.cmd_console = true;             break;
					
				case "-t": case "--target":
					last_cmd = "--target";               continue;
					
				case "-V": case "--version":
					this.cmd_version = true;             break;
				
				case "-e": case "--export":
					last_cmd = "--export";               continue;
				
				case "--license":
					this.cmd_license = true;             break;
				
				case "-d": case "--daemon":
					this.cmd_daemon = true;              break;
					
				default:
					throw new RuntimeException("Unknown command " + arg);
			}
		}
		if (last_cmd != null) {
			CommandLineController.show_error("Command " + last_cmd + " had no parameter set.");
		}
	}
}
