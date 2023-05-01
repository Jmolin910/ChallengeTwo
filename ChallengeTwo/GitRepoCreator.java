import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GitRepoCreator {

    private JTextField projectNameField;
    private JTextField projectPathField;
    private JRadioButton publicRadioButton;
    private JRadioButton privateRadioButton;
    private JTextField descriptionField;
    private JButton createRepoButton;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("GitHub Repo Creator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new GitRepoCreator().createContentPane());
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JPanel createContentPane() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5, 5, 5, 5);

        // Logo images
        try {
            // First logo in top-left corner
            ImageIcon logo1 = new ImageIcon("ChallengeTwo/Logos/Microsoft.png");
            JLabel label1 = new JLabel(logo1);
            label1.setPreferredSize(new Dimension(50, 50)); // Set size of image label
            c.gridx = 0;
            c.gridy = 0;
            panel.add(label1, c);
    
            // Second logo in top-right corner
            ImageIcon logo2 = new ImageIcon("ChallengeTwo/Logos/Quinnipiac.png");
            JLabel label2 = new JLabel(logo2);
            label2.setPreferredSize(new Dimension(50, 50)); // Set size of image label
            c.gridx = 2;
            c.gridy = 0;
            c.anchor = GridBagConstraints.EAST; // Align to right of cell
            panel.add(label2, c); 
        }
            catch (Exception e) {
            System.out.println("Logo images not found.");
        }

        // Project name
        c.gridx = 0;
        c.gridy = 1;
        panel.add(new JLabel("Project Name:"), c);

        projectNameField = new JTextField(20);
        c.gridx = 1;
        c.gridy = 1;
        panel.add(projectNameField, c);

        // Project path
        c.gridx = 0;
        c.gridy = 2;
        panel.add(new JLabel("Project Path:"), c);

        projectPathField = new JTextField(20);
        c.gridx = 1;
        c.gridy = 2;
        panel.add(projectPathField, c);

        // Visibility
        c.gridx = 0;
        c.gridy = 3;
        panel.add(new JLabel("Visibility:"), c);

        ButtonGroup visibilityGroup = new ButtonGroup();
        publicRadioButton = new JRadioButton("Public");
        privateRadioButton = new JRadioButton("Private");
        visibilityGroup.add(publicRadioButton);
        visibilityGroup.add(privateRadioButton);
        publicRadioButton.setSelected(true);

        JPanel visibilityPanel = new JPanel();
        visibilityPanel.add(publicRadioButton);
        visibilityPanel.add(privateRadioButton);
        c.gridx = 1;
        c.gridy = 3;
        panel.add(visibilityPanel, c);

        // Description
        c.gridx = 0;
        c.gridy = 4;
        panel.add(new JLabel("Description:"), c);

        descriptionField = new JTextField(20);
        c.gridx = 1;
        c.gridy = 4;
        panel.add(descriptionField, c);

        // Create Repo Button
        createRepoButton = new JButton("Create Repo");
        createRepoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform tasks to create and push the Git repo
                // This is where you would call your functions to create the Git repo, create a GitHub repo, and push the changes
            }
        });
        c.gridx = 1;
        c.gridy = 5;
        panel.add(createRepoButton, c);
    
        return panel;
    }
}
    
