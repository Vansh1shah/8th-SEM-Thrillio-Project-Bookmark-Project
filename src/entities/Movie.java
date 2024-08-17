package entities;

import constants.MovieGenre;

import java.util.Arrays;

public abstract class Movie extends Bookmark {
    private int releaseYear;
    private String[] caast;
    private String[] directors;
    private MovieGenre genre;


    private double imdbRating;

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String[] getCaast() {
        return caast;
    }

    public void setCaast(String[] caast) {
        this.caast = caast;
    }

    public String[] getDirectors() {
        return directors;
    }

    public void setDirectors(String[] directors) {
        this.directors = directors;
    }


    public MovieGenre getGenre() {
        return genre;
    }

    public void setGenre(MovieGenre genre) {
        this.genre = genre;
    }



    public double getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(double imdbRating) {
        this.imdbRating = imdbRating;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "releaseYear=" + releaseYear +
                ", caast=" + Arrays.toString(caast) +
                ", directors=" + Arrays.toString(directors) +
                ", genre='" + genre + '\'' +
                ", imdbRating=" + imdbRating +
                '}';
    }

    public boolean isKidFriendlyEligible() {
        if (genre.equals(MovieGenre.HORROR) || genre.equals(MovieGenre.THRILLERS)) {
            return false;
        }
        return true;
    }

}
