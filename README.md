Setting Up a Discord Server and Adding a Bot
In this tutorial, we'll walk through the process of setting up a Discord server, adding a bot to it, and using the provided code files to create a message moving bot. The bot will create a new thread, delete a specified number of messages, and move them to the thread.

### Note
> the python script is the only one that is confirmed to work

# Prerequisites
Before we begin, make sure you have the following:

- A Discord account
- Administrative access to create and manage servers
- Knowledge of programming in the language of your choice (e.g., JavaScript, Python, Java, C#, Ruby, Go, PHP)
## Step 1: Create a Discord Server
 - Open Discord and log in to your account.
 - On the left side panel, click the '+' button to create a new server.
 - Choose a server name and select a region.
 - Click the "Create" button to create your server.
## Step 2: Create a Discord Bot
 - Go to the Discord Developer Portal and log in with your Discord account.
 - Click the "New Application" button and give it a name.
 - In the left navigation pane, click on "Bot" and then click the "Add Bot" button.
 - Customize your bot's name and avatar if desired.
 - Toggle on the necessary bot permissions such as "Read Messages," "Send Messages," "Manage Threads," and "Manage Messages" depending on your bot's requirements.
 - Copy the bot token. This will be used to authenticate the bot in the code.
## Step 3: Invite the Bot to Your Server
 - In the Discord Developer Portal, go to the "OAuth2" section.
 - In the "Scopes" section, select "bot" from the list.
 - Scroll down to the "Bot Permissions" section and select the necessary permissions based on your bot's requirements.
 - Copy the generated OAuth2 URL.
 - Open a new browser tab and paste the URL.
 - Select the server you want to add the bot to and click "Authorize" or "Continue" to grant the necessary permissions.
## Step 4: Choose a Programming Language and Set Up the Development Environment
 - Choose the programming language you prefer from the options provided (e.g., JavaScript, Python, Java, C#, Ruby, Go, PHP). Install the necessary dependencies and set up the development environment for the chosen language.

## Step 5: Use the Provided Code Files
 - Download the code file for your chosen programming language.
 - Open the file in your preferred code editor.
 - Locate the placeholder 'YOUR_BOT_TOKEN' and replace it with the bot token you copied earlier.
 - Customize any other settings or variables in the code, if needed.
## Step 6: Run the Bot
 - Follow the language-specific instructions below to run the bot:

### JavaScript (Node.js)
1. Open a command prompt or terminal.
2. Navigate to the directory containing the bot code file.
3. Run the command: node filename.js (replace filename.js with the actual filename of the code file).
### Python
1. Open a command prompt or terminal.
2. Navigate to the directory containing the bot code file.
3. Run the command: python filename.py (replace filename.py with the actual filename of the code file).
### Java
1. Compile the Java code using the appropriate compiler command for your environment.
2. Run the compiled Java code using the command: java filename (replace filename with the actual filename of the code file without the extension).
### C#
1. Compile the C# code using the appropriate compiler command for your environment.
2. Run the compiled C# code using the command: dotnet run or execute the compiled executable file.
### Ruby
1. Open a command prompt or terminal.
2. Navigate to the directory containing the bot code file.
3. Run the command: ruby filename.rb (replace filename.rb with the actual filename of the code file).
### Go
1. Open a command prompt or terminal.
2. Navigate to the directory containing the bot code file.
3. Run the command: go run filename.go (replace filename.go with the actual filename of the code file).
### Step 7: Test the Bot
1. Open Discord and go to the server where you added the bot.
2. Type /move <number> "<thread name>" in a channel, replacing <number> with the desired number of messages and <thread name> with the desired name for the new thread.
3. Send the command.
 - The bot should create a new thread, delete the specified number of messages, and move them to the thread. The messages will be displayed with the original sender's username.
Congratulations! You have set up a Discord server, added a bot to it, and created a message moving bot using the provided code files. You can now customize and expand the functionality of the bot based on your requirements.

> Note: Make sure to refer to the respective language's documentation and libraries for any additional configuration or troubleshooting specific to that language.
