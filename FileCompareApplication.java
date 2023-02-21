package com.example.filecompare;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;


public class FileCompareApplication extends Application {


    public Path pathFile1 = null;
    public Path pathFile2 = null;

    public String file = "";
    public String file1Name;
    public String file2Name;

    public String file1Context;
    public String file2Context;

    TextArea file1Text = new TextArea(null);
    TextArea file2Text = new TextArea(null);


    TextArea differenceFile1 = new TextArea();
    TextArea differenceFile2 = new TextArea();

    public FileChooser chooser = new FileChooser();
    public File chosenFile;


    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("File Compare");

        // Header
        FileInputStream input = new FileInputStream("headingTitle.png");
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);                             /* Setting Image*/


        HBox upperHBox = new HBox();
        upperHBox.setAlignment(Pos.CENTER);
        upperHBox.setSpacing(40);
        VBox upperHBoxVBox1 = new VBox();
        upperHBoxVBox1.setAlignment(Pos.CENTER);
        upperHBoxVBox1.setSpacing(10);
        Label file1Label = new Label("File 1");
        file1Label.setStyle("-fx-text-fill: white; -fx-font-size: 20");
        Pane textArea1 = new Pane();
        Label paneLabel1 = new Label("");
        paneLabel1.setStyle("-fx-text-fill: white; -fx-font-size: 18");
        paneLabel1.setPadding(new Insets(30, 30, 30,30));
        textArea1.setMinSize(350, 200);
        textArea1.getChildren().add(paneLabel1);
        textArea1.setStyle("-fx-background-radius: 40; -fx-border-radius: 40; -fx-background-color: #EC9156");
        HBox hBoxButton1 = new HBox();
        hBoxButton1.setPadding(new Insets(0,0,0,10));
        hBoxButton1.setAlignment(Pos.CENTER_RIGHT);
        Button uploadFile1Button = new Button("Upload File");
        uploadFile1Button.setMinSize(120, 25);
        uploadFile1Button.setStyle("-fx-background-radius: 10; -fx-text-fill: white; -fx-background-color: #4E62A9");
        hBoxButton1.getChildren().add(uploadFile1Button);

        upperHBoxVBox1.getChildren().addAll(file1Label, textArea1, hBoxButton1);

        VBox upperHBoxVBox2 = new VBox();
        upperHBoxVBox2.setAlignment(Pos.CENTER);
        upperHBoxVBox2.setSpacing(10);
        Label file2Label = new Label("File 2");
        file2Label.setStyle("-fx-text-fill: white; -fx-font-size: 20");
        Pane textArea2 = new Pane();
        Label paneLabel2 = new Label("");
        paneLabel2.setStyle("-fx-text-fill: white; -fx-font-size: 18");
        paneLabel2.setPadding(new Insets(30, 30, 30,30));
        textArea2.setMinSize(350, 200);
        textArea2.getChildren().add(paneLabel2);
        textArea2.setStyle("-fx-background-radius: 40; -fx-border-radius: 40; -fx-background-color: #EC9156");
        HBox hBoxButton2 = new HBox();
        hBoxButton2.setPadding(new Insets(0,0,0,10));
        hBoxButton2.setAlignment(Pos.CENTER_RIGHT);
        Button uploadFile2Button = new Button("Upload File");
        uploadFile2Button.setMinSize(120, 25);
        uploadFile2Button.setStyle("-fx-background-radius: 10; -fx-text-fill: white; -fx-background-color: #4E62A9");
        hBoxButton2.getChildren().add(uploadFile2Button);

        upperHBoxVBox2.getChildren().addAll(file2Label, textArea2, hBoxButton2);

        upperHBox.getChildren().addAll(upperHBoxVBox1, upperHBoxVBox2);


        Button compareFiles = new Button("Compare Files");
        compareFiles.setStyle("-fx-background-radius: 10; -fx-text-fill: white; -fx-background-color: #8C52FF");
        compareFiles.setMinSize(740, 30);


        HBox lowerHBox = new HBox();
        lowerHBox.setAlignment(Pos.CENTER);
        lowerHBox.setSpacing(40);

        VBox lowerHBoxVBox1 = new VBox();
        lowerHBoxVBox1.setAlignment(Pos.CENTER);
        lowerHBoxVBox1.setSpacing(10);
        Label similarities = new Label("Similarities");
        similarities.setStyle("-fx-text-fill: white; -fx-font-size: 20");
        Pane textArea3 = new Pane();
        Label paneLabel3 = new Label("");
        paneLabel3.setStyle("-fx-text-fill: white; -fx-font-size: 18");
        paneLabel3.setPadding(new Insets(30, 30, 30,30));
        textArea3.setMinSize(350, 200);
        textArea3.getChildren().add(paneLabel3);
        textArea3.setStyle("-fx-background-radius: 40; -fx-border-radius: 40; -fx-background-color: #FF25F6");
        hBoxButton1.setPadding(new Insets(0,0,0,10));
        hBoxButton1.setAlignment(Pos.CENTER_RIGHT);

        lowerHBoxVBox1.getChildren().addAll(similarities, textArea3);

        VBox lowerHBoxVBox2 = new VBox();
        lowerHBoxVBox2.setAlignment(Pos.CENTER);
        lowerHBoxVBox2.setSpacing(10);
        Label differences = new Label("Differences");
        differences.setStyle("-fx-text-fill: white; -fx-font-size: 20");
        Pane textArea4 = new Pane();
        Label paneLabel4 = new Label("");
        paneLabel4.setStyle("-fx-text-fill: white; -fx-font-size: 18");
        paneLabel4.setPadding(new Insets(30, 30, 30,30));
        textArea4.setMinSize(350, 200);
        textArea4.getChildren().add(paneLabel4);
        textArea4.setStyle("-fx-background-radius: 40; -fx-border-radius: 40; -fx-background-color: #19FAA9");

        lowerHBoxVBox2.getChildren().addAll(differences, textArea4);

        lowerHBox.getChildren().addAll(lowerHBoxVBox1, lowerHBoxVBox2);

        // Setting the image view parameters
