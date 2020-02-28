# Plotatoe Maths

Plotatoe is a Java library for function plotting. You give the bot a function with some parameters and then it creates an image with the plot of that function for you. Everything is made from scratch.

## Telegram Bot

You can test it here: https://telegram.me/plotatoemathsbot. It has three commands right now:

```
/plot f(x) start end step [options] // Normal plot

/results f(x) start end step        // Results in text

/projective f(x) start end step     // Plot in the projective plane
```


`f(x)` -> must be a function, with x as variable (i.e. `3*x`). You can't put spaces in the function. The bot supports sum(+), substract(-), multiplication(\*), division(/) and power function(^). It respects the natural order of operations but you can include parenthesis.

`start` -> can be any double.

`end` -> can be any number greater than start.

`step` -> can be any number greater than 0 and greater than `end-start`.

`options` -> are optional arguments you can add with a #.

## Options

`#nolabel` -> represents the function but without numbers (only start and end)

## Libraries used

Thanks to rubenlagus for the TelegramBots library - [TelegramBots](https://github.com/rubenlagus/TelegramBots)

## Contributors

Thanks to MrCamoga for implementing the projective plane - [MrCamoga](https://github.com/MrCamoga)
