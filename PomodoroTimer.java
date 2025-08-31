import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PomodoroTimer extends JFrame implements ActionListener {

    private javax.swing.Timer swingTimer;
    private JButton start;
    private JButton reset;
    private JButton pause;
    private JButton short_break;
    private JButton long_break;
    private JLabel time;


    private boolean isRunning = false;
    private boolean isBreak = false;

    private int currentTimer = 25*60;
    private int shortBreakTime = 5*60;
    private int longBreakTime = 15*60;

    private final Color PASTEL_GREEN= new Color(183,228,199);
    private final Color PASTEL_YELLOW = new Color(255,236,179);
    private final Color PASTEL_RED = new Color(255,175,175);
    private final Color PASTEL_BLUE= new Color(173,216,230);
    private final Color PASTEL_PURPLE = new Color(216,191,216);
    private final Color DARK_TEXT = new Color(50,50,50);

    public PomodoroTimer(){
        this.setSize(400,400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setLocationRelativeTo(null);

        this.getContentPane().setBackground(new Color(245,245,245));

        // Time display
        this.time = new JLabel("25:00", JLabel.CENTER);
        this.time.setBounds(100,70,200,60);
        this.time.setFont(new Font("Arial", Font.BOLD, 48));
        this.time.setOpaque(true);
        this.time.setBackground(Color.WHITE);
        this.time.setForeground(DARK_TEXT);
        this.add(this.time);

        // Start button
        this.start = new JButton("Start");
        this.start.setBounds(150,150,100,40);
        this.start.setFont(new Font("Arial", Font.BOLD, 16));
        this.start.setBackground(PASTEL_GREEN);
        this.start.setForeground(DARK_TEXT);
        this.start.setOpaque(true);
        this.add(this.start);

        // Other buttons
        this.pause = new JButton("Pause");
        this.pause.setBounds(50, 200, 80, 30);
        this.pause.setBackground(PASTEL_YELLOW);
        this.pause.setForeground(DARK_TEXT);
        this.pause.setOpaque(true);
        this.add(this.pause);
        
        this.reset = new JButton("Reset");
        this.reset.setBounds(150, 200, 80, 30);
        this.reset.setBackground(PASTEL_RED);
        this.reset.setForeground(DARK_TEXT);
        this.reset.setOpaque(true);
        this.add(this.reset);
        
        this.short_break = new JButton("Short Break");
        this.short_break.setBounds(250, 200, 100, 30);
        this.short_break.setBackground(PASTEL_BLUE);
        this.short_break.setForeground(DARK_TEXT);
        this.short_break.setOpaque(true);
        this.add(this.short_break);
        
        this.long_break = new JButton("Long Break");
        this.long_break.setBounds(50, 250, 100, 30);
        this.long_break.setBackground(PASTEL_PURPLE);
        this.long_break.setForeground(DARK_TEXT);
        this.long_break.setOpaque(true);
        this.add(this.long_break);

        // Action listeners
        this.start.addActionListener(this);
        this.reset.addActionListener(this);
        this.pause.addActionListener(this);
        this.short_break.addActionListener(this);
        this.long_break.addActionListener(this);

        

        // Timer
        this.swingTimer = new javax.swing.Timer(1000, e -> {
    if(currentTimer > 0){
        currentTimer--;
        updateTime();
    }
    else{
        swingTimer.stop();
        isRunning = false;
        start.setText("Start");
        start.setBackground(PASTEL_GREEN);
        JOptionPane.showMessageDialog(PomodoroTimer.this, isBreak ? "Break finished!": "Pomodoro finished!");
    }
});

        this.setVisible(true);
    }



    private void updateTime(){
        int minutes = currentTimer/60;
        int seconds = currentTimer%60;
        time.setText(String.format("%02d:%02d", minutes, seconds));
    }

    private void switchToPomodoro(){
        swingTimer.stop();
        isRunning = false;
        isBreak = false;
        currentTimer = 25*60;
        updateTime();
        start.setText("Start");
        start.setBackground(PASTEL_GREEN);
        
    }

    private void switchToShortBreak(){
        swingTimer.stop();
        isRunning = false;
        isBreak = true;
        currentTimer = shortBreakTime;
        updateTime();
        start.setText("Start");
        start.setBackground(PASTEL_GREEN);
        
    }

    private void switchToLongBreak(){
        swingTimer.stop();
        isRunning = false;
        isBreak = true;
        currentTimer = longBreakTime;
        updateTime();
        start.setText("Start");
        start.setBackground(PASTEL_GREEN);
        

    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == start){
            if(!isRunning){
                swingTimer.start();
                isRunning = true;
                start.setText("Pause");
                start.setBackground(PASTEL_YELLOW);
            }
            else{
                swingTimer.stop();
                isRunning = false;
                start.setText("Start");
                start.setBackground(PASTEL_GREEN);
            }
        }
        else if(e.getSource() == reset){
            if(isBreak) {
                switchToPomodoro();
                
            } else {
                currentTimer = 25*60;
                updateTime();
                swingTimer.stop();
                isRunning= false;
                start.setText("Start");
                start.setBackground(PASTEL_GREEN);
            }
        }
        else if(e.getSource() == pause){
            if(isRunning){
                swingTimer.stop();
                isRunning = false;
                start.setText("Start");
                start.setBackground(PASTEL_GREEN);
            }
        }
        else if(e.getSource() == short_break){
            switchToShortBreak();
        }
        else if(e.getSource() == long_break){
            switchToLongBreak();
        }
    }

    public static void main(String[]args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PomodoroTimer();
            }
        });
    }
}
