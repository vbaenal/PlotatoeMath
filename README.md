Plotatoe is a Telegram Math Bot. You give it a function with some parameters and then it creates an image with the plot of that function for you. Everything except the Telegram API is done from scratch.

Usage is simple, just follow this pattern:
/plot f(x) start end step \[options\]

f(x) must be a function, with x as variable (i.e. 3\*x). If you are going to put spaces you put the function in parenthesis: (3 \* x)

start can be any double

end can be any number greater than end

step can be any number greater than zero and greater than end-start

options are:
  #noplot -> it gives you the results without the image
  #nolabel -> represents the function but without numbers (only start and end)

It supports sum(+), substract(-), multiplication(\*), division(/) and power function(^). It respects the natural order of operations but you can include parenthesis.
