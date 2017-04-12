package java.trip;

import java.util.List;

import java.exception.CollaboratorCallException;
import java.user.User;

import java.util.List;

import java.exception.CollaboratorCallException;
import java.user.User;

public class TripDAO {

	public static List<Trip> findTripsByUser(User user) {
		throw new CollaboratorCallException(
				"TripDAO should not be invoked on an unit test.");
	}
	
}