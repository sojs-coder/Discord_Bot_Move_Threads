using System;
using System.Linq;
using System.Threading.Tasks;
using Discord;
using Discord.Commands;
using Discord.WebSocket;

public class DiscordBot
{
    private DiscordSocketClient _client;
    private CommandService _commands;
    private IServiceProvider _services;

    public static void Main(string[] args) => new DiscordBot().RunBotAsync().GetAwaiter().GetResult();

    public async Task RunBotAsync()
    {
        _client = new DiscordSocketClient();
        _commands = new CommandService();

        string token = "YOUR_BOT_TOKEN";

        _client.Log += Log;

        await RegisterCommandsAsync();

        await _client.LoginAsync(TokenType.Bot, token);

        await _client.StartAsync();

        await Task.Delay(-1);
    }

    private Task Log(LogMessage arg)
    {
        Console.WriteLine(arg);
        return Task.CompletedTask;
    }

    public async Task RegisterCommandsAsync()
    {
        _client.MessageReceived += HandleCommandAsync;

        await _commands.AddModulesAsync(System.Reflection.Assembly.GetEntryAssembly(), _services);
    }

    private async Task HandleCommandAsync(SocketMessage arg)
    {
        var message = arg as SocketUserMessage;
        var context = new SocketCommandContext(_client, message);

        if (message.Author.IsBot) return;

        if (message.Content.StartsWith("/move"))
        {
            var args = message.Content.Split(' ');
            int numMessages = int.Parse(args[1]);
            string threadName = string.Join(' ', args.Skip(2));

            var channel = context.Channel as SocketTextChannel;
            var messages = await channel.GetMessagesAsync(numMessages + 1).FlattenAsync();

            var thread = await channel.Threads.CreateAsync(threadName);

            await channel.DeleteMessagesAsync(messages);

            foreach (var msg in messages.SkipLast(1))
            {
                await thread.SendMessageAsync(msg.Content, username: msg.Author.Username);
            }
        }
    }
}
