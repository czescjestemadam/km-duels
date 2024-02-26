# km-duels
turbo bombing duels beta plugin

1. listener/command - guiManager.open - queueManager.queue
2. queueManager.matchFinder - mapManager.lock - fightManager.start
3. death/quit listener - fightManager.end - mapManager.unlock

## commands
- `/duels` - shows plugin info
- `/duels reload` - reloads config
- `/dkit list` - shows kit names
- `/dkit <create | remove | info | setItems> <name>` - manages kits
- `/dkit setDisplayname <minimessage>` - sets kit displayname
- `/dkit get <name>` - sets inventory to kit contents
- `/dmap list` - shows map names
- `/dmap <create | remove | info> <name>` - manages maps
- `/dkit setDisplayname <minimessage>` - sets map displayname
- `/dmap <pointA | pointB> <name>` - sets map boundaries
- `/dmap addSpawnPos <name>` - adds player spawn positions to map
- `/dmap clearSpawnPos <name>` - clears player spawn positions from map


## todo
- api
    - [ ] events
- commands
    - [ ] command builder by nodes
- fights
    - [ ] 1v1
    - [ ] party
    - [ ] party v party
    - [ ] ffa
    - [ ] after fight statistics
    - [ ] fight history
    - [ ] spectate after death
    - [ ] spectate
    - [ ] command on win/lose
    - [ ] command on start/end
    - [ ] win rewards
    - [ ] time duration limit
    - [ ] on death effects
    - [ ] countdown
- gui
    - [ ] simple single window gui helper
    - [ ] multi window gui helper
    - [ ] fight mode selector
    - [ ] fight type selector
    - [ ] kit preview in selector
    - [ ] fight map selector
    - [ ] per player kit editor
- kits
    - [ ] per player kit items override
    - [ ] better kit edition
- listeners
    - [ ] revoke elo on cheat ban
    - [ ] undo revoke on cheat unban
- maps
    - [ ] better map edition
    - [ ] regen
    - [ ] prevent escaping
    - [ ] death/lose on escape
- party
    - [ ] public/private
    - [ ] command
    - [ ] player divider
- players
    - [ ] stats
- queue
    - [ ] elo based queue
    - [ ] unranked queue
    - [ ] ping limit
    - [ ] signs
- utils
    - [ ] PlaceholderAPI extension
    - [ ] vault api
