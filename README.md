# Getting Started

This application provides currency exchange rates for provided currency.

The exchange rates are updated in defined interval of time (default interval is 15 minutes).
If application failed to update currency, it retries to update in provided retry interval.

# Tech stack

This is a basic spring boot application with Redis integration for caching rates value.

# Notes on implementation

Normally, the better approach for better user experience would be providing rates for base currency and calculate it 'on the fly'
on UI part. But since UI is missing, I added endpoint to convert from one currency to another with provided.

Another approach which could be applied is calculating rates based on default currency (i.e. we know how many euros we'll receive 
for one dollar, and we know how many canadian dollars we get for one USD, so we can simply calculate those values, but it may contradict the logic of currency conversion).
