package easv.my_tunes.dal;

import easv.my_tunes.be.Playlist;
import easv.my_tunes.be.Song;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Playlists_SongsAccessObject {

    public void addSongToPlaylist(Playlist playlist, Song song) {
        try (Connection con = ConnectionManager.getConnection()){
            String sqlPrompt = "Insert Into playlist_songs (playlist_id, song_id) VALUES (?, ?)";
            PreparedStatement ps = con.prepareStatement(sqlPrompt);
            ps.setInt(1, playlist.getID());
            ps.setInt(2, song.getID());
            ps.execute();
        }
        catch (SQLException e){
            throw  new RuntimeException(e);
        }
    }

    public void deleteSong(Song song, Playlist playlist) {
        try (Connection con = ConnectionManager.getConnection()){
            String sqlPrompt = "delete from playlist_songs where id=?";
            PreparedStatement ps = con.prepareStatement(sqlPrompt);
            ps.setInt(1, song.getPlaylist_song_id());
            ps.execute();
        }
        catch (SQLException e){
            throw  new RuntimeException(e);
        }
    }

    public List<Song> getSongsOnPlaylist(Playlist playlist) {
        List<Song> songs = new ArrayList<>();
        try (Connection con = ConnectionManager.getConnection()){
            String sqlPrompt = "select playlist_songs.id as field_id, songs.id as song_id, songs.title as song_title, songs.artist as song_artist, songs.category as song_category, songs.time as song_time, songs.path as song_path from playlist_songs INNER JOIN songs on playlist_songs.song_id = songs.id where playlist_songs.playlist_id = ?";
            PreparedStatement ps = con.prepareStatement(sqlPrompt);
            ps.setInt(1, playlist.getID());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int field_id = rs.getInt("field_id");
                int song_id = rs.getInt("song_id");
                String song_title = rs.getString("song_title");
                String song_artist = rs.getString("song_artist");
                String song_category = rs.getString("song_category");
                int song_time = rs.getInt("song_time");
                String song_path = rs.getString("song_path");
                songs.add(new Song(song_id, song_title, song_artist, song_category, song_time, song_path, field_id));
            }
            return songs;
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
