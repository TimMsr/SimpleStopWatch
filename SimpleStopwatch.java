import javax.swing.*;

public class SimpleStopwatch1 {
    public static void main(String[] args) {
        StopwatchUI stopwatch = new StopwatchUI(400, 200);
        stopwatch.display();
    }
}

class StopwatchTimer {
    private long startTime = 0;
    private long curTime = 0;
    private Timer timer;
    private boolean running = false;

    public StopwatchTimer(JLabel timeLabel) {
        timer = new Timer(10, e -> {
            if (running) {
                long currentTime = System.currentTimeMillis();
                long totalElapsed = curTime + (currentTime - startTime);
                int minutes = (int) (totalElapsed / 60000);
                int seconds = (int) (totalElapsed / 1000) % 60;
                int milliseconds = (int) (totalElapsed % 1000);
                timeLabel.setText(String.format("%02d:%02d:%03d", minutes, seconds, milliseconds));
            }
        });
    }

    public void start() {
        if (!running) {
            running = true;
            startTime = System.currentTimeMillis();
            timer.start();
        }
    }

    public void stop() {
        if (running) {
            curTime += System.currentTimeMillis() - startTime;
            running = false;
            timer.stop();
        }
    }

    public void reset(JLabel timeLabel) {
        timer.stop();
        running = false;
        curTime = 0;
        timeLabel.setText("00:00:000");
    }
}

class StopwatchUI {
    private final int width;
    private final int height;

    public StopwatchUI(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void display() {
        //set up frame
        JFrame frame = new JFrame("Stopwatch");
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        

        //Main Panel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); 
        frame.add(panel);

        //Time
        JLabel timeLabel = new JLabel("00:00:000", SwingConstants.CENTER);
        timeLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        panel.add(timeLabel);

        //Buttons
        JButton startButton = new JButton("Start");
        JButton stopButton = new JButton("Stop");
        JButton resetButton = new JButton("Reset");
        JPanel buttons = new JPanel();
        buttons.add(startButton);
        buttons.add(stopButton);
        buttons.add(resetButton);
        panel.add(buttons);

        //Make timer logic function
        StopwatchTimer timer = new StopwatchTimer(timeLabel);
        startButton.addActionListener(e -> timer.start());
        stopButton.addActionListener(e -> timer.stop());
        resetButton.addActionListener(e -> timer.reset(timeLabel));

        frame.setVisible(true);
    }
}
