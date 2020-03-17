# Introduction

This document holds all the API details of the `PlotatoeMath` bot.
For quick start, you may refer to [Examples] [code_examples]

# Usage

To use this bot you will need an array of 4 strings, where each position contains:
* `String[0]`: is the function 
* `String[1]`: is the start point 
* `String[2]`: is the end point
* `String[3]`: is the step

When you have the inputs ready, you can choose to return the function printed or to
return the calculated points through text.

#### Projective

To return an image of the fuction you have to call the `getProjectivePlot()` method.

#### Text

To re turn the calculated points you have to call the `results()` method.

[code_examples]: ./src/com/plotatoe/Test.java