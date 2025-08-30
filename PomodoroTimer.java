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

    private int timer = 25*60;
    private int shortBreakTime = 5*60;
    private int longBreakTime = 15*60;

    public PomodoroTimer(){
        this.setSize(400,400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setLocationRelativeTo(null);


        // Time display
        this.time = new JLabel("25:00", JLabel.CENTER);
        this.time.setBounds(100,70,200,60);
        this.time.setFont(new Font("Arial", Font.BOLD, 48));
        this.add(this.time);

        // Start button
        this.start = new JButton("Start");
        this.start.setBounds(150,150,100,40);
        this.start.setFont(new Font("Arial", Font.BOLD, 16));
        this.start.setBackground(new Color(76,175,80));
        this.start.setForeground(Color.BLACK);
        this.add(this.start);

        // Other buttons
        this.pause = new JButton("Pause");
        this.pause.setBounds(50, 200, 80, 30);
        this.add(this.pause);
        
        this.reset = new JButton("Reset");
        this.reset.setBounds(150, 200, 80, 30);
        this.add(this.reset);
        
        this.short_break = new JButton("Short Break");
        this.short_break.setBounds(250, 200, 100, 30);
        this.add(this.short_break);
        
        this.long_break = new JButton("Long Break");
        this.long_break.setBounds(50, 250, 100, 30);
        this.add(this.long_break);

        // Action listeners
        this.start.addActionListener(this);
        this.reset.addActionListener(this);
        this.pause.addActionListener(this);
        this.short_break.addActionListener(this);
        this.long_break.addActionListener(this);

        

        // Timer
        this.swingTimer = new javax.swing.Timer(1000, e -> {
    if(timer > 0){
        timer--;
        updateTime();
    }
    else{
        swingTimer.stop();
        isRunning = false;
        start.setText("Start");
        start.setBackground(new Color(76,175,80));
        JOptionPane.showMessageDialog(PomodoroTimer.this, isBreak ? "Break finished!": "Pomodoro finished!");
    }
});

        this.setVisible(true);
    }

    private void updateTime(){
        int minutes = timer/60;
        int seconds = timer%60;
        time.setText(String.format("%02d:%02d", minutes, seconds));
    }

    private void switchToPomodoro(){
        swingTimer.stop();
        isRunning = false;
        isBreak = false;
        timer = 25*60;
        updateTime();
        start.setText("Start");
        start.setBackground(new Color(76,175,80));
        
    }

    private void switchToShortBreak(){
        swingTimer.stop();
        isRunning = false;
        isBreak = true;
        timer = shortBreakTime;
        updateTime();
        start.setText("Start");
        start.setBackground(new Color(76,175,80));
        
    }

    private void switchToLongBreak(){
        swingTimer.stop();
        isRunning = false;
        isBreak = true;
        timer = longBreakTime;
        updateTime();
        start.setText("Start");
        start.setBackground(new Color(76,175,80));
        

    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == start){
            if(!isRunning){
                swingTimer.start();
                isRunning = true;
                start.setText("Pause");
                start.setBackground(new Color(33,150,243));
            }
            else{
                swingTimer.stop();
                isRunning = false;
                start.setText("Start");
                start.setBackground(new Color(76,175,80));
            }
        }
        else if(e.getSource() == reset){
            if(isBreak) {
                switchToPomodoro();
                
            } else {
                timer = 25*60;
                updateTime();
                swingTimer.stop();
                isRunning= false;
                start.setText("Start");
                start.setBackground(new Color(76,175,80));
            }
        }
        else if(e.getSource() == pause){
            if(isRunning){
                swingTimer.stop();
                isRunning = false;
                start.setText("Start");
                start.setBackground(new Color(76,175,80));
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
