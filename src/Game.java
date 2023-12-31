import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Game {

    JFrame window;
    Container con;
    JPanel titleNamePanel, startButtonPanel, mainTextPanel, choiceButtonPanel, playerPanel;
    JLabel titleNameLabel, hpLabel, hpLabelNumber, weaponLabel, weaponLabelName;
    Font titleFont = new Font("Times New Roman", Font.PLAIN, 90);
    Font normalFont = new Font("Times New Roman", Font.PLAIN, 28);
    JButton startButton, choice1, choice2, choice3, choice4;
    JTextArea mainTextArea;
    int  monsterHP, silverRing, playerHP;
    String  position, weapon;

    TitleScreenHandler tsHandler = new TitleScreenHandler();
    ChoiceHandler choiceHandler = new ChoiceHandler();





    public static void main(String[] args) {

        new Game();
    }

    public Game(){

        window = new JFrame();
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);
        window.setLayout(null);
        con = window.getContentPane();
        playRandomSoundtrack();

        titleNamePanel = new JPanel();
        titleNamePanel.setBounds(100, 100, 600, 150);
        titleNamePanel.setBackground(Color.black);
        titleNameLabel = new JLabel("New Arrival");
        titleNameLabel.setForeground(Color.white);
        titleNameLabel.setFont(titleFont);

        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(300, 400, 200, 100);
        startButtonPanel.setBackground(Color.black);

        startButton = new JButton("START");
        startButton.setBackground(Color.black);
        startButton.setForeground(Color.white);
        startButton.setFont(normalFont);
        startButton.addActionListener(tsHandler);
        startButton.setFocusPainted(false);

        titleNamePanel.add(titleNameLabel);
        startButtonPanel.add(startButton);

        con.add(titleNamePanel);
        con.add(startButtonPanel);

        window.setVisible(true);
    }

    // modify this later to play differently each time
    public void playRandomSoundtrack() {
        try {
            // array of soundtracks
            String[] soundtracks = {"music1.wav" /* other music files */};

            // choosing a random soundtrack, can change this to a logical vers that takes in soundtrack name later, easier for demo.
            Random random = new Random();
            String soundtrack = soundtracks[random.nextInt(soundtracks.length)];

            // open music file
            File soundFile = new File("./public/" + soundtrack); // Update with the correct path
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);

            // get a sound clip resource
            Clip clip = AudioSystem.getClip();

            // open audio input stream
            clip.open(audioIn);

            // loop continuously
            clip.loop(Clip.LOOP_CONTINUOUSLY);

            // start playing
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void createGameScreen(){
        titleNamePanel.setVisible(false);
        startButtonPanel.setVisible(false);

        mainTextPanel = new JPanel();
        mainTextPanel.setBounds(100, 100, 600, 250);
        mainTextPanel.setBackground(Color.black);
        con.add(mainTextPanel);
        mainTextArea = new JTextArea("main text area");
        mainTextArea.setBounds(100, 100, 600, 250);
        mainTextArea.setBackground(Color.black);
        mainTextArea.setForeground(Color.white);
        mainTextArea.setFont(normalFont);
        mainTextArea.setLineWrap(true);
        mainTextArea.setWrapStyleWord(true);
        mainTextArea.setEditable(false);

        mainTextPanel.add(mainTextArea);

        choiceButtonPanel = new JPanel();
        choiceButtonPanel.setBounds(250, 350, 300, 150);
        choiceButtonPanel.setBackground(Color.black);
        choiceButtonPanel.setLayout(new GridLayout(4,1));
        con.add(choiceButtonPanel);
        choice1 = new JButton("Choice 1");
        choice1.setBackground(Color.black);
        choice1.setForeground(Color.white);
        choice1.setFont(normalFont);
        choice1.setFocusPainted(false);
        choice1.addActionListener(choiceHandler);
        choice1.setActionCommand("c1");
        choiceButtonPanel.add(choice1);
        choice2 = new JButton("Choice 2");
        choice2.setBackground(Color.black);
        choice2.setForeground(Color.white);
        choice2.setFont(normalFont);
        choice2.setFocusPainted(false);
        choice2.addActionListener(choiceHandler);
        choice2.setActionCommand("c2");
        choiceButtonPanel.add(choice2);
        choice3 = new JButton("Choice 3");
        choice3.setBackground(Color.black);
        choice3.setForeground(Color.white);
        choice3.setFont(normalFont);
        choice3.setFocusPainted(false);
        choice3.addActionListener(choiceHandler);
        choice3.setActionCommand("c3");
        choiceButtonPanel.add(choice3);
        choice4 = new JButton("Choice 4");
        choice4.setBackground(Color.black);
        choice4.setForeground(Color.white);
        choice4.setFont(normalFont);
        choice4.setFocusPainted(false);
        choice4.addActionListener(choiceHandler);
        choice4.setActionCommand("c4");
        choiceButtonPanel.add(choice4);

//		choice4.setContentAreaFilled(false);  // Disable highlighting on press!!!


        playerPanel = new JPanel();
        playerPanel.setBounds(100, 15, 600, 50);
        playerPanel.setBackground(Color.black);
        playerPanel.setLayout(new GridLayout(1,4));
        con.add(playerPanel);
        hpLabel = new JLabel("HP:");
        hpLabel.setFont(normalFont);
        hpLabel.setForeground(Color.white);
        playerPanel.add(hpLabel);
        hpLabelNumber = new JLabel();
        hpLabelNumber.setFont(normalFont);
        hpLabelNumber.setForeground(Color.white);
        playerPanel.add(hpLabelNumber);
        weaponLabel = new JLabel("Weapon:");
        weaponLabel.setFont(normalFont);
        weaponLabel.setForeground(Color.white);
        weaponLabel.setBackground(Color.red);
        playerPanel.add(weaponLabel);
        weaponLabelName = new JLabel();
        weaponLabelName.setFont(normalFont);
        weaponLabelName.setForeground(Color.white);
        playerPanel.add(weaponLabelName);

        playerSetup();

    }

    Character player = new Character("Player", 15, "Knife");
    public void playerSetup(){

        monsterHP = 20;
        weapon = "Knife";
        playerHP = player.getHP();
        weaponLabelName.setText(weapon);
        hpLabelNumber.setText("" + playerHP);

        townGate();
    }


    public void townGate(){
        position = "townGate";
        mainTextArea.setText("You arrive at the gate of a town. \nA guard is standing in front of you. \n\nWhat do you do?");
        choice1.setText("Approach the guard");
        choice2.setText("Attack the guard");
        choice3.setText("Turn back around");
        choice4.setText("");
    }
    public void talkGuard(){
        position = "talkGuard";
        mainTextArea.setText("Guard: Hello stranger. You must be new here. \nThe gate is currently closed due to a monster roaming around nearby \n I'm sorry but we cannot let you in at this time.");
        choice1.setText(">");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }
    public void attackGuard(){
        position = "attackGuard";
        mainTextArea.setText("Guard: Hey don't be stupid!\n\nThe guard fought back and hit you hard.\n(You take 3 damage)");
        player.setHP(playerHP-=3);
        hpLabelNumber.setText(""+playerHP);
        choice1.setText(">");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }
    public void crossRoad(){
        position = "crossRoad";
        mainTextArea.setText("You are at a crossroad.\nIf you go south, you will go back to the town.");
        choice1.setText("Go north");
        choice2.setText("Go east");
        choice3.setText("Go south, back to town");
        choice4.setText("Go west");
    }
    public void north(){
        position = "north";
        mainTextArea.setText("There is a river. \nYou drink the water and rest at the riverside. \n\n(Your HP is recovered by 2)");
        playerHP = playerHP + 2;
        hpLabelNumber.setText(""+playerHP);
        choice1.setText("Go south");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }
    public void east(){
        position = "east";
        mainTextArea.setText("You walked into a forest and found a Greatsword!\n\n(You obtained a Greatword)");
        weapon = "Greatsword";
        weaponLabelName.setText(weapon);
        choice1.setText("Go west");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");

    }

    public void bush() {
        position = "bush";
        mainTextArea.setText("You stumble across a rustling bush");
        choice1.setText("Reach into the bush");
        choice2.setText("Head back");
        choice3.setText("");
        choice4.setText("");
    }
    public void west(){
        position = "west";
        mainTextArea.setText("A goblin jumps out and prepares to fight you");
        choice1.setText("Fight back");
        choice2.setText("Run");
        choice3.setText("");
        choice4.setText("");
    }
    public void fight(){
        position = "fight";
        mainTextArea.setText("Monster HP: " + monsterHP + "\n\nWhat do you do?");
        choice1.setText("Attack");
        choice2.setText("Run");
        choice3.setText("");
        choice4.setText("");
    }
    public void playerAttack(){
        position = "playerAttack";

        int playerDamage = 0;

        if(weapon.equals("Knife")){
            playerDamage = new java.util.Random().nextInt(3);
        }
        else if(weapon.equals("Greatsword")){
            playerDamage = new java.util.Random().nextInt(3, 13);
        }

        mainTextArea.setText("You attacked the monster and dealt " + playerDamage + " damage!");

        monsterHP = monsterHP - playerDamage;

        choice1.setText(">");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }
    public void monsterAttack(){
        position = "monsterAttack";

        int monsterDamage = 0;

        monsterDamage = new java.util.Random().nextInt(6);

        mainTextArea.setText("The monster attacked you and dealt " + monsterDamage + " damage!");

        playerHP = playerHP - monsterDamage;
        hpLabelNumber.setText(""+playerHP);

        choice1.setText(">");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }
    public void win(){
        position = "win";

        mainTextArea.setText("You defeated the monster!\nThe monster dropped a ring!\n\n(You obtained a Silver Ring)");

        silverRing = 1;

        choice1.setText("Go east");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");

    }
    public void lose(){
        position = "lose";

        mainTextArea.setText("You have been slain!\n\nGAME OVER");

        choice1.setText("");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
        choice1.setVisible(false);
        choice2.setVisible(false);
        choice3.setVisible(false);
        choice4.setVisible(false);
    }
    public void ending(){
        position = "ending";

        mainTextArea.setText("Guard:  You killed the goblin?\n*You show the silver ring* \nWOW! You really did. Thanks to you we can open our gates back up, please come in. \n\n YOU HAVE WON");
        choice1.setText("");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
        choice1.setVisible(false);
        choice2.setVisible(false);
        choice3.setVisible(false);
        choice4.setVisible(false);
    }




    public class TitleScreenHandler implements ActionListener{

        public void actionPerformed(ActionEvent event){

            createGameScreen();
        }
    }


    public class ChoiceHandler implements ActionListener{

        public void actionPerformed(ActionEvent event){

            String yourChoice = event.getActionCommand();

            switch(position){
                case "townGate":
                    switch(yourChoice){
                        case "c1":
                            if(silverRing==1){
                                ending();
                            }
                            else{
                                talkGuard();
                            }
                            break;
                        case "c2": attackGuard();break;
                        case "c3": crossRoad();break;
                    }
                    break;
                case "talkGuard":
                    switch(yourChoice){
                        case "c1": townGate(); break;
                    }
                    break;
                case "attackGuard":
                    switch(yourChoice){
                        case "c1":
                            if(playerHP <1 ){
                                lose();
                            }
                            else{
                                townGate();
                            }
                            break;
                    }
                    break;
                case "crossRoad":
                    switch(yourChoice){
                        case "c1": north(); break;
                        case "c2": east();break;
                        case "c3": townGate(); break;
                        case "c4": bush();break;
                    }
                    break;
                case "north":
                    switch(yourChoice){
                        case "c1": crossRoad(); break;
                    }
                    break;
                case "east":
                    switch(yourChoice){
                        case "c1": crossRoad(); break;
                    }
                    break;
                case "bush":
                    switch(yourChoice){
                        case "c1": west(); break;
                        case "c2": crossRoad(); break;
                    }
                    break;
                case "west":
                    switch(yourChoice){
                        case "c1": fight(); break;
                        case "c2": crossRoad(); break;
                    }
                    break;
                case "fight":
                    switch(yourChoice){
                        case "c1": playerAttack();break;
                        case "c2": crossRoad(); break;
                    }
                    break;
                case "playerAttack":
                    switch(yourChoice){
                        case "c1":
                            if(monsterHP <1 ){
                                win();
                            }
                            else{
                                monsterAttack();
                            }
                            break;
                    }
                    break;
                case "monsterAttack":
                    switch(yourChoice){
                        case "c1":
                            if(playerHP <1 ){
                                lose();
                            }
                            else{
                                fight();
                            }
                            break;
                    }
                    break;
                case "win":
                    switch(yourChoice){
                        case "c1": crossRoad();
                    }
                    break;

            }


        }
    }

}
