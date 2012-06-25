library(ggplot2)

View(diamonds)
summary(diamonds)

summary(diamonds$price)
avesize <-round(mean(diamonds$carat), 4)
clarity.factor <- factor(diamonds$clarity)

p <- qplot(carat, price, 
           data = diamonds, color = clarity.factor,
           xlab="Carat", ylab="Price",
           main="Diamond Pricing")
