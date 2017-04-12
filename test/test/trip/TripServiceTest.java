package test.trip;

import java.awt.List;
import java.exception.UserNotLoggedInException;
import java.trip.Trip;
import java.trip.TripDAO;
import java.trip.TripService;
import java.user.User;

import org.junit.Before;
import org.junit.Test;

public class TripServiceTest {
	
	private static final User GUEST = null;
	private static final User UNUSED_USER = null;
	private static final User REGISTERED_USER = new User();
	private static final User ANOTHER_USER = new User();
	private static final Trip TO_BRAZIL = new Trip();
	private static final Trip TO_LONDON = new Trip();
	private User LoggedInUser;
	
	private TripService tripService;
	
	
	@Before
	public void initialize(){
		tripService = new TestableTripService();
		LoggedInUser = REGISTERED_USER;
	}
	
	@Test(expected=UserNotLoggedInException.class) public void
	should_throw_exception_when_user_not_loggged_In(){
		TripService tripService = new TripService();
		
		LoggedInUser = GUEST;
		
		tripService.getTripsByUser(UNUSED_USER);
	
	}
	
	@Test public void
	should_not_return_any_trips_when_are_not_friends(){
		
		tripService.getTripsByUser(UNUSED_USER);
		
		User friend = new User();
		friend.addFriend(ANOTHER_USER);
		friend.addTrip(TO_BRAZIL);
		
		List<Trip> friendTrips = tripService.getTripsByUser(friend);
		
		assertThat(friendTrips.size(), is(0));
	}
	
	@Test public void
	should_return_friend_trips_when_users_are_frinds(){
		
		
		tripService.getTripsByUser(UNUSED_USER);
		
		User friend = new User();
		friend.addFriend(ANOTHER_USER);
		friend.addFriend(LoggedInUser);
		friend.addTrip(TO_BRAZIL);
		friend.addTrip(TO_LONDON);
		
		List<Trip> friendTrips = tripService.getTripsByUser(friend);
		
		assertThat(friendTrips.size(), is(2));
	}
		
	private class TestableTripService extends TripService{
		@Override
		protected User getLoggedInUesr(){
			return null;
		}
		
		@Override
		protected List<Trip> tripsBy(User user) {
			return user.trips();
		}
	}
	
}
