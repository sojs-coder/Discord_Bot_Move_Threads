// I am not sure that this one will actually work... test it throuroughly

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageHistory;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.ThreadedTextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;
import java.util.List;

public class DiscordBot extends ListenerAdapter {
    public static void main(String[] args) throws LoginException {
        JDABuilder.createDefault("YOUR_BOT_TOKEN")
                .enableIntents(GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MESSAGE_HISTORY)
                .addEventListeners(new DiscordBot())
                .build();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;

        String messageContent = event.getMessage().getContentRaw();
        if (messageContent.startsWith("/move")) {
            String[] args = messageContent.split(" ");
            int numMessages = Integer.parseInt(args[1]);
            String threadName = String.join(" ", args).substring(args[0].length() + args[1].length() + 2);

            TextChannel channel = event.getTextChannel();
            MessageHistory history = channel.getHistory();
            List<Message> messages = history.retrievePast(numMessages + 1).complete();

            ThreadedTextChannel thread = channel.createThread(threadName).complete();

            channel.purgeMessages(messages);

            for (Message message : messages.subList(0, messages.size() - 1)) {
                User author = message.getAuthor();
                thread.sendMessage(message.getContentRaw()).setUsername(author.getName()).queue();
            }
        }
    }
}
