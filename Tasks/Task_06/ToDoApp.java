package ElevateLabs.Tasks.Task_06;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToDoApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("To-Do List");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 500);
            frame.setLayout(new BorderLayout());

            DefaultListModel<String> listModel = new DefaultListModel<>();
            JList<String> taskList = new JList<>(listModel);
            taskList.setFont(new Font("Arial", Font.PLAIN, 16));
            JScrollPane scrollPane = new JScrollPane(taskList);

            JPanel inputPanel = new JPanel(new BorderLayout(5, 5));
            JTextField taskInput = new JTextField();
            taskInput.setFont(new Font("Arial", Font.PLAIN, 16));
            JButton addButton = new JButton("Add Task");
            addButton.setFont(new Font("Arial", Font.BOLD, 14));

            inputPanel.add(taskInput, BorderLayout.CENTER);
            inputPanel.add(addButton, BorderLayout.EAST);
            inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            JButton deleteButton = new JButton("Delete Selected Task");
            deleteButton.setFont(new Font("Arial", Font.BOLD, 14));
            JPanel bottomPanel = new JPanel();
            bottomPanel.add(deleteButton);
            bottomPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));

            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String task = taskInput.getText().trim();
                    if (!task.isEmpty()) {
                        listModel.addElement(task);
                        taskInput.setText("");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Please enter a task.", "Warning",
                                JOptionPane.WARNING_MESSAGE);
                    }
                }
            });

            taskInput.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    addButton.doClick();
                }
            });

            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int selectedIndex = taskList.getSelectedIndex();
                    if (selectedIndex != -1) {
                        listModel.remove(selectedIndex);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Please select a task to delete.", "Warning",
                                JOptionPane.WARNING_MESSAGE);
                    }
                }
            });

            frame.add(inputPanel, BorderLayout.NORTH);
            frame.add(scrollPane, BorderLayout.CENTER);
            frame.add(bottomPanel, BorderLayout.SOUTH);

            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
