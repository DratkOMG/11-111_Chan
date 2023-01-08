package com.example.controllers.multi;

import com.example.gameplay.DeckOfCards;
import com.example.gameplay.MyDeck;
import com.example.models.Card;
import com.example.sockets.multi.Client2;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ClientController2 implements Initializable {
    private static final String nextTurn = "next turn";
    private static final String reverseTurn = "reverse turn";
    @FXML
    public HBox seeTheFuture;
    @FXML
    public Text playerName0;
    @FXML
    public VBox themDeck0;
    @FXML
    public ImageView themExploding0;
    @FXML
    public VBox notificationVBox;
    @FXML
    public Text playerName1;
    @FXML
    public HBox themDeck1;
    @FXML
    public ImageView themExploding1;
    @FXML
    public Text myName;
    @FXML
    public Button readyButton;
    @FXML
    public AnchorPane apMain;
    @FXML
    public Button countCard;
    public VBox themDeck2;
    public ImageView themExploding2;
    public Text playerName2;
    @FXML
    public ImageView cardsPlayed;
    @FXML
    public Text danger;
    @FXML
    public Button btn_send;
    @FXML
    public TextField tf_mess;
    @FXML
    public ScrollPane sp_chat;
    @FXML
    public VBox vb_mess;
    @FXML
    private Button closeButton;
    @FXML
    public HBox previewCard;
    @FXML
    private ImageView deckImageViews;
    @FXML
    private ImageView imageView;
    @FXML
    private HBox hBoxMyDeck;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Button nextCardBtn;
    @FXML
    private ImageView isExploding;
    private static volatile DeckOfCards deckOfCards;
    private static MyDeck myDeck;

    private static Client2 client;
    private Card explodingCard;

    private DeckOfCards deckOfCards2;
    private static DeckOfCards listCardPlayed = DeckOfCards.builder()
            .deck(new ArrayList<>())
            .build();
    private static String setMyDeck;
    private static String toPlayer;

    private static List<String> listPlayer = new ArrayList<>();

    private static String username;
    private static String showMyName;

    public static void setDeckOfCard(DeckOfCards deckOfCards1) {
        deckOfCards = deckOfCards1;
    }

    public static void setThemDeckHBox(MyDeck themDeck, HBox hBoxThemDeck) {
        Platform.runLater(() -> {
            if (themDeck == null) {
                hBoxThemDeck.getChildren().clear();
            } else {
                hBoxThemDeck.getChildren().clear();
                List<Card> myCardList = themDeck.getMyCardList();
                for (int i = 0; i < myCardList.size(); i++) {
                    Image image;
                    try {
                        image = new Image(new FileInputStream("src/main/resources/DoneCards/Back Of Card.png"));
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    ImageView imageView = new ImageView(image);
                    imageView.setFitHeight(95);
                    imageView.setFitWidth(70);

                    Button button = new Button();
                    Card card = myCardList.get(i);
                    int finalI = i;
                    button.setOnAction(event -> {
                        showCard(card);
                    });
                    button.setMinHeight(95);
                    button.setMinWidth(70);
                    button.setOpacity(0.3);

                    button.setStyle("-fx-background-color: transparent; -fx-text-fill: black;");
                    button.setOnMouseEntered(event3 -> button.setStyle("-fx-background-color: #32b2de; -fx-text-fill: black;"));
                    button.setOnMouseExited(event4 -> button.setStyle("-fx-background-color: transparent; -fx-text-fill: black;"));


                    Pane pane = new Pane();
                    pane.getChildren().add(imageView);
//                    pane.getChildren().add(button);
                    hBoxThemDeck.getChildren().add(pane);
                }
            }
        });
    }

    public static void setThemDeckVBox(MyDeck themDeck, VBox vBoxThemDeck) {
        Platform.runLater(() -> {
            if (themDeck == null) {
                vBoxThemDeck.getChildren().clear();
            } else {
                vBoxThemDeck.getChildren().clear();
                List<Card> myCardList = themDeck.getMyCardList();
                for (int i = 0; i < myCardList.size(); i++) {
                    Image image = null;
                    try {
                        image = new Image(new FileInputStream("src/main/resources/DoneCards/Back Of Card.png"));
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    ImageView imageView = new ImageView(image);
                    imageView.setFitHeight(95);
                    imageView.setFitWidth(70);

                    Button button = new Button();
                    Card card = myCardList.get(i);
                    int finalI = i;
                    button.setOnAction(event -> {
                        showCard(card);
                    });
                    button.setMinHeight(95);
                    button.setMinWidth(70);
                    button.setOpacity(0.3);

                    button.setStyle("-fx-background-color: transparent; -fx-text-fill: black;");
                    button.setOnMouseEntered(event3 -> button.setStyle("-fx-background-color: #32b2de; -fx-text-fill: black;"));
                    button.setOnMouseExited(event4 -> button.setStyle("-fx-background-color: transparent; -fx-text-fill: black;"));


                    Pane pane = new Pane();
                    pane.getChildren().add(imageView);
//                    pane.getChildren().add(button);
                    vBoxThemDeck.getChildren().add(pane);
                }
            }
        });
    }

    public static void setThemExploding(Boolean withoutExploding, ImageView themExploding) {
        if (!withoutExploding) {
            try {
                themExploding.setImage(new Image(new FileInputStream("src/main/resources/DoneCards/Exploding Kittens.png")));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            themExploding.setImage(null);
        }
    }

    public static void setMyDeck(MyDeck myDeck1, DeckOfCards deckOfCards1) {
        myDeck = myDeck1;
        deckOfCards = deckOfCards1;
    }

    public static void setPlayerName(String playerName, Text playerNameText) {
        playerNameText.setText(playerName);
    }

    public static void setCardsPlayed(DeckOfCards deckOfCards, ImageView cardsPlayed) {
        listCardPlayed = deckOfCards;
        cardsPlayed.setImage(listCardPlayed.getTopCard().getImage());
    }

    public static void setListPlayer(String username, List<String> listPlayer) {
        listPlayer.add(username);
    }

    public static void usedFavorToYou(String username, String notification, Text danger) {
        danger.setText(notification);
        toPlayer = username;
    }

    public static void receiveCard(Card card) {
        myDeck.addCard(card);
        client.updateMyDeckForEveryone(setMyDeck, myDeck);

    }

    public static void removePlayer(String username, List<String> listPlayer) {
        listPlayer.remove(username);
    }

    public static void usedAttackToYou(String notification, Text danger) {
        danger.setText(notification);
    }

    public static void addLabel(String msgFromChat, VBox vb_mess) {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setPadding(new Insets(5, 5, 5, 10));

        Text text = new Text(msgFromChat);
        TextFlow textFlow = new TextFlow(text);

        textFlow.setStyle("-fx-background-color: rgb(233,233,235);" +
                "-fx-background-radius: 20px");
        textFlow.setPadding(new Insets(5, 10, 5, 10));

        hBox.getChildren().add(textFlow);

        Platform.runLater(() -> {
            vb_mess.getChildren().add(hBox);
        });
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String imageUrl = "src/main/resources/DoneCards/wallpaper.png";
        BackgroundImage backgroundImage;
        try {
            backgroundImage = new BackgroundImage(
                    new Image(new FileInputStream(imageUrl)),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT,
                    new BackgroundSize(100, 100, true, true, true, true));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Background background = new Background(backgroundImage);
        apMain.setBackground(background);
        apMain.widthProperty().addListener((observable, oldValue, newValue) -> {
            apMain.setPrefWidth(newValue.doubleValue());
        });
        apMain.heightProperty().addListener((observable, oldValue, newValue) -> {
            apMain.setPrefHeight(newValue.doubleValue());
        });
        danger.setText(null);

        countCard.setMinHeight(95);
        countCard.setMinWidth(70);
        countCard.setOpacity(0.3);
        countCard.setStyle("-fx-background-color: transparent; -fx-text-fill: black;");
        countCard.setOnAction(event -> {
            showCountCard(deckOfCards);
        });
        System.out.println("xin moi");
        Scanner scanner = new Scanner(System.in);
        username = scanner.nextLine();
        try {
            client = new Client2(new Socket("localhost", 27), username);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        client.acceptNewClient();

        closeButton.setVisible(false);

        vb_mess.heightProperty().addListener((observableValue, numberOldValue, numberNewValue) -> {
            sp_chat.setVvalue((Double) numberNewValue);
        });

        client.listenForMess(nextCardBtn, themDeck0, themDeck1, themDeck2,
                themExploding0, themExploding1, themExploding2,
                playerName0, playerName1, playerName2,
                cardsPlayed, listPlayer, danger, vb_mess);

        btn_send.setOnAction(event -> {
            String messToSend = tf_mess.getText();
            if (!messToSend.isEmpty()) {
                HBox hBox = new HBox();
                hBox.setAlignment(Pos.CENTER_RIGHT);
                hBox.setPadding(new Insets(5, 5, 5, 10));

                Text text = new Text(messToSend);
                TextFlow textFlow = new TextFlow(text);
                textFlow.setStyle("-fx-color: rgb(0,0,0);" +
                        "-fx-background-color: rgb(237,251,220);" +
                        "-fx-background-radius: 20px");
                textFlow.setPadding(new Insets(5, 10, 5, 10));
                text.setFill(Color.color(0, 0, 0));

                hBox.getChildren().add(textFlow);
                vb_mess.getChildren().add(hBox);
                client.sendMess("chat", messToSend);
                tf_mess.clear();
            }
        });

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        deckOfCards2 = deckOfCards;
        showMyDeck(myDeck);
        myName.setText(username);
        setMyDeck = "set deck " + username + " for everyone";
        client.updateMyDeckForEveryone(setMyDeck, ClientController2.myDeck);

        showMyName = "show name " + username + " for everyone";

        client.showMyNameForEveryone(showMyName, username);
        deckImageViews.setImage(deckOfCards.getBackOfCard());

        hBoxMyDeck.widthProperty().addListener(((observableValue, number, t1) -> {
            scrollPane.setHvalue((Double) t1);
        }));

        readyButton.setOnAction(event -> {
            client.showMyNameForEveryone(showMyName, username);
            client.updateMyDeckForEveryone(setMyDeck, myDeck);
            apMain.getChildren().remove(readyButton);
            client.updateListPlayer("update list player", username);
        });

        nextCardBtn.setOnAction(event -> {
            seeTheFuture.getChildren().clear();
            previewCard.getChildren().clear();
            if (deckOfCards.checkOneTopCard().getFaceName().equals("Exploding Kittens")) {
                explodingCard = deckOfCards.checkOneTopCard();
                isExploding.setImage(deckOfCards.dealTopCard().getImage());
                client.setMyExplodingForAnyone("bom huhu", isExploding.getImage() == null);

                nextCardBtn.setVisible(false);
                if (myDeck.withoutDefuse()) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("You are loser");
                    alert.showAndWait();
                    if (deckOfCards.size() == 0) {
                        deckOfCards.getDeck().add(deckOfCards2.getDefuseCard());
                    }
                    client.nextTurn(nextTurn);
                    client.showMyNameForEveryone(showMyName, "LOSE");
                    client.updateCommonDeckForEveryone("update deck", deckOfCards);
                    client.removePlayer("remove player", username);
                    Stage primaryStage = (Stage) closeButton.getScene().getWindow();
                    primaryStage.close();
                    client.close();
                }

            } else if (deckOfCards.size() == 1 || deckOfCards.getDeck() == null) {
                addImageButton(deckOfCards.dealTopCard());
                deckImageViews.setImage(null);
                showMyDeck(myDeck);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("You are winner!");
                alert.showAndWait();
                closeButton.setVisible(true);
            } else if (!danger.getText().isBlank()) {
                danger.setText(null);
                addImageButton(deckOfCards.dealTopCard());
                showMyDeck(myDeck);
                client.updateMyDeckForEveryone(setMyDeck, myDeck);

            } else {
                addImageButton(deckOfCards.dealTopCard());
                showMyDeck(myDeck);
                nextCardBtn.setVisible(false);

                client.updateCommonDeckForEveryone("update deck", deckOfCards);
                client.nextTurn(nextTurn);
                client.updateMyDeckForEveryone(setMyDeck, myDeck);
            }
        });

        closeButton.setOnAction(event -> {
            Stage primaryStage = (Stage) closeButton.getScene().getWindow();
            primaryStage.close();
            client.close();
        });
    }

    private void actionForCardInMyDeck(Card cardInMyDeck, int itThis, MyDeck myDeck) {
        showMyDeck(myDeck);
        ImageView imageView = new ImageView(cardInMyDeck.getImage());
        setFitHeightAndWidthForImageView(imageView);

        Button button1 = new Button();
        setANiceButton(button1);

        button1.setOnAction(event -> {
            showInformation(cardInMyDeck);
        });

        Pane pane = new Pane();
        pane.getChildren().add(imageView);
        pane.getChildren().add(button1);

        ScrollPane scrollPane1 = new ScrollPane();
        scrollPane1.setFitToWidth(true);
        scrollPane1.setStyle("-fx-border-color: transparent;\n" +
                "    -fx-background-color: transparent;");
        VBox vBox = new VBox();


        if (isExploding.getImage() != null) {
            if (cardInMyDeck.getFaceName().equals("Defuse")) {
                Button button2 = new Button("Use");
                button2.setOnAction(event -> {
                    cardsPlayed.setImage(cardInMyDeck.getImage());
                    listCardPlayed.addCard(cardInMyDeck);
                    client.updateListCardPlayed("update cards played", listCardPlayed);
                    Text text = new Text("Put Exploding Kitten in the deck!");

                    TextField textField = new TextField();

                    Button button = new Button("Put");
                    button.setOnAction(event1 -> {
                        int index = Integer.parseInt(textField.getText());

                        if (index < 0 || index > deckOfCards.size() + 1) {
                            deckOfCards.getDeck().add(0, explodingCard);
                        } else {
                            deckOfCards.getDeck().add(deckOfCards.getDeck().size() - (index - 1), explodingCard);
                        }


                        client.updateCommonDeckForEveryone("update deck", deckOfCards);
                        client.nextTurn(nextTurn);
                        client.updateMyDeckForEveryone(setMyDeck, ClientController2.myDeck);

                        notificationVBox.getChildren().clear();
                    });

                    notificationVBox.getChildren().addAll(text, textField, button);
                    isExploding.setImage(null);

                    client.setMyExplodingForAnyone("bom huhu", isExploding.getImage() == null);

                    ClientController2.myDeck.remove(cardInMyDeck, itThis);
                    showMyDeck(ClientController2.myDeck);
                    previewCard.getChildren().clear();


                });
                vBox.getChildren().add(button2);
            }

        } else if (cardInMyDeck.getHaveFunction() && nextCardBtn.isVisible()) {
            Button button2 = new Button("Use");
            ChoiceBox<String> choiceBox = new ChoiceBox<>();

            if (cardInMyDeck.getFaceName().equals("Favor")) {
                choiceBox.getItems().addAll(listPlayer);
                choiceBox.setValue(listPlayer.get(0));
                vBox.getChildren().addAll(button2, choiceBox);
            } else {
                vBox.getChildren().add(button2);
            }

            button2.setOnAction(event -> {
                if (cardInMyDeck.getFaceName().equals("Shuffle")) {
                    seeTheFuture.getChildren().clear();
                    deckOfCards.shuffle();
                }

                if (cardInMyDeck.getFaceName().equals("Favor")) {
                    if (choiceBox.getValue() != null) {
                        seeTheFuture.getChildren().clear();
                        client.playerUsedFavor("use favor", choiceBox.getValue());
                    }
                }

                if (cardInMyDeck.getFaceName().equals("Attack")) {
                    danger.setText(null);
                    seeTheFuture.getChildren().clear();
                    nextCardBtn.setVisible(false);
                    client.playerUsedAttack("use attack");
                    client.nextTurn(nextTurn);
                }

                if (cardInMyDeck.getFaceName().equals("Self Slap")) {

                    if (deckOfCards.checkOneTopCard().getFaceName().equals("Exploding Kittens")) {
                        explodingCard = deckOfCards.checkOneTopCard();
                        isExploding.setImage(deckOfCards.dealTopCard().getImage());
                        client.setMyExplodingForAnyone("bom huhu", isExploding.getImage() == null);

                        nextCardBtn.setVisible(false);
                        if (myDeck.withoutDefuse()) {
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("You are loser");
                            alert.showAndWait();
                            if (deckOfCards.size() == 0) {
                                deckOfCards.getDeck().add(deckOfCards2.getDefuseCard());
                            }
                            client.nextTurn(nextTurn);
                            client.showMyNameForEveryone(showMyName, "LOSE");
                            client.updateCommonDeckForEveryone("update deck", deckOfCards);
                            client.removePlayer("remove player", username);
                            Stage primaryStage = (Stage) closeButton.getScene().getWindow();
                            primaryStage.close();
                            client.close();
                        }
                    } else {
                        addImageButton(deckOfCards.dealTopCard());
                    }

                    seeTheFuture.getChildren().clear();
                }

                if (cardInMyDeck.getFaceName().equals("Skip")) {
                    if (danger.getText().equals("used attack to you")) {
                        danger.setText(null);
                    } else {
                        nextCardBtn.setVisible(false);
                        client.nextTurn(nextTurn);
                    }
                    seeTheFuture.getChildren().clear();
                }

                if (cardInMyDeck.getFaceName().equals("Super Skip")) {
                    danger.setText(null);
                    nextCardBtn.setVisible(false);
                    client.nextTurn(nextTurn);
                    seeTheFuture.getChildren().clear();
                }

                if (cardInMyDeck.getFaceName().equals("Reverse")) {
                    if (danger.getText().equals("used attack to you")) {
                        danger.setText(null);
                        client.reverseTurn(reverseTurn);
                    } else {
                        nextCardBtn.setVisible(false);
                        client.reverseTurn(reverseTurn);
                        client.nextTurn(nextTurn);
                    }

                    seeTheFuture.getChildren().clear();
                }

                if (cardInMyDeck.getFaceName().equals("Draw From The Bottom")) {
                    if (danger.getText().equals("used attack to you")) {
                        danger.setText(null);
                        if (deckOfCards.checkOneBotCard().getFaceName().equals("Exploding Kittens")) {
                            explodingCard = deckOfCards.checkOneBotCard();
                            isExploding.setImage(deckOfCards.dealBotCard().getImage());
                            client.setMyExplodingForAnyone("bom huhu", isExploding.getImage() == null);

                            nextCardBtn.setVisible(false);
                            if (myDeck.withoutDefuse()) {
                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setTitle("You are loser");
                                alert.showAndWait();
                                if (deckOfCards.size() == 0) {
                                    deckOfCards.getDeck().add(deckOfCards2.getDefuseCard());
                                }
                                client.nextTurn(nextTurn);
                                client.showMyNameForEveryone(showMyName, "LOSE");
                                client.updateCommonDeckForEveryone("update deck", deckOfCards);
                                client.removePlayer("remove player", username);
                                Stage primaryStage = (Stage) closeButton.getScene().getWindow();
                                primaryStage.close();
                                client.close();
                            }
                        } else {
                            addImageButton(deckOfCards.dealBotCard());
                        }

                    } else {
                        if (deckOfCards.checkOneBotCard().getFaceName().equals("Exploding Kittens")) {
                            explodingCard = deckOfCards.checkOneBotCard();
                            isExploding.setImage(deckOfCards.dealBotCard().getImage());
                            client.setMyExplodingForAnyone("bom huhu", isExploding.getImage() == null);

                            nextCardBtn.setVisible(false);
                            if (myDeck.withoutDefuse()) {
                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setTitle("You are loser");
                                alert.showAndWait();
                                if (deckOfCards.size() == 0) {
                                    deckOfCards.getDeck().add(deckOfCards2.getDefuseCard());
                                }
                                client.nextTurn(nextTurn);
                                client.showMyNameForEveryone(showMyName, "LOSE");
                                client.updateCommonDeckForEveryone("update deck", deckOfCards);
                                client.removePlayer("remove player", username);
                                Stage primaryStage = (Stage) closeButton.getScene().getWindow();
                                primaryStage.close();
                                client.close();
                            }
                        } else {
                            nextCardBtn.setVisible(false);
                            addImageButton(deckOfCards.dealBotCard());
                            client.nextTurn(nextTurn);
                        }

                    }

                    seeTheFuture.getChildren().clear();
                }

                if (cardInMyDeck.getFaceName().startsWith("See The Future")) {
                    seeTheFuture.getChildren().clear();
                    Button buttonClear3Card = new Button("Done");
                    buttonClear3Card.setOnAction(eventClear -> {
                        seeTheFuture.getChildren().clear();
                    });

                    for (int i = 0; i < Integer.parseInt(cardInMyDeck.getFaceName().substring(16)); i++) {
                        ImageView imageViews;
                        try {
                            imageViews = new ImageView(deckOfCards.getDeck().get(deckOfCards.size() - 1 - i).getImage());
                            setFitHeightAndWidthForImageView(imageViews);
                            seeTheFuture.getChildren().add(imageViews);
                        } catch (IndexOutOfBoundsException ignored) {
                        }
                    }
                    seeTheFuture.getChildren().add(buttonClear3Card);
                }

                if (cardInMyDeck.getFaceName().equals("Swap Top And Bottom")) {
                    seeTheFuture.getChildren().clear();
                    Card top = deckOfCards.getTopCard();
                    deckOfCards.getDeck().set(deckOfCards.size() - 1, deckOfCards.getBotCard());
                    deckOfCards.getDeck().set(0, top);
                }

                ClientController2.myDeck.remove(cardInMyDeck, itThis);
                showMyDeck(ClientController2.myDeck);
                client.updateCommonDeckForEveryone("update deck", deckOfCards);
                previewCard.getChildren().clear();
                cardsPlayed.setImage(cardInMyDeck.getImage());
                listCardPlayed.addCard(cardInMyDeck);
                client.updateListCardPlayed("update cards played", listCardPlayed);
                client.updateMyDeckForEveryone(setMyDeck, ClientController2.myDeck);
            });


        } else if (danger.getText().endsWith("used favor to you")) {
            Button button2 = new Button("Give");
            vBox.getChildren().add(button2);
            button2.setOnAction(event -> {
                client.giveMyCardToPlayer("give card", cardInMyDeck, toPlayer);
                ClientController2.myDeck.remove(cardInMyDeck, itThis);
                previewCard.getChildren().clear();
                showMyDeck(ClientController2.myDeck);
                client.updateMyDeckForEveryone(setMyDeck, ClientController2.myDeck);
                danger.setText(null);
            });
        }

        scrollPane1.setContent(vBox);

        previewCard.getChildren().setAll(pane, scrollPane1);
    }

    private void showMyDeck(MyDeck myDeck) {
        hBoxMyDeck.getChildren().clear();
        List<Card> myCardList = myDeck.getMyCardList();
        for (int i = 0; i < myCardList.size(); i++) {
            Image image = myCardList.get(i).getImage();
            imageView = new ImageView(image);
            setFitHeightAndWidthForImageView(imageView);


            Button button = new Button();
            setANiceButton(button);


            Card cardToUse = myCardList.get(i);
            int finalI = i;
            button.setOnAction(event -> {
                actionForCardInMyDeck(cardToUse, finalI, myDeck);
            });


            Pane pane = new Pane();
            pane.getChildren().add(imageView);
            pane.getChildren().add(button);
            hBoxMyDeck.getChildren().add(pane);
        }
    }

    private void addImageButton(Card cardInMyDeck) {
        myDeck.addCard(cardInMyDeck);
    }

    private static void showInformation(Card deckOfCards) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(deckOfCards.getFaceName());
        alert.setTitle("Card Information");

        TextArea textArea = new TextArea(deckOfCards.getCardInformation());
        textArea.setEditable(false);
        textArea.setWrapText(false);
        alert.getDialogPane().setContent(textArea);
        alert.showAndWait();
    }

    private static void showCountCard(DeckOfCards deckOfCards) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(String.valueOf(deckOfCards.size()));
        alert.setTitle("Count Card");

        alert.showAndWait();
    }

    private static void showCard(Card card) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(card.toString());
        alert.setTitle("Card");

        alert.showAndWait();
    }

    private static void setANiceButton(Button button) {
        button.setMinHeight(95);
        button.setMinWidth(70);
        button.setOpacity(0.3);

        button.setStyle("-fx-background-color: transparent; -fx-text-fill: black;");
        button.setOnMouseEntered(event3 -> button.setStyle("-fx-background-color: #32b2de; -fx-text-fill: black;"));
        button.setOnMouseExited(event4 -> button.setStyle("-fx-background-color: transparent; -fx-text-fill: black;"));
    }

    private void setFitHeightAndWidthForImageView(ImageView imageView) {
        imageView.setFitHeight(95);
        imageView.setFitWidth(70);
    }

}
