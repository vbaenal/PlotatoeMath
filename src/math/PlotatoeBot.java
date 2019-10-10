package math;

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

public class PlotatoeBot extends AbilityBot {

	//Your bot token
	public static final String BOT_TOKEN = "";
	//Your bot username
	public static final String BOT_USERNAME = "";
	//Your user id
	public static final int CREATOR_ID = 0;

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
		super(BOT_TOKEN, BOT_USERNAME);
	}

	@Override
	public int creatorId() {
		return CREATOR_ID;
	}

	public Ability sendPlot() {
		return Ability.builder().name("plot").info("plots the function").input(0).locality(Locality.ALL)
				.privacy(Privacy.PUBLIC).action(ctx -> plotter(ctx)).build();
	}

	public void plotter(MessageContext ctx) {
		String[] command = ctx.arguments();
		try {
			MsgParser mp = new MsgParser(command);
			FunctionParser fp = new FunctionParser(mp.function);
			Compute c = new Compute(fp, mp);
			c.compute();
			
			if(!Common.arrayContains(mp.options, "noplot")) {
				BufferedImage result = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
				Drawer.plot(mp, c, result);

				ByteArrayOutputStream os = new ByteArrayOutputStream();
				ImageIO.write(result, "png", os);
				InputStream is = new ByteArrayInputStream(os.toByteArray());

				SendPhoto sender = new SendPhoto();
				sender.setChatId(ctx.chatId());
				sender.setPhoto("plot", is);
				execute(sender);
				is.close();
				result.flush();
			} else {
				String sentMessage="Function: f(x) = " + fp.toString() +
						"\n\tFirst Point: " + mp.start + 
						"\n\tEnd Point: " + mp.end + 
						"\n\tStep: " + mp.step;
				sentMessage+="\n"+c.toString();
				
				SendMessage sender = new SendMessage();
				sender.setChatId(ctx.chatId());
				sender.setText(sentMessage);
				execute(sender);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
