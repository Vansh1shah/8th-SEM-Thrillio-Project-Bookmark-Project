package thrillio.test.entities;

import constants.BookGenre;
import constants.MovieGenre;
import entities.Book;
import entities.BookmarkManager;
import entities.Movie;

import static org.junit.Assert.assertFalse;

public class BookTest {
    public void testIsKidFriendlyEligible() {

        //Test 1
        Book book = BookmarkManager.getInstance().createBook(4000,"Walden",1854,"Wilder Publications",new String[]{"Henry David Thoreau"}, BookGenre.PHILOSOPHY,4.3);
        boolean isKidFriendlyEligible = book.isKidFriendelyEligible();

        assertFalse("For Philosophy Genre - isKidFriendlyEligible should return false",isKidFriendlyEligible);

        //Test 2
      book = BookmarkManager.getInstance().createBook(4000,"Walden",1854,"Wilder Publications",new String[]{"Henry David Thoreau"}, BookGenre.SELF_HELP,4.3);
      isKidFriendlyEligible = book.isKidFriendelyEligible();
        assertFalse("For Horror Genre, isKidFriendlyEligible should return false",isKidFriendlyEligible);



    }
}
