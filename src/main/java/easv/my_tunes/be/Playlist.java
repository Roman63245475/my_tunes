package easv.my_tunes.be;

import java.util.List;

public class Playlist {
    private int id;
    private String name;
    private List<Song> songs;

    public Playlist(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addSong(Song song) {
        songs.add(song);
    }
}