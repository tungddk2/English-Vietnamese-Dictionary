package org.twoguys.engdictionaryapp;

import com.jfoenix.controls.JFXButton;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class DictionaryController implements Initializable {
    @FXML
    private AnchorPane menuPane, searchPane;

    @FXML
    private TextField searchTextField;

    @FXML
    private Text foundWord;

    @FXML
    private ListView suggestedWordList, allWordList;

    @FXML
    private WebView searchedWordInfo;

    @FXML
    private VBox miniMenuPane;

    @FXML
    private JFXButton menuClose, menuExtend, searchMiniButton, searchLargeButton, soundButton;

    //private final double MINI_MENU_WIDTH = menuPane.getWidth();
    //private final double MINI_MENU_HEIGHT = menuPane.getWidth();

    DictionaryManagement dictionaryManagement;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        menuPane.toBack();
        menuPane.setTranslateX(-600);
        searchPane.toFront();

        dictionaryManagement = new DictionaryManagement();
        dictionaryManagement.init(searchedWordInfo);
        dictionaryManagement.loadWordListView(allWordList);
        allWordList.setVisible(true);
        allWordList.toFront();
    }

    public void onMenuExtendClicked(MouseEvent event) {
        System.out.println(menuPane.getTranslateX());
        if (menuPane.getTranslateX() != -600) return;
        System.out.println("Extend");
        miniMenuPane.setVisible(false);

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), menuPane);
        fadeTransition.setFromValue(0.5);
        fadeTransition.setToValue(1);
        fadeTransition.play();

        TranslateTransition movePane = new TranslateTransition(Duration.seconds(0.5), menuPane);
        movePane.setByX(+600);
        movePane.play();
        menuPane.setTranslateX(0);
        menuPane.toFront();
    }

    public void onMenuCloseClicked(MouseEvent event) {
        System.out.println(menuPane.getTranslateX());
        if (menuPane.getTranslateX() != 0) return;
        System.out.println("Close");

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), menuPane);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0.5);
        fadeTransition.play();

        TranslateTransition movePane = new TranslateTransition(Duration.seconds(0.5), menuPane);
        movePane.setByX(-600);
        movePane.play();
        menuPane.setTranslateX(-600);
        menuPane.toBack();
        miniMenuPane.setVisible(true);
    }

    public void onMiniSearchButtonClicked(MouseEvent e) {
        System.out.println("Mini Search");
        searchPane.toFront();
    }

    public void onLargeSearchButtonClicked(MouseEvent e) {
        System.out.println("Big Search");
        System.out.println(menuPane.getTranslateX());
        if (menuPane.getTranslateX() != 0) return;
        System.out.println("Close");

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), menuPane);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();

        TranslateTransition movePane = new TranslateTransition(Duration.seconds(0.5), menuPane);
        movePane.setByX(-600);
        movePane.play();
        menuPane.setTranslateX(-600);
        menuPane.toBack();
        miniMenuPane.setVisible(true);
        searchPane.toFront();
    }

    public void onEnterSearchField(KeyEvent e) {
        dictionaryManagement.loadWordListView(suggestedWordList, searchTextField.getCharacters().toString());
        allWordList.toBack();
        suggestedWordList.toFront();
    }

    public void onAllWordListClicked(MouseEvent e) {
        //if (!allWordList.getItems().isEmpty() && !allWordList.getSelectionModel().isEmpty()) {
        ObservableList selectedWord = allWordList.getSelectionModel().getSelectedItems();
        dictionaryManagement.setSearchedWordInfo(selectedWord.get(0).toString());
        searchedWordInfo.toFront();
        foundWord.setText(selectedWord.get(0).toString());
        //}
    }

    public void onSuggestedWordListClicked(MouseEvent e) {
        ObservableList selectedWord = suggestedWordList.getSelectionModel().getSelectedItems();
        dictionaryManagement.setSearchedWordInfo(selectedWord.get(0).toString());
        searchedWordInfo.toFront();
        foundWord.setText(selectedWord.get(0).toString());
    }

    public void onSoundButtonClicked() {
        //dictionaryManagement.playSoundWord(foundWord.getText());
    }
}