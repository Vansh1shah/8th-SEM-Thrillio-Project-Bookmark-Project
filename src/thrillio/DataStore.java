package thrillio;

import constants.BookGenre;
import constants.Gender;
import constants.MovieGenre;
import constants.UserType;
import entities.*;
import managers.UserManager;
import util.IOUtil;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.DoubleStream;

import static java.lang.Long.*;


public class DataStore {

    private static List<User> users = new ArrayList<>();

    public static List<User> getUsers() {
        return users;
    }

    private static List<List<Bookmark>>  bookmarks = new ArrayList<>();

    public static List<List<Bookmark>> getBookmarks() {
        return bookmarks;
    }

    private static List<UserBookmark> userBookmarks = new ArrayList();
    private static int bookmarkIndex;



    public static void loadData() {
        /*loadUsers();
        loadWebLinks();
        loadMovies();
        loadBooks();*/

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jid_thrillio?useSSL=false","postgres","postgres");
             Statement stmt = conn.createStatement();) {
            loadUsers(stmt);
            loadWebLinks(stmt);
            loadMovies(stmt);
            loadBooks(stmt);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    private static void loadUsers(Statement stmt) throws SQLException {
        String query = "SELECT \"id\", email, password, first_name, last_name, gender_id, user_type_id FROM \"User\"";

        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            long id = rs.getLong("id");
            String email = rs.getString("email");
            String password = rs.getString("password");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            int gender_id = rs.getInt("gender_id");
            Gender gender = Gender.values()[gender_id];
            int user_type_id = rs.getInt("user_type_id");
            UserType userType = UserType.values()[user_type_id];

            User user = UserManager.getInstance().createUser(id, email, password, firstName, lastName, gender, String.valueOf(userType));
            users.add(user);
        }
    }

    private static void loadWebLinks(Statement stmt) throws SQLException {
        String query = "Select * from WebLink";
        ResultSet rs = stmt.executeQuery(query);

         List<Bookmark> bookmarkList = new ArrayList<>();
         while (rs.next()) {
             long id = rs.getLong("id");
             String title = rs.getString("title");
             String url = rs.getString("url");
             String host = rs.getString("host");

            Bookmark bookmark = BookmarkManager.getInstance().createWebLink(id,title,url,host);
            bookmarkList.add(bookmark);
        }

        bookmarks.add(bookmarkList);
    }

    private static void loadMovies(Statement stmt) throws SQLException {
        String query = "SELECT m.id, title, release_year, ARRAY_TO_STRING(ARRAY_AGG(DISTINCT a.name), ','), ARRAY_TO_STRING(ARRAY_AGG(DISTINCT d.name), ',') AS directors, movie_genre_id, imdb_rating FROM Movie m JOIN Movie_Actor ma ON m.id = ma.movie_id JOIN Actor a ON ma.actor_id = a.id JOIN Movie_Director md ON m.id = md.movie_id JOIN Director d ON md.director_id = d.id GROUP BY m.id, title, release_year, movie_genre_id, imdb_rating";

        ResultSet rs = stmt.executeQuery(query);

        List<Bookmark> bookmarkList = new ArrayList<>();

        while (rs.next()) {
            long id = rs.getLong("id");
            String title = rs.getString("title");
            int releaseYear = rs.getInt("release_year");
            String[] cast = rs.getString(4).split(","); // Fetch the fourth column
            String[] directors = rs.getString("directors").split(",");
            int genre_id = rs.getInt("movie_genre_id");
            MovieGenre genre = MovieGenre.values()[genre_id];
            double imdbRating = rs.getDouble("imdb_rating");

            Bookmark bookmark = BookmarkManager.getInstance().createMovie(id, title, "", releaseYear, cast, directors, genre, imdbRating/*, values[7]*/);
            bookmarkList.add(bookmark);
        }
        bookmarks.add(bookmarkList);
    }


    private static void loadBooks(Statement stmt) throws SQLException {
        String query = "SELECT b.id, title, publication_year, p.name, STRING_AGG(a.name, ',') AS authors, book_genre_id, amazon_rating, created_date FROM Book b JOIN Publisher p ON b.publisher_id = p.id JOIN Book_Author ba ON b.id = ba.book_id JOIN Author a ON ba.author_id = a.id GROUP BY b.id, title, publication_year, p.name, book_genre_id, amazon_rating, created_date";

        ResultSet rs = stmt.executeQuery(query);

        List<Bookmark> bookmarkList = new ArrayList<>();
        while (rs.next()) {
            long id = rs.getLong("id");
            String title = rs.getString("title");
            int publicationYear = rs.getInt("publication_year");
            String publisher = rs.getString("name");
            String[] authors = rs.getString("authors").split(",");
            int genre_id = rs.getInt("book_genre_id");
            BookGenre genre = BookGenre.values()[genre_id];
            double amazonRating = rs.getDouble("amazon_rating");

            Date createdDate = rs.getDate("created_date");
            System.out.println("createdDate: " + createdDate);
            Timestamp timeStamp = rs.getTimestamp(8);
            System.out.println("timeStamp: " + timeStamp);
            System.out.println("localDateTime: " + timeStamp.toLocalDateTime());

            System.out.println("id: " + id + ", title: " + title + ", publication year: " + publicationYear + ", publisher: " + publisher + ", authors: " + String.join(", ", authors) + ", genre: " + genre + ", amazonRating: " + amazonRating);

            Bookmark bookmark = BookmarkManager.getInstance().createBook(id, title, publicationYear, publisher, authors, genre, amazonRating/*, values[7]*/);
            bookmarkList.add(bookmark);
        }
        bookmarks.add(bookmarkList);
    }

    public static void add(UserBookmark userBookmark) {
        userBookmarks.add(userBookmark);
    }
}