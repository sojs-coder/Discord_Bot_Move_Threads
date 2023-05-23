require 'discordrb'

bot = Discordrb::Bot.new token: 'YOUR_BOT_TOKEN'

bot.message do |event|
  next if event.author.bot_account?

  if event.content.start_with?('/move')
    args = event.content.split(' ')
    num_messages = args[1].to_i
    thread_name = args[2..].join(' ')

    messages = event.channel.history(num_messages + 1)
    thread = event.channel.create_thread(thread_name)

    event.channel.delete_messages(messages)
    
    messages[0..-2].each do |message|
      thread.send_message(message.content, username: message.author.username)
    end
  end
end

bot.run
