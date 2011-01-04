
                   _                                            __ 
                  | |                                          / _|
                  | | ___  _   _ _ __ _ __   ___ _   _    ___ | |_ 
              _   | |/ _ \| | | | '__| '_ \ / _ \ | | |  / _ \|  _|
             | |__| | (_) | |_| | |  | | | |  __/ |_| | | (_) | |  
              \____/ \___/ \__,_|_|  |_| |_|\___|\__, |  \___/|_|  
                                                  __/ |            
                                                 |___/             
                             _____             _     
                            / ____|           | |    
                           | (___   ___  _   _| |___ 
                            \___ \ / _ \| | | | / __|
                            ____) | (_) | |_| | \__ \
                           |_____/ \___/ \__,_|_|___/

                     Copyright (c) 2010 - 2011, Jeremy Privett
                               All rights reserved.

Journey of Souls is licensed under the New BSD License. Full details are in the
LICENSE.txt file that should be packaged with this source. The license can also
be found by going to: http://www.josmud.com/license.php

---------------------------
Core Game Design Principles
---------------------------

Game and Engine Architecture
----------------------------

To be written by Jeremy.

Quick Bullet Points:

- Core Code Only: Keep as much out of this layer as feasible.
- Embedded Scripting: A Scripting Language (hopefully JavaScript via Rhino) will
	power most of the scriptable events within the game.
- MySQL Backed: Game data will be stored in a MySQL Database.
- Generic: Nothing game-specific should be in the codebase. It should be
	possible to build entirely different games based off the same codebase.

Socket Server Overview
----------------------

Journey of Souls (JoS) is a multi-threaded game server with support for multiple
simultaneous client connections. The class which contains the listening socket
is a singleton thread object that is constantly listening for and accepting
new connections.

When a client connects, the server thread spawns a client "Descriptor" thread
that holds all of the details on the connected client. Descriptors are
organized within the Server Thread in a HashMap that ties the player's
Character name to the Descriptor for easy access. Until a player character name
is available, Descriptors are placed in a "nanny" collection that facilitates
the authentication and/or character creation workflows.

Area Efficiency
---------------

JoS is being built with the understanding that Java is not necessarily the most
memory-efficient programming language on the planet. Steps will be taken to try
to keep the game server lean, primarily with regards to Area Loading and how
much of the game world is kept in-memory at once.

The game will have a method of distinguishing between important areas that need
to be available at all times (major cities, common areas, and paths between)
and lesser areas that are primarily used for questing or general hunting of
mobiles. The game will also keep track of whether or not there are any players
in any given area and flag lesser areas that are empty for deletion from the
game's internal memory structures.

When the game detects that a player is about to cross into an area that has been
unloaded from memory, it will quickly fetch the Area information from the
database along with a small grid of rooms that the player is most likely to
enter. The rest of the area will be lazily loaded in the background as the
player explores.

When the player departs from the area, it will go a few ticks in the game loop
as an active, empty area before being flagged for removal again.

Mobile / NPC Intelligence
-------------------------

To be written by Jeremy.

Combat System Overview
----------------------

To be written by Dutch.

------------------------
Java Package Information
------------------------

com.josmud - Base Package. Contains class Game with main().
com.josmud.core - Core classes which include Descriptor.
com.josmud.interfaces - Game-specific Interfaces
com.josmud.util - Common utilities, like the dice roller and text manipulation
	classes.

--------------------------
MySQL Database Information
--------------------------

Schema Overview
---------------

TBD
