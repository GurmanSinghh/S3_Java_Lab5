package com.example.lab5;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GameGUI extends Application {

    public static final String BLANK = "";

    GridPane gridPane;
    Button insertGameButton, insertPlayerButton, mapPlayerGameButton, updatePlayerInfoButton, displayReportButton,
            insertPlayer, insertGame, mapPlayerGame;
    Label playerIdLabel, firstNameLabel, lastNameLabel, addressLabel, postalCodeLabel, provinceLabel, phoneNumberLabel,
            gameTitleLabel, gamePlayedOnLabel, scoreLabel, selectPlayerLabel, selectGameLabel;
    TextField playerIdField, firstNameField, lastNameField, addressField, postalCodeField, provinceField,
            phoneNumberField, gameTitleField, scoreField;
    DatePicker playedDate;
    ComboBox<Player> playerCb;
    ComboBox<Game> gameCb;

    @Override
    public void start(Stage stage) {
        stage.setTitle("GameApp");

        gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));

        insertPlayerButton = new Button("INSERT PLAYER");
        insertGameButton = new Button("INSERT GAME");
        mapPlayerGameButton = new Button("MAP PLAYER & GAME");
        updatePlayerInfoButton = new Button("UPDATE PLAYER");
        displayReportButton = new Button("DISPLAY REPORT");

        gridPane.add(insertPlayerButton, 0, 0);
        gridPane.add(insertGameButton, 0, 1);
        gridPane.add(mapPlayerGameButton, 0, 2);
        gridPane.add(updatePlayerInfoButton, 0, 3);
        gridPane.add(displayReportButton, 0, 4);

        insertPlayerButton.setOnAction(actionEvent -> {
            GridPane grid = new GridPane();
            grid.setAlignment(Pos.CENTER);
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(25, 25, 25, 25));

            firstNameLabel = new Label("First Name:");
            grid.add(firstNameLabel, 0, 0);

            firstNameField = new TextField();
            grid.add(firstNameField, 1, 0);

            lastNameLabel = new Label("Last Name:");
            grid.add(lastNameLabel, 0, 1);

            lastNameField = new TextField();
            grid.add(lastNameField, 1, 1);

            addressLabel = new Label("Address:");
            grid.add(addressLabel, 0, 2);

            addressField = new TextField();
            grid.add(addressField, 1, 2);

            postalCodeLabel = new Label("Postal Code:");
            grid.add(postalCodeLabel, 0, 3);

            postalCodeField = new TextField();
            grid.add(postalCodeField, 1, 3);

            provinceLabel = new Label("Province:");
            grid.add(provinceLabel, 0, 4);

            provinceField = new TextField();
            grid.add(provinceField, 1, 4);

            phoneNumberLabel = new Label("Phone Number:");
            grid.add(phoneNumberLabel, 0, 5);

            phoneNumberField = new TextField();
            grid.add(phoneNumberField, 1, 5);

            insertPlayer = new Button("INSERT PLAYER");
            grid.add(insertPlayer, 1, 6);

            Scene insertPlayerScene = new Scene(grid, 600, 600);
            Stage insertPlayerWindow = new Stage();
            insertPlayerWindow.setScene(insertPlayerScene);
            insertPlayerWindow.setTitle("Insert Player");
            insertPlayerWindow.show();

            insertPlayer.setOnAction(ae -> {
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String address = addressField.getText();
                String postalCode = postalCodeField.getText();
                String province = provinceField.getText();
                String phoneNumber = phoneNumberField.getText();

                Player player = new Player(firstName, lastName, address, postalCode, province, phoneNumber);
                insertPlayerInfo(player);
                insertPlayerWindow.close();
            });
        });

        insertGameButton.setOnAction(actionEvent -> {
            GridPane grid = new GridPane();
            grid.setAlignment(Pos.CENTER);
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(25, 25, 25, 25));

            gameTitleLabel = new Label("Game Title");
            grid.add(gameTitleLabel, 0, 0);

            gameTitleField = new TextField();
            grid.add(gameTitleField, 1, 0);

            insertGame = new Button("INSERT GAME");
            grid.add(insertGame, 1, 1);

            Scene insertGameScene = new Scene(grid, 400, 300);
            Stage insertGameWindow = new Stage();
            insertGameWindow.setScene(insertGameScene);
            insertGameWindow.setTitle("Insert Game");
            insertGameWindow.show();

            insertGame.setOnAction(ae -> {
                String gameTitle = gameTitleField.getText();
                Game game = new Game(gameTitle);
                insertGameInfo(game);
                insertGameWindow.close();
            });

        });

        mapPlayerGameButton.setOnAction(actionEvent -> {
            GridPane grid = new GridPane();
            grid.setAlignment(Pos.CENTER);
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(25, 25, 25, 25));

            selectPlayerLabel = new Label("Select a player");
            grid.add(selectPlayerLabel, 0, 0);

            //Map<Integer, String> playersInfoMap = getAllPlayersInfo();
            List<Player> players = getAllPlayersInfo();

            playerCb = new ComboBox<Player>();
			/*for (Integer playerId : playerIds) {
				playerCb.getItems().add(playerId);
			}*/
			/*for (Integer key: playersInfoMap.keySet()) {
				playerCb.getItems().add(key+"-"+playersInfoMap.get(key));
			}*/
            for (Player player: players) {
                playerCb.getItems().add(player);
            }

            grid.add(playerCb, 1, 0);

            selectGameLabel = new Label("Select a game");
            grid.add(selectGameLabel, 0, 1);

            List<Game> games = getAllGames();

            gameCb = new ComboBox<Game>();
			/*for (Integer gameId : gameIds) {
				gameCb.getItems().add(gameId);
			}*/
            for (Game game: games) {
                gameCb.getItems().add(game);
            }
            grid.add(gameCb, 1, 1);

            gamePlayedOnLabel = new Label("Game Played On");
            grid.add(gamePlayedOnLabel, 0, 2);

            playedDate = new DatePicker();
            grid.add(playedDate, 1, 2);

            scoreLabel = new Label("Score");
            grid.add(scoreLabel, 0, 3);

            scoreField = new TextField();
            grid.add(scoreField, 1, 3);

            mapPlayerGame = new Button("MAP PLAYER & GAME");
            grid.add(mapPlayerGame, 1, 4);

            Scene insertGameScene = new Scene(grid, 600, 600);
            Stage insertGameWindow = new Stage();
            insertGameWindow.setScene(insertGameScene);
            insertGameWindow.setTitle("Insert Game");
            insertGameWindow.show();

            mapPlayerGame.setOnAction(ae -> {
                String gameTitle = gameTitleField.getText();
                Game game = new Game(gameTitle);
                insertGameInfo(game);

                //String playerId = (String) playerCb.getSelectionModel().getSelectedItem();
                //String gameId = (String) gameCb.getSelectionModel().getSelectedItem();

                Player selectedPlayer = playerCb.getSelectionModel().getSelectedItem();
                Game selectedGame = gameCb.getSelectionModel().getSelectedItem();

                LocalDate playedOn = playedDate.getValue();
                String score = scoreField.getText();

                PlayerGame playerGame = new PlayerGame(selectedGame.getGameId(), selectedPlayer.getPlayerId(), playedOn,
                        Integer.parseInt(score));
                insertPlayerGameInfo(playerGame);
            });

        });

        Scene scene = new Scene(gridPane, 300, 300);
        stage.setScene(scene);

        stage.show();
    }

    private void insertPlayerGameInfo(PlayerGame playerGame) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Database.getDBConnection();
            connection.setAutoCommit(false);
            String query = "INSERT INTO PLAYER_AND_GAME VALUES (?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(query);
            statement.setInt(1, getNextPlayerGameId());
            statement.setInt(2, playerGame.getGameId());
            statement.setInt(3, playerGame.getPlayerId());
            statement.setDate(4, java.sql.Date.valueOf(playerGame.getPlayingDate()));
            statement.setInt(5, playerGame.getScore());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != statement) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (null != connection) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private int getNextPlayerGameId() {
        int nextGameId = 0;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Database.getDBConnection();
            connection.setAutoCommit(false);
            String query = "SELECT MAX(PLAYER_GAME_ID) FROM PLAYER_AND_GAME";
            statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                nextGameId = rs.getInt(1) + 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != statement) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (null != connection) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return nextGameId;

    }

    private List<Game> getAllGames() {
        List<Game> games = new ArrayList<Game>();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Database.getDBConnection();
            connection.setAutoCommit(false);
            String query = "SELECT * FROM GAME";
            statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            Game game = null;
            while (rs.next()) {
                game = new Game();
                game.setGameId(rs.getInt(1));
                game.setGameTitle(rs.getString(2));
                games.add(game);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != statement) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (null != connection) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return games;
    }

    private List<Player> getAllPlayersInfo() {
        List<Player> players = new ArrayList<Player>();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Database.getDBConnection();
            connection.setAutoCommit(false);
            String query = "SELECT * FROM PLAYER";
            statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            Player player = null;
            while (rs.next()) {
                player = new Player(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
                player.setPlayerId(rs.getInt(1));
                players.add(player);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != statement) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (null != connection) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return players;
    }

    private void insertGameInfo(Game game) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Database.getDBConnection();
            connection.setAutoCommit(false);
            String query = "INSERT INTO GAME VALUES (?, ?)";
            statement = connection.prepareStatement(query);
            statement.setInt(1, getNextGameId());
            statement.setString(2, game.getGameTitle());
            int count = statement.executeUpdate();
            if (count == 1) {
                this.alert("Success", "Game Information inserted to DB successfully", AlertType.INFORMATION);
            } else {
                this.alert("Failure", "Some error while adding Game Information", AlertType.ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != statement) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (null != connection) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private int getNextGameId() {
        int nextGameId = 0;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Database.getDBConnection();
            connection.setAutoCommit(false);
            String query = "SELECT MAX(GAME_ID) FROM GAME";
            statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                nextGameId = rs.getInt(1) + 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != statement) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (null != connection) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return nextGameId;

    }

    private void insertPlayerInfo(Player player) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Database.getDBConnection();
            connection.setAutoCommit(false);
            String query = "INSERT INTO PLAYER VALUES (?, ?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(query);
            statement.setInt(1, getNextPlayerId());
            statement.setString(2, player.getFirstName());
            statement.setString(3, player.getLastName());
            statement.setString(4, player.getAddress());
            statement.setString(5, player.getPostalCode());
            statement.setString(6, player.getProvince());
            statement.setString(7, player.getPhoneNumber());
            int count = statement.executeUpdate();
            if (count == 1) {
                this.alert("Success", "Player Information inserted to DB successfully", AlertType.INFORMATION);
            } else {
                this.alert("Failure", "Some error while adding Player Information", AlertType.ERROR);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != statement) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (null != connection) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private int getNextPlayerId() {
        int nextPlayerId = 0;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Database.getDBConnection();
            connection.setAutoCommit(false);
            String query = "SELECT MAX(PLAYER_ID) FROM PLAYER";
            statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                nextPlayerId = rs.getInt(1) + 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != statement) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (null != connection) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return nextPlayerId;

    }

    // Will show alert messages
    public void alert(String title, String message, AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Executon starts from here
    public static void main(String[] args) {
        launch(args);
    }
}
