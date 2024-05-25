# Game Review

## Overview
A Java-based application that allows users to review games , and search a large variety of games.

## Features
- Multiple types of users;
- Authentication and authorization;
-Search games
-Rate games and review them (comment on them)
-Audit to see past commands
-Adding played games
-Adding Games ,review and played games in more ways


## Database diagram


## Types of users
- Anonymous: Users who have not gone through a login step. Their access is limited to login and registration commands;
- Authenticated: Users who have an account and their access consists in basic functions: create playlists, add songs to playlists, etc.;
- Admin: Users who have an account and access to all commands.

## Commands
### Anonymous
  1. Login
  2. Register
  3. Exit

### User
  1. Search Games
  2. Comment on Game , rate Game
  3. Delete review on Game
  4. Add Game to played games list
  5. Remove Game from played games list
  6. Show games played by a user
  7. Search reviews on Game
  8. Search reviews of a user
  9. Delete account
  10. See comand history
  11. Exit

### Admin
  1. Search Games
  2. Add Game
  3. Comment on Game , rate Game
  4. Delete review on Game
  5. Add Game to played games list
  6. Remove Game from played games list
  7. Show games played by a user
  8. Delete Game
  9. Search reviews on Game
  10. Search reviews of a user
  11. Promote User
  12. Demote User
  13. Delete User
  14. Add Users using CSV
  15. Output Users to CSV
  16. Add Users using JSON
  17. Output Users to JSON
  18. Add Users using MyInput
  19. Output Users to MyOutput
  20. Add review using CSV
  21. Output review to CSV
  22. Add review using JSON
  23. Output review to JSON
  24. Add review using MyInput
  25. Output review to MyOutput
  26. Add game using CSV
  27. Output game to CSV
  28. Add game using JSON
  29. Output game to JSON
  30. Add game using MyInput
  31. Output game to MyOutput
  32. Read audit of a user
  33. Add played using CSV
  34. Output played to CSV
  35. Add played using JSON
  36. Output played to JSON
  37. Add played using MyInput
  38. Output played to MyOutput
  39. Output audit to CSV
  40. Output audit to JSON
  41. Output audit to MyOutput
  42. Exit



## More about Command and Input/Output system
-Most commands will ask for further information like the name of the user , name of the game
-Most commands are not case sensitive
-When putting a file it s sugested to put it in the same folder as the extension (JSON whith JSWON ....) and the path to be from src. Example of path: src/main/json/Review.Json

