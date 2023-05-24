import discord
from discord.ext import commands
import os

TOKEN = os.environ['TOKEN']


intents = discord.Intents.all()
print(intents)
intents.message_content = True
intents.guild_messages = True
intents.messages = True


bot = commands.Bot(command_prefix='!', intents=intents)

@bot.event
async def on_ready():
    print(f'Bot is ready! Logged in as {bot.user.name}')

@bot.command()
async def move(ctx, num_messages: int, thread_name: str):
    # Get the last num_messages messages from the channel
    messages = []
    async for message in ctx.channel.history(limit=num_messages + 1):
        messages.append(message)
    
    
    
    # Delete the last num_messages messages from the channel
    await ctx.channel.delete_messages(messages);
    # Create a new thread
    thread = await ctx.channel.create_thread(name=thread_name)
    # Move the messages to the new thread
    for message in messages[:-1]:
        content = message.author.mention + " said: \n" + message.content
        await thread.send(content=content)

bot.run(TOKEN)
