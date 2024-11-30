import javax.swing.*;  

public class SimpleStopwatch {
    private static int elapsedTime = 0;

    public static void main(String[] args) {
        JFrame frame =new JFrame("Stopwatch");
        int width = 400;
        int height = 200;
        frame.setSize(width,height);

        JLabel currentTime = new JLabel("00:00:000",SwingConstants.CENTER); 
        
        currentTime.setBounds(0, 0, width, height/2);
        frame.add(currentTime);

        JButton startButton = new JButton("Start");
        JButton stopButton = new JButton("Stop");
        JButton resetButton = new JButton("Reset");

        JPanel buttons = new JPanel();
        buttons.add(startButton);
        buttons.add(stopButton);
        buttons.add(resetButton);
        frame.add(buttons);


        Timer timer = new Timer(1, e -> {
            elapsedTime++;
            int minutes = elapsedTime / 60000;
            int seconds = (elapsedTime / 1000) % 60;
            int milliseconds = elapsedTime % 1000;
            currentTime.setText(String.format("%02d:%02d:%03d", minutes, seconds, milliseconds));
        });
        startButton.addActionListener(e -> timer.start());
        stopButton.addActionListener(e -> timer.stop());
        resetButton.addActionListener(e -> {
            timer.stop();
            elapsedTime = 0;
            currentTime.setText("00:00:000");
        });

        frame.setVisible(true);

    }
}
