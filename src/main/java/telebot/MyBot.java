package telebot;

import java.io.IOException;
import java.net.Socket;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Laba2.FrekenBok;
import Laba2.Person;
import Laba2.RequestsResponcesTable;
import Visual.Updater;

public class MyBot extends TelegramLongPollingBot {
	static Socket server;
	public static void main(String[] args) {
		
		ApiContextInitializer.init();
		TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
		try {
			telegramBotsApi.registerBot(new MyBot());
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void onUpdateReceived(Update update) {
		Message msg = update.getMessage();
				String[] args = msg.getText().split(" ");
				switch(args[0]){
				case "/add":{
					if (args.length == 4){
						 ObjectMapper mapper = new ObjectMapper();
				         mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
						int val = Integer.parseInt(args[2]);
						String Legs = new String();
						if(val>0){
							String RandomLegSiz = "Medium";
					        Legs += "{\"Washed\":" + String.valueOf(Math.random() >0.5? true : false) +
					                ",\"Barefoot\":"+ String.valueOf(Math.random() >0.5? true : false) +
					                ",\"LegSize\":\"" + RandomLegSiz+ "\"}";
					        int i =0;
					        while(i < val-1){
					        	Legs += ",{\"Washed\":" + String.valueOf(Math.random() >0.5? true : false) +
						                ",\"Barefoot\":"+ String.valueOf(Math.random() >0.5? true : false) +
						                ",\"LegSize\":\"" + RandomLegSiz+ "\"}";
					         i++;
					        }
						}else{
							MyBot.this.sendMsg(msg, "Negative num of legs");
							return;
						}
						new RequestsResponcesTable();
						String message = String.format("Laba2.FrekenBok {\"Legs\":[%s], \"Place\":\"%s\","
								+ "\"Name\":\"%s\", \"Came\":%s, \"wait\":%s}", 
								Legs, args[3], args[1], String.valueOf(false), String.valueOf(false));
						Updater.SetInetAddress();
						try {
							Person p = (Person) mapper.readValue(message.split("Laba2.FrekenBok", 2)[1], FrekenBok.class);
							Updater.AddPerson(p);
							MyBot.this.sendMsg(msg, "Alright");
							return;
						} catch (JsonParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (JsonMappingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				case "/remove":{
					if (args.length==2){
						new RequestsResponcesTable();
						Updater.SetInetAddress();
						Updater.DeletePerson(args[1]);
					}else{
						this.sendMsg(msg, "Wrong number of parameters");
						break;
					}
				}
			}
	}

	@Override
	public String getBotUsername() {
		return "Dummy";
	}

	@Override
	public String getBotToken() {
		return "377047449:AAFF2Qk8KpI9HGXrLoTdlI4FlKQsCpwpXP4";
	}
	public void sendMsg(Message message, String text){
		SendMessage sendMessage = new SendMessage();
		sendMessage.enableMarkdown(true);
		sendMessage.setChatId(message.getChatId().toString());
		sendMessage.setText(text);
		try {
			sendMessage(sendMessage);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}
}