//        imageView.setX(10);
//        imageView.setY(10);
//        imageView.setFitWidth(575);
//        imageView.setPreserveRatio(true);

        // Buttons




//        SplitPane splitPane = new SplitPane();
//
//        VBox leftVBox = new VBox(file1Text, getFile1, differenceFile1);
//        StackPane leftSide = new StackPane(leftVBox);
//
//        VBox rightVBox = new VBox(file2Text, getFile2, differenceFile2, compareFiles);
//        StackPane rightSide = new StackPane(rightVBox);
//
//        splitPane.getItems().addAll(leftSide, rightSide);
//
//        AnchorPane pane = new AnchorPane();
//        AnchorPane.setTopAnchor(splitPane, 15.0);
//        AnchorPane.setRightAnchor(splitPane, 15.0);
//        AnchorPane.setBottomAnchor(splitPane, 15.0);
//        AnchorPane.setLeftAnchor(splitPane, 15.0);
//        pane.getChildren().addAll(splitPane);

//        // Customizing GridPane
//        GridPane gridpane = new GridPane();
//        gridpane.setAlignment(Pos.TOP_CENTER);
//        gridpane.setPadding(new Insets(20, 20, 20, 20));
//        gridpane.setHgap(20);
//        gridpane.setVgap(20);



        //Allows user to add two files, using getFile to get file path
        uploadFile1Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                chosenFile = chooser.showOpenDialog(primaryStage);
                pathFile1 = Path.of(String.valueOf(chosenFile));
                file1Name = String.valueOf(chosenFile);

                file1Context =Methods.readFile(file1Name);
                paneLabel1.setText(file1Context);
            }
        });

        uploadFile2Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                chosenFile = chooser.showOpenDialog(primaryStage);
                pathFile2 = Path.of(String.valueOf(chosenFile));
                file2Name = String.valueOf(chosenFile);

                file2Context =Methods.readFile(file2Name);
                paneLabel2.setText(file2Context);
            }
        });


        //Compare files and displays difference by showing the lines that are different
        compareFiles.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ArrayList<String> similarities = Methods.similaritiesOrDifferences(file1Name, file2Name, 0);
                ArrayList<String> differences = Methods.similaritiesOrDifferences(file1Name, file2Name, 1);

                StringBuilder similar = new StringBuilder();
                StringBuilder difference = new StringBuilder();
                for (String same:
                     similarities) {
                    similar.append(same).append("\n");
                }

                for (String dif:
                        differences) {
                    difference.append(dif).append("\n");
                }

                paneLabel3.setText(similar.toString());
                paneLabel4.setText(difference.toString());
            }
        });

        // Setting the Scene
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(25);
        vBox.setPadding(new Insets(80, 80, 80, 80));
        vBox.setStyle("-fx-background-color: #1B152E");
        vBox.getChildren().addAll(imageView, upperHBox, compareFiles, lowerHBox);

        primaryStage.setScene(new Scene(vBox, 800, 800));
        primaryStage.setTitle("Displaying Image");
        primaryStage.show();

        // Getting Style Sheet
        //scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());


        primaryStage.show();
    }


    public static void main(String[] args) {
        launch();
    }

}