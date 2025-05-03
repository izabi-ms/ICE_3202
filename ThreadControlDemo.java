import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ThreadControlDemo extends JFrame {
    private JButton startBtn, suspendBtn, resumeBtn, stopBtn;
    private JTextArea textArea;
    private JScrollPane scrollPane;

    private volatile boolean suspended = false;
    private volatile boolean running = false;
    private Thread worker;

    public ThreadControlDemo() {
        setTitle("Thread Control Demo");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        startBtn = new JButton("Start");
        suspendBtn = new JButton("Suspend");
        resumeBtn = new JButton("Resume");
        stopBtn = new JButton("Stop");

        suspendBtn.setEnabled(false);
        resumeBtn.setEnabled(false);
        stopBtn.setEnabled(false);

        textArea = new JTextArea(10, 30);
        textArea.setEditable(false);
        scrollPane = new JScrollPane(textArea);

        add(startBtn);
        add(suspendBtn);
        add(resumeBtn);
        add(stopBtn);
        add(scrollPane);

        startBtn.addActionListener(e -> startThread());
        suspendBtn.addActionListener(e -> suspended = true);
        resumeBtn.addActionListener(e -> suspended = false);
        stopBtn.addActionListener(e -> stopThread());

        setVisible(true);
    }

    private void startThread() {
        if (worker != null && worker.isAlive()) return;

        running = true;
        suspended = false;
        worker = new Thread(() -> {
            int count = 1;
            appendText("Thread started...");
            while (running) {
                if (!suspended) {
                    appendText("Count: " + count++);
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
            appendText("Thread stopped.");
        });
        worker.start();

        startBtn.setEnabled(false);
        suspendBtn.setEnabled(true);
        resumeBtn.setEnabled(true);
        stopBtn.setEnabled(true);
    }

    private void stopThread() {
        running = false;
        startBtn.setEnabled(true);
        suspendBtn.setEnabled(false);
        resumeBtn.setEnabled(false);
        stopBtn.setEnabled(false);
    }

    private void appendText(String text) {
        SwingUtilities.invokeLater(() -> textArea.append(text + "\n"));
    }

    public static void main(String[] args) {
        new ThreadControlDemo();
    }
}