const Discord = require('discord.js');
const client = new Discord.Client();

client.once('ready', () => {
    console.log(`Bot is ready! Logged in as ${client.user.tag}`);
});

client.on('message', async (message) => {
    if (!message.guild) return; // Ignore DMs

    if (message.content.startsWith('/move')) {
        const args = message.content.split(' ');
        const numMessages = parseInt(args[1]);
        const threadName = args.slice(2).join(' ');

        // Get the last numMessages messages from the channel
        const messages = await message.channel.messages.fetch({ limit: numMessages + 1 });

        // Create a new thread
        const thread = await message.channel.threads.create({ name: threadName });

        // Delete the last numMessages messages from the channel
        await message.channel.bulkDelete(messages);

        // Move the messages to the new thread
        for (const msg of messages.array().slice(0, -1)) {
            const username = msg.author.username;
            thread.send({ content: msg.content, username: username });
        }
    }
});

client.login('YOUR_BOT_TOKEN');
