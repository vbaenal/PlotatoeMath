# Plotatoe Bot

Plotatoe is a Telegram Math Bot. You give the bot a function with some parameters and then it creates an image with the plot of that function for you. Everything except the Telegram API is done from scratch.

## How to use the bot

Usage is simple, just follow this pattern:

```
/plot f(x) start end step [options]
```

f(x) must be a function, with x as variable (i.e. 3\*x). You can't put spaces in the function. The bot supports sum(+), substract(-), multiplication(\*), division(/) and power function(^). It respects the natural order of operations but you can include parenthesis.

start can be any double.

end can be any number greater than end.

step can be any number greater than zero and greater than end-start.

options are optional arguments you can add with a #.

## Options

#noplot -> it gives you the results without the image

#nolabel -> represents the function but without numbers (only start and end)

## TelegramBots Library

Thanks to rubenlagus for the TelegramBots library - [TelegramBots](https://github.com/rubenlagus/TelegramBots) 
