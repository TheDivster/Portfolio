import pandas as pd
import numpy as np
from matplotlib import pyplot as plt

# This portfolio will be composed of 30% Global Equity Composite, 40% US Large Cap Composite, and 30% US Small
# Cap Composite
# If we have a portfolio of $1000, we will have $300 Global Equity Composite, $400 US Large Cap Composite,  and
# $300 US Small Cap Composite

portfolio = pd.read_excel("Intern_Portfolio_Task.xlsx", sheet_name=1)
print(portfolio.head())

# We see that the first column is unnamed
# Let's fix that

# Rename the 'Unnamed: 0' column to date
portfolio.rename({'Unnamed: 0': 'Date'}, axis=1, inplace=True)

# Now if we print the DataFrame, we will see the right column names.
print(portfolio.head())

# Let's check the data types to make sure that we can properly perform analysis:
print(portfolio.dtypes)

# Everything looks good. Let's start.


# Let's calculate beta
# Helper method to calculate beta
def beta(e, m):
    covariance = np.cov(e, m)[0][1]
    variance   = np.var(m)
    return covariance / variance


portfolio_beta = beta(portfolio["Global Equity Composite"], portfolio["SPXT Index"]) * 0.3 + \
                 beta(portfolio["US Large Cap Composite"], portfolio["SPXT Index"]) * 0.4 + \
                 beta(portfolio["US Small Cap Composite"], portfolio["SPXT Index"]) * 0.3
print("Our portfolio beta is", portfolio_beta)
# Since our beta is just above 1 our stock will move slightly
# more than the market. This means greater losses when the market
# moves down, but it means greater gains when the market moves up.


# Let's calculate VaR
returns = []
for data in portfolio["Global Equity Composite"]:
    returns.append(data * 0.3)
for data in portfolio["US Large Cap Composite"]:
    returns.append(data * 0.4)
for data in portfolio["US Small Cap Composite"]:
    returns.append(data * 0.3)

var = np.quantile(returns, 0.05)
print("The VaR of our portfolio is", var)
# This means that 95% of the time, we will not see losses greater than
# -3.4381% a month.

plt.hist(returns)
plt.title("Histogram of Returns")
plt.axvline(x=var, color='red', label='VaR')
plt.legend()
plt.show()
