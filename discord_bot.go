package main

import (
	"fmt"
	"strings"

	"github.com/bwmarrin/discordgo"
)

func main() {
	dg, err := discordgo.New("Bot YOUR_BOT_TOKEN")
	if err != nil {
		fmt.Println("Error creating Discord session:", err)
		return
	}

	dg.AddHandler(messageCreate)

	err = dg.Open()
	if err != nil {
		fmt.Println("Error opening Discord session:", err)
		return
	}

	fmt.Println("Bot is now running. Press CTRL-C to exit.")
	<-make(chan struct{})
	return
}

func messageCreate(s *discordgo.Session, m *discordgo.MessageCreate) {
	if m.Author.Bot {
		return
	}

	if strings.HasPrefix(m.Content, "/move") {
		args := strings.Fields(m.Content)
		if len(args) < 3 {
			return
		}

		numMessages := 0
		fmt.Sscanf(args[1], "%d", &numMessages)

		threadName := strings.Join(args[2:], " ")

		messages, err := s.ChannelMessages(m.ChannelID, numMessages, "", "", "")
		if err != nil {
			fmt.Println("Error retrieving messages:", err)
			return
		}

		thread, err := s.ChannelThreadCreate(m.GuildID, m.ChannelID, threadName)
		if err != nil {
			fmt.Println("Error creating thread:", err)
			return
		}

		s.ChannelMessagesBulkDelete(m.ChannelID, messages)

		for _, msg := range messages[:len(messages)-1] {
			s.ChannelMessageSend(thread.ID, msg.Content, discordgo.WithUsername(msg.Author.Username))
		}
	}
}
