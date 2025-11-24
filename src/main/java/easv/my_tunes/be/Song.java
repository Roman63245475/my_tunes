package easv.my_tunes.be;

public class Song {
    private int id;
    private String title;
    private String artist;
    private String category;
    private double time;
    private String path;

    public Song(int id, String title, String artist, String category, double time,  String path) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.category = category;
        this.time = time;
        this.path = path;
    }

    public int getId() {
        return id;
    }
}