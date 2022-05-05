#Question 1
seedcnt2 <- read.csv(choose.files())
#Part a
hist(seedcnt2[seedcnt2$Plant==1746,]$SeedCount,xlab="1746", main = "Histogram of Number of Seeds", n = 7)
boxplot(seedcnt2[seedcnt2$Plant==1746,]$SeedCount,xlab="1746", main = "Box Plot of Number of Seeds")
hist(seedcnt2[seedcnt2$Plant==1748,]$SeedCount,xlab="1748", main = "Histogram of Number of Seeds", n = 4)
boxplot(seedcnt2[seedcnt2$Plant==1748,]$SeedCount,xlab="1748", main = "Box Plot of Number of Seeds")
#The histogram of the 1746 plant is fairly normally distributed although some could argue against it
#Looking at the boxplot of the 1746 plant seems to further indicate that the 1746 plant is normally 
#distributed
#The histogram of the 1748 plant is not normally distributed. It's histogram is skewed to the left
#The histogram of the 1748 plant seems to confirm that the distribution is skewed left
#T procedure does make sense for plant 1746, but it doesn't make sense for plant 1748.
#This is because plant 1746's distributions seems reasonable normal, but plant 1748's distribution seems
#not normal whatsoever

#Part b
mean1746 <- mean(seedcnt2[seedcnt2$Plant==1746,]$SeedCount)
mean1748 <- mean(seedcnt2[seedcnt2$Plant==1748,]$SeedCount)
meandifference <- mean1746 - mean1748
sigma1746 <- sd(seedcnt2[seedcnt2$Plant==1746,]$SeedCount)
sigma1748 <- sd(seedcnt2[seedcnt2$Plant==1748,]$SeedCount)
mean1746 + sigma1746/sqrt(50) * qnorm(0.995, 0, 1)#Upper bound for 1746 plant
mean1746 - sigma1746/sqrt(50) * qnorm(0.995, 0, 1)#Lower bound for 1746 plant
#Confidence interval for 1746 plant is (1573, 1695.83) at 1% significance level
mean1748 + sigma1748/sqrt(19) * qnorm(0.995, 0, 1)#Upper bound for 1748 plant
mean1748 - sigma1748/sqrt(19) * qnorm(0.995, 0, 1)#Lower bound for 1748 plant
#Confidence interval for 1746 plant is (1742.04, 2021.96) at 1% significance level

#Part c

(mean(seedcnt2$SeedCount)-1550)/(sd(seedcnt2$SeedCount)/(sqrt(nrow(seedcnt2)))) < qt(.995,nrow(seedcnt2)-1)
1-pt((mean(seedcnt2$SeedCount)-1550)/(sd(seedcnt2$SeedCount)/(sqrt(nrow(seedcnt2)))),nrow(seedcnt2)-1) > 0.01

1-pt((mean(seedcnt2$SeedCount)-1560)/(sd(seedcnt2$SeedCount)/(sqrt(nrow(seedcnt2)))),nrow(seedcnt2)-1) > 0.01

#Question 2
drivethrough <- read.csv(choose.files())

#Part a
#mean of mcdonalds visit score = about 3.99
meanmcdonalds <- mean(drivethrough[drivethrough$Chain=="McDonald's",]$VisitScore)
#mean of  visit taco bell score = about 4.22
meantacobell  <- mean(drivethrough[drivethrough$Chain=="Taco Bell",]$VisitScore)

#Part b
#H(a) and H(0) they're not equal because the data isn't statistically significant.
#H(0) is m1 = m2
#H(a) is m1 != m2

n2<-length(drivethrough[drivethrough$Chain=="Taco Bell",]$VisitScore)
n1<-length(drivethrough[drivethrough$Chain=="McDonald's",]$VisitScore)
m2<-mean(drivethrough[drivethrough$Chain=="Taco Bell",]$VisitScore)
m1<-mean(drivethrough[drivethrough$Chain=="McDonald's",]$VisitScore)
sd2<-sd(drivethrough[drivethrough$Chain=="Taco Bell",]$VisitScore)
sd1<-sd(drivethrough[drivethrough$Chain=="McDonald's",]$VisitScore)

#Part a 5% significant``
(m1-m2)/sqrt(sd1^2/n1+sd2^2/n2)
pt((m1-m2)/sqrt(sd1^2/n1+sd2^2/n2),n1-1)
pt(-3.479119,n2-1)
pt(-3.479119,n2-1) > 0.05 #Reject H0: The data show evidence of a significant difference between the groups in Visit Score.
#Data does show the significant difference

#Question 3
wheat <- read.csv(choose.files())
#Part a
meanjuly <- mean(wheat[wheat$Month=="July",]$Price)
meanjanuary <- mean(wheat[wheat$Month=="January",]$Price)
sdjuly <- sd(wheat[wheat$Month=="July",]$Price)
sdjanuary <- sd(wheat[wheat$Month=="January",]$Price)
njuly<-length(wheat[wheat$Month=="July",]$Price)
njanuary<-length(wheat[wheat$Month=="January",]$Price)
#Critical values is 2.776 from the t table

#part b
#Critical value pooled for n1 + n2 - 2 degrees of freedom is 2.306

#part c
#The p value is 0.05 for both values. However, the pooled critical is lower which means that
#that your calculated t value has to not be as high as the unpooled critical value to be significant