package com.plotatoe.bots.telegram;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.abilitybots.api.objects.Ability;
import org.telegram.abilitybots.api.objects.Locality;
import org.telegram.abilitybots.api.objects.MessageContext;
import org.telegram.abilitybots.api.objects.Privacy;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.plotatoe.Plotatoe;

public class PlotatoeBot extends AbilityBot {
	public static void main(String[] args) {
		
		ApiContextInitializer.init();
		TelegramBotsApi botsApi = new TelegramBotsApi();

		try {
			botsApi.registerBot(new PlotatoeBot());
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}

	public PlotatoeBot() {
		super(BotOptions.BOT_TOKEN, BotOptions.BOT_USERNAME);
	}

	@Override
	public int creatorId() {
		return BotOptions.CREATOR_ID;
	}

	public Ability plot() {
		return Ability.builder().name("plot").info("plots the function").input(0).locality(Locality.ALL)
				.privacy(Privacy.PUBLIC).action(ctx -> plotFunction(ctx, 0)).build();
	}
	
	public Ability projective() {
		return Ability.builder().name("projective").info("plots the function").input(0).locality(Locality.ALL)
				.privacy(Privacy.PUBLIC).action(ctx -> plotFunction(ctx, 1)).build();
	}
	
	public Ability results() {
		return Ability.builder().name("results").info("get the function results").input(0)
				.locality(Locality.ALL).privacy(Privacy.PUBLIC).action(ctx -> resultsFunction(ctx)).build();
	}

	public void plotFunction(MessageContext ctx, int type) {
		String[] command = ctx.arguments();
		try {
			BufferedImage result = Plotatoe.getPlotAsBufferedImage(command, type);

			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(result, "png", os);
			InputStream is = new ByteArrayInputStream(os.toByteArray());

			SendPhoto sender = new SendPhoto();
			sender.setChatId(ctx.chatId());
			sender.setPhoto("plot", is);
			execute(sender);
			is.close();
			result.flush();
		} catch (Exception e) {
			errorHandler(ctx);
		}
	}

	public void resultsFunction(MessageContext ctx) {
		String[] command = ctx.arguments();
		try {
			String computed = Plotatoe.results(command).toString();		
			SendMessage sender = new SendMessage();
			sender.setChatId(ctx.chatId());
			sender.setText(computed);
			execute(sender);
		} catch (TelegramApiException e) {
			errorHandler(ctx);
		}
	}

	public void errorHandler(MessageContext ctx) {
		SendMessage sm = new SendMessage();
		sm.setChatId(ctx.chatId());
		sm.setText("Something went wrong, try with something different");
		try {
			execute(sm);
		} catch (TelegramApiException e1) {
			e1.printStackTrace();
		}
	}
	
}
