Plotatoe is a Telegram Math Bot. You give it a function with some parameters and then it creates an image with the plot of that function for you. Everything except the Telegram API is done from scratch.

Usage is simple, just follow this pattern:
function\_name(variables)=equation;variable1<-[start\_point,end\_point,delta],variable2[blabla..]

function_name: it is the name of the function, nothing really important. You can call it f i.e.

variables: here you put the variables of your function, let's say x and y.

You should get something like f(x,y)=

equation: this is the function to plot. It supports sum(+), substract(-), multiplication(\*) and division(/). It respects the natural order of operations but you can include parenthesis. And example function: x*x+2*y-x/y

Now, you have this: f(x,y)=x\*x+2\*y-x/y;

Then, you need to define the variables like this: x<-[0,10,0.1],y[1,20,0.3]

So you end with this: f(x,y)=x\*x+2\*y-x/y;x<-[0,10,0.1],y<-[1,20,0.3]

IMPORTANT NOTE: You can only put a maximum of 2 variables, name them like you want but don't put more than that. If you do so, you will get an error from the telegram bot.