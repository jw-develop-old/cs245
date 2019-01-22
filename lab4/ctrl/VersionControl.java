package ctrl;

public class VersionControl {

    public static void main(String[] args) {

        VCWindow window = new RealVCWindow();
        Controller controller = new Controller(window);
        window.getCommitButton().addActionListener(new CommitActionListener(controller));
        window.getPreviousButton().addActionListener(new PreviousActionListener(controller));
        window.getNextButton().addActionListener(new NextActionListener(controller));
        window.getParallelButton().addActionListener(new ParallelActionListener(controller));
        window.getGoToButton().addActionListener(new GoToActionListener(controller));
    }

}