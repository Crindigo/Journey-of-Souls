
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

                               By: Jeremy Privett
                               
Core Design Principles
----------------------

Journey of Souls (JoS) is a multithreaded game server with support for multiple
simultaneous client connections. The class which contains the listening socket
is a singleton thread object that is constantly listening for and accepting
new connections.

When a client connects, the server thread spawns a client "Descriptor" thread
that holds all of the details on the connected client. Descriptors are
organized within the Server Thread in a HashMap that ties the player's
Character name to the Descriptor for easy access. Until a player character name
is available, Descriptors are placed in a "nanny" collection that facilitates
the authentication and/or character creation workflows.

Package Information
-------------------

com.josmud - Base Package. Contains class Game with main().
com.josmud.core - Core classes which include Descriptor.
com.josmud.interfaces - Game-specific Interfaces
com.josmud.util - Common utilities, like the dice roller and text manipulation
	classes.

Database Information
--------------------

TBD


