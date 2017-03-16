Universe -   a project to simulate an alternative, finite universe.

This project was provided to Shaun Porter as a starting point ot the INPROG Java coursework.

You will need to modify and add code as detailed in the coursework specification on Moodle

The world consists of four different types of objects, that act differently and take on different properties depending on their type.

The universe features full collision, with each object type having unqiue collision events depending on the two objects that have collided.

-Comets
    -Bounces off walls
    -Travel in X and Y axies
    -Can bounce off equal size space obejcts
    -Larger Comets absorb other objects
    -Can be abosrbed by stars to increase their lifespan
    -Some comets have unique propertys, such as changing of speed and direction
    
-Planets
    -Rotate around a pre-defined star
    -Longer lifespans than comets
    -Can abosrb comets to increase their size
    
-Sun
    -Acts a center point of rotation for planets
    -Instantly destorys any comet that touches it
    -Object destruction leads to an increase in lifetime, not size

-Black hole
    -Sucks in any comet with it's radius times three
    -If a comet touches the center, it removes the comet from the universe

The program also features an interface to select what universe to run on startup, as well as giving infomation about the collisions going on, and the data about them.