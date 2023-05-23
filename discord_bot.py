import discord
from discord.ext import commands

intents = discord.Intents.default()
intents.message_content = True
intents.message_history = True

bot = commands.Bot(command_prefix='/', intents=intents)

@bot.event
async def on_ready():
    print(f'Bot is ready! Logged in as {bot.user.name}')

@bot.command()
async def move(ctx, num_messages: int, thread_name: str):
    # Get the last num_messages messages from the channel
    messages = await ctx.channel.history(limit=num_messages + 1).flatten()
    
    # Create a new thread
    thread = await ctx.channel.create_thread(name=thread_name)
    
    # Delete the last num_messages messages from the channel
    await ctx.channel.purge(limit=num_messages + 1)
    
    # Move the messages to the new thread
    for message in messages[:-1]:
        await thread.send(content=message.content, username=message.author.name)

bot.run('YOUR_BOT_TOKEN')
