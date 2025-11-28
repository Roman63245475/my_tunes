package easv.my_tunes.dal;

import easv.my_tunes.be.Song;

import java.io.File;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SongsAccessObject {

    public List<Song> getSongs(){
        List<Song> songs = new ArrayList<>();
        try (Connection con = ConnectionManager.getConnection()){
            String sqlPrompt = "SELECT * FROM songs";
            PreparedStatement pst = con.prepareStatement(sqlPrompt);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String artist = rs.getString("artist");
                String category = rs.getString("category");
                int time = rs.getInt("time");
                String path = rs.getString("path");
                songs.add(new Song(id, title, artist, category, time, path));
            }
            return songs;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveSong(String title, String artist, String category, int time, Path targetPath) {
        try (Connection con = ConnectionManager.getConnection()){
            String sqlPrompt = "Insert Into songs (title, artist, category, time, path) VALUES (?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sqlPrompt);
            pst.setString(1, title);
            pst.setString(2, artist);
            pst.setString(3, category);
            pst.setInt(4, time);
            pst.setString(5, targetPath.toString());
            pst.execute();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void editSong(String title, String artist, String category, Song obj) {
        try(Connection con = ConnectionManager.getConnection()){
            String sqlPrompt = "Update songs Set title=?, artist=?, category=? where id=?";
            PreparedStatement pst = con.prepareStatement(sqlPrompt);
            pst.setString(1, title);
            pst.setString(2, artist);
            pst.setString(3, category);
            pst.setInt(4, obj.getID());
            pst.execute();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteSong(Song song) {
        try (Connection con = ConnectionManager.getConnection()) {
            String sqlRel = "DELETE FROM playlist_songs WHERE song_id = ?";
            PreparedStatement psRel = con.prepareStatement(sqlRel);
            psRel.setInt(1, song.getID());
            psRel.execute();

            String sql = "DELETE FROM songs WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, song.getID());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}