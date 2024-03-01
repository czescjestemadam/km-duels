# km-duels
turbo bombing duels beta plugin

1. listener/command - guiManager.open - queueManager.queue
2. queueManager.matchFinder - mapManager.lock - fightManager.start
3. death/quit listener - fightManager.end - mapManager.unlock

## commands
- `/duels` - shows plugin info
- `/duels reload` - reloads config
- `/duels save` - saves maps, kits, players
### kits
- `/dkit list` - shows kit names
- `/dkit <create | remove | info | items> <name>` - manages kits
- `/dkit displayname <minimessage>` - sets kit displayname
- `/dkit get <name>` - sets inventory to kit contents
- `/dkit bindMaps <name> [maps...]` - bind maps to kit (empty to unbind)
- `/dkit icon <name>` - sets kit icon from item in hand
### maps
- `/dmap list` - shows map names
- `/dmap <create | remove | info> <name>` - manages maps
- `/dmap displayname <minimessage>` - sets map displayname
- `/dmap <pointA | pointB> <name>` - sets map boundaries
- `/dmap addSpawnPos <name>` - adds player spawn positions to map
- `/dmap clearSpawnPos <name>` - clears player spawn positions from map
- `/dkit icon <name>` - sets map icon from item in hand


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
    - [x] simple single window gui helper
    - [ ] multi window gui helper
    - [x] fight kit selector
    - [ ] kit preview in selector
    - [ ] fight map selector
    - [ ] per player kit editor
- kits
    - [ ] per player kit items override
    - [ ] better kit edition
    - [x] icon
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
    - [x] stats
- queue
    - [ ] elo based queue
    - [ ] ping limit
    - [ ] signs
- utils
    - [ ] PlaceholderAPI extension
    - [ ] vault api
