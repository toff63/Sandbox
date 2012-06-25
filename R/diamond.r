library(ggplot2)

View(diamonds)
summary(diamonds)

summary(diamonds$price)
avesize <-round(mean(diamonds$carat), 4)
clarity.levels <- levels(diamonds$clarity)

p <- qplot(carat, price, 
           data = diamonds, colors = clarity.levels,
           xlab="Carat", ylab="Price",
           main="Diamond Pricing")
