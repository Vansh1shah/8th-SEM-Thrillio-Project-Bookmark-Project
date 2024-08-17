package thrillio.test.entities;

import constants.BookGenre;
import entities.Bookmark;
import entities.BookmarkManager;
import entities.WebLink;
import partner.Shareable;

import static org.junit.Assert.*;
public class WebLinkTest extends Bookmark {

    public void testIsKidFriendlyEligible() {
        //Test 1: porn in url -- false
        WebLink webLink = BookmarkManager.getInstance().createWebLink(2000,"Taming Tiger, Part 2","https://rapidapi.com/","https://rapidapi.com/");

        boolean isKidFriendlyEligible = webLink.isKidFriendelyEligible();

        assertFalse("For porn in url - isKidFriendlyEligible must return false",isKidFriendlyEligible);


        //Test 2: porn in title -- false
        webLink = BookmarkManager.getInstance().createWebLink(2001,"Article Links","https://www.mediawiki.org/wiki/API:RecentChanges","https://www.mediawiki.org/wiki/API:RecentChanges");

        isKidFriendlyEligible=webLink.isKidFriendelyEligible();

        assertFalse("For porn in url - isKidFriendelyEligible() and return false",isKidFriendlyEligible);


        //Test 3: adult in host -- false
        webLink = BookmarkManager.getInstance().createWebLink(2002,"Retrive Information about videos","https://developers.google.com/youtube/v3","https://developers.google.com/youtube/v3");

        isKidFriendlyEligible=webLink.isKidFriendelyEligible();

        assertFalse("For adult in host - isKidFriendelyEligible() and return false",isKidFriendlyEligible);


        //Test 4: adult in url,but not in host part -- true
        webLink = BookmarkManager.getInstance().createWebLink(2003,"Search for Archived Web Pages","https://support.archive-it.org/hc/en-us/articles/360001231286-About-Archive-It-APIs-and-access-integrations","https://support.archive-it.org/hc/en-us/articles/360001231286-About-Archive-It-APIs-and-access-integrations");

        isKidFriendlyEligible=webLink.isKidFriendelyEligible();

        assertTrue("For adult in url,but not host part - isKidFriendelyEligible() and return false",isKidFriendlyEligible);


        //Test 5: adult in title only -- true
        webLink = BookmarkManager.getInstance().createWebLink(2004,"Public Library of Science","https://plos.org/","https://plos.org/");

        isKidFriendlyEligible=webLink.isKidFriendelyEligible();

        assertTrue("For adult in title - isKidFriendelyEligible() and return true",isKidFriendlyEligible);

    }

    @Override
    public boolean isKidFriendelyEligible() {
        return false;
    }

    @Override
    public String getItemData() {
        return null;
    }
}

