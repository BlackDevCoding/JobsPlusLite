# 🌱 JobsPlus

[![Modrinth Downloads](https://img.shields.io/modrinth/dt/jobspluslite?logo=modrinth&color=00AF5C)](https://modrinth.com/plugin/jobspluslite)

A powerful and lightweight **jobs system plugin** for **Minecraft Paper 1.21+**.  
Reward players with **money and job XP** for performing tasks like chopping trees, farming, mining, and more!

---

## ✨ Features

### Lite Edition
- ✅ **10 Prebuilt Jobs** (Treechopper, Farmer, Miner, Hunter, Fisher, Builder, Digger, Enchanter, Butcher, Explorer)  
- ✅ Earn **Vault money** and **job XP** by completing actions  
- ✅ `/jobs stats` to track your job progress  
- ✅ Actionbar notifications (`+$2 +4xp`)  
- ✅ Per-player `/jobs notify` toggle  
- ✅ Tab completion for all commands  
- ❌ No custom jobs (fixed prebuilt jobs only)

### Pro Edition
- 💎 Everything in Lite  
- 💎 Create **custom jobs** with YAML or `/jobs create`  
- 💎 **Set rewards** in-game with `/jobs setreward`  
- 💎 GUI Browser: `/jobs gui` for joining/leaving jobs visually  
- 💎 **Multipliers** (permission or global)  
- 💎 Leaderboards: `/jobs top <job>`  
- 💎 Optional **sound feedback** for rewards  

---

## 🖼️ Screenshots

_Coming soon!_  
(Add GUI previews, actionbar reward popups, etc.)

---

## 🔧 Installation

1. Download the plugin from [Modrinth](https://modrinth.com/plugin/jobsplus).
2. Drop the JAR into your server’s `plugins/` folder.
3. Start the server once to generate configs.
4. (Optional) Configure `config.yml` and job files (Pro edition).
5. Reload or restart your server.

Requires:
- [Paper 1.21+](https://papermc.io)  
- [Vault](https://modrinth.com/plugin/vault) + any economy plugin (EssentialsX, CMI, etc.) for money rewards  

---

## ⌨️ Commands & Permissions

| Command | Description | Permission |
|---------|-------------|------------|
| `/jobs list` | List available jobs | jobsplus.use |
| `/jobs info <id>` | Show job info | jobsplus.use |
| `/jobs join <id>` | Join a job | jobsplus.use |
| `/jobs leave <id>` | Leave a job | jobsplus.use |
| `/jobs stats [id]` | Show XP and level | jobsplus.use |
| `/jobs notify [on/off]` | Toggle notifications | jobsplus.use |
| `/jobs reload` | Reload config/jobs | jobsplus.admin |
| `/jobs create` (Pro) | Create a new job | jobsplus.admin |
| `/jobs setreward` (Pro) | Edit job rewards | jobsplus.admin |
| `/jobs gui` (Pro) | GUI interface | jobsplus.use |
| `/jobs top <id>` (Pro) | Job leaderboard | jobsplus.use |

---

## 🏆 Job XP & Leveling

- Each action gives **job XP** (not vanilla XP orbs).  
- XP is stored per-job per-player.  
- **100 XP = +1 level**.  
- Example: Breaking 10 oak logs as a Treechopper = +40xp and +$20.

---

## 📦 Editions

- **JobsPlus Lite** → for simple servers that just want prebuilt jobs.  
- **JobsPlus Pro** → for servers needing full customization and advanced features.  

---

## ❤️ Contributing & Support

- Found a bug or want a feature? Open an issue or PR on GitHub.  
- Join the discussion on the Modrinth page.  
- Contributions and feedback are welcome!  

---

## 📜 License

This project is licensed under the **MIT License**.  
Free to use, modify, and share.

