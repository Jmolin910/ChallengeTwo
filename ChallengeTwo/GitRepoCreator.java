import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import git.tools.client.GitSubprocessClient;
import github.tools.client.GitHubApiClient;
import github.tools.client.RequestParams;
import github.tools.responseObjects.*;

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
        
            // First logo in top-left corner
            ImageIcon logo1 = new ImageIcon("Microsoft.png");
            JLabel label1 = new JLabel(logo1);
            label1.setPreferredSize(new Dimension(50, 50)); // Set size of image label
            c.gridx = 0;
            c.gridy = 0;
            panel.add(label1, c);
    
            // Second logo in top-right corner
            ImageIcon logo2 = new ImageIcon("Quinnipiac.png");
            JLabel label2 = new JLabel(logo2);
            label2.setPreferredSize(new Dimension(50, 50)); // Set size of image label
            c.gridx = 2;
            c.gridy = 0;
            c.anchor = GridBagConstraints.EAST; // Align to right of cell
            panel.add(label2, c);
    
            // Reset anchor for remaining components
            c.anchor = GridBagConstraints.WEST;
    
            // GitHub Username
            c.gridx = 0;
            c.gridy = 1;
            panel.add(new JLabel("GitHub Username:"), c);
    
            JTextField usernameField = new JTextField(20);
            c.gridx = 1;
            c.gridy = 1;
            panel.add(usernameField, c);
    
            // GitHub Token
            c.gridx = 0;
            c.gridy = 2;
            panel.add(new JLabel("GitHub Token:"), c);
    
            JTextField tokenField = new JTextField(20);
            c.gridx = 1;
            c.gridy = 2;
            panel.add(tokenField, c);
    
            // Project name
            c.gridx = 0;
            c.gridy = 3;
            panel.add(new JLabel("Project Name:"), c);
    
            projectNameField = new JTextField(20);
            c.gridx = 1;
            c.gridy = 3;
            panel.add(projectNameField, c);
    
            // Project path
            c.gridx = 0;
            c.gridy = 4;
            panel.add(new JLabel("Project Path:"), c);
    
            projectPathField = new JTextField(20);
            c.gridx = 1;
            c.gridy = 4;
            panel.add(projectPathField, c);
    
            // Visibility
            c.gridx = 0;
            c.gridy = 5;
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
            c.gridy = 5;
            panel.add(visibilityPanel, c);
    
            // Description
            c.gridx = 0;
            c.gridy = 6;
            panel.add(new JLabel("Description:"), c);
    
            descriptionField = new JTextField(20);
            c.gridx = 1;
            c.gridy = 6;
            panel.add(descriptionField, c);
    
            // Create Repo Button
            createRepoButton = new JButton("Create Repo");
            createRepoButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String username = usernameField.getText();
                    String token = tokenField.getText();
                    String projectName = projectNameField.getText();
                    String projectPath = projectPathField.getText();
                    boolean isPublic = publicRadioButton.isSelected();
                    String description = descriptionField.getText();
                    // Perform tasks to create and push the Git repo
                    // This is where you would call your functions to create the Git repo, create a GitHub repo, and push the changes
                    System.out.println("GitHub Username: " + username);
                    System.out.println("GitHub Token: " + token);
                    System.out.println("Project Name: " + projectName);
                    System.out.println("Project Path: " + projectPath);
                    System.out.println("Visibility: " + (isPublic ? "Public" : "Private"));
                    System.out.println("Description: " + description);

                    GitHubApiClient gitHubApiClient = new GitHubApiClient(username, token);
                    GitSubprocessClient gitSubprocessClient = new GitSubprocessClient(projectPath);
                    gitSubprocessClient.gitInit();
                    RequestParams requestParams = new RequestParams();
                    requestParams.addParam("name", projectName); // name of repo
                    requestParams.addParam("description", description);
                    requestParams.addParam("private", !isPublic); // if repo is private or not
                    CreateRepoResponse createRepo = gitHubApiClient.createRepo(requestParams);
                    String repoLink = createRepo.getUrl();
                    gitSubprocessClient.gitRemoteAdd("origin", repoLink);
                    gitSubprocessClient.gitAddAll();
                    String commitMessage = "Initial commit";
                    gitSubprocessClient.gitCommit(commitMessage);
                    gitSubprocessClient.gitPush("main");
                    gitSubprocessClient.gitPull("main");

                }
            });
            c.gridx = 1;
            c.gridy = 7; // Increase grid y-index to make space for new components
            panel.add(createRepoButton, c);
    
        return panel;
    }
}


    
