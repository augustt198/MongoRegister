MongoRegister
=============

A plugin for adding an email to a minecraft account in-game, using MongoDB.

Idea from `msalihov/MySQL-Register` :)

#Building

Use `mvn clean install` and move the `target/mongoregister-0.1.jar` file to `plugins/`

These dependencies are used: `mongodb-driver` and `commons-validator`

#Configuration

Edit the config.yml file as needed:

`dbaddress` - the database's address

`dbport` - the port the database is on

`dbname` - the database name in mongoDB

`collectionname` - the collection you want to use

`usernamefield` - field used for storing a player's username

`emailfield` - field used for storing a player's email

`addplayer` - set to `true` to enable the automatic addition of documents for new players on login. **Plugin will only work if there is already a document containing the username field for the specified player OR this is set to true**
