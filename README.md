# VenuesAroundMe
This is a single screen app that shows all open(at the time of requesting data) recommended venues for the day within dynamically selected radius, that depends on  the density of venues in the area around place that user provides.

Instructions:

1. On top of the screen enter name of the place e.g. Holborn, London or UK (it must be name not postcode) 
2. Press button find venues around.If there are any open venues nearby, you should see list of venues with venue name - Hyde Park, venue type - Park, and address.

If you forget to enter the place and press button it will show snackbar telling you so.
For all other errors I used snackbar with error - Can't get venues at the moment, please try again later. 

I used MVVM architecture with 1 fragment + viewmodel, that contains setup for Goggle's paging library, way of invalidating data on new search and error livedata. There is one interactor(used in VenueDataSource) -FindVenues that uses VenueRepository, which hides network logic within FourSquareVenueService. This class uses the FourSquareApi interface to make the calls to the api. App has data, di, domain, presentation, utils packages. 
