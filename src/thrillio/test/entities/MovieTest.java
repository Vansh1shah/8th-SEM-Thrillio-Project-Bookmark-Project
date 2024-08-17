package thrillio.test.entities;


import static org.junit.Assert.*;
import entities.BookmarkManager;
import entities.Movie;
import constants.MovieGenre;

public class MovieTest {
    public void testIsKidFriendlyEligible() {
        //Test 1
        Movie movie = BookmarkManager.getInstance().createMovie(3000,"Citizen Kane","",1941,new String[] {"Orson Welles","Joseph Cotten"},new String[] {"Orson Welles"},MovieGenre.HORROR,8.5);

        boolean isKidFriendlyEligible = movie.isKidFriendelyEligible();

        assertFalse("For Horror Genre: isKidFriendlyEligible should return false",isKidFriendlyEligible);

        //Test 2
        movie = BookmarkManager.getInstance().createMovie(3000,"Citizen Kane","",1941,new String[] {"Orson Welles",""},new String[]{"Orson Welles"},MovieGenre.THRILLERS,8.5);

        isKidFriendlyEligible = movie.isKidFriendelyEligible();

        assertFalse("For Thrillers Genre - isKidFriendlyEligible should return false",isKidFriendlyEligible);
    }
}
